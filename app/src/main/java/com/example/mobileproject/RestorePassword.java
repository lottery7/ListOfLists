package com.example.mobileproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.databinding.ActivityRestorePasswordBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RestorePassword extends AppCompatActivity {
    private ActivityRestorePasswordBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestorePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        mAuth = FirebaseAuth.getInstance();

        binding.restorePasswordBtn.setOnClickListener(v -> {
            String email = binding.emailEt.getText().toString();
            if (email.isEmpty()){
                Toast.makeText(getApplicationContext(), "Email field shouldn't be empty ", Toast.LENGTH_SHORT).show();
            }
            else{
                restorePassword(email);
            }
        });

        binding.goToSignInImgbtn.setOnClickListener(v -> goToSignIn());
    }

    private void restorePassword(String email) {

        mAuth.sendPasswordResetEmail(email).addOnSuccessListener(unused -> {
            Toast.makeText(getApplicationContext(), "A recovery email has been sent to you", Toast.LENGTH_SHORT).show();
            goToSignIn();
        }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "The email is incorrect", Toast.LENGTH_SHORT).show());

    }


    private void goToSignIn() {
        startActivity(new Intent(RestorePassword.this, SignIn.class));
    }
}