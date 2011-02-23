/*
 * This file is part of "SnipSnap Radeox Rendering Engine".
 *
 * Copyright (c) 2002 Stephan J. Schmidt, Matthias L. Jugel
 * All Rights Reserved.
 *
 * Please visit http://radeox.org/ for updates and contact.
 *
 * --LICENSE NOTICE--
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 * --LICENSE NOTICE--
 */
package org.radeox.macro.code;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.radeox.EngineManager;
import org.radeox.macro.MacroTestSupport;

public class SqlCodeMacroTest extends MacroTestSupport {
  final String S_CODE = "<div class=\"code\"><pre>";
  final String E_CODE = "</pre></div>";
  final String S_SQL_OBJECT = "<span class='xml&#45;tag'>&lt;";
  final String E_SQL_OBJECT = "&gt;</span>";
  final String S_SQL_KEYWORD = "<span class='sql&#45;keyword'>";
  final String E_SQL_KEYWORD = "</span>";
  final String S_SQL_QUOTE = "<span class='sql&#45;quote'>\"";
  final String E_SQL_QUOTE = "\"</span>";

  public SqlCodeMacroTest(String name) {
    super(name);
  }

  public static Test suite() {
    return new TestSuite(SqlCodeMacroTest.class);
  }
  
  public void testSqlCodeSelect() {
    String result = EngineManager.getInstance().render(
    		"{code:sql}SELECT a FROM table WHERE b = 1 AND c = 3 OR d = 5{code}", context);

    assertEquals(
      S_CODE
      + S_SQL_KEYWORD + "SELECT" + E_SQL_KEYWORD
			+ " a "
      + S_SQL_KEYWORD + "FROM" + E_SQL_KEYWORD
			+ " table "
      + S_SQL_KEYWORD + "WHERE" + E_SQL_KEYWORD
			+ " b = 1 "
      + S_SQL_KEYWORD + "AND" + E_SQL_KEYWORD
			+ " c = 3 " 
			+ S_SQL_KEYWORD + "OR" + E_SQL_KEYWORD
			+ " d = 5"
      + E_CODE,
      result);
  }

  public void testSqlCodeQuotes() {
    String result = EngineManager.getInstance().render(
    		"{code:sql}SELECT a FROM table WHERE name = \"abc\"{code}", context);

    assertEquals(
      S_CODE
      + S_SQL_KEYWORD + "SELECT" + E_SQL_KEYWORD
			+ " a "
      + S_SQL_KEYWORD + "FROM" + E_SQL_KEYWORD
			+ " table "
      + S_SQL_KEYWORD + "WHERE" + E_SQL_KEYWORD
			+ " name = "
      + S_SQL_QUOTE + "abc" + E_SQL_QUOTE
      + E_CODE,
      result);
  }
}

