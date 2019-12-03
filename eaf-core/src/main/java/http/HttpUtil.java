package http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import requestbean.person.PersonGroupListRequestBean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpUtil{

    public JSONObject doPost(String url, PersonGroupListRequestBean personGroupListRequestBean){
        JSONObject responseJson = null;

        //发送请求的URL
//        String url = "http://10.24.158.188/PERSON/personGroup/list";

        //编码格式
        String charset = "UTF-8";

        //请求内容
        //使用JSON.toJSONString 将requestbean转换成jsonstring
        String requestBody = JSON.toJSONString(personGroupListRequestBean);

        //使用帮助类HttpClients创建CloseableHttpClient对象.
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //HTTP请求类型创建HttpPost实例
        HttpPost post = new HttpPost(url);

        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.setHeader("Authorization", "Basic d2FuZ2NoZW5nLDA6bHVtbmc5");

        //组织数据
        StringEntity entity = null;
        try {
            entity = new StringEntity(requestBody);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //设置编码格式
        entity.setContentEncoding(charset);

        //设置数据类型
        entity.setContentType("application/json");

        //对于POST请求,把请求体填充进HttpPost实体.
        post.setEntity(entity);

        //通过执行HttpPost请求获取CloseableHttpResponse实例 ,从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等.
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理 
        HttpEntity responseEntity = response.getEntity();

        String resData = null;
        try {
            resData = EntityUtils.toString(responseEntity);
            System.out.println(resData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将response转换成JSONObj
        responseJson = JSONObject.parseObject(resData);


        //最后关闭HttpClient资源.
        try {
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseJson;

    }

    public String doPost(CloseableHttpClient closeableHttpClient,String path,PersonGroupListRequestBean personGroupListRequestBean){
        JSONObject responseJson = null;

        //发送请求的URL
        String url = "http://www.stg.intersense.sensetime.com"+path;

        //编码格式
        String charset = "UTF-8";

        //请求内容
        //使用JSON.toJSONString 将requestbean转换成jsonstring
        String requestBody = JSON.toJSONString(personGroupListRequestBean);

        //使用帮助类HttpClients创建CloseableHttpClient对象.
//        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //HTTP请求类型创建HttpPost实例
        HttpPost post = new HttpPost(url);

        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.setHeader("Authorization", "Basic d2FuZ2NoZW5nLDA6aWpkOXFu");

        //组织数据
        StringEntity entity = null;
        try {
            entity = new StringEntity(requestBody);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //设置编码格式
        entity.setContentEncoding(charset);

        //设置数据类型
        entity.setContentType("application/json");

        //对于POST请求,把请求体填充进HttpPost实体.
        post.setEntity(entity);

        //通过执行HttpPost请求获取CloseableHttpResponse实例 ,从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等.
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理 
        HttpEntity responseEntity = response.getEntity();

        String resData = null;
        try {
            resData = EntityUtils.toString(responseEntity);
            System.out.println(resData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将response转换成JSONObj
//        responseJson = JSONObject.parseObject(resData);


        //最后关闭HttpClient资源.
        try {
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return responseJson;
        return resData;

    }







}
