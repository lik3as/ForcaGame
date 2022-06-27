package br.edu.forcagame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.edu.forcagame.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

    }
}