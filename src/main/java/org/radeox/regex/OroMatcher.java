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


package org.radeox.regex;

import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Util;
import org.apache.oro.text.regex.Perl5Substitution;

/*
 * Matcher matches regular expressions (Pattern) to input
 * Implementation for Jakarta ORO package
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: OroMatcher.java,v 1.2 2004/04/20 13:16:41 stephan Exp $
 */

public class OroMatcher extends Matcher {
  private OroPattern pattern;
  private String input;
  private org.apache.oro.text.regex.Perl5Matcher internalMatcher;

  public OroMatcher(String input, Pattern pattern) {
    this.input = input;
    this.pattern = (OroPattern) pattern;
    internalMatcher = new Perl5Matcher();
 }

  public String substitute(Substitution substitution) {
    return Util.substitute(internalMatcher,
        pattern.getPattern(),
        new OroActionSubstitution(substitution),
        input,
        Util.SUBSTITUTE_ALL);
  }

  public String substitute(String substitution) {
   return  Util.substitute(internalMatcher,
       pattern.getPattern(),
       new Perl5Substitution(substitution, Perl5Substitution.INTERPOLATE_ALL),
       input,
       Util.SUBSTITUTE_ALL);
  }

  protected Perl5Matcher getMatcher() {
    return internalMatcher;
  }


  public boolean contains() {
    return internalMatcher.contains(input, pattern.getPattern());
  }

  public boolean matches() {
    return internalMatcher.matches(input, pattern.getPattern());
  }
}