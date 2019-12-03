package guns;

import Base.BaseApiTest;
import appcaller.GunsAppCaller;
import com.google.gson.Gson;
import dataProvider.guns.GunsMgrLoginTestData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import requestbean.guns.GunsMgrLoginRequestBean;
import responsebean.basic.BaseRes;

public class GunsMgrLoginTest extends BaseApiTest {

    @DataProvider(name = "dataPrdGetGunsMgrLoginTest")
    public Object[][] getDataProviderGetGunsMgrLoginDataProvider( ){
        //通过context.class 获取PersonGroupListTestData.genPersonGroupListTestData
        GunsMgrLoginTestData gunsMgrLoginTestData = new GunsMgrLoginTestData();
        return gunsMgrLoginTestData.genGunsMgrLoginTestData();
    }


    @Test(dataProvider = "dataPrdGetGunsMgrLoginTest")
    public void testGetGunsMgrLoginTest(String caseName, GunsMgrLoginRequestBean gunsMgrLoginRequestBean, String httpResponseCode){
        logger.info("--------------"+caseName+" start--------------");
        String responseString = new GunsAppCaller().getGunsMgrLogin(gunsMgrLoginRequestBean);
        BaseRes loginResponse = gson.fromJson(responseString, BaseRes.class);
        Assert.assertEquals(loginResponse.getCode(),httpResponseCode);
        logger.info("--------------"+caseName+" end--------------");
    }
}
