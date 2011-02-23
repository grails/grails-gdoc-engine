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
import org.radeox.api.engine.context.RenderContext;
import org.radeox.filter.mock.MockWikiRenderEngine;

public class LinkTestFilterTest extends FilterTestSupport {
  private static final String RESULT_URL = "<div class=\"error\">Do not surround URLs with [...].</div>";
  private static final String RESULT_CREATE = "'Roller' - 'Roller'";
  private static final String RESULT_LINK = "link:SnipSnap|SnipSnap";
  private static final String RESULT_LINK_LOWER = "link:stephan|stephan";
  private static final String RESULT_LINK_ALIAS = "link:stephan|alias";
  private static final String RESULT_LINK_ALIAS_ANCHOR = "link:stephan|alias#hash";
  private static final String RESULT_ESCAPED = "'<link>' - '&#60;link&#62;'";

  protected void setUp() throws Exception {
    filter = new LinkTestFilter();
    context.getRenderContext().setRenderEngine(new MockWikiRenderEngine());
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(LinkTestFilterTest.class);
  }

  public void testUrlInLink() {
    assertEquals("Url is reported", RESULT_URL, filter.filter("[http://radeox.org]", context));
  }

  public void testUrlInLinkCreole() {
    assertEquals("Url is reported", RESULT_URL, filterCreole.filter("[[http://radeox.org]]", context));
  }

  public void testCreate() {
    assertEquals(RESULT_CREATE, filter.filter("[Roller]", context));
  }

  public void testCreateCreole() {
    assertEquals(RESULT_CREATE, filterCreole.filter("[[Roller]]", context));
  }

  public void testLink() {
    assertEquals(RESULT_LINK, filter.filter("[SnipSnap]", context));
  }

  public void testLinkCreole() {
    assertEquals(RESULT_LINK, filterCreole.filter("[[SnipSnap]]", context));
  }

  public void testLinkLower() {
    assertEquals(RESULT_LINK_LOWER, filter.filter("[stephan]", context));
  }

  public void testLinkLowerCreole() {
    assertEquals(RESULT_LINK_LOWER, filterCreole.filter("[[stephan]]", context));
  }

  public void testLinkAlias() {
    assertEquals(RESULT_LINK_ALIAS, filter.filter("[alias|stephan]", context));
  }

  public void testLinkAliasCreole() {
    assertEquals(RESULT_LINK_ALIAS, filterCreole.filter("[[stephan|alias]]", context));
  }

  public void testLinkAliasAnchor() {
    assertEquals(RESULT_LINK_ALIAS_ANCHOR, filter.filter("[alias|stephan#hash]", context));
  }

  public void testLinkAliasAnchorCreole() {
    assertEquals(RESULT_LINK_ALIAS_ANCHOR, filterCreole.filter("[[stephan#hash|alias]]", context));
  }

  public void testLinkAliasAnchorType() {
    assertEquals(RESULT_LINK_ALIAS_ANCHOR, filter.filter("[alias|type:stephan#hash]", context));
  }

  public void testLinkCacheable() {
    RenderContext renderContext = context.getRenderContext();
    renderContext.setCacheable(false);
    filter.filter("[SnipSnap]", context);
    renderContext.commitCache();
    assertTrue("Normal link is cacheable", renderContext.isCacheable());
  }

  public void testCreateLinkNotCacheable() {
    RenderContext renderContext = context.getRenderContext();
    renderContext.setCacheable(false);
    filter.filter("[Roller]", context);
    renderContext.commitCache();
    assertTrue("Non existing link is not cacheable", ! renderContext.isCacheable());
  }

  public void testLinksWithEscapedChars() {
    assertEquals(RESULT_ESCAPED, filter.filter("[<link>]", context));
  }

  public void testLinksWithEscapedCharsCreole() {
    assertEquals(RESULT_ESCAPED, filterCreole.filter("[[<link>]]", context));
  }

}
