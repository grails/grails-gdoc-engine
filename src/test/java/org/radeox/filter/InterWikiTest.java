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
import org.radeox.filter.LinkTestFilter;
import org.radeox.filter.interwiki.InterWiki;
import org.radeox.filter.mock.MockInterWikiRenderEngine;

import java.io.StringWriter;
import java.io.IOException;

public class InterWikiTest extends FilterTestSupport {

  protected void setUp() throws Exception {
    filter = new LinkTestFilter();
    context.getRenderContext().setRenderEngine(new MockInterWikiRenderEngine());
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(InterWikiTest.class);
  }

  public void testAnchorInterWiki() {
     assertEquals("<a href=\"http://www.c2.com/cgi/wiki?foo#anchor\">foo@C2</a>", filter.filter("[foo@C2#anchor]", context));
  }

  public void testInterWiki() {
    assertEquals("<a href=\"http://snipsnap.org/space/stephan\">stephan@SnipSnap</a>", filter.filter("[stephan@SnipSnap]", context));
  }

  public void testGoogle() {
    assertEquals("<a href=\"http://www.google.com/search?q=stephan\">stephan@Google</a>", filter.filter("[stephan@Google]", context));
  }

  public void testInterWikiAlias() {
    assertEquals("<a href=\"http://snipsnap.org/space/AliasStephan\">Alias</a>", filter.filter("[Alias|AliasStephan@SnipSnap]", context));
  }

  public void testInterWikiExpander() {
    InterWiki interWiki = InterWiki.getInstance();
    StringWriter writer = new StringWriter();
    try {
      interWiki.expand(writer, "Google", "stephan", "StephanAlias");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("<a href=\"http://www.google.com/search?q=stephan\">StephanAlias</a>", writer.toString());
  }

  public void testCacheable() {
    RenderContext renderContext = context.getRenderContext();
    renderContext.setCacheable(false);
    filter.filter("[stephan@SnipSnap]", context);
    assertTrue("InterWiki is cacheable", renderContext.isCacheable());
  }
}
