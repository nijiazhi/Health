package iscas.otcaix.di.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static int getAge(Date birthday,Date today){
		Calendar todayCal = Calendar.getInstance();
		todayCal.setTime(today);
		Calendar birthdayCal = Calendar.getInstance();
		birthdayCal.setTime(birthday);
		
		int year = todayCal.get(Calendar.YEAR)-birthdayCal.get(Calendar.YEAR);
		int month = todayCal.get(Calendar.MONTH)-birthdayCal.get(Calendar.MONTH);
		int day = todayCal.get(Calendar.DATE)-birthdayCal.get(Calendar.DATE);
		if(year<0){
			throw new RuntimeException("出生日期大于当前日期");
		}
		if(month<0||day<0){
			year--;
		}	
		return year;	
	}
	
	public static long getIntervalDays(Date start,Date end){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(start);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(end);    
        long time2 = cal.getTimeInMillis();   
        if(time2<time1) throw new RuntimeException("结束日期大于起始日期");
        long between_days=(time2-time1)/(1000*3600*24); 
        return between_days;
	}
	
	public static Date randomDate(String startDate,String endDate){
		try {  
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
            Date start = format.parse(startDate);// 构造开始日期  
            Date end = format.parse(endDate);// 构造结束日期  
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  
            if (start.getTime() >= end.getTime()) {  
                return null;  
            }  
            long date = random(start.getTime(), end.getTime());  
            return new Date(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
	}
	
	private static long random(long begin, long end) {  
        long rtn = begin + (long) (Math.random() * (end - begin));  
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值  
        if (rtn == begin || rtn == end) {  
            return random(begin, end);  
        }  
        return rtn;  
    }  
	
}
