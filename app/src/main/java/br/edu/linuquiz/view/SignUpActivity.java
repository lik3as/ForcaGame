package br.edu.linuquiz.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.edu.linuquiz.R;
import br.edu.linuquiz.controller.services.dao.UserDAO;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class SignUpActivity extends AppCompatActivity {
    EditText name, nickname, password, email;
    ArrayAdapter<String> adapterProfileIcon;
    AutoCompleteTextView act;
    Button btnOk;
    ImageView imgIcon;
    ProgressBar progressBar;
    String icon= "";

    UserDAO userDAO = new UserDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initObjects();

        btnOk.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            userDAO.insert(format(password), format(name), format(nickname), format(email), icon, l -> {
                if (l) {
                    Toast.makeText(this, "Usuário cadastrado. ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Houve um erro ao efetuar o cadastro!", Toast.LENGTH_SHORT)
                            .show();
                }
                progressBar.setVisibility(View.GONE);
            });
        });


        adapterProfileIcon = new ArrayAdapter<>(this, R.layout.atcstyle, getResources()
                .getStringArray(R.array.profileIcon));

        act.setAdapter(adapterProfileIcon);
        act.setOnItemClickListener((adapterView, view, pos, l) -> {
            act.setBackgroundColor(40);
            switch (adapterView.getItemAtPosition(pos).toString()) {
                case ("Arch"):
                    imgIcon.setImageResource(R.drawable.arch);
                    icon = adapterView.getItemAtPosition(pos).toString().toLowerCase();
                    break;
                case ("Gentoo"):
                    imgIcon.setImageResource(R.drawable.gentoo);
                    icon = adapterView.getItemAtPosition(pos).toString().toLowerCase();
                    break;
                case ("Debian"):
                    imgIcon.setImageResource(R.drawable.debian);
                    icon = adapterView.getItemAtPosition(pos).toString().toLowerCase();
                    break;
                case ("Fedora"):
                    imgIcon.setImageResource(R.drawable.fedora);
                    icon = adapterView.getItemAtPosition(pos).toString().toLowerCase();
                    break;
                case ("Ubuntu"):
                    imgIcon.setImageResource(R.drawable.iconubuntu);
                    icon = adapterView.getItemAtPosition(pos).toString().toLowerCase();
                    break;
                case ("Tux"):
                    imgIcon.setImageResource(R.drawable.icontuxnobg);
                    icon = adapterView.getItemAtPosition(pos).toString().toLowerCase();
            }
        });
    }

    public void initObjects() {
        email = findViewById(R.id.edtSignEmail);
        nickname = findViewById(R.id.edtSignUserName);
        name = findViewById(R.id.edtSignName);
        password = findViewById(R.id.edtSignSenha);
        act = findViewById(R.id.atcSign);
        imgIcon = findViewById(R.id.imageView);
        btnOk = findViewById(R.id.btnOk);
        progressBar = findViewById(R.id.signupProgressBar);

    }

    String format(EditText edt){
        return edt.getText().toString().trim();
    }
}