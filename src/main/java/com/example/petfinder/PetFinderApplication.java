package com.example.petfinder;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
@SpringBootApplication
public class PetFinderApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PetFinderApplication.class, args);
    }

}
