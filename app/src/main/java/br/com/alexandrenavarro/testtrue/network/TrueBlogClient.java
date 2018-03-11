package br.com.alexandrenavarro.testtrue.network;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexa on 11/03/2018.
 */

public class TrueBlogClient {

    private Retrofit retrofit =  new Retrofit.Builder()
            .baseUrl("https://blog.truecaller.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void load10hCharacter(final MutableLiveData<String> liveData){
        retrofit.create(TrueBlogService.class).getHtml().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    String result = null;
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        Log.d(TrueBlogClient.class.getSimpleName(), e.getMessage());
                    }
                    if(!TextUtils.isEmpty(result) && result.length() >=10){
                        liveData.postValue(Character.toString(result.charAt(9)));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TrueBlogClient.class.getSimpleName(), t.getMessage());
            }
        });
    }

    public void loadEver10hCharacter(final MutableLiveData<String> liveData){
        retrofit.create(TrueBlogService.class).getHtml().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    String result = null;
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        Log.d(TrueBlogClient.class.getSimpleName(), e.getMessage());
                    }
                    if(!TextUtils.isEmpty(result) && result.length() >=10){
                        StringBuilder builder = new StringBuilder();

                        for(int i = 0; i < result.length(); i++){
                            if((i + 1) % 10 == 0){
                                builder.append(result.charAt(i)).append(" ");
                            }
                        }

                        liveData.postValue(builder.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TrueBlogClient.class.getSimpleName(), t.getMessage());
            }
        });
    }

    public void countSameWorkOnPage(final MutableLiveData<Map<String, Integer>> liveData){
        retrofit.create(TrueBlogService.class).getHtml().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    String result = null;
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        Log.d(TrueBlogClient.class.getSimpleName(), e.getMessage());
                    }
                    if(!TextUtils.isEmpty(result) && result.length() >=10){
                        Map<String, Integer> count = new HashMap<>();
                        String[] split = result.split("\\s+");

                        for(String string : split){
                            String keyLowerCase = string.toLowerCase();
                            if(count.containsKey(keyLowerCase)){
                                count.put(keyLowerCase, count.get(keyLowerCase) + 1);
                            }else {
                                count.put(keyLowerCase,1);
                            }
                        }

                        liveData.postValue(count);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TrueBlogClient.class.getSimpleName(), t.getMessage());
            }
        });

    }
}