package com.example.petfinder.service;

import com.example.petfinder.dto.IdResponseDto;
import com.example.petfinder.dto.MessageResponseDto;
import com.example.petfinder.dto.UserResponseDto;
import com.example.petfinder.entity.user.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl {
    public static final String COLLECTION_NAME = "users";

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        final ApiFuture<WriteResult> collectionApiFuture = firestoreDB.collection(COLLECTION_NAME).document(user.getId()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public User getUser(String id) throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        final DocumentReference documentReference = firestoreDB.collection(COLLECTION_NAME).document(id);
        final ApiFuture<DocumentSnapshot> future = documentReference.get();
        final DocumentSnapshot document = future.get();
        User user = null;
        if (document.exists()) {
            user = document.toObject(User.class);
            return user;
        } else {
            return null;
        }
    }

    public IdResponseDto updateUser(User user) {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        firestoreDB.collection(COLLECTION_NAME).document(user.getId()).set(user);
        return IdResponseDto.builder().id(user.getId()).build();
    }

    public MessageResponseDto deleteUser(String id) {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        firestoreDB.collection(COLLECTION_NAME).document(id).delete();
        return new MessageResponseDto("Post with id " + id + " was deleted successfully");
    }

    public UserResponseDto getAllUsers() throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = firestoreDB.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();
        List<User> list = new ArrayList<>();
        User user = null;
        while (iterator.hasNext()) {
            DocumentReference reference = iterator.next();
            ApiFuture<DocumentSnapshot> future = reference.get();
            DocumentSnapshot document = future.get();
            user = document.toObject(User.class);
            list.add(user);
        }
        return new UserResponseDto(list);
    }

}
