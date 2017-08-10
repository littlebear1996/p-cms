package com.chinaredstar.cms.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by mdc on 2016/11/22.
 */
public class HttpClientUtil {

    static final String Encode = "UTF-8";

    public static String httpPost(String url, Map<String, String> params) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> formParams = Lists.newArrayList();
            NameValuePair nameValuePair;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                //System.out.println(entry.getKey()+"--->"+entry.getValue());
                nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                formParams.add(nameValuePair);
            }

            httppost.setEntity(new UrlEncodedFormEntity(formParams,Encode));
            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpClient.execute(httppost);
            try {
                //System.out.println("----------------------------------------");
                //System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }

                String resString = EntityUtils.toString(resEntity, Encode);

                System.out.println(resString);

                EntityUtils.consume(resEntity);

                return resString;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {

            }
        }

        return "";
    }


    public static String httpGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resString = "";
        try {
            HttpGet httpget = new HttpGet(url);
            System.out.println("executing request " + httpget.getURI());
            CloseableHttpResponse response = httpClient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                }
                System.out.println("------------------------------------");
                resString  = EntityUtils.toString(entity, Encode);

            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resString;
    }

    public static void main(String[] args) throws Exception {
        Map params = Maps.newHashMap();
        params.put("cdkey","6SDK-EMY-6666-RGZRQ");
        params.put("password","211188");
        //params.put("phone","15800904710,15216742630");
        params.put("phone","18703876065");

        String message = "我也曾经憧憬过,只能靠一首歌真的在说我,其实都没有.....";
        message = "【红星美凯龙】" + message + ",退订回N";

        //params.put("message", URLEncoder.encode(message, Encode));
        params.put("message",message);

        String url = "http://sdktaows.eucp.b2m.cn:8080/sdkproxy/sendtimesms.action";
        System.out.println("<-----res----->" + httpPost(url, params));


/*        String message = "新年快乐新年快乐新年快乐新年快乐新年快乐";
        message = "【红星美凯龙】" + message + ",退订回N";
        message=URLEncoder.encode(message, Encode);
        String url = "http://sdktaows.eucp.b2m.cn:8080/sdkproxy/sendtimesms.action?cdkey=6SDK-EMY-6666-RGZRQ&password=211188&phone=18703876065&message="+message;
        httpGet(url);*/
    }


}
