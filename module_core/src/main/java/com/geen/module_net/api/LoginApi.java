package com.geen.module_net.api;
import com.geen.module_net.bean.response.LoginResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author youtui
 */
public interface LoginApi {

    @FormUrlEncoded
    @POST("ucapi/userlogin/login")
    Observable<LoginResponse> loginApp(@Field("loginName") String loginName, @Field("userPass") String pwd);

    @POST("ucapi/userlogin/loginByToken")
    Observable<LoginResponse> loginAppByToken();

}
