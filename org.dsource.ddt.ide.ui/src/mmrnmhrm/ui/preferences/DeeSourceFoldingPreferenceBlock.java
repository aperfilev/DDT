package mmrnmhrm.ui.preferences;

import java.util.List;

import mmrnmhrm.ui.editor.folding.DeeFoldingPreferenceConstants;

import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.dltk.ui.text.folding.SourceCodeFoldingPreferenceBlock;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Group;

public class DeeSourceFoldingPreferenceBlock extends SourceCodeFoldingPreferenceBlock {
	
	public DeeSourceFoldingPreferenceBlock(OverlayPreferenceStore store, PreferencePage page) {
		super(store, page);
	}
	
	@Override
	protected void addOverlayKeys(List<OverlayKey> keys) {
		super.addOverlayKeys(keys);
		keys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				DeeFoldingPreferenceConstants.EDITOR_FOLDING_INIT_UNITTESTS));
		keys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				DeeFoldingPreferenceConstants.EDITOR_FOLDING_INIT_CONDITIONALS));
	}
	
	@Override
	protected boolean supportsClassFolding() {
		return true;
	}
	
	@Override
	protected String getInitiallyFoldClassesText() {
		return DeePreferencesMessages.Folding_initFoldAggregates;
	}
	
	@Override
	protected String getInitiallyFoldMethodsText() {
		return DeePreferencesMessages.Folding_initFoldFunctions;
	}
	
	@Override
	protected void addInitiallyFoldOptions(Group group) {
		super.addInitiallyFoldOptions(group);
		createCheckBox(group,
				DeePreferencesMessages.Folding_initUnitTests,
				DeeFoldingPreferenceConstants.EDITOR_FOLDING_INIT_UNITTESTS);
		createCheckBox(group,
				DeePreferencesMessages.Folding_initConditionals,
				DeeFoldingPreferenceConstants.EDITOR_FOLDING_INIT_CONDITIONALS);
	}
	
}
