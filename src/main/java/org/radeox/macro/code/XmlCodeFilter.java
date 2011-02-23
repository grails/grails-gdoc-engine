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

/*
 * XmlCodeFilter colourizes Xml Code
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: XmlCodeFilter.java,v 1.6 2003/12/11 13:24:56 leo Exp $
 */

public class XmlCodeFilter extends DefaultRegexCodeFormatter implements SourceCodeFormatter {
  private static final String KEYWORDS = "\\b(xsl:[^&\\s]*)\\b";
  private static final String TAGS = "(&#60;/?[^!0-9].*?&#62;)";
  private static final String QUOTE = "\"(([^\"\\\\]|\\.)*)\"";
  private static final String COMMENT = "(&#60;!--.*--&#62;)";

  public XmlCodeFilter() {
    super(QUOTE, "<span class=\"xml-quote\">\"$1\"</span>");
    addRegex(TAGS, "<span class=\"xml-tag\">$1</span>");
    addRegex(KEYWORDS, "<span class=\"xml-keyword\">$1</span>");
    addRegex(COMMENT, "<span class=\"xml-comment\">$1</span>");
  }

  public String getName() {
    return "xml";
  }
}
