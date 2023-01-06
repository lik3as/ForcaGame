package br.edu.linuquiz.controller.services.dao;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import br.edu.linuquiz.controller.services.Callbacks;
import br.edu.linuquiz.model.OS;
import br.edu.linuquiz.model.User;
public class UserDAO{
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    String UID;

    public UserDAO() {
        reference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void insert(String password, String name, String nickname, String email, String icon, Callbacks.SignUser c) {
        User user = new User(name, nickname, password, email, icon);
        OS os = new OS(icon.toLowerCase());

        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(t -> {
                        if (t.isSuccessful()) {
                            UID = firebaseAuth.getUid();

                            assert UID != null;
                            reference.child(UID)
                                    .setValue(user).addOnCompleteListener(t2 -> {
                                        c.onUserCallback(t2.isSuccessful());
                                    });
                            reference.child(UID).child("OS").setValue(os).addOnCompleteListener(t2 -> {
                                c.onUserCallback(t2.isSuccessful());
                            });

                        } else {
                            Objects.requireNonNull(t.getException()).printStackTrace();
                            c.onUserCallback(false);
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            c.onUserCallback(false);
        }
    }

    public void authUser(String email, String password, Callbacks.SignUser c) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(t -> {
                c.onUserCallback(t.isSuccessful());
            });
        } catch (Exception e) {
            c.onUserCallback(false);
        }
    }

    public void updateUserField(String field, String value, Callbacks.SignUser callback){
        HashMap<String, Object> map = new HashMap<>();
        map.put(field, value);

        reference.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(map).addOnCompleteListener(t -> {
            callback.onUserCallback(t.isSuccessful());
        });
    }

    public void getCurrentUser(Callbacks.GetUser callback){
        reference.child(Objects.requireNonNull(firebaseAuth.getCurrentUser().getUid())).get().addOnCompleteListener(t -> {
           if (t.isSuccessful() && t.getResult().exists()){
               callback.onUser(t.getResult().getValue(User.class));
           }
        });
    }

    public void usersList(Callbacks.UserList c) {
        List<User> users = new ArrayList<>();
        reference.get().addOnCompleteListener(t -> {
            if (t.isSuccessful() && t.getResult().exists()) {
                for (DataSnapshot snapshot : t.getResult().getChildren()) {
                    users.add(snapshot.getValue(User.class));

                }
                c.onUserListCallback(users);
            }
        });
    }
}
