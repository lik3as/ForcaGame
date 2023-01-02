package br.edu.linuquiz.controller.services.dao;

import android.telecom.Call;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.linuquiz.controller.services.Callbacks;
import br.edu.linuquiz.controller.services.DAO;
import br.edu.linuquiz.model.User;
public class UserDAO{
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    String UID;

    public UserDAO() {
        reference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void insert(String password, String name, String nickname, String email, String icon, Callbacks.Register c) {
        User user = new User(password, name, nickname, email, icon);

        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(t -> {
                        if (t.isSuccessful()) {
                            UID = firebaseAuth.getUid();

                            assert UID != null;
                            reference.child(UID)
                                    .setValue(user).addOnCompleteListener(t2 -> {
                                        c.onRegisterCallback(t2.isSuccessful());
                                    });

                        } else {
                            Objects.requireNonNull(t.getException()).printStackTrace();
                            c.onRegisterCallback(false);
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            c.onRegisterCallback(false);
        }
    }

    public void authUser(String email, String password, Callbacks.Login c) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(t -> {
                c.onLoginCallback(t.isSuccessful());
            });
        } catch (Exception e) {
            c.onLoginCallback(false);
        }
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
