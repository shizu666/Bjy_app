package app.wms.tool;


import android.os.Handler;
import android.os.Message;
import android.system.Os;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import app.wms.empty.HttpApi;
import app.wms.empty.Product;

/**
 * Created by zhou on 2016/9/21.
 */
public class HttpUtils {

    //get请求
    public static void httpGET(final String ur , final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(ur);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setDoInput(true);
                    http.setConnectTimeout(1000*10);
                    http.setRequestMethod("GET");
                    if(http.getResponseCode()==200){
                        String result = HttpUtils.InputStreamToString(http.getInputStream());
                        Message message = Message.obtain();
                        message.arg1 = 1;
                        message.obj = result;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void getPost(final String ur , final Handler handler , final String params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(ur);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setDoInput(true);
                    http.setDoOutput(true);
                    http.setConnectTimeout(1000*10);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("Content-Length", params.length()+"");
                    http.setRequestProperty("Cache-Control", "max-age=0");
                    http.setRequestProperty("Origin", HttpApi.Ip);

                    OutputStream os = http.getOutputStream();
                    PrintWriter pw = new PrintWriter(os);
                    pw.write(params);
                    pw.flush();
                    pw.close();
                    if(http.getResponseCode()==200){
                        String result = HttpUtils.InputStreamToString(http.getInputStream());
                        Message message = Message.obtain();
                        message.arg1 = 2;
                        message.obj = result;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
