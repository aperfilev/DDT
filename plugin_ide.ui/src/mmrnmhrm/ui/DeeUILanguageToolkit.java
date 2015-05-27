/*******************************************************************************
 * Copyright (c) 2008, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package mmrnmhrm.ui;

import static melnorme.utilbox.core.Assert.AssertNamespace.assertFail;
import melnorme.lang.ide.ui.EditorSettings_Actual;
import melnorme.lang.ide.ui.LangUIPlugin;
import mmrnmhrm.core.text.DeePartitions;
import mmrnmhrm.ui.editor.DeeSimpleSourceViewerConfiguration;
import mmrnmhrm.ui.preferences.pages.DeeContentAssistPreferencePage;
import mmrnmhrm.ui.preferences.pages.DeeEditorPreferencePage;
import mmrnmhrm.ui.preferences.pages.DeeEditorTypingPreferencePage;
import mmrnmhrm.ui.preferences.pages.DeeFoldingPreferencePage;
import mmrnmhrm.ui.preferences.pages.DeeSourceColoringPreferencePage;
import mmrnmhrm.ui.preferences.pages.DeeTemplatePreferencePage;

import org.dsource.ddt.ide.core.DeeLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.templates.ITemplateAccess;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.preference.IPreferenceStore;

public class DeeUILanguageToolkit extends AbstractDLTKUILanguageToolkit implements IDLTKUILanguageToolkit {
	
	private static final DeeUILanguageToolkit instance = new DeeUILanguageToolkit();
	private static final DeeScriptElementLabels elementLabels = new DeeScriptElementLabels(); 
	
	
	public static DeeUILanguageToolkit getDefault() {
		return instance ;
	}
	
	@Override
	public IDLTKLanguageToolkit getCoreToolkit() {
		return DeeLanguageToolkit.getDefault();
	}
	
	@Override
	public IPreferenceStore getPreferenceStore() {
		return DeeUIPlugin.getInstance().getPreferenceStore();
	}
	
	@Override
	public String getEditorId(Object inputElement) {
		return EditorSettings_Actual.EDITOR_ID;
	}
	
	@Override
	public String getPartitioningId() {
		return DeePartitions.PARTITIONING_ID;
	}
	
	@Override
	public ScriptTextTools getTextTools() {
		return DeeUIPlugin.getDefault().getTextTools();
	}
	
	@Override
	public ITemplateAccess getEditorTemplates() {
		throw assertFail();
	}
	
	@Deprecated
	@Override
	public org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
		throw assertFail();
	}
	
	public DeeSimpleSourceViewerConfiguration createSourceViewerConfiguration2() {
		return new DeeSimpleSourceViewerConfiguration(LangUIPlugin.getInstance().getColorManager(),
				getPreferenceStore(), false);
	}
	
	@Override
	public ScriptUILabelProvider createScriptUILabelProvider() {
		return null;
	}
	
	@Override
	public DeeScriptElementLabels getScriptElementLabels() {
		return elementLabels; 
	}
	
	@Override
	public String[] getEditorPreferencePages() {
		return new String[]{ 
				DeeEditorPreferencePage.PAGE_ID, 
				DeeContentAssistPreferencePage.PAGE_ID,
				DeeEditorTypingPreferencePage.PAGE_ID,
				DeeFoldingPreferencePage.PAGE_ID,
				DeeTemplatePreferencePage.PAGE_ID,
				DeeSourceColoringPreferencePage.PAGE_ID};
	}
	
	@Override
	public String getDebugPreferencePage() {
		// TODO DLTK getDebugPreferencePage
		return null;
	}
	
	
	@Override
	public String getInterpreterContainerId() {
		return "mmrnmrhm.core.launching.INTERPRETER_CONTAINER";
	}
	
	@Override
	public boolean getProvideMembers(ISourceModule element) {
		return true;
	}
	
}