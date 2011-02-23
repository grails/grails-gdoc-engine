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


package org.radeox.api.engine.context;

import org.radeox.filter.FilterPipe;


/**
 * IntialRenderContext tells a Filter how to behave at initializiation.
 * For example the Filter can read pattern locales for it's pattern
 * matching.
 *
 * @author Stephan J. Schmidt
 * @version $Id: InitialRenderContext.java,v 1.1 2003/10/07 08:20:24 stephan Exp $
 */

public interface InitialRenderContext extends RenderContext {
  public final static String FILTER_PIPE = "InitialRenderContext.filter_pipe";

  public void setFilterPipe(FilterPipe fp);
  public FilterPipe getFilterPipe();
}
