import appcaller.PersonAppCaller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import dataProvider.person.PersonGroupListTestData;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import responsebean.person.PageRes;
import requestbean.person.PersonGroupListRequestBean;
import responsebean.person.PersonGroupListRes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class JsonTest {


    @DataProvider(name = "getPersonGroupListDataProvider")
    public Object[][] getDataProviderPersonGroupListDataProvider( ){
        //通过context.class 获取PersonGroupListTestData.genPersonGroupListTestData
        PersonGroupListTestData personGroupListTestData = new PersonGroupListTestData();
        return personGroupListTestData.genPersonGroupListTestData();
    }

    //获取groupId
    //框架搭建
    @Test(dataProvider = "getPersonGroupListDataProvider")
    public void testGetPersonGroupListTest(String caseName, PersonGroupListRequestBean personGroupListRequestBean, String httpResponseCode){

        String responseString = new PersonAppCaller().getPostPersonGroupList(personGroupListRequestBean);
        PageRes<PersonGroupListRes> pageRes = new Gson().fromJson(responseString,PageRes.class);


    }



    @Test
    public void doPostAndParam() {

        JSONObject responseJson = null;

        //发送请求的URL
        String url = "http://10.24.158.188/DEVICE/device/list";

        //编码格式
        String charset = "UTF-8";

        //请求内容
        String requestBody = "{\"current\":1,\"size\":1001,\"productType\":[],\"deviceType\":[],\"deviceCnName\":\"wc-pass-device-20191125\",\"deviceSerial\":\"\",\"sts\":\"\"}";

        //使用帮助类HttpClients创建CloseableHttpClient对象.
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //HTTP请求类型创建HttpPost实例
        HttpPost post = new HttpPost(url);

        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.setHeader("Authorization", "Basic d2FuZ2NoZW5nLDA6ejJ1MHA1");

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


        //#############################处理response#################

        List<DeviceListRes> deviceListResList = new ArrayList<DeviceListRes>();
        deviceListResList = JSON.parseObject( responseJson.get("data").toString(),new TypeReference<List<DeviceListRes>>(){});
        System.out.println(deviceListResList);

        //##########################组装devicerequest################

        List<PolicyDevices> policyDevicesList = new ArrayList<PolicyDevices>();
        for (int i=0;i<deviceListResList.size();i++){
            PolicyDevices policyDevices = new PolicyDevices();
            policyDevices.setDeviceId(deviceListResList.get(i).getDid());
            policyDevices.setDeviceSerial("");
            policyDevices.setDeviceType("");
            policyDevicesList.add(policyDevices);
        }
//        PolicyDevices policyDevices = new PolicyDevices("1197406116745580545","","");
//        policyDevicesList.add(policyDevices);

        String jsonString = JSON.toJSONString(policyDevicesList);
        System.out.println("request deviceInfoList : "+jsonString);


    }

}
