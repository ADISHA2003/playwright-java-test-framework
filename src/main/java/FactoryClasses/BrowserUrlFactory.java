package FactoryClasses;

import Constants.ApiDomain;
import Constants.PageUrl;

public class BrowserUrlFactory {


    String qaEnv = "https://qaapp.vccedge.com/";
    String uatEnv = "https://uatapp.vccedge.com/";
    String prodEnv = "https://www.vccedge.com/";

    public String getDomain(String env) {
        switch (env.toLowerCase()) {
            case "qa":
                return qaEnv;
            case "uat":
                return uatEnv;
            case "prod":
                return prodEnv;
            default:
                throw new IllegalArgumentException("Invalid environment: " + env);
        }
    }


}
