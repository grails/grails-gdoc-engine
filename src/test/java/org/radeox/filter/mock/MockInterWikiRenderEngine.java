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


package org.radeox.filter.mock;

import org.radeox.api.engine.RenderEngine;
import org.radeox.api.engine.WikiRenderEngine;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.api.engine.context.InitialRenderContext;

import java.io.IOException;
import java.io.Writer;
import java.io.Reader;

public class MockInterWikiRenderEngine implements RenderEngine, WikiRenderEngine {

  public InitialRenderContext getInitialRenderContext() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  public boolean exists(String name) {
    return false;
  }

  public boolean showCreate() {
    return false;
  }

  public void appendLink(StringBuffer buffer, String name, String view, String anchor) {
  }

  public void appendLink(StringBuffer buffer, String name, String view) {
  }

  public void appendCreateLink(StringBuffer buffer, String name, String view) {
  }

  public String getName() {
    return "mock-wiki";
  }

  public String render(String content, RenderContext context) {
    return null;
  }

  public void render(Writer out, String content, RenderContext context) throws IOException {
  }

  public String render(Reader in, RenderContext context) throws IOException {
    return null;
  }
}
