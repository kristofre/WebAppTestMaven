package com.compuware.apm.samples.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.compuware.apm.samples.web.model.Destination;
import com.compuware.apm.samples.web.model.Journey;

public class TestDataCreator {
  
  public static Destination[] createTestData() {
    List<Destination> journeys = new ArrayList<Destination>();
    
    // create 1. Destination
    List<Journey> j1Dates = new ArrayList<Journey>();
    Calendar from = GregorianCalendar.getInstance();
    from.set(Calendar.YEAR, 2014);
    from.set(Calendar.MONTH, Calendar.FEBRUARY);
    from.set(Calendar.DAY_OF_MONTH, 1);
    from.set(Calendar.HOUR, 8);
    from.set(Calendar.MINUTE, 0);
    from.set(Calendar.AM_PM, Calendar.AM);
    Calendar to = GregorianCalendar.getInstance();
    to.set(Calendar.YEAR, 2014);
    to.set(Calendar.MONTH, Calendar.FEBRUARY);
    to.set(Calendar.DAY_OF_MONTH, 10);
    to.set(Calendar.HOUR, 8);
    to.set(Calendar.MINUTE, 0);
    to.set(Calendar.AM_PM, Calendar.AM);
    j1Dates.add(new Journey(from.getTime(), to.getTime()));
    
    from.set(Calendar.MONTH, Calendar.APRIL);
    to.set(Calendar.MONTH, Calendar.APRIL);
    j1Dates.add(new Journey(from.getTime(), to.getTime()));
    Destination j1 = new Destination();
    j1.setTitle("Cuba");
    j1.setDates(j1Dates);
    
    // create second Destination
    List<Journey> j2Dates = new ArrayList<Journey>();
    from.set(Calendar.YEAR, 2014);
    from.set(Calendar.MONTH, Calendar.JULY);
    from.set(Calendar.DAY_OF_MONTH, 15);
    from.set(Calendar.HOUR, 8);
    from.set(Calendar.MINUTE, 0);
    from.set(Calendar.AM_PM, Calendar.AM);

    to.set(Calendar.YEAR, 2014);
    to.set(Calendar.MONTH, Calendar.JULY);
    to.set(Calendar.DAY_OF_MONTH, 21);
    to.set(Calendar.HOUR, 8);
    to.set(Calendar.MINUTE, 0);
    to.set(Calendar.AM_PM, Calendar.AM);
    
    j2Dates.add(new Journey(from.getTime(), to.getTime()));

    Destination j2 = new Destination();
    j2.setTitle("London");
    j2.setDates(j2Dates);

    journeys.add(j1);
    journeys.add(j2);
    return journeys.toArray(new Destination[2]);
  }
  
}
