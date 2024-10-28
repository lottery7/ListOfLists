package com.example.mobileproject;

import static com.example.mobileproject.utils.FirebaseUtils.getCurrentUserID;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import com.example.mobileproject.utils.FirebaseUtils;

public class SignUp extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private String TAG = "SignUpTag";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        mAuth = FirebaseAuth.getInstance();

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });

        binding.goToSignInImgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignIn();
            }
        });
    }

    private void signUpUser() {
        String email = binding.emailEt.getText().toString();
        String username = binding.usernameEt.getText().toString();
        String password = binding.passwordEt.getText().toString();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        User user = new User(username, email, getCurrentUserID());
                        FirebaseUtils.setNewUser(user);
                        goToMainMap();
                    } else {
                        Exception exception = task.getException();
                        if (exception instanceof FirebaseAuthWeakPasswordException) {
                            Toast.makeText(getApplicationContext(), "Password is too weak, use at least 8 symbols", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "Email is already in use by another account", Toast.LENGTH_SHORT).show();
                        } else {
                            Exception e = task.getException();
                            Log.w(TAG, "signUpResult:failed " + e.toString());
                            Toast.makeText(getApplicationContext(), "An error occurred during registration", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void goToSignIn() {
        startActivity(new Intent(SignUp.this, SignIn.class));
    }

    private void goToMainMap() {
        startActivity(new Intent(SignUp.this, MainActivity.class));
    }
}