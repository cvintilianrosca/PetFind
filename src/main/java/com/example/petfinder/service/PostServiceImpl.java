package com.example.petfinder.service;

import com.example.petfinder.dto.IdResponseDto;
import com.example.petfinder.dto.MessageResponseDto;
import com.example.petfinder.dto.PostResponseDto;
import com.example.petfinder.entity.post.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class PostServiceImpl {
    public static final String COLLECTION_NAME = "posts";

    public IdResponseDto savePost(Post post) {
        final UUID uuid = UUID.randomUUID();
        post.setId(uuid.toString());
        Firestore firestoreDB = FirestoreClient.getFirestore();
        firestoreDB.collection(COLLECTION_NAME).document(post.getId()).set(post);
        return IdResponseDto.builder().id(uuid.toString()).build();
    }

    public Post getPost(String id) throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        final DocumentReference documentReference = firestoreDB.collection(COLLECTION_NAME).document(id);
        final ApiFuture<DocumentSnapshot> future = documentReference.get();
        final DocumentSnapshot document = future.get();
        Post post = null;
        if (document.exists()){
            post = document.toObject(Post.class);
            return post;
        } else {
            return null;
        }
    }

    public IdResponseDto updatePost(Post post){
        Firestore firestoreDB = FirestoreClient.getFirestore();
        firestoreDB.collection(COLLECTION_NAME).document(post.getId()).set(post);
        return IdResponseDto.builder().id(post.getId()).build();
    }

    public MessageResponseDto deletePost(String id){
        Firestore firestoreDB = FirestoreClient.getFirestore();
        firestoreDB.collection(COLLECTION_NAME).document(id).delete();
        return new MessageResponseDto("Post with id " + id + " was deleted successfully");
    }

    public PostResponseDto getAllPosts() throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = firestoreDB.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();
        List<Post> list = new ArrayList<>();
        Post post = null;
        while (iterator.hasNext()){
            DocumentReference reference = iterator.next();
            ApiFuture<DocumentSnapshot> future = reference.get();
            DocumentSnapshot document = future.get();
            post = document.toObject(Post.class);
            list.add(post);
        }
        return new PostResponseDto(list);
    }
}
