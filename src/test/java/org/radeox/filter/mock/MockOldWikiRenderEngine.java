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

import org.radeox.api.engine.context.InitialRenderContext;


public class MockOldWikiRenderEngine extends MockWikiRenderEngine {

  public InitialRenderContext getInitialRenderContext() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  public void appendLink(StringBuffer buffer, String name, String view, String anchor) {
    buffer.append("link:"+view+"#"+anchor);
  }

  public void appendLink(StringBuffer buffer, String name, String view) {
    buffer.append("link:"+view);
  }

  public void appendCreateLink(StringBuffer buffer, String name, String view) {
    buffer.append(view);
    buffer.append("?");
  }

  public String getName() {
    return "mock-old-wiki";
  }


}
