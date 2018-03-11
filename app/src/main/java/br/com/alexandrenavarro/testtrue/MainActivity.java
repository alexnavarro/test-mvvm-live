package br.com.alexandrenavarro.testtrue;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.alexandrenavarro.testtrue.databinding.ActivityMainBinding;
import br.com.alexandrenavarro.testtrue.viewmodel.BlogViewModel;

public class MainActivity extends AppCompatActivity {

    private BlogViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = ViewModelProviders.of(this, new ViewModelFactory(getApplication())).get(BlogViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(viewmodel);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewmodel.load10thCharacter(MainActivity.this);
            }
        });
    }
}