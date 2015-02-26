/*******************************************************************************
 * Copyright (c) 2013, 2014 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.tooling.ast;

import static melnorme.utilbox.core.Assert.AssertNamespace.assertFail;
import static melnorme.utilbox.core.Assert.AssertNamespace.assertNotNull;
import static melnorme.utilbox.core.Assert.AssertNamespace.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import melnorme.lang.tooling.ast.util.ASTDirectChildrenVisitor;
import melnorme.lang.tooling.ast_actual.ASTNode;

public abstract class NodeData {
	
	public abstract boolean isParsedStatus();
	
	public abstract Collection<ParserError> getNodeErrors();
	
	public boolean hasErrors() {
		return getNodeErrors().size() > 0;
	}
	
	public abstract boolean isSemanticReadyStatus();
	
	public abstract void setSemanticReady(CommonLanguageElement node);
	
	public static CreatedStatusNodeData CREATED_STATUS = new CreatedStatusNodeData() {
		
		@Override
		public String toString() {
			return "(CREATED)";
		}
		
	};
	
	
	public static class CreatedStatusNodeData extends NodeData {
		
		@Override
		public boolean isParsedStatus() {
			return false;
		}
		
		@Override
		public boolean isSemanticReadyStatus() {
			return false;
		};
		
		@Override
		public Collection<ParserError> getNodeErrors() {
			throw assertFail();
		}
		
		public void setParsed(ASTNode node) {
			setParsedWithErrors(node, (ParserError[]) null);
		}
		
		public void setParsedWithErrors(final ASTNode node, ParserError... errors) {
			// Ensure children are also in parsed status
			node.visitDirectChildren(new ASTDirectChildrenVisitor() {
				@Override
				protected void geneticChildrenVisit(ASTNode child) {
					assertTrue(child.getLexicalParent() == node);
					assertTrue(child.isParsedStatus());
				}
			});
			
			if(errors == null) {
				node.setData(NodeData.DEFAULT_PARSED_STATUS);
			} else {
				node.setData(new ParsedNodeDataWithErrors(errors));
			}
			assertTrue(node.hasSourceRangeInfo());
		}
		
		@Override
		public void setSemanticReady(CommonLanguageElement node) {
			node.setData(DEFAULT_LOCALLY_ANALYZED_STATUS);
		}
		
	}
	
	public static NodeData DEFAULT_PARSED_STATUS = new ParsedNodeData();
	
	public static class ParsedNodeData extends NodeData {
		
		protected static final Collection<ParserError> NO_ERRORS = Collections.emptyList(); 
		
		@Override
		public boolean isParsedStatus() {
			return true;
		}
		
		@Override
		public boolean isSemanticReadyStatus() {
			return false;
		}
		
		@Override
		public Collection<ParserError> getNodeErrors() {
			return NO_ERRORS;
		};
		
		@Override
		public String toString() {
			return "(PARSED)";
		}
		
		@Override
		public void setSemanticReady(CommonLanguageElement node) {
			if(this == DEFAULT_PARSED_STATUS) {
				// Reuse instance to avoid unnecessary allocations
				node.setData(DEFAULT_LOCALLY_ANALYZED_STATUS);
			} else {
				node.setData(new AnalysedNodeData(this));
			}
		}
		
	}
	
	public static class ParsedNodeDataWithErrors extends ParsedNodeData {
		
		protected Collection<ParserError> errors;
		
		public ParsedNodeDataWithErrors(ParserError... errors) {
			for (ParserError parserError : errors) {
				assertNotNull(parserError);
			}
			this.errors = Arrays.asList(errors); 
		}
		
		@Override
		public Collection<ParserError> getNodeErrors() {
			return errors;
		};
		
	}
	
	public static NodeData DEFAULT_LOCALLY_ANALYZED_STATUS = new AnalysedNodeData(DEFAULT_PARSED_STATUS);
	
	public static class AnalysedNodeData extends NodeData {
		
		protected NodeData parsedStatus;
		
		public AnalysedNodeData(NodeData parsedStatus) {
			this.parsedStatus = assertNotNull(parsedStatus);
		}
		
		@Override
		public boolean isParsedStatus() {
			return false;
		}
		
		@Override
		public boolean isSemanticReadyStatus() {
			return true;
		}
		
		@Override
		public Collection<ParserError> getNodeErrors() {
			return parsedStatus.getNodeErrors();
		}
		
		@Override
		public String toString() {
			return "(ANALYSED)";
		}
		
		@Override
		public void setSemanticReady(CommonLanguageElement node) {
			// Do nothing
			assertTrue(node.getData() == this);
		}
		
	}
	
	/* ----------------- util ----------------- */
	
	public static final class CompleteNodeVisitor extends ASTVisitor {
		
		public static final CompleteNodeVisitor instance = new CompleteNodeVisitor();
		
		@Override
		public boolean preVisit(ASTNode node) {
			return true;
		}
		
		@Override
		public void postVisit(ASTNode node) {
			node.setSemanticReady_afterChildren();
		}
		
	}
	
}