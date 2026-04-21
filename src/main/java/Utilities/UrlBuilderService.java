package Utilities;

import java.util.Map;

public class UrlBuilderService {

    public static String buildUrl(String baseUrl, String endpoint, Map<String, String> params) {
        String url = baseUrl + endpoint;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            url = url.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return url;
    }
}
