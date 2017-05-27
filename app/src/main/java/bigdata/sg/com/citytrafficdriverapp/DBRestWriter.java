package bigdata.sg.com.citytrafficdriverapp;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bigdata.sg.com.citytrafficdriverapp.database.DaoDatabase;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsData;
import bigdata.sg.com.citytrafficdriverapp.database.IDatabase;
import bigdata.sg.com.citytrafficdriverapp.rest.CityTrafficApi;
import bigdata.sg.com.citytrafficdriverapp.rest.Models.Answer;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DBRestWriter implements DataWriter {
    private static final String TAG = "DBRestWriter";

    private IDatabase mDatabase;
    private CityTrafficApi mCityTrafficApiService;

    public DBRestWriter(Context context) {
        mDatabase = new DaoDatabase(context);

        Gson gson = new GsonBuilder()
                .setDateFormat(Config.DATE_FORMAT)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CityTrafficApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mCityTrafficApiService = retrofit.create(CityTrafficApi.class);
    }

    @Override
    public void write(final GpsData data) {
        mCityTrafficApiService.sendGpsData(data.getQrValue(), data.getTime(), data.getLatitude(),
                data.getLongitude(), data.getAccuracy(), data.getSpeed())
                .enqueue(new Callback<Answer>() {
                    @Override
                    public void onResponse(Call<Answer> call, Response<Answer> response) {
                        if(!response.isSuccessful() || response.body().getError())
                        {
                            mDatabase.insert(data);
                        }
                        Log.d(TAG, response.body().getError().toString());
                    }

                    @Override
                    public void onFailure(Call<Answer> call, Throwable t) {
                        mDatabase.insert(data);
                    }
                });
    }

    @Override
    public void write(final AuthData data) {

        //TODO Add image
        RequestBody image = null;

        if(data.getLogin())
        {
            mCityTrafficApiService.login(data.getQrValue(), data.getTime(), data.getPicturePath(), image)
                    .enqueue(new Callback<Answer>() {
                        @Override
                        public void onResponse(Call<Answer> call, Response<Answer> response) {
                            if(!response.isSuccessful() || response.body().getError())
                            {
                                mDatabase.insert(data);
                            }
                            Log.d(TAG, response.body().getError().toString());
                        }

                        @Override
                        public void onFailure(Call<Answer> call, Throwable t) {
                            mDatabase.insert(data);
                        }
                    });
        } else {
            mCityTrafficApiService.logout(data.getQrValue(), data.getTime())
                    .enqueue(new Callback<Answer>() {
                        @Override
                        public void onResponse(Call<Answer> call, Response<Answer> response) {
                            if(!response.isSuccessful() || response.body().getError())
                            {
                                mDatabase.insert(data);
                            }
                            Log.d(TAG, response.body().getError().toString());
                        }

                        @Override
                        public void onFailure(Call<Answer> call, Throwable t) {
                            mDatabase.insert(data);
                        }
                    });
        }
    }
}
