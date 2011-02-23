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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OroMatcherTest extends TestCase {

  public static Test suite() {
    return new TestSuite(OroMatcherTest.class);
  }

  public void testSubstituteWithoutVariables() {
    OroCompiler compiler = new OroCompiler();
    Pattern pattern = compiler.compile("A");
    OroMatcher matcher = new OroMatcher("A", pattern);
    String substituted = matcher.substitute("B");
    assertEquals("Correct substitution without variables.", "B", substituted);
  }

  public void testSubstituteWithVariables() {
    OroCompiler compiler = new OroCompiler();
    Pattern pattern = compiler.compile("(A)");
    OroMatcher matcher = new OroMatcher("BAB", pattern);
    String substituted = matcher.substitute("C$1C");
    assertEquals("Correct substitution with variables.", "BCACB", substituted);
  }

  public void testSubstitutionWithSubstitution() {
    OroCompiler compiler = new OroCompiler();
    Pattern pattern = compiler.compile("(A)");
    OroMatcher matcher = new OroMatcher("BABAB", pattern);
    String substituted = matcher.substitute(new Substitution() {
      public void handleMatch(StringBuffer buffer, MatchResult result) {
        String match = result.group(1);
        buffer.append(match.toLowerCase());
      }
    });
    assertEquals("Correct substitution with substitution.", "BaBaB", substituted);
  }
}
