package com.example.basemedia.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

  public static String convertCalendarToTimeFormat(Calendar calendar) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    SimpleDateFormat dateFormatChat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z");

    return dateFormat.format(calendar.getTime());
  }
  
  public static String convertCalendarToTimeFormatGChat(Calendar calendar) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//    SimpleDateFormat dateFormatChat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z");
    
    return dateFormat.format(calendar.getTime());
  }

  public static String covertTimeToText(String dataDate) {

    String convTime = null;

    String suffix = "trước";

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date pasTime;
    try {
      pasTime = dateFormat.parse(dataDate);

      Date nowTime = new Date();

      long dateDiff = nowTime.getTime() - pasTime.getTime();

      long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
      long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
      long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
      long day  = TimeUnit.MILLISECONDS.toDays(dateDiff);

      if (second <= 0) {
        //convTime = 0+" giây "+suffix;
        convTime = "Vừa xong";
      } else if (second < 60) {
        //convTime = second+" giây "+suffix;
        convTime = "Vừa xong";
      } else if (minute < 60) {
        convTime = minute+" phút "+suffix;
      } else if (hour < 24) {
        convTime = hour+" giờ "+suffix;
      } else if (day >= 7) {
        if (day > 30) {
          convTime = (day / 30)+" tháng "+suffix;
        } else if (day > 360) {
          convTime = (day / 360)+" năm "+suffix;
        } else {
          convTime = (day / 7) + " tuần "+suffix;
        }
      } else if (day < 7) {
        convTime = day+" ngày "+suffix;
      }

      return convTime;
    } catch (ParseException e) {
      e.printStackTrace();
      return "";
    }
  }
  public static String covertTimeToTextChat(String dataDate) {

    String convTime = null;

    String suffix = "trước";

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    Date pasTime;
    try {
      pasTime = dateFormat.parse(dataDate);

      Date nowTime = new Date();

      long dateDiff = nowTime.getTime() - pasTime.getTime();

      long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
      long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
      long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
      long day  = TimeUnit.MILLISECONDS.toDays(dateDiff);

      if (second <= 0) {
        //convTime = 0+" giây "+suffix;
        convTime = "Vừa xong";
      } else if (second < 60) {
        //convTime = second+" giây "+suffix;
        convTime = "Vừa xong";
      } else if (minute < 60) {
        convTime = minute+" phút "+suffix;
      } else if (hour < 24) {
        convTime = hour+" giờ "+suffix;
      } else if (day >= 7) {
        if (day > 30) {
          convTime = (day / 30)+" tháng "+suffix;
        } else if (day > 360) {
          convTime = (day / 360)+" năm "+suffix;
        } else {
          convTime = (day / 7) + " tuần "+suffix;
        }
      } else if (day < 7) {
        convTime = day+" ngày "+suffix;
      }

      return convTime;
    } catch (ParseException e) {
      e.printStackTrace();
      return "";
    }
  }
  public static String covertTime(String dataDate ){
    String convTime = null;

    String suffix = "trước";

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date pasTime;
    try {
      pasTime = dateFormat.parse(dataDate);

      Date nowTime = new Date();

      long dateDiff = nowTime.getTime() - pasTime.getTime();

      long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
      long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
      long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
      long day  = TimeUnit.MILLISECONDS.toDays(dateDiff);

      if (second <= 0) {
        convTime = 0+" giây "+suffix;
      } else if (second < 60) {
        convTime = second+" giây "+suffix;
      } else if (minute < 60) {
        convTime = minute+" phút "+suffix;
      } else if (hour < 24) {
        convTime = hour+" giờ "+suffix;
      } else if (day >= 7) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM");
        convTime = format.format(pasTime);

      } else if (day < 7) {
        convTime = day+" ngày "+suffix;
      }

      return convTime;
    } catch (ParseException e) {
      e.printStackTrace();
      return "";
    }
  }

  public static String covertTimeMess(String dataDate ){
    String convTime = null;

    String suffix = "mới";

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date pasTime;
    try {
      pasTime = dateFormat.parse(dataDate);

      Date nowTime = new Date();

      long dateDiff = nowTime.getTime() - pasTime.getTime();

      long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
//      long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
      long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
      long day  = TimeUnit.MILLISECONDS.toDays(dateDiff);

      if (second <= 60) {
        convTime = "Vừa xong";
      } else if (second < 60) {
        convTime= "Vừa xong";
      } else if (hour < 24) {
        SimpleDateFormat format = new SimpleDateFormat(" HH:mm");
        convTime = format.format(pasTime);
      } else if (day >= 1) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM HH:mm");
        convTime = format.format(pasTime);
      }
      return convTime;
    } catch (ParseException e) {
      e.printStackTrace();
      return "";
    }
  }
  public static String covertTimeMessGChat(String dataDate ){
    String convTime = null;

    String suffix = "mới";
  
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    Date pasTime;
    try {
      pasTime = dateFormat.parse(dataDate);

      Date nowTime = new Date();

      long dateDiff = nowTime.getTime() - pasTime.getTime();

      long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
//      long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
      long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
      long day  = TimeUnit.MILLISECONDS.toDays(dateDiff);

      if (second <= 60) {
      } else if (second < 60) {
        convTime = suffix;
      } else if (hour < 24) {
        SimpleDateFormat format = new SimpleDateFormat(" HH:mm");
        convTime = format.format(pasTime);
      } else if (day >= 1) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM HH:mm");
        convTime = format.format(pasTime);
      }
      return convTime;
    } catch (ParseException e) {
      e.printStackTrace();
      return "";
    }
  }



}
