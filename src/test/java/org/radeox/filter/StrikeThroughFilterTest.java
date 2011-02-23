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
import org.radeox.filter.StrikeThroughFilter;

public class StrikeThroughFilterTest extends FilterTestSupport {

  protected void setUp() throws Exception {
    filter = new StrikeThroughFilter();
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(StrikeThroughFilterTest.class);
  }

  public void testStrikeThroughDash() {
    assertEquals("Test<strike class=\"strike\">Test-Text</strike>", filter.filter("Test--Test-Text--", context));
  }

  public void testStrikeThroughDoubleDash() {
    assertEquals("Test<strike class=\"strike\">Test</strike>Text--", filter.filter("Test--Test--Text--", context));
  }

  public void testStartStrikeThrough() {
    assertEquals("Test<strike class=\"strike\">Text</strike>", filter.filter("Test--Text--", context));
  }

  public void testEndStrikeThrough() {
    assertEquals("<strike class=\"strike\">Text</strike>Test", filter.filter("--Text--Test", context));
  }

  public void testStrikeThrough() {
    assertEquals("Test<strike class=\"strike\">Text</strike>Test", filter.filter("Test--Text--Test", context));
  }

  public void testFourDashes() {
    assertEquals("----", filter.filter("----",context));
  }

  public void testFiveDashes() {
    assertEquals("-----", filter.filter("-----", context));
  }

  public void testHtmlComment() {
    assertEquals("<!-- comment -->", filter.filter("<!-- comment -->", context));
  }

}
