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



package org.radeox.macro.book;

/**
 * Manages links to book dealears or comparison services
 *
 * @author Stephan J. Schmidt
 * @version $Id: BookServices.java,v 1.4 2003/02/06 13:41:41 leo Exp $
 */

public class BookServices extends TextFileUrlMapper {
  private static BookServices instance;

  public BookServices() {
    super(BookServices.class);
  }

  public static synchronized UrlMapper getInstance() {
    if (null == instance) {
      instance = new BookServices();
    }
    return instance;
  }

  public String getFileName() {
    return "conf/bookservices.txt";
  }

  public String getKeyName() {
    return "isbn";
  }
}
