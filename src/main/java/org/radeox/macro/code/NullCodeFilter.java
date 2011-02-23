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


package org.radeox.macro.code;

import org.radeox.filter.context.FilterContext;

/*
 * Dummy filter that does nothing
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: NullCodeFilter.java,v 1.4 2003/08/29 12:32:12 stephan Exp $
 */

public class NullCodeFilter implements SourceCodeFormatter {

  public NullCodeFilter() {
  }

  public String filter(String content, FilterContext context) {
    return content;
  }

  public String getName() {
    return "none";
  }

  public int getPriority() {
    return 0;
  }
}