package app.wms.tool;


import android.os.Handler;
import android.os.Message;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

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
                    http.setDoOutput(true);
                    http.setConnectTimeout(1000*10);
                    http.setRequestMethod("GET");
                    if(http.getResponseCode()==200){
                        String result = HttpUtils.InputStreamToString(http.getInputStream());
                        Message message = Message.obtain();
                        message.arg1=1;
                        message.obj = result;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void httpClentGet(final String url,final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet get = new HttpGet();
                    get.setURI(new URI(url));
                    HttpResponse httpResponse = httpClient.execute(get);
                    StatusLine statusLine = httpResponse.getStatusLine();
                    int code = statusLine.getStatusCode();
                    if(code==200){
                        HttpEntity entity = httpResponse.getEntity();
                        InputStream is = entity.getContent();
                        String rusult = HttpUtils.InputStreamToString(is);
                        Message message = Message.obtain();
                        message.obj = rusult;
                        message.arg1 = 2;
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
