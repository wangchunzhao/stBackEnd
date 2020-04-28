package com.qhc.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
//	private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static String getUrl(String url, Map<String, String> headers)
    {
    	logger.info("GET请求调用!" + url.toString());
        HttpURLConnection httpUrlConn = null;
        InputStream inputStream = null;
        try {
            URL geturl=new URL(url);
            httpUrlConn = (HttpURLConnection) geturl.openConnection();
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("GET");

            for (Entry<String, String> entry : headers.entrySet()) {
            	logger.info("[Header]  key= " + ((String) entry.getKey())
                        + " ,value= " + ((String) entry.getValue()));
                httpUrlConn.setRequestProperty((String) entry.getKey(),
                        (String) entry.getValue());
            }

            inputStream = httpUrlConn.getInputStream();
            String reqmsg = IOUtils.toString(inputStream, "UTF-8");
            logger.info("GET调用成功，返回：" + reqmsg);
            return reqmsg;
        } catch (Exception e){
            return  null;
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    return null;
                }
                inputStream = null;
            }

            if (httpUrlConn != null)
                httpUrlConn.disconnect();
        }
    }
    
    /**
     *调用内部系统post接口
     */
    public static  String postbody(String tourl,String body) 
        throws Exception {
      return postbody(tourl, body, 120);
    }
    
    /**
     * 调用内部系统post接口
     * @param tourl
     * @param body
     * @param timeout second
     * @return
     * @throws Exception
     */
    public static  String postbody(String tourl,String body, int timeout)
            throws Exception {
        URL  url=null;
        logger.info("POST请求调用：" + tourl.toString());
        logger.info("POST请求数据：" + body);

        HttpURLConnection httpUrlConn = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            url = new URL(tourl);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            //当接口访问需要用户名密码的情况
//            String plainCredentials = "ST2SAP:Dalian11!";
//            String base64Credentials = new String(org.apache.tomcat.util.codec.binary.Base64.encodeBase64(plainCredentials.getBytes()));
//            httpUrlConn.setRequestProperty("Authorization", "Basic " + base64Credentials);
            httpUrlConn.setConnectTimeout(timeout * 1000); // 毫秒
            httpUrlConn.setReadTimeout(timeout * 1000); // 毫秒
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("POST");

            if (body != null) {
                byte[] b = body.getBytes("UTF-8");
                httpUrlConn.setRequestProperty("Charset", "UTF-8");
                httpUrlConn.setRequestProperty("Content-Type", "application/json");
                httpUrlConn.setRequestProperty("Content-length", String.valueOf(b.length));

                outputStream = httpUrlConn.getOutputStream();
                outputStream.write(b);
                outputStream.close();
            }

            Integer status = httpUrlConn.getResponseCode();
            if (status == 200) {
            	inputStream = httpUrlConn.getInputStream();
                String reqmsg = IOUtils.toString(inputStream, "UTF-8");
                logger.info("POST调用成功，返回：" + reqmsg);
                return reqmsg;
            } else {
            	inputStream = httpUrlConn.getErrorStream();
                String reqmsg = IOUtils.toString(inputStream, "UTF-8");
                logger.info("POST调用失败，返回：" + reqmsg);
            	throw new Exception(reqmsg);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("error");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

            if (httpUrlConn != null)
                httpUrlConn.disconnect();
        }
    }

    /**
     * 1原始post方法
     * @param strURL
     * @param params
     * @return
     */
    public static String postjson(String strURL, String params) {
    	logger.info("post方法：url=" + strURL + ",params=" + params);
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            int length = (int) connection.getContentLength();// 获取长度
            InputStream is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }

}
