package com.finance.util;

import com.finance.util.tradeutil.CharsetEnum;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author wuwenming
 */
public class HttpUtils {

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

    public static String get(String url) {

        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static String post(String url, Map<String, String> paramsMap, CharsetEnum charsetEnum) {

        HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象
        CloseableHttpResponse response = null;
        String result = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Set<Entry<String, String>> paramsSet = paramsMap.entrySet();
        Iterator<Entry<String, String>> it = paramsSet.iterator();
        while (it.hasNext()) {
            Entry<String, String> param = it.next();
            params.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }

        try {
            httpRequst.setEntity(new UrlEncodedFormEntity(params,
                    DEFAULT_CHARSET));
            response = httpclient.execute(httpRequst);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity, charsetEnum.getCode());// 取出应答字符串
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
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

    public static String post(String url, Map<String, String> paramsMap) {
        return post(url, paramsMap, CharsetEnum.GBK);
    }

    public static String post(String url, String body) {

        HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象
        CloseableHttpResponse response = null;
        String result = "";
        try {
            httpRequst.setEntity(new StringEntity(body, DEFAULT_CHARSET));
            response = httpclient.execute(httpRequst);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity, DEFAULT_CHARSET);// 取出应答字符串
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (httpRequst != null) {
                httpRequst.releaseConnection();
            }
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

    public static String post(String url, String body, Map<String, String> headerMap) {

        HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象
        CloseableHttpResponse response = null;
        String result = "";
        try {
            if (!CollectionUtils.isEmpty(headerMap)) {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpRequst.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpRequst.setEntity(new StringEntity(body, ContentType.create(ContentType.APPLICATION_JSON.getMimeType(), Consts.UTF_8)));
            response = httpclient.execute(httpRequst);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity, DEFAULT_CHARSET);// 取出应答字符串
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (httpRequst != null) {
                httpRequst.releaseConnection();
            }
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

    public static byte[] postMarket(String url, byte[] body, Map<String, String> headerMap) {

        HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象
        CloseableHttpResponse response = null;
        byte[] result;
        try {
            if (!CollectionUtils.isEmpty(headerMap)) {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpRequst.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpRequst.setEntity(new ByteArrayEntity(body, ContentType.APPLICATION_OCTET_STREAM));
            response = httpclient.execute(httpRequst);
            System.out.println(response);
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toByteArray(httpEntity);// 取出应答字符串
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (httpRequst != null) {
                httpRequst.releaseConnection();
            }
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

//    public static void main(String[] args) {
//        String s = get("http://hq.sinajs.cn/list=f_260108");
//        System.out.println(s);
//        String[] strings = s.split(",");
//        System.out.println(strings[1]);
//    }

}
