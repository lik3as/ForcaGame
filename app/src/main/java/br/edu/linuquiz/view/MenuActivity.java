package br.edu.linuquiz.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import br.edu.linuquiz.R;
import br.edu.linuquiz.model.OS;
import br.edu.linuquiz.controller.services.Quiz;

public class MenuActivity extends AppCompatActivity implements OnNavigationItemSelectedListener{

    DrawerLayout layout;
    NavigationView nav_view;
    Toolbar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        layout = findViewById(R.id.drawerlyt);
        nav_view = findViewById(R.id.nav_view);
        bar = findViewById(R.id.toolbar);

        //Toolbar cfg
        setSupportActionBar(bar);

        //Nav Cfg
        nav_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, bar,
                R.string.nav_open, R.string.nav_close);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        nav_view.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (layout.isDrawerOpen(GravityCompat.START)){
            layout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}