package app.wms.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.squareup.okhttp.internal.http.HttpDate.format;

/**
 * Created by zhou on 2016/9/27.
 */

public class DateUtils {

    private static SimpleDateFormat sf = null;
    /*获取系统时间 格式为："yyyy-MM-dd "*/
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try{
            date = sf.parse(time);
            } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        return date.getTime();
    }
}
