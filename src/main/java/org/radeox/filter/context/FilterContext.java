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


package org.radeox.filter.context;

import org.radeox.api.engine.context.RenderContext;
import org.radeox.macro.parameter.MacroParameter;

/**
 * FilterContext stores basic data for the context
 * filters are called in. FilterContext is used to allow
 * filters be called in different enviroments, inside
 * SnipSnap or outside. Special enviroments should
 * implement FilterContext
 *
 * @author Stephan J. Schmidt
 * @version $Id: FilterContext.java,v 1.7 2003/10/07 08:20:24 stephan Exp $
 */

public interface FilterContext {
  public MacroParameter getMacroParameter();
  public void setRenderContext(RenderContext context);
  public RenderContext getRenderContext();
}
