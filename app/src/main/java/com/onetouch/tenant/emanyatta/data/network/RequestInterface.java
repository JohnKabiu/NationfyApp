package com.onetouch.tenant.emanyatta.data.network;

import com.onetouch.tenant.emanyatta.data.local.model.Average;
import com.onetouch.tenant.emanyatta.data.local.model.Bill;
import com.onetouch.tenant.emanyatta.data.local.model.BillHistory;
import com.onetouch.tenant.emanyatta.data.local.model.LogIn;
import com.onetouch.tenant.emanyatta.data.local.model.MpesaResponse;
import com.onetouch.tenant.emanyatta.data.local.model.OtpCheck;
import com.onetouch.tenant.emanyatta.data.local.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("Api/vacant")
    Call<JSONResponse> getJSON();

    @FormUrlEncoded
    @POST("Api/login")
    Call<LogIn> getLoginResponse(@Field("mobile") String mobile, @Field("password") String password, @Field("otp") int otp);

    @FormUrlEncoded
    @POST("Api/otpcheck")
    Call<OtpCheck> verifyOtp(@Field("otp") String otp, @Field("mobile") String mobile);

    @POST("Api/mybill")
    Call<List<Bill>> getBills(@Header("Authorization") String token);

    @POST("Api/historyPayment")
    Call<List<BillHistory>> getHistoryBills();

    @POST("Api/bearer")
    Call<User> getUser();

    @FormUrlEncoded
    @POST("Api/buy")
    Call<MpesaResponse> stkPush(@Field("msisdn") String mobile, @Field("tenantaccount") String account, @Field("cost") int amount);

    @POST("Api/totalsum")
    Call<Average> getAverage();
}
