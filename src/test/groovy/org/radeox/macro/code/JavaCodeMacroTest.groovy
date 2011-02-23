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

package org.radeox.macro.code;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.radeox.api.engine.RenderEngine;
import org.radeox.engine.BaseRenderEngine;
import org.radeox.macro.MacroTestSupport;

public class JavaCodeMacroTest extends MacroTestSupport {
  final String S_CODE = "<div class=\"code\"><pre>";
  final String E_CODE = "</pre></div>";
  final String S_JAVA_OBJECT = "<span class=\"java&#45;object\">";
  final String E_JAVA_OBJECT = "</span>";
  final String S_JAVA_KEYWORD = "<span class=\"java&#45;keyword\">";
  final String E_JAVA_KEYWORD = "</span>";
  final String S_JAVA_QUOTE = "<span class=\"java&#45;quote\">\"";
  final String E_JAVA_QUOTE = "\"</span>";

  public JavaCodeMacroTest(String name) {
    super(name);
  }

  public static Test suite() {
    return new TestSuite(JavaCodeMacroTest.class);
  }

  public void testJavaCode() {
    RenderEngine engine = new BaseRenderEngine();
    String result = engine.render("{code:java}String name = \"Peter\";{code}", context);
    assertEquals(
      S_CODE +
        S_JAVA_OBJECT + "String" + E_JAVA_OBJECT +
        " name = " + S_JAVA_QUOTE +
        "Peter" + E_JAVA_QUOTE +
        ";" + E_CODE,
      result);
  }

  public void testJavaCodeWithGString() {
    RenderEngine engine = new BaseRenderEngine();
    String result = engine.render('{code:java}String out = "Person: ${name}"{code}', context);
    assertEquals(
      S_CODE +
        S_JAVA_OBJECT + 'String' + E_JAVA_OBJECT +
        ' out = ' + S_JAVA_QUOTE +
        'Person: $&#123;name&#125;' + E_JAVA_QUOTE + E_CODE,
      result);
  }

  public void testMultilineJavaCodeWithGString() {
    RenderEngine engine = new BaseRenderEngine()
    String result = engine.render('''\
{code:java}
String name = "Peter";
String out = "Person: ${name}";
{code}
int i = 100;''', context)

    String expected = """\
${S_CODE}${S_JAVA_OBJECT}String${E_JAVA_OBJECT} name = ${S_JAVA_QUOTE}Peter${E_JAVA_QUOTE};
${S_JAVA_OBJECT}String${E_JAVA_OBJECT} out = ${S_JAVA_QUOTE}Person: \$&#123;name&#125;${E_JAVA_QUOTE};${E_CODE}
int i = 100;"""

    assertEquals expected, result
  }

  void testGrailsGuideFailure() {
    def engine = new BaseRenderEngine()
    def result = engine.render('''\
{code:java}
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
        grails.dbconsole.enabled = true
        grails.dbconsole.urlRoot = '/admin/dbconsole'
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
}
{code}
''', context)

    String expected = """\
${S_CODE}environments &#123;
    production &#123;
        grails.serverURL = ${S_JAVA_QUOTE}http://www.changeme.com${E_JAVA_QUOTE}
        grails.dbconsole.enabled = ${S_JAVA_KEYWORD}true${E_JAVA_KEYWORD}
        grails.dbconsole.urlRoot = '/admin/dbconsole'
    &#125;
    development &#123;
        grails.serverURL = ${S_JAVA_QUOTE}http://localhost:8080/\$&#123;appName&#125;${E_JAVA_QUOTE}
    &#125;
    test &#123;
        grails.serverURL = ${S_JAVA_QUOTE}http://localhost:8080/\$&#123;appName&#125;${E_JAVA_QUOTE}
    &#125;
&#125;${E_CODE}
"""

    assertEquals expected, result
  }
}
