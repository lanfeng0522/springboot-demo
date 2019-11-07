package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	
	public static void main(String[] args) throws ParseException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-05-29 16:20:18"));
		
		System.out.println(daysBetween(getDate("2019-05-29 16:20:18"), new Date()));
	}
	
	/**
     * 格式yyyy-MM-dd HH:mm:ss
    *字符串转日期
    */
    public static Date getDate(String date) throws ParseException{
	    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
    }
    
    /**
	 * 计算两个日期之间相差的天数
	 * @param date1
	 * @param date2
	 * @return 返回天数绝对值
	 */
	public static int daysBetween(Date date1,Date date2){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);
		return Math.abs(Integer.parseInt(String.valueOf(between_days)));
	}
}
