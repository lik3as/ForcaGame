package br.edu.linuquiz.controller.services;

import java.util.HashMap;
import br.edu.linuquiz.R;
import android.content.res.Resources;

public class Quiz {
    HashMap<String, HashMap<String, String>> OS = new HashMap<>();

    public static Quiz instance = new Quiz();
    private Quiz(){
        for(String name : Resources.getSystem().getStringArray(R.array.profileIcon)){
            OS.put(name, new HashMap<>());
        }
    }
}
