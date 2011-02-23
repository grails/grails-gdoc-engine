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


package org.radeox.macro.parameter;

import org.radeox.api.engine.context.RenderContext;

import java.util.Map;

/**
 * Encapsulates parameters for an executed Macro call
 *
 * @author Stephan J. Schmidt
 * @version $Id: MacroParameter.java,v 1.10 2004/01/20 12:07:53 stephan Exp $
 */

public interface MacroParameter {
  public void setParams(String stringParams);

  public String getContent();

  public void setContent(String content);

  public int getLength();

  public String get(String index, int idx);

  public String get(String index);

  public String get(int index);

  public Map getParams();

  public void setStart(int start);

  public void setEnd(int end);

  public int getStart();

  public int getEnd();

  public void setContentStart(int start);

  public void setContentEnd(int end);

  public int getContentStart();

  public int getContentEnd();

  public RenderContext getContext();
}
