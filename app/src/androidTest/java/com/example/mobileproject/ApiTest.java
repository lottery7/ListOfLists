package com.example.mobileproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.mobileproject.utils.FirebaseUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ApiTest {
    @Test
    public void testUserCreation() throws ExecutionException, InterruptedException {
        String uuid = UUID.randomUUID().toString();
        User userPushed = new User("testUser@email.ru", "testUser", uuid);

        Task<Void> addUserTask = FirebaseUtils.setNewUser(userPushed);
        Tasks.await(addUserTask);

        assertNotNull(addUserTask);
        assertTrue(addUserTask.isSuccessful());

        Task<DocumentSnapshot> getUserTask = FirebaseUtils.getUser(uuid);
        DocumentSnapshot documentSnapshot = Tasks.await(getUserTask);

        User userPulled = documentSnapshot.toObject(User.class);

        assertNotNull(userPulled);
        assertEquals(userPushed.id, userPulled.id);
        assertEquals(userPushed.email, userPulled.email);
        assertEquals(userPushed.name, userPulled.name);
    }
}