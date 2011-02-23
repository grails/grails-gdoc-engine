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

package org.radeox;

import com.clarkware.junitperf.TimedTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.radeox.api.engine.RenderEngine;
import org.radeox.engine.BaseRenderEngine;
import org.radeox.engine.context.BaseRenderContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PerformanceTests {
  public static void main(String[] args) throws IOException {
    TestRunner.run(suite());
  }

  public static Test suite() throws IOException {
    // get test markup from text file
    File wikiTxt = new File("wiki.txt");
    BufferedReader reader = new BufferedReader(new FileReader(wikiTxt.getCanonicalFile()));
    StringBuffer input = new StringBuffer();
    String tmp;
    while ((tmp = reader.readLine()) != null) {
      input.append(tmp);
    }
    RenderEngine engine = new BaseRenderEngine();
    System.err.println(engine.render("__initialized__", new BaseRenderContext()));

    TestSuite s = new TestSuite();
    long maxElapsedTime = 30 * 1000; // 30s
    StringBuffer testString = new StringBuffer();
    for (int i = 0; i < 10; i++) {
      testString.append(input);
      Test renderEngineTest = new RenderEnginePerformanceTest(testString.toString());
      Test timedTest = new TimedTest(renderEngineTest, maxElapsedTime, false);
      s.addTest(timedTest);
    }
    return s;
  }


}