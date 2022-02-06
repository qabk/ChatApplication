package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityChangeNameBinding;
import com.example.myapplication.utilities.Constants;
import com.example.myapplication.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChangeNameActivity extends AppCompatActivity {

    private @NonNull ActivityChangeNameBinding binding; // bien databinding
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
    }

    private void showToast(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    void setListeners()
    {
        binding.buttonChange.setOnClickListener(v ->saveNameChanged());
    }
    private void loading(Boolean isLoading)
    {
        if(isLoading)
        {
            binding.buttonChange.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.buttonChange.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
    private void saveNameChanged()
    {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_PASSWORD, binding.inputConfirmPassword.getText().toString())
                .get()
                .addOnCompleteListener(task ->{
                            if(task.isSuccessful() && task.getResult()!= null
                                    && task.getResult().getDocuments().size() > 0 )
                            {
                                preferenceManager.putString(Constants.KEY_NAME,binding.inputNewName.getText().toString());
                                DocumentReference documentReference = database.collection(Constants.KEY_COLLECTION_USERS).document(
                                        preferenceManager.getString(Constants.KEY_USER_ID));
                                documentReference.update(Constants.KEY_NAME,binding.inputNewName.getText().toString())
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