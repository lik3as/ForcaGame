package br.edu.linuquiz.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.linuquiz.R;
import br.edu.linuquiz.controller.services.OS;
import br.edu.linuquiz.controller.services.Quiz;

public class MenuActivity extends AppCompatActivity {
    EditText answer;
    TextView question;
    Button confirm;

    static Quiz instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        instance = Quiz.getInstance(new OS("Arch"));

        answer = findViewById(R.id.edtAnswer);
        question = findViewById(R.id.txtQuest);
        confirm = findViewById(R.id.btnConfirm);

        question.setText(instance.requestQuest());

        confirm.setOnClickListener(l -> {
            if (answer.getText().toString().trim()
                    .equals(instance.requestAnswer(question.getText().toString()))){

                Toast.makeText(getApplicationContext(), "certo", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "errado", Toast.LENGTH_SHORT).show();
            }
            question.setText(instance.requestQuest());
        });
    }
}