package com.example.finalproject.Repository;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StorageManager {

    FirebaseStorage storage;
    StorageReference storageReference;

    @Inject
    public StorageManager(){
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("profilePictures");
    }

    public void UploadPhoto(byte [] photoData,OnCompleteListener<Uri> urlListener,OnCompleteListener<UploadTask.TaskSnapshot> completeListener) {
        String path = UUID.randomUUID() + ".png";
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("image/png")
                .build();

        UploadTask uploadTask = storageReference.child(path).putBytes(photoData, metadata);
        uploadTask.addOnCompleteListener(completeListener);
        setUriListener(uploadTask, path, urlListener);
    }

    private void setUriListener(UploadTask uploadTask, String path, OnCompleteListener<Uri> urlListener) {
        Task<Uri> uri = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful())// אי אפשר להביא את הלינק
                    return null;
                return storageReference.child(path).getDownloadUrl();
            }
        });

        uri.addOnCompleteListener(urlListener);
    }
}
