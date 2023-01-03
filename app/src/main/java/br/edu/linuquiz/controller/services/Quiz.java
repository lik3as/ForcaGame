package br.edu.linuquiz.controller.services;

import java.util.HashMap;
import br.edu.linuquiz.R;
import android.content.res.Resources;

import java.util.Random;

public class Quiz{
    Resources r;
    HashMap<String, HashMap<String, String>> OS = new HashMap<>();
    HashMap<String, String> arch = new HashMap<>();
    HashMap<String, String> debian = new HashMap<>();
    HashMap<String, String> fedora = new HashMap<>();
    HashMap<String, String> tux = new HashMap<>();

    String[] formats = new String[10];

    public static Quiz getInstance(String[] OS){
        return new Quiz(OS);
    }
    private Quiz(String[] Systems){
        formats[0] = "Qual é o formato padrão dos pacotes? ";
        formats[1] = "Qual é o Package Manager? ";
        formats[9] = "Qual é o bootloader padrão? ";

        arch.put(formats[1], "pacman");
        arch.put(formats[0], "x86_64");
        debian.put(formats[1], "dpkg");
        debian.put(formats[0], ".deb");
        fedora.put(formats[1], "dnf");
        fedora.put(formats[0], "rpm");

        tux.put(formats[9], "grub");

        for(String name : Systems) {
            switch(name) {
                case "Arch":
                    OS.put(name, arch);
                    break;
                case "Debian":
                    OS.put(name, debian);
                    break;
                case "Fedora":
                    OS.put(name, fedora);
                    break;
                default:
                    OS.put(name, tux);
                    break;
            }
        }
    }
    public String requestQuest(String OS_name){
        int i = new Random().nextInt(2);
        return formats[i];
    }

    public String requestAnswer(String OS_name, String question){
        return OS.get(OS_name).get(question);
    }
}
