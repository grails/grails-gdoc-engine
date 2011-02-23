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

/*
 * Default code formatter
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: DefaultRegexCodeFormatter.java,v 1.1 2003/08/29 12:32:55 stephan Exp $
 */

import org.radeox.filter.regex.RegexReplaceFilter;

public class DefaultRegexCodeFormatter extends RegexReplaceFilter {
  public DefaultRegexCodeFormatter(String regex, String substitute) {
    super(regex, substitute);
  }

  public int getPriority() {
    return 0;
  }
}
