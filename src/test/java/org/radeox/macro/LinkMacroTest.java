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

package org.radeox.macro;

import junit.framework.Test;
import junit.framework.TestSuite;

public class LinkMacroTest extends MacroTestSupport {
  public LinkMacroTest(String name) {
    super(name);
  }

  public static Test suite() {
    return new TestSuite(LinkMacroTest.class);
  }

//  public void testFile() {
//    String result = EngineManager.getInstance().render("{link:Test|c:\\some\\file}", context);
//    assertEquals("<a href=\"\"></a>", result);
//  }

  public void testSimpleLink() {
    String result = engine.render("{link:TEST|http://foo.com/}", context);
    assertEquals("<span class=\"nobr\"><a href=\"http://foo.com/\">TEST</a></span>", result);
  }

  public void testSimpleLinkWithoutName() {
    String result = engine.render("{link:http://foo.com/}", context);
    assertEquals("<span class=\"nobr\"><a href=\"http://foo.com/\">&#104;ttp://foo.com/</a></span>", result);
  }

  public void testCorrectEndWithSpace() {
    String result = engine.render("{link:TEST|http://foo.com/} ", context);
    assertEquals("<span class=\"nobr\"><a href=\"http://foo.com/\">TEST</a></span> ", result);
  }

  public void testCorrectEndWithComma() {
    String result = engine.render("{link:TEST|http://foo.com/},", context);
    assertEquals("<span class=\"nobr\"><a href=\"http://foo.com/\">TEST</a></span>,", result);
  }

  public void testCorrectEndWithSpaceAndComma() {
    String result = engine.render("{link:TEST|http://foo.com/} ,", context);
    assertEquals("<span class=\"nobr\"><a href=\"http://foo.com/\">TEST</a></span> ,", result);
  }

  public void testSimpleLinkWithoutNameAndComma() {
    String result = engine.render("{link:http://foo.com/},", context);
    assertEquals("<span class=\"nobr\"><a href=\"http://foo.com/\">&#104;ttp://foo.com/</a></span>,", result);
  }

  public void testLinkWithAmpersand() {
    String result = engine.render("{link:test|http://foo.com/foo.cgi?test=aaa&test1=bbb},", context);
    assertEquals("<span class=\"nobr\"><a href=\"http://foo.com/foo.cgi?test=aaa&#38;test1=bbb\">test</a></span>,", result);
  }

}
