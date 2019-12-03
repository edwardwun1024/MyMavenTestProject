package appcaller;

import app.GunsApp;
import com.alibaba.fastjson.JSONObject;
import http.HttpClientUtil;
import http.HttpUtil;
import http.common.HttpConfig;
import http.common.HttpHeader;
import http.common.HttpMethods;
import http.exception.HttpProcessException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import requestbean.guns.GunsMgrLoginRequestBean;

public class GunsAppCaller {
    public CloseableHttpClient getClient(){
        return HttpClients.createDefault();
    }

    public String getGunsMgrLogin(GunsMgrLoginRequestBean gunsMgrLoginRequestBean){
        return this.getPostgetGunsMgrLogin(this.getClient(),gunsMgrLoginRequestBean);
    }
    private String getPostgetGunsMgrLogin(CloseableHttpClient closeableHttpClient,GunsMgrLoginRequestBean gunsMgrLoginRequestBean){
        HttpHeader header = HttpHeader.custom();
        header.contentType("application/json;charset=UTF-8");

        HttpConfig httpConfig = HttpConfig.custom();
        httpConfig.headers(header.build());
        httpConfig.method(HttpMethods.POST);
        httpConfig.url("http://www.stg.intersense.sensetime.com/GUNS/mgr/login");
        httpConfig.json(JSONObject.toJSONString(gunsMgrLoginRequestBean));
        String result = null;
        try {
            result = HttpClientUtil.post(httpConfig);
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
