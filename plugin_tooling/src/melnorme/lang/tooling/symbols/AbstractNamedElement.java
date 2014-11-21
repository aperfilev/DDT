/*******************************************************************************
 * Copyright (c) 2014, 2014 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.tooling.symbols;

import static melnorme.utilbox.core.Assert.AssertNamespace.assertNotNull;
import melnorme.lang.tooling.bundles.ISemanticContext;
import melnorme.lang.tooling.bundles.ModuleFullName;
import melnorme.lang.tooling.engine.AbstractElement;
import melnorme.lang.tooling.engine.INamedElementSemantics;
import dtool.resolver.CommonDefUnitSearch;

public abstract class AbstractNamedElement extends AbstractElement implements INamedElement {
	
	protected final String name;
	
	public AbstractNamedElement(String name) {
		this.name = assertNotNull(name);
	}
	
	@Override
	public final String getName() {
		return name;
	}
	
	@Override
	public String getExtendedName() {
		return name;
	}
	
	@Override
	public String getNameInRegularNamespace() {
		return getName();
	}
	
	@Override
	public ModuleFullName getModuleFullName() {
		return ModuleFullName.fromString(getModuleFullyQualifiedName());
	}
	
	@Override
	public abstract INamedElementSemantics getSemantics();
	
	@Override
	public final IConcreteNamedElement resolveConcreteElement(ISemanticContext context) {
		return getSemantics().resolveConcreteElement(context).result;
	}
	
	@Override
	public final void resolveSearchInMembersScope(CommonDefUnitSearch search) {
		getSemantics().resolveSearchInMembersScope(search);
	}
	
	@Override
	public final INamedElement resolveTypeForValueContext(ISemanticContext mr) {
		return getSemantics().resolveTypeForValueContext(mr);
	}
	
}