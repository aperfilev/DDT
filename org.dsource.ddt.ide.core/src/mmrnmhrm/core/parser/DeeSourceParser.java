package mmrnmhrm.core.parser;

import mmrnmhrm.core.LangCore;

import org.dsource.ddt.ide.core.model.DeeModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.IModelElement;

import descent.core.compiler.IProblem;
import descent.internal.compiler.parser.Module;
import dtool.DeeNamingRules;
import dtool.descentadapter.DescentASTConverter;
import dtool.refmodel.ParserAdapter;

public class DeeSourceParser extends AbstractSourceParser {
	
	private static final class DescentProblemAdapter implements
	descent.internal.compiler.parser.ast.IProblemReporter {
		
		private IProblemReporter reporter;
		
		public DescentProblemAdapter(IProblemReporter reporter) {
			this.reporter = reporter;
		}
		
		@Override
		public void reportProblem(IProblem problem) {
			reporter.reportProblem(DLTKDescentProblemWrapper.createProblemWrapper(problem));
		}
		
		public static descent.internal.compiler.parser.ast.IProblemReporter create(IProblemReporter reporter) {
			if(reporter == null)
				return null;
			return new DescentProblemAdapter(reporter);
		}
	}
	
	@Override
	public DeeModuleDeclaration parse(IModuleSource input, IProblemReporter reporter) {
		int langVersion = 2; // TODO we should use value from project configured interpreter version
		
		String source = input.getSourceContents();
		Module dmdModule = null;
		try {
			dmdModule = ParserAdapter.parseSource(source, langVersion, DescentProblemAdapter.create(reporter)).mod;
		} catch (RuntimeException e) {
			LangCore.log(e);
			throw e;
		}
		
		DeeModuleDeclaration deeModuleDecl = new DeeModuleDeclaration(dmdModule);
		boolean adaptMalformedAST = true;
		if(dmdModule.hasSyntaxErrors() && !adaptMalformedAST) {
			// DontLet's try to convert a malformed AST
			return deeModuleDecl;
		}
		String moduleName = "_unnamedSource_";
		IModelElement modelElement = input.getModelElement();
		if(modelElement != null) {
			moduleName = DeeNamingRules.getModuleNameFromFileName(modelElement.getElementName());
		}
		dtool.ast.definitions.Module neoModule = DescentASTConverter.convertModule(dmdModule, moduleName);
		deeModuleDecl.setNeoModule(neoModule);
		//setModuleDeclModuleUnit(fileName, deeModuleDecl);
		return deeModuleDecl;
		
	}
	
}