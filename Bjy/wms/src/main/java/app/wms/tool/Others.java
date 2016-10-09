package app.wms.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by zhou on 2016/9/27.
 */

public class Others {



    //获取操作终端人员信息
    public static final String getOperator(){
        String code = "admin";
        return code;
    }

    //缓存数据
    public static void saveShare(Context context, String key, String value , String user){
        SharedPreferences shared=context.getSharedPreferences(user, 0);
        SharedPreferences.Editor editor=shared.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String readShare(Context context,String key ,String user){
        String result=null;
        SharedPreferences shared=context.getSharedPreferences(user, 0);
        result=shared.getString(key, "-1");
        return result;
    }


    public static void showToast(Context context ,String name){
        Toast.makeText(context,name,Toast.LENGTH_LONG).show();
    }

}
