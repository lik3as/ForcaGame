package br.edu.forcagame.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.forcagame.R;
import br.edu.forcagame.controller.UserDAO;

public class SignUpActivity extends AppCompatActivity {
    EditText edtNome, edtNickname, edtPassword, edtEmail;
    private String name, nickname, password, email, icon;
    ArrayAdapter<String> adapterProfileIcon;
    AutoCompleteTextView act;
    Button btnOk;
    ImageView imgIcon;
    ProgressBar progressBar;
    UserDAO userDAO = new UserDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initObjects();

        btnOk.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            setName(edtNome);
            setEmail(edtEmail);
            setNickname(edtNickname);
            setPassword(edtPassword);
            userDAO.insert(password, name, nickname, email, icon, l -> {
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
                    imgIcon.setImageResource(R.drawable.iconarchlinux);
                    icon = adapterView.getItemAtPosition(pos).toString();
                    break;
                case ("Gentoo"):
                    imgIcon.setImageResource(R.drawable.icongentoo);
                    icon = adapterView.getItemAtPosition(pos).toString();
                    break;
                case ("Debian"):
                    imgIcon.setImageResource(R.drawable.icondebian);
                    icon = adapterView.getItemAtPosition(pos).toString();
                    break;
                case ("Fedora"):
                    imgIcon.setImageResource(R.drawable.iconfedora);
                    icon = adapterView.getItemAtPosition(pos).toString();
                    break;
                case ("Ubuntu"):
                    imgIcon.setImageResource(R.drawable.iconubuntu);
                    icon = adapterView.getItemAtPosition(pos).toString();
                    break;
                case ("Tux"):
                    imgIcon.setImageResource(R.drawable.icontuxnobg);
                    icon = adapterView.getItemAtPosition(pos).toString();
            }
        });
    }

    public void initObjects() {
        edtEmail = findViewById(R.id.edtSignEmail);
        edtNickname = findViewById(R.id.edtSignUserName);
        edtNome = findViewById(R.id.edtSignName);
        edtPassword = findViewById(R.id.edtSignSenha);
        act = findViewById(R.id.atcSign);
        imgIcon = findViewById(R.id.imageView);
        btnOk = findViewById(R.id.btnOk);
        progressBar = findViewById(R.id.signupProgressBar);

    }

    public void setName(EditText name) {
        this.name = name.getText().toString().trim();
    }

    public void setNickname(EditText nickname) {
        this.nickname = nickname.getText().toString().trim();
    }

    public void setPassword(EditText password) {
        this.password = password.getText().toString();
    }

    public void setEmail(EditText email) {
        this.email = email.getText().toString().trim();
    }
}