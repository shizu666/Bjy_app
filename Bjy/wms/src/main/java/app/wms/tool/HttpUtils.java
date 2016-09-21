package app.wms.tool;


import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhou on 2016/9/21.
 */
public class HttpUtils {

    //get请求
    public static void httpGET(String ur){
        try {
            URL url = new URL(ur);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setConnectTimeout(5000);
            http.setRequestMethod("GET");
            if(http.getResponseCode()==200){
                String result = HttpUtils.InputStreamToString(http.getInputStream());
                Log.i("result",result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static String InputStreamToString(InputStream is){
        String result = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int index = 0;
        try {
            while ((index = is.read(buffer))!= -1){
                baos.write(buffer,0,index);
                baos.flush();
            }
            result = new String(baos.toByteArray(),"utf-8");
            // result = baos.toString();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
