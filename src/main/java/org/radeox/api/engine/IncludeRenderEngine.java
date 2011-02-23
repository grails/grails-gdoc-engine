/*
 *      Copyright 2001-2004 Fraunhofer Gesellschaft, Munich, Germany, for its 
 *      Fraunhofer Institute Computer Architecture and Software Technology
 *      (FIRST), Berlin, Germany
 *      
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package org.radeox.api.engine;

/**
 * Interface for RenderEngines that allow to include content like wiki pages
 * or snips, e.g. with {!includeWiki} in MacroFilter
 *
 * @author Stephan J. Schmidt
 * @version $Id: IncludeRenderEngine.java,v 1.1 2003/10/07 08:20:24 stephan Exp $
 */

public interface IncludeRenderEngine {
    /**
   * Include an object in the input. This could be a
   * wiki page, snips, comments.
   *
   * @param name Name of the object to include, e.g. wiki page name
   * @return result A string representation of the included object
   */
  public String include(String name);
}
