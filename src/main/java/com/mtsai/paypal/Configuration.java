package com.mtsai.paypal;

import java.util.HashMap;
import java.util.Map;

/**
 *  For a full list of configuration parameters refer in wiki page.(https://github.com/paypal/sdk-core-java/blob/master/README.md)
 */
public class Configuration {
    // Creates a configuration map containing credentials and other required configuration parameters.
    public static final Map<String,String> getAcctAndConfig(){
        Map<String,String> configMap = new HashMap<String,String>();
        configMap.putAll(getConfig());
        // Account Credential
        return configMap;
    }

    public static final Map<String,String> getConfig(){
        Map<String,String> configMap = new HashMap<String,String>();

        // Endpoints are varied depending on whether sandbox OR live is chosen for mode
        //configMap.put("mode", "sandbox");


        // These values are defaulted in SDK. If you want to override default values, uncomment it and add your value.
        // configMap.put("http.ConnectionTimeOut", "5000");
        // configMap.put("http.Retry", "2");
        // configMap.put("http.ReadTimeOut", "30000");
        // configMap.put("http.MaxConnection", "100");
        return configMap;
    }
}