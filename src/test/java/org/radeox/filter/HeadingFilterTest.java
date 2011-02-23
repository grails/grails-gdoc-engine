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

public class HeadingFilterTest extends FilterTestSupport {
  private static final String RESULT_HEADING_1 = "<h3 class=\"heading-1\">Test</h3>";
  private static final String RESULT_SUB_HEADINGS = "<h3 class=\"heading-1\">Test</h3>\n" +
          "<h3 class=\"heading-1-1\">Test</h3>\n" +
          "<h3 class=\"heading-1-1-1\">Test</h3>\n" +
          RESULT_HEADING_1;

  protected void setUp() throws Exception {
    filter = new HeadingFilter();
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(HeadingFilterTest.class);
  }

  public void testHeading() {
    assertEquals(RESULT_HEADING_1, filter.filter("1 Test", context));
  }

  public void testHeadingCreole() {
    assertEquals(RESULT_HEADING_1, filterCreole.filter("= Test", context));
  }

  public void testHeadingCreoleWithoutSpace() {
    assertEquals(RESULT_HEADING_1, filterCreole.filter("=Test", context));
  }

  public void testSubHeadings() {
    assertEquals(RESULT_SUB_HEADINGS, filter.filter("1 Test\n1.1 Test\n1.1.1 Test\n1 Test", context));
  }

  public void testSubHeadingsCreole() {
    assertEquals(RESULT_SUB_HEADINGS, filterCreole.filter("= Test\n== Test\n=== Test\n= Test", context));
  }

  public void testSubHeadingsCreoleWithoutSpace() {
    assertEquals(RESULT_SUB_HEADINGS, filterCreole.filter("=Test\n==Test\n===Test\n=Test", context));
  }
}
