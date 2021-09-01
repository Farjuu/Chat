package dev.farjana.chat.MessageData.remote;

/**
 * Created by Farjana on 1/24/2018.
 */

public class ApiUtils {
    private ApiUtils(){}

        public static final String Base_URL = "http://hi-gateway.herokuapp.com/";

        public static APIService getAPIService(){
            return RetrofitClient.getClient(Base_URL).create(APIService.class);
    }


}
