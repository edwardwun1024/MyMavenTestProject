package app;

import http.HttpApi;
import http.HttpMethod;

public class GunsApp {
    public static final String GUNS_MGR_LOGIN_PATH = "/GUNS/mgr/login";
    public static final HttpApi POST_GUNS_MGR_LOGIN = new HttpApi(GUNS_MGR_LOGIN_PATH, HttpMethod.POST.getName());

}
