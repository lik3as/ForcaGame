package br.edu.linuquiz.controller.services.dao;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

import javax.security.auth.callback.Callback;

import br.edu.linuquiz.controller.services.Callbacks;
import br.edu.linuquiz.model.OS;

public class OSDao {
    DatabaseReference reference;
    FirebaseAuth auth;

    public OSDao(){
        reference = FirebaseDatabase.getInstance().getReference("Users");
        auth = FirebaseAuth.getInstance();
    }

    public void updateOSField(String field, String value,  Callbacks.OSIn osIn){
        HashMap<String, Object> map = new HashMap<>();
        map.put(field, value);
        reference.child(auth.getCurrentUser().getUid()).child("OS").updateChildren(map).addOnCompleteListener(t ->{
            osIn.onInsert(t.isSuccessful());
        });
    }

    public void insert(String icon,  Callbacks.OSIn osIn){
        OS os = new OS(icon);

        reference.child(auth.getCurrentUser().getUid()).child("OS").setValue(os).addOnCompleteListener(t -> {
            osIn.onInsert(t.isSuccessful());
        });
    }

    public void getOS(Callbacks.OSGet callback){
        reference.child(auth.getCurrentUser().getUid()).child("OS").get().addOnCompleteListener( t -> {
           if (t.getResult().exists() && t.isSuccessful()){
               callback.onGet(t.getResult().getValue(OS.class));
           }
        });
    }


}
