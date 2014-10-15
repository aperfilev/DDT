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
package mmrnmhrm.core.build;

import melnorme.lang.ide.core.tests.CommonCoreTest;
import mmrnmhrm.tests.SampleProject;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DubProjectBuilderTest extends CommonCoreTest {
	
	protected static SampleProject sampleProj;
	
	@BeforeClass
	public static void init() throws CoreException {
		sampleProj = new SampleProject(DubProjectBuilderTest.class.getSimpleName());
	}
	
	@AfterClass
	public static void cleanup() throws CoreException {
		if(sampleProj != null) sampleProj.cleanUp();
	}
	
	@Test
	public void testBuilder() throws Exception { testBuilder$(); }
	public void testBuilder$() throws Exception {
		 sampleProj.getProject().build(IncrementalProjectBuilder.FULL_BUILD, null);
		 sampleProj.getProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, null);
		 sampleProj.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, null);
		 sampleProj.getProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, null);
	}
	
}