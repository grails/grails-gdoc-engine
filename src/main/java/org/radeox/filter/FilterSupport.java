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


package org.radeox.filter;

import org.radeox.api.engine.context.InitialRenderContext;

/*
 * Abstract Filter Class that supplies the
 * Filter interface. Concrete Filters should
 * inherit from Filter. Filters transform a
 * String (usually snip content) to another String
 * (usually HTML).
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: FilterSupport.java,v 1.9 2003/10/07 08:20:24 stephan Exp $
 */

public abstract class FilterSupport implements Filter {
  protected InitialRenderContext initialContext;

  public FilterSupport() {
  }

  public String[] replaces() {
    return FilterPipe.NO_REPLACES;
  }

  public String[] before() {
    return FilterPipe.EMPTY_BEFORE;
  }

  public void setInitialContext(InitialRenderContext context) {
     this.initialContext = context;
  }

  public String getDescription() {
    return "";
  }
}