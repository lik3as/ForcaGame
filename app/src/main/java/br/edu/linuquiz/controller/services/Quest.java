package br.edu.linuquiz.controller.services;
import android.util.Log;

import java.util.Random;

public class Quest {

    //Number of times that this question appeared
    public int n_questioned = 0;
    public String quest;
    public Quest(String quest){
        this.quest = quest;
    }

    Quest(){}
    public String getRQuestion(){
        Log.d(this.quest + ": ", String.valueOf(n_questioned));
        String dft = ((new Random().nextInt(2) - n_questioned) <= 0) ? null : quest;
        if (dft != null) n_questioned++;
        if (n_questioned == 3) n_questioned -= 2;
        return dft;
    }
}
