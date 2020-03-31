package com.finance.util;

/**
 * @author : shenhao
 * @date : 2020/3/26 9:39
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpPost {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static PoolingHttpClientConnectionManager cm;
    private static CloseableHttpClient httpclient;

    static {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        httpclient = HttpClients.custom().setConnectionManager(cm).build();


    }


    public static String post(String url, String body, Map<String, String> headerMap) {

        org.apache.http.client.methods.HttpPost httpRequst = new org.apache.http.client.methods.HttpPost(url);// 创建HttpPost对象
        httpRequst.setHeader("Content-type", "application/x-www-form-urlencoded");
        CloseableHttpResponse response = null;
        String result = "";
        try {
            if (!CollectionUtils.isEmpty(headerMap)) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpRequst.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpRequst.setEntity(new StringEntity(body, ContentType.create(ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), Consts.UTF_8)));
            response = httpclient.execute(httpRequst);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity, DEFAULT_CHARSET);// 取出应答字符串
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            httpRequst.releaseConnection();
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    public static String postRequest(String url, byte[] byteArr) {
        return null;
    }

//    public static void main(String[] args) {
//        String url = "http://byt-gateway:9999/auth/oauth/token";
//
//        Map<String, String> map = new HashMap<String, String>();
//        Map<String, String> map1 = new HashMap();
//        map.put("Authorization", "Basic Ynl0OmJ5dA==");
//        map.put("isToken", "false");
//        map.put("grant type", "password");
//        map1.put("grant type", "password");
//        map1.put("username", "admin");
//        map1.put("password", "123456");
//        map1.put("scope", "server");
//        String body = JSON.toJSONString(map1);
//        String result = post(url, body, map);
//
//        System.out.println("result == " + result);
//
//        // HttpHeaders headers = new HttpHeaders();
//        //        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        //        headers.set("Authorization",authorizationInfo);
//        //        headers.set("isToken","false");
//        //        headers.setAccept(new LinkedList(){{add(MediaType.ALL);}});
//        //        MultiValueMap<String, String> postParameters= new LinkedMultiValueMap();
//        //        postParameters.add("grant-type",grantType);
//        //        postParameters.add("username",username);
//        //        postParameters.add("password",password);
//        //        postParameters.add("scope",scope);
//        //        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(postParameters, headers);
//        //        ResponseEntity response = restTemplate.postForEntity(authUrl,requestEntity,String.class);
//        //
//        //        url: http://byt-gateway:9999/auth/oauth/token
//        //        authorization: Basic Ynl0OmJ5dA==
//        //        grant-type: password
//        //        username: admin
//        //        password: 123456
//        //        scope: server
//    }

}
