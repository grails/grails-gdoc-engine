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
import org.radeox.filter.WikiLinkFilter;
import org.radeox.filter.mock.MockOldWikiRenderEngine;

public class WikiLinkFilterTest extends FilterTestSupport {

  protected void setUp() throws Exception {
    filter = new WikiLinkFilter();
    context.getRenderContext().setRenderEngine(new MockOldWikiRenderEngine());
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(WikiLinkFilterTest.class);
  }

  public void testWikiCreate() {
    assertEquals("WebRoller?", filter.filter("WebRoller", context));
  }

  public void testWikiLink() {
    assertEquals("link:SnipSnap", filter.filter("SnipSnap", context));
  }
}
