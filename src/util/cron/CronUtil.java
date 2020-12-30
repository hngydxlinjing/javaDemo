package util.cron;

import org.quartz.CronTrigger;
import org.quartz.TriggerUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CronUtil {
    /**
     * 按年生成区间段
     * @param cron
     * @return
     */
    public static List<Date> getRecentTriggerTimeByYear(String cron,Date from ,Date to )  throws  Exception{
        List<Date> dateList ;
        try {
            CronTrigger cronTrigger  = new CronTrigger("0 0 0 1 0/1 2021");
            // 这个是重点，一行代码搞定
            dateList = TriggerUtils.computeFireTimesBetween(cronTrigger, null, from,to);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return dateList;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date from = simpleDateFormat.parse("2020-12-23");
        Date to = simpleDateFormat.parse("2021-12-31");
        List<Date> list = CronUtil.getRecentTriggerTimeByYear("0 0 0 * 1/1 ? 2020,2021", from, to);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(simpleDateFormat.format(list.get(i)));
        }
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getThisYearLastDay(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year + 1);
        calendar.add(calendar.DATE, -1);
        Date lastYearFirst = calendar.getTime();
        return lastYearFirst;
    }



    /**
     * 获取某年某月的第一天
     * @Title:getFisrtDayOfMonth
     * @Description:
     * @param:@param year
     * @param:@param month
     * @param:@return
     * @return:String
     * @throws
     */
    public static Date getFisrtDayOfMonth(int year,int month)
    {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal.getTime();
    }



    /**
     * 获取某月的最后一天
     *
     */
    public static Date getLastDayOfMonth(int year,int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }

    /**
     * 获取某年某周的第一天
     * @param today
     * @return
     */
    public static Date getFirstDayOfWeek(Date today) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        cal.clear();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DATE,day);
       // cal.setTime(today);

        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        return  cal.getTime();
    }

    /**
     * 获取某年某周的最后一天
     * @param today
     * @return
     */

    public static Date getLastDayOfWeek(Date today)  {
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = 6;
        } else {
            d = 7-cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周结束日期
        return cal.getTime();

    }


}
