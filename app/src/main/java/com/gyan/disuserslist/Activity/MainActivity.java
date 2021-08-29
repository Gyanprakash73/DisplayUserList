package com.gyan.disuserslist.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gyan.disuserslist.Adapter.UserAdapter;
import com.gyan.disuserslist.Instance.ApiController;
import com.gyan.disuserslist.Model.Data;
import com.gyan.disuserslist.Model.UserModel;
import com.gyan.disuserslist.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //click next button
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SecondPageActivity.class));
            }
        });

        //Set recyclerView
        binding.recycle.setHasFixedSize(true);
        binding.recycle.setLayoutManager(new LinearLayoutManager(this));

        //ProgressBar
        binding.progressBar.setVisibility(View.VISIBLE);

        //retrofit
        Call<UserModel> call= ApiController.getInstance().getApi().getPage1();

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                binding.progressBar.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    UserModel model =response.body();
                    List<Data> dataList=model.getData();
                    binding.recycle.setAdapter(new UserAdapter(dataList, MainActivity.this));
                }
                else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}