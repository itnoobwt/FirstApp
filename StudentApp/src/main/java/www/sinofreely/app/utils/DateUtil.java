package www.sinofreely.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期转换类
 */
public class DateUtil {
	public static Date stringToDate(String time) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpledateformat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static long stringToLong(String time) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpledateformat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	public static String DateToString(Date time) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = null;
		try {
			date = simpledateformat.format(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getDay(String date){
		String return_day = "" ;
		try {
			String s [] = date.split("-") ;
			String mday = s[2] ;
			if(mday.startsWith("0")){
				return_day = mday.substring(1) ;
			}else{
				return_day = mday ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(return_day+"这是转换后的日期");
		return return_day ;
	}

	public static Date getDate(String date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date format_date = null;
		try {
			format_date = sdf.parse(date);// 将字符串转换为日期
		} catch (Exception e) {
			System.out.println("输入的日期格式不合理！");
		}
		return format_date;
	}

	public static String getStringDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat msdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date format_date = null;
		String mdate = "";
		try {
			format_date = sdf.parse(date);// 将字符串转换为日期
			mdate = msdf.format(format_date);
		} catch (Exception e) {
			System.out.println("输入的日期格式不合理！");
		}
		return mdate;
	}

	public static String getStringDate2(String date) {

		// Date currentTime = new Date();
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		// String dateString = formatter.format(currentTime);
		// String hour;
		// hour = dateString.substring(11, 13);
		// return hour;
		//
		//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date format_date = null;
		String mdate = "";
		try {
			format_date = sdf.parse(date);// 将字符串转换为日期
			mdate = format_date.getMonth() + "月" + format_date.getDay() + "日";
		} catch (Exception e) {
			System.out.println("输入的日期格式不合理！");
		}
		return mdate;
	}

	// 根据日期取得星期几
	public static String getWeek(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String week = sdf.format(date);
		return week;
	}

	// 取得某个月有多少天
	public static int getDaysOfMonth(Calendar calendar, int year, int month) {
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		int days_of_month = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days_of_month;
	}

	// 取得某个月有多少天
	public static int getDaysOfMonth() {
		int days_of_month = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		return days_of_month;
	}

	// 取得日期是一周的第几天
	public static int getDaysOfWeek(Calendar calendar, int year, int month, int day) {
		calendar.setTime(getDate(year + "-" + month + "-" + day));
		int days_of_week = calendar.get(Calendar.DAY_OF_WEEK);
		return days_of_week;
	}

	// 取得日期是一周的第几天
	public static int getDaysOfWeek() {
		int days_of_week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		return days_of_week;
	}
	// 取得日期是一周的第几天
	public static int getDaysOfWeek(Calendar calendar) {
		int days_of_week = calendar.get(Calendar.DAY_OF_WEEK);
		return days_of_week;
	}

	public static int getMonth(Calendar calendar) {
		int month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	public static int getYear(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR);
		return year;
	}

	public static int getDay(Calendar calendar) {
		int days = calendar.get(Calendar.DAY_OF_MONTH);
		return days;
	}

	public static String getNextDay(int days) {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		// 得到当前时间，+3天
		rightNow.add(Calendar.DAY_OF_MONTH, days);
		String date = sim.format(rightNow.getTime());
		return date;

	}

	public static String getNextDay2(int days) {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-M-d");
		// 得到当前时间，+3天
		rightNow.add(Calendar.DAY_OF_MONTH, days);
		String date = sim.format(rightNow.getTime());
		return date;

	}

	public static String getNextDayofDate(int days) {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat("d");
		rightNow.add(Calendar.DAY_OF_MONTH, days);
		String date = sim.format(rightNow.getTime());

		return date;

	}

	public static boolean getNext7DayisCurrentMonth() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat("m");
		rightNow.add(Calendar.DAY_OF_MONTH, 7);
		int currenMonth = Calendar.getInstance().get(Calendar.MONTH);

		return Calendar.getInstance().get(Calendar.MONTH) == rightNow.get(Calendar.MONTH) ? true : false;

	}

	public static int string2Day(String time) {
		return Integer.parseInt(time.substring(8));
	}

	public static String getWeekday(String date) {// 必须yyyy-MM-dd
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdw = new SimpleDateFormat("E");
		Date d = null;
		try {
			d = sd.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdw.format(d);
	}
	/**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
	public static String getWeekDayString(){
		String weekString = "";
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		weekString = dayNames[dayOfWeek - 1];
		return weekString;
	}
	 /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
    /**
     * 获取当前日期
     * @return
     */
    public static String getDate(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	return dateFormat.format(date);
    }

}
