package com.sajorahasan.skeleton.network;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    @FormUrlEncoded
    @POST("questions")
    Observable<String> getQuestionPostMap(@FieldMap Map<String, Object> stringMap);

    @FormUrlEncoded
    @POST("questions")
    Observable<String> getQuestionPost(@Field("page") int page,
                                       @Field("pagesize") int pagesize,
                                       @Field("order") String order,
                                       @Field("sort") String sort,
                                       @Field("tagged") String tagged,
                                       @Field("size") String size);

    @GET("questions")
    Observable<String> getQuestionGet(@Query("page") int page,
                                      @Query("pagesize") int pagesize,
                                      @Query("order") String order,
                                      @Query("sort") String sort,
                                      @Query("tagged") String tagged,
                                      @Query("site") String size);

}
