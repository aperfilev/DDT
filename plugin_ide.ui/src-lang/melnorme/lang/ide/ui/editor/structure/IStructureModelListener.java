/*******************************************************************************
 * Copyright (c) 2015, 2015 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.ide.ui.editor.structure;

import melnorme.lang.tooling.structure.SourceFileStructure;
import melnorme.utilbox.misc.Location;

public interface IStructureModelListener {
	
	/** 
	 * Indicates that the source file structure of the file at given location has changed.
	 * 
	 * This method runs under the scope of a Structure Model lock, so listeners should respond quickly.
	 */
	void structureChanged(Location location, SourceFileStructure sourceFileStructure, Object structureModelLock);
	
}