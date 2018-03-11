package br.com.alexandrenavarro.testtrue;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import br.com.alexandrenavarro.testtrue.repository.TrueBlogRepository;
import br.com.alexandrenavarro.testtrue.viewmodel.BlogViewModel;

/**
 * Created by alexa on 11/03/2018.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application mApplication;

    public ViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BlogViewModel.class)) {
            //noinspection unchecked
            return (T) new BlogViewModel(mApplication, new TrueBlogRepository());
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}