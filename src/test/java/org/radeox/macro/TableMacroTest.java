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

public class TableMacroTest extends MacroTestSupport {
  public TableMacroTest(String name) {
    super(name);
  }

  public static Test suite() {
    return new TestSuite(TableMacroTest.class);
  }

  public void testTable() {
    String result = engine.render("{table}1|2\n3|4{table}", context);
    assertEquals("<table class=\"wiki-table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><th>1</th><th>2</th></tr><tr class=\"table-odd\"><td>3</td><td>4</td></tr></table>", result);
  }

  public void testEmptyHeader() {
    String result = engine.render("{table}|\n3|4{table}", context);
    assertEquals("<table class=\"wiki-table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><th>&#160;</th><th>&#160;</th></tr><tr class=\"table-odd\"><td>3</td><td>4</td></tr></table>", result);
  }

  public void testMultiTable() {
    String result = engine.render("{table}1|2\n3|4{table}\n{table}5|6\n7|8{table}", context);
    assertEquals("<table class=\"wiki-table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><th>1</th><th>2</th></tr><tr class=\"table-odd\"><td>3</td><td>4</td></tr></table>\n"+
                 "<table class=\"wiki-table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><th>5</th><th>6</th></tr><tr class=\"table-odd\"><td>7</td><td>8</td></tr></table>", result);
  }

  public void testCalcIntSum() {
    String result = engine.render("{table}1|2\n3|=SUM(A1:A2){table}", context);
    assertEquals("<table class=\"wiki-table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><th>1</th><th>2</th></tr><tr class=\"table-odd\"><td>3</td><td>4</td></tr></table>", result);
  }

  public void testCalcFloatSum() {
    String result = engine.render("{table}1|2\n3.0|=SUM(A1:A2){table}", context);
    assertEquals("<table class=\"wiki-table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><th>1</th><th>2</th></tr><tr class=\"table-odd\"><td>3.0</td><td>4.0</td></tr></table>", result);
  }

  public void testFloatAvg() {
    String result = engine.render("{table}1|2\n4|=AVG(A1:A2){table}", context);
    assertEquals("<table class=\"wiki-table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><th>1</th><th>2</th></tr><tr class=\"table-odd\"><td>4</td><td>2.5</td></tr></table>", result);
  }

	/*public void testDoubleLinks() {
    String result = engine.render("{table}1|2\nlink|[here|http://grails.org] [hereAlso|http://grails.org]{table}", context);
    assertEquals("<table class=\"wiki-table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><th>1</th><th>2</th></tr><tr class=\"table-odd\"><td>link</td><td><span class=\"nobr\"><a href=\"http://grails.org\">here</a></span> <span class=\"nobr\"><a href=\"http://grails.org\">hereAlso</a></span></td></tr></table>", result);
  }*/

}
