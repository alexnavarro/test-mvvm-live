package br.com.alexandrenavarro.testtrue.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

import br.com.alexandrenavarro.testtrue.repository.TrueBlogRepository;

/**
 * Created by alexa on 11/03/2018.
 */

public class BlogViewModel extends AndroidViewModel {

    public final ObservableField<String> textRequest1 = new ObservableField<>();
    public final ObservableField<String> textRequest2 = new ObservableField<>();
    public final ObservableField<String> textRequest3 = new ObservableField<>();
    private TrueBlogRepository repository;

    public BlogViewModel(@NonNull Application application, TrueBlogRepository repository) {
        super(application);
        this.repository = repository;
    }

    public void load10thCharacter(LifecycleOwner lifecycleOwner) {
        MutableLiveData<String> stringMutableLiveData = repository.load10hCharacter();
        stringMutableLiveData.observe(lifecycleOwner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textRequest1.set(s);
            }
        });

        repository.loadEver10hCharacter().observe(lifecycleOwner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textRequest2.set(s);
            }
        });

        repository.countSameWorkOnPage().observe(lifecycleOwner, new Observer<Map<String, Integer>>() {
            @Override
            public void onChanged(@Nullable Map<String, Integer> map) {
                StringBuilder builder = new StringBuilder();
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    builder.append(entry.getKey()).append(" total -> ").append(entry.getValue()).append("\n");
                }

                textRequest3.set(builder.toString());
            }
        });
    }
}