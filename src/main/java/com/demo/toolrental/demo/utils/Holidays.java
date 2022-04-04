package com.demo.toolrental.demo.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Miguel
 */
public class Holidays {
    
     public static int IndependenceDayObserved (LocalDate date)
    {
    int dayValue;
    int nYear = date.getYear();
    int nMonth = 6; // July
    if(nMonth != date.getMonthValue()-1){
        return -1;
    }
    Calendar myCalendar = new GregorianCalendar(nYear, nMonth, 4);
    Date dtD = myCalendar.getTime();
    dayValue = dtD.getDay();
    switch(dayValue)
        {
        case 0 : // Sunday
        return 5;
        case 1 : // Monday
        case 2 : // Tuesday
        case 3 : // Wednesday
        case 4 : // Thursday
        case 5 : // Friday
        return 4;
        default :
        // Saturday
        return 3;
        }
    }
    
    public static int LaborDayObserved (LocalDate date)
    {
    // The first Monday in September
    int nYear = date.getYear();
    int dayValue;
    int nMonth = 8; // September
    if(nMonth != date.getMonthValue()-1){
        return -1;
    }
    Calendar myCalendar = new GregorianCalendar(nYear, nMonth, 1);
    Date dtD = myCalendar.getTime();
    dayValue = dtD.getDay();
    switch(dayValue)
        {
        case 0 : // Sunday
        return dayValue+2;
        case 1 : // Monday
        return dayValue+7;
        case 2 : // Tuesday
        return dayValue+6;
        case 3 : // Wednesday
        return dayValue+5;
        case 4 : // Thursday
        return dayValue+4;
        case 5 : // Friday
        return dayValue+3;
        default : // Saturday
        return dayValue+2;
        }
    }
}

