package org.radeox.filter;

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


import junit.framework.Test;
import junit.framework.TestSuite;

public class ListFilterTest extends FilterTestSupport {
  private static final String RESULT_UNNUMBERED = "<ul class=\"minus\">\n<li>test</li>\n</ul>";
  private static final String RESULT_UNNUMBERED_2 = "<ul class=\"minus\">\n<li>test</li>\n<li>test</li>\n</ul>";
  private static final String RESULT_ORDERED = "<ol>\n<li>test</li>\n<li>test</li>\n<li>test</li>\n</ol>";
  private static final String RESULT_NESTED_SIMPLE = "<ul class=\"minus\">\n" +
          "<li>test</li>\n" +
          "<ul class=\"minus\">\n" +
          "<li>test</li>\n" +
          "<li>test</li>\n" +
          "</ul>\n" +
          "<li>test</li>\n" +
          "</ul>";
  private static final String RESULT_NESTED_LIST = "<ul class=\"minus\">\n" +
          "<li>test</li>\n" +
          "<ol class=\"alpha\">\n" +
          "<li>test</li>\n" +
          "<li>test</li>\n" +
          "</ol>\n" +
          "<li>test</li>\n" +
          "</ul>";

  protected void setUp() throws Exception {
    filter = new ListFilter();
//    context.getRenderContext().setRenderEngine((RenderEngine)
//        new MockWikiRenderEngine()
//    );
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(ListFilterTest.class);
  }

  public void testListsWithStrike() {
    assertEquals("<ul class=\"minus\">\n" +
            "<li>test</li>\n" +
            "<li>test</li>\n" +
            "<li>test</li>\n" +
            "</ul>", filter.filter("- test\n- test\n\n-----\n\n- test", context));
  }

  public void testUnnumberedList() {
    assertEquals(RESULT_UNNUMBERED, filter.filter("- test", context));
  }

  public void testUnnumberedListCreole() {
    assertEquals(RESULT_UNNUMBERED, filterCreole.filter("- test", context));
  }

  public void testUnnumberedListTwoItems() {
    assertEquals(RESULT_UNNUMBERED_2, filter.filter("- test\n- test", context));
  }

  public void testUnnumberedListTwoItemsCreole() {
    assertEquals(RESULT_UNNUMBERED_2, filterCreole.filter("- test\n- test", context));
  }

  public void testOrderedList() {
    assertEquals(RESULT_ORDERED,
                 filter.filter("1. test\n1. test\n 1. test", context));
  }

  public void testOrderedListCreole() {
    assertEquals(RESULT_ORDERED,
                 filterCreole.filter("# test\n# test\n # test", context));
  }

  public void testSimpleNestedList() {
    assertEquals(RESULT_NESTED_SIMPLE, filter.filter("- test\r\n-- test\r\n-- test\r\n- test", context));
  }

  public void testSimpleNestedListCreole() {
    assertEquals(RESULT_NESTED_SIMPLE, filter.filter("- test\r\n-- test\r\n-- test\r\n- test", context));
  }

  public void testNestedList() {
    assertEquals(RESULT_NESTED_LIST, filter.filter("- test\n-a. test\n-a. test\n- test", context));
  }

  public void testSequentialLists() {
    assertEquals("<ul class=\"minus\">\n" +
            "<li>test</li>\n" +
            "</ul>TEXT\n" +
            "<ul class=\"minus\">\n" +
            "<li>test</li>\n" +
            "</ul>", filter.filter("- test\nTEXT\n- test", context));
  }

  public void testListWithLinks() {
    assertEquals("<ul class=\"minus\">\n" +
            "<li>[test]</li>\n" +
            "<li>[test1]</li>\n" +
            "<li>[test test2]</li>\n" +
            "</ul>", filter.filter("- [test]\n- [test1]\n- [test test2]\n", context));
  }
}
