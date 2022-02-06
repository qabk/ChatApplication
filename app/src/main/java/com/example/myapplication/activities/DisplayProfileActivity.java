package com.example.myapplication.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityDisplayProfileBinding;
import com.example.myapplication.utilities.Constants;
import com.example.myapplication.utilities.PreferenceManager;

public class DisplayProfileActivity extends AppCompatActivity {
    private ActivityDisplayProfileBinding binding; // bien databinding
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        loadUserDetails();
        setListeners();

    }

    private void setListeners()
    {
        binding.buttonBackMain.setOnClickListener(v -> backMain());
        binding.buttonChangeUserName.setOnClickListener(v -> changeName());
        binding.buttonChangePassword.setOnClickListener(v -> changePassword());
       binding.imageProfile.setOnClickListener(v -> changeImage());
    }

    private void changePassword()
    {
        startActivity(new Intent(getApplicationContext(),ChangePasswordActivity.class));
    }

    private void changeName() {
        startActivity(new Intent(getApplicationContext(),ChangeNameActivity.class));

    }

    private void changeImage()
    {
        startActivity(new Intent(getApplicationContext(),ChangeImageActivity.class));
    }

    private void backMain()
    {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }

    private void loadUserDetails()
    {
        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE),Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageProfile.setImageBitmap(bitmap);
        binding.displayName.setText(preferenceManager.getString(Constants.KEY_NAME));

    }
}