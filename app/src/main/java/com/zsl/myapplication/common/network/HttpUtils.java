package com.zsl.myapplication.common.network;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zsl on 2/29/16.
 */
public class HttpUtils {

    private String httpUrl;

    public void excute() {
    }

}

    class HttpClientRunnable implements Runnable{
        @Override
        public void run(){
            HttpClient hc = new DefaultHttpClient();
            //HttpGet实例
            HttpGet get = new HttpGet("http://www.android-study.com");
            //连接
            try {
                HttpResponse rp = null;
                try {
                    rp = hc.execute(get);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(rp.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
                {
                    InputStream is = rp.getEntity().getContent();
                    //处理数据
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class HttpConnectRunnable implements Runnable{

        /**
         * Starts executing the active part of the class' code. This method is
         * called when a thread is started that has been created with a class which
         * implements {@code Runnable}.
         */
        @Override
        public void run() {
            try {
                URL url = new URL("http://www.baidu.com");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("get");

                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    //处理数据

                    InputStream inputStream = urlConnection.getInputStream();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
}
