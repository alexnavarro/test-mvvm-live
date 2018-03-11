package br.com.alexandrenavarro.testtrue.repository;

import android.arch.lifecycle.MutableLiveData;

import java.util.Map;

import br.com.alexandrenavarro.testtrue.network.TrueBlogClient;

/**
 * Created by alexa on 11/03/2018.
 */

public class TrueBlogRepository {

    public MutableLiveData<String> load10hCharacter() {
        MutableLiveData mutableLiveData= new MutableLiveData<String>();
        new TrueBlogClient().load10hCharacter(mutableLiveData);
        return mutableLiveData;
    }

    public MutableLiveData<String> loadEver10hCharacter(){
        MutableLiveData mutableLiveData= new MutableLiveData<String>();
        new TrueBlogClient().loadEver10hCharacter(mutableLiveData);
        return mutableLiveData;
    }

    public MutableLiveData<Map<String, Integer>> countSameWorkOnPage(){
        MutableLiveData mutableLiveData= new MutableLiveData<Map<String, Integer>>();
        new TrueBlogClient().countSameWorkOnPage(mutableLiveData);
        return mutableLiveData;
    }
}