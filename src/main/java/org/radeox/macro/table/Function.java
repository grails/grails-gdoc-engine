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


package org.radeox.macro.table;


/**
 * A function interface for functions in table cells like
 * =SUM(A1:A3)
 *
 * @author stephan
 * @version $Id: Function.java,v 1.1 2003/02/17 07:21:21 stephan Exp $
 */

public interface Function {
  public String getName();
  public void execute(Table table, int posx, int posy, int startX, int startY, int endX, int endY);
}
