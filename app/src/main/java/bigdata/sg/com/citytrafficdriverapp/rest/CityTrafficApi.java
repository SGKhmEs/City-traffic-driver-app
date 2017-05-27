package bigdata.sg.com.citytrafficdriverapp.rest;

import bigdata.sg.com.citytrafficdriverapp.rest.Models.Answer;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface CityTrafficApi {
    public static final String BASE_URL = "http://mockable.io/";

    @Multipart
    @POST("http://demo3000095.mockable.io/")
    Call<Answer> login(@Part("qr") String qrValue, @Part("time") String time,
                       @Part("name") String name, @Part("image") RequestBody image);

    @POST("http://demo3000095.mockable.io/")
    Call<Answer> logout(@Query("qr") String qrValue, @Query("time") String time);

    @POST("http://demo3000095.mockable.io/")
    Call<Answer> sendGpsData(@Query("qr") String qrValue, @Query("time") String time,
                             @Query("latitude") double latitude, @Query("longitude") double longitude,
                             @Query("accuracy") float accuracy, @Query("speed") float speed);
}
