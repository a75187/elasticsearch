package org.com.es.user.utils;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
;

/**
 * @Author linxuanzhan
 * @Date 2019/7/26 14:58
 */
public class EsClientUtil {
    private static RestHighLevelClient client=null;


    public static RestHighLevelClient getCilent(){
        if (client == null) {
            synchronized (EsClientUtil.class){
                if (client == null) {
                    client = new RestHighLevelClient(
                            RestClient.builder(
                                    new HttpHost("172.16.62.139", 9200, "http")));
                }
            }
        }
        return client;
    }
}
