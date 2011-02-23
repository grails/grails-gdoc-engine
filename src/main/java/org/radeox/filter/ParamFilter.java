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

import org.radeox.filter.context.FilterContext;
import org.radeox.filter.regex.LocaleRegexTokenFilter;
import org.radeox.regex.MatchResult;

import java.util.Map;

/*
 * ParamFilter replaces parametes from from the MacroFilter in the input.
 * These parameters could be read from an HTTP request and put in
 * MacroFilter.
 * A parameter is replaced in {$paramName}
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: ParamFilter.java,v 1.7 2004/04/15 13:56:14 stephan Exp $
 */

public class ParamFilter extends LocaleRegexTokenFilter {
  public void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {
    Map param = context.getRenderContext().getParameters();

    String name = result.group(1);
    if (param.containsKey(name)) {
      Object value = param.get(name);
      if (value instanceof String[]) {
        buffer.append(((String[]) value)[0]);
      } else {
        buffer.append(value);
      }
    } else {
      buffer.append("<");
      buffer.append(name);
      buffer.append(">");
    }
  }

  protected String getLocaleKey() {
    return "filter.param";
  }

  protected boolean isSingleLine() {
    return true;
  }
}