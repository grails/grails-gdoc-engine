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

package org.radeox;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.radeox.api.engine.RenderEngine;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.engine.BaseRenderEngine;
import org.radeox.engine.context.BaseRenderContext;
import org.radeox.filter.mock.MockWikiRenderEngine;

import java.io.IOException;
import java.io.StringWriter;

public class BaseRenderEngineTest extends TestCase {
  private RenderContext context;
  private RenderEngine engine;

  public BaseRenderEngineTest(String name) {
    super(name);
  }

  protected void setUp() throws Exception {
    context = new BaseRenderContext();
    engine = new BaseRenderEngine();
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(BaseRenderEngineTest.class);
  }

  public void testBoldInList() {
    assertEquals("<ul class=\"minus\">\n<li><b class=\"bold\">test</b></li>\n</ul>", engine.render("- __test__", context));
  }

  public void testRenderEngine() {
    String result = engine.render("__SnipSnap__ {link:Radeox|http://radeox.org}", context);
    assertEquals("<b class=\"bold\">SnipSnap</b> <span class=\"nobr\"><a href=\"http://radeox.org\">Radeox</a></span>", result);
  }

  public void testEmpty() {
    String result = engine.render("", context);
    assertEquals("", result);
  }

  public void testWriter() {
    StringWriter writer = new StringWriter();
    try {
      engine.render(writer, "__SnipSnap__", context);
    } catch (IOException e) {
      // never reach
    }
    assertEquals("BaseRenderEngine writes to Writer","<b class=\"bold\">SnipSnap</b>", writer.toString());
  }

  public void testFilterOrder() {
    context.setRenderEngine(new MockWikiRenderEngine());
    assertEquals("'<link>' - '&#60;link&#62;'", engine.render("[<link>]", context));
  }
}
