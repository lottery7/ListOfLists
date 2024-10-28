package com.example.mobileproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private String TAG = "SignInTag";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            goToMainActivity();
        }
        binding.signInBtn.setOnClickListener(v -> signInUser());

        binding.goToSingUpTv.setOnClickListener(v -> goToSignUp());

        binding.forgotPasswordTv.setOnClickListener(v -> goToRestorePassword());
    }

    private void signInUser() {
        String email = binding.userEt.getText().toString();
        String password = binding.passwordEt.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        goToMainActivity();
                    } else {
                        Exception e = task.getException();
                        Log.w(TAG, "signInResult:failed " + e.toString());
                        Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToMainActivity() {
        startActivity(new Intent(SignIn.this, MainActivity.class));
    }
    private void goToSignUp() {
        startActivity(new Intent(SignIn.this, SignUp.class));
    }

     private void goToRestorePassword() {
         startActivity(new Intent(this, RestorePassword.class));
     }
}