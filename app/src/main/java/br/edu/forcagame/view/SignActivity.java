package br.edu.forcagame.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import br.edu.forcagame.R;
import br.edu.forcagame.controller.UserDAO;

public class SignActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtNoAccount, txtForgotPsw;
    Button btnSignIn;
    EditText edtEmail, edtPassword;
    ProgressBar progressBar;
    UserDAO userDAO = new UserDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_ForcaGame);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        invalidateOptionsMenu();
        initObjects();

    }




    @SuppressLint("ResourceType")
    private void initObjects() {
        txtNoAccount = findViewById(R.id.txtNoAccount);
        txtForgotPsw = findViewById(R.id.txtForgPsw);
        btnSignIn = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        progressBar = findViewById(R.id.progressBar);
        txtNoAccount.setOnClickListener(this);
        txtForgotPsw.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent switchActivities;
        switch (view.getId()) {
            case (R.id.txtNoAccount):
                switchActivities = new Intent(this, SignUpActivity.class);
                startActivity(switchActivities);
                break;
            case (R.id.txtForgPsw):
                break;
            case (R.id.btnLogin):
                progressBar.setVisibility(View.VISIBLE);
                userDAO.authUser(edtEmail.getText().toString(), edtPassword.getText().toString(), c -> {
                    if (c) {
                        Intent i = new Intent(this, MenuActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(this, "Verifique as credenciais!", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                });

        }
    }
}
