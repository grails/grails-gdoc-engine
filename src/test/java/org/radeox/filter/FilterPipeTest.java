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

package org.radeox.filter;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.filter.Filter;
import org.radeox.filter.FilterPipe;
import org.radeox.filter.context.FilterContext;
import org.radeox.filter.context.BaseFilterContext;
import org.radeox.filter.mock.MockReplacedFilter;
import org.radeox.filter.mock.MockReplacesFilter;
import org.jmock.Mock;

public class FilterPipeTest extends FilterTestSupport {
  protected void setUp() throws Exception {
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(FilterPipeTest.class);
  }

  public void testDeactivatedFilterIsListed() {
    FilterPipe fp = new FilterPipe();

    Filter f1 = new Filter() {
      public String[] before() {
        return FilterPipe.EMPTY_BEFORE;
      }

      public void setInitialContext(InitialRenderContext context) {
      }

      public String[] replaces() {
        return new String[0];  //To change body of implemented methods use Options | File Templates.
      }

      public String filter(String input, FilterContext context) {
        return null;
      }

      public String getDescription() {
        return "";
      }

    };
    fp.addFilter(f1);
    fp.deactivateFilter(f1.getClass().getName());
    assertTrue("Filter is listed as deactived", fp.getInactiveFilters().contains(f1));
  }

  public void testActivatedFilterIsUsed() {
    FilterPipe fp = new FilterPipe();

    Mock mockFilter = mock(Filter.class);
     mockFilter.expects(atLeastOnce()).method("setInitialContext");
     mockFilter.expects(atLeastOnce()).method("before").will(returnValue(new String[]{}));
     mockFilter.expects(atLeastOnce()).method("filter").will(returnValue("executed"));

    Filter filter = (Filter) mockFilter.proxy();

    fp.addFilter(filter);
    fp.deactivateFilter(filter.getClass().getName());
    fp.activateFilter(filter.getClass().getName());

    assertEquals("executed", fp.filter("test", context));
    assertTrue(fp.getInactiveFilters().isEmpty());
    assertEquals("[mockFilter]",fp.getAllFilters().toString());
  }

   public void testDeactivatedFilterIsNotUsed() {
    FilterPipe fp = new FilterPipe();

    Mock mockFilter = mock(Filter.class);
     mockFilter.expects(atLeastOnce()).method("setInitialContext");
     mockFilter.expects(atLeastOnce()).method("before").will(returnValue(new String[]{}));
     mockFilter.expects(never()).method("filter");

    Filter filter = (Filter) mockFilter.proxy();

    fp.addFilter(filter);
    fp.deactivateFilter(filter.getClass().getName());
    fp.filter("test", new BaseFilterContext() );
  }

  public void testBefore() {
    FilterPipe fp = new FilterPipe();

    Filter f1 = new Filter() {
      public String[] before() {
        return FilterPipe.EMPTY_BEFORE;
      }

      public void setInitialContext(InitialRenderContext context) {
      }

      public String[] replaces() {
        return new String[0];  //To change body of implemented methods use Options | File Templates.
      }

      public String filter(String input, FilterContext context) {
        return null;
      }

      public String getDescription() {
        return "";
      }
    };
    // f2 should be inserted before f1
    Filter f2 = new Filter() {
      public String[] before() {
        return FilterPipe.FIRST_BEFORE;
      }

      public String[] replaces() {
        return new String[0];  //To change body of implemented methods use Options | File Templates.
      }

      public void setInitialContext(InitialRenderContext context) {
      }

      public String filter(String input, FilterContext context) {
        return null;
      }

      public String getDescription() {
        return "";
      }
    };
    fp.addFilter(f1);
    fp.addFilter(f2);
    assertEquals("'FIRST_BEFORE Filter is first in FilterPipe", fp.getFilter(0), f2 );
  }

  public void testReplace() {
    FilterPipe fp = new FilterPipe();

    Filter f1 = new MockReplacedFilter();
    Filter f2 = new MockReplacesFilter();

    fp.addFilter(f1);
    fp.addFilter(f2);

    fp.init();

    assertTrue("MockReplacedFilter is removed from FilterPipe", -1 == fp.index(MockReplacedFilter.class.getName()));
    assertTrue("MockReplacesFilter is not removed from FilterPipe", -1 != fp.index(MockReplacesFilter.class.getName()));
  }
}
