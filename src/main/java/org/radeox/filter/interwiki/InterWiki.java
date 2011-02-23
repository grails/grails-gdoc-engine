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


package org.radeox.filter.interwiki;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.radeox.util.Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Stores information and links to other wikis forming a
 * InterWiki
 *
 * @author Stephan J. Schmidt
 * @version $Id: InterWiki.java,v 1.6 2004/01/19 11:45:24 stephan Exp $
 */

public class InterWiki {
  private static Log log = LogFactory.getLog(InterWiki.class);

  private static InterWiki instance;
  private Map interWiki;

  public static synchronized InterWiki getInstance() {
    if (null == instance) {
      instance = new InterWiki();
    }
    return instance;
  }

  public InterWiki(InputStream in) {
    try {
      init(in);
    } catch (IOException e) {
      log.warn("Unable to initialize from stream.", e);
    }
 }

  public InterWiki() {
    try {
      init(new FileInputStream("conf/intermap.txt"));
    } catch (IOException e) {
      log.warn("Unable to read conf/intermap.txt", e);
    }
  }

  public void init(InputStream in ) throws IOException {
    interWiki = new HashMap();
    interWiki.put("LCOM", "http://www.langreiter.com/space/");
    interWiki.put("ESA", "http://earl.strain.at/space/");
    interWiki.put("C2", "http://www.c2.com/cgi/wiki?");
    interWiki.put("WeblogKitchen", "http://www.weblogkitchen.com/wiki.cgi?");
    interWiki.put("Meatball", "http://www.usemod.com/cgi-bin/mb.pl?");
    interWiki.put("SnipSnap", "http://snipsnap.org/space/");

    BufferedReader reader = new BufferedReader(
        new InputStreamReader(in));
    String line;
    while ((line = reader.readLine()) != null) {
      int index = line.indexOf(" ");
      interWiki.put(line.substring(0, index), Encoder.escape(line.substring(index + 1)));
    }
  }

  public Writer appendTo(Writer writer) throws IOException {
    Iterator iterator = interWiki.entrySet().iterator();
    writer.write("{table}\n");
    writer.write("Wiki|Url\n");
    while (iterator.hasNext()) {
      Map.Entry entry = (Map.Entry) iterator.next();
      writer.write((String) entry.getKey());
      writer.write("|");
      writer.write((String) entry.getValue());
      writer.write("\n");
    }
    writer.write("{table}");
    return writer;
  }

  public boolean contains(String external) {
    return interWiki.containsKey(external);
  }

  public String getWikiUrl(String wiki, String name) {
    return ((String) interWiki.get(wiki)) + name;
  }

  public Writer expand(Writer writer, String wiki, String name, String view, String anchor) throws IOException  {
    writer.write("<a href=\"");
    writer.write((String) interWiki.get(wiki));
    writer.write(name);
    if (!"".equals(anchor)) {
       writer.write("#");
       writer.write(anchor);
    }
    writer.write("\">");
    writer.write(view);
    writer.write("</a>");
    return writer;
  }

  public Writer expand(Writer writer, String wiki, String name, String view) throws IOException {
     return expand(writer, wiki, name, view, "");
  }
}
