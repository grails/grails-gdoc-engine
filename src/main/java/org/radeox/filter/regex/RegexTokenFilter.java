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
 *
 *
 *  Additional changes to original Radeox code by:
 *
 *    Peter Ledbrook, SpringSource
 */


package org.radeox.filter.regex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.radeox.filter.context.FilterContext;
import org.radeox.regex.Pattern;
import org.radeox.regex.Matcher;
import org.radeox.regex.MatchResult;
import org.radeox.regex.Substitution;

/*
 * Filter that calls a special handler method handleMatch() for
 * every occurance of a regular expression.
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: RegexTokenFilter.java,v 1.11 2004/04/16 07:47:41 stephan Exp $
 */

public abstract class RegexTokenFilter extends RegexFilter {

  private static Log log = LogFactory.getLog(RegexTokenFilter.class);

  public RegexTokenFilter() {
    super();
  }

  /**
   * create a new regular expression and set
   */
  public RegexTokenFilter(String regex, boolean multiline) {
    super(regex, "", multiline);
  }

  /**
   * create a new regular expression and set
   */
  public RegexTokenFilter(String regex) {
    super(regex, "");
  }

  protected void setUp(FilterContext context) {
  }

  /**
   * Method is called for every occurance of a regular expression.
   * Subclasses have to implement this mehtod.
   *
   * @param buffer Buffer to write replacement string to
   * @param result Hit with the found regualr expression
   * @param context FilterContext for filters
   */
  public abstract void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context);

  public String filter(String input, final FilterContext context) {
    setUp(context);

    String result = null;
    int size = pattern.size();
    for (int i = 0; i < size; i++) {
      Pattern p = (Pattern) pattern.get(i);
      try {
        Matcher m = Matcher.create(input, p);
        result = m.substitute(new Substitution() {
          public void handleMatch(StringBuffer buffer, MatchResult result) {
            RegexTokenFilter.this.handleMatch(buffer, result, context);
          }
        });

        // result = Util.substitute(matcher, p, new ActionSubstitution(s, this, context), result, limit);
      } catch (Exception e) {
        log.warn("<span class=\"error\">Exception</span>: " + this, e);
        log.debug("Input that caused the exception:\n" + input);
      } catch (Error err) {
        log.warn("<span class=\"error\">Error</span>: " + this + ": " + err);
        err.printStackTrace();
      }
      input = result;
    }
    return input;
  }
}
