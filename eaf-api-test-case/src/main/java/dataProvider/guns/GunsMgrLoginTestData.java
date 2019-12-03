package dataProvider.guns;

import common.AES256Utils;
import requestbean.guns.GunsMgrLoginRequestBean;

public class GunsMgrLoginTestData {

    public Object[][] genGunsMgrLoginTestData(){
        String name = "wangcheng";
        String decPwd = "Edward2019@";
        String accountType = "0";
        String encPwd = null;
        String dectoPwd = null;

        try {
            encPwd = AES256Utils.encrypt(decPwd.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        GunsMgrLoginRequestBean gunsMgrLoginRequestBean = new GunsMgrLoginRequestBean();
        gunsMgrLoginRequestBean.setUsername(name);
        gunsMgrLoginRequestBean.setPassword(encPwd);
        gunsMgrLoginRequestBean.setAccountType(accountType);

        return new Object[][]{
                {"用户登陆studio",gunsMgrLoginRequestBean,"0000"}
        };
    }
}
