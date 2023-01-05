package br.edu.linuquiz.controller.services;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import br.edu.linuquiz.model.OS;

public class Quiz{
    HashMap<String, String> q_map = new HashMap<>();

    ArrayList<Quest> quests = new ArrayList<>();

    public static Quiz getInstance(OS os){
        return new Quiz(os);
    }

    private Quiz(OS os){
        quests.add(new Quest("Qual é o formato padrão dos pacotes? "));
        quests.add(new Quest("Qual é o Package Manager? "));
        quests.add(new Quest("Qual é o bootloader padrão? "));
        quests.add(new Quest("Lista os arquivos e subdiretórios do diretório especificado "));

        q_map.put(quests.get(0).quest, os.pkg_extension);
        q_map.put(quests.get(1).quest, os.pkg_manager);
        q_map.put(quests.get(2).quest, os.bootloader);
        q_map.put(quests.get(3).quest, os.list);
    }

    public String requestQuest(){
        String quest = null;

        for (int x = 0, i = 0; x < 50 && quest == null; x++, i++){
            quest = quests.get(i).getRQuestion();
            Log.d("for: ", String.valueOf(x));
            if (i == quests.size() - 1) i = 0;
        }

        if (quest==null){
            for (Quest q : quests){
                q.n_questioned = 0;
            }
            return quests.get(2).quest;
        }

        return quest;
    }

    public String requestAnswer(String question){
        return q_map.get(question);
    }
}
