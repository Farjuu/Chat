package dev.farjana.chat.MessageData.remote;

import dev.farjana.chat.MessageData.model.Messages;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by Farjana on 1/24/2018.
 */

public interface APIService {

    @POST("/api/")
    @FormUrlEncoded
    Observable<Messages> sendMessage(@Field("message") String message,
                                     @Field("numbers") String number);


}
