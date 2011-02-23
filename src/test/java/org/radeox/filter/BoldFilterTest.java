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

import junit.framework.Test;
import junit.framework.TestSuite;

public class BoldFilterTest extends FilterTestSupport {
  private static final String RESULT_BOLD_1 = "<b class=\"bold\">Text</b>";
  private static final String RESULT_NOSPACE_UNDERLINE = "Test__Text__Test";
  private static final String RESULT_NOSPACE_STAR = "Test**Text**Test";
  private static final String RESULT_PUNCT = "<b class=\"bold\">Text</b>:";

  protected void setUp() throws Exception {
    filter = new BoldFilter();
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(BoldFilterTest.class);
  }

  public void testBold() {
    assertEquals(RESULT_BOLD_1, filter.filter("__Text__", context));
  }

  public void testBoldCreole() {
    assertEquals(RESULT_BOLD_1, filterCreole.filter("**Text**", context));
  }

  public void testBoldMustStartAndEndWithSpace() {
    assertEquals(RESULT_NOSPACE_UNDERLINE, filter.filter(RESULT_NOSPACE_UNDERLINE, context));
  }

  public void testBoldMustStartAndEndWithSpaceCreole() {
    assertEquals(RESULT_NOSPACE_STAR, filterCreole.filter(RESULT_NOSPACE_STAR, context));
  }

  public void testBoldWithPunctuation() {
    assertEquals(RESULT_PUNCT, filter.filter("__Text__:", context));
  }

  public void testBoldWithPunctuationCreole() {
    assertEquals(RESULT_PUNCT, filterCreole.filter("**Text**:", context));
  }
}
