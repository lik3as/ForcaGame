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

    /*  Returns null or the String quest based on
     *  how many times it was already questioned
     *
     */
    public String getRQuestion(){
        Log.d(this.quest + ": ", String.valueOf(n_questioned));

        final int r_bound = 6; //min -> 3
        String dft = ((new Random().nextInt(r_bound) - n_questioned) <= 0) ? null : quest;
        if (dft != null) n_questioned++;

        //  Prevents a quest from never appearing again
        if (n_questioned == r_bound + 1) n_questioned -= 3;
        return dft;
    }
}
