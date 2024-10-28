package com.example.mobileproject.utils;

import android.util.Log;

import com.example.mobileproject.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtils {
    static private String TAG = "FirebaseUtils";

    public static String getCurrentUserID() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static FirebaseFirestore db() {
        return FirebaseFirestore.getInstance();
    }

    public static Task<Void> setNewUser(User user) {
        if (user.id == null || user.id.isEmpty()) {
            Log.e(TAG, "User ID is null or empty");
            return null;
        }

        DocumentReference userRef = FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(user.id);

        return userRef.set(user)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "User saved successfully"))
                .addOnFailureListener(e -> Log.e(TAG, "Error saving user", e));
    }

    public static Task<DocumentSnapshot> getUser(String uuid) {
        return FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(uuid)
                .get();
    }
}
