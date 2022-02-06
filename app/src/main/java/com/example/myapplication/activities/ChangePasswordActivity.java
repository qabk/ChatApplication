package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityChangePasswordBinding;
import com.example.myapplication.utilities.Constants;
import com.example.myapplication.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChangePasswordActivity extends AppCompatActivity {
    private PreferenceManager preferenceManager;
    private @NonNull ActivityChangePasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
    }

    private void setListeners() {
        binding.buttonConfirm.setOnClickListener(v -> changePassword());
    }
    private void showToast(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void changePassword()
    {
        {
            loading(true);
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Constants.KEY_COLLECTION_USERS)
                    .whereEqualTo(Constants.KEY_PASSWORD, binding.inputCurrentPassword.getText().toString())
                    .get()
                    .addOnCompleteListener(task ->{
                                if(task.isSuccessful() && task.getResult()!= null
                                        && task.getResult().getDocuments().size() > 0  &&
                                binding.inputNewPassword.getText().toString().equals(
                                        binding.inputConfirmPassword.getText().toString()))
                                {

                                    preferenceManager.putString(Constants.KEY_PASSWORD,binding.inputNewPassword.getText().toString());
                                    DocumentReference documentReference = database.collection(Constants.KEY_COLLECTION_USERS).document(
                                            preferenceManager.getString(Constants.KEY_USER_ID));
                                    documentReference.update(Constants.KEY_PASSWORD,binding.inputNewPassword.getText().toString())
                                            .addOnFailureListener(e -> showToast("update fail"));
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    loading(false);
                                    showToast("Unable to Change");
                                }
                            }
                    );
        }
    }

    private void loading(Boolean isLoading)
    {
        if(isLoading)
        {
            binding.buttonConfirm.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.buttonConfirm.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}