package br.edu.linuquiz.view;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView.*;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import br.edu.linuquiz.R;
import br.edu.linuquiz.controller.services.Callbacks;
import br.edu.linuquiz.controller.services.dao.OSDao;
import br.edu.linuquiz.controller.services.dao.UserDAO;
import br.edu.linuquiz.model.OS;


public class MenuActivity extends AppCompatActivity implements OnNavigationItemSelectedListener{

    DrawerLayout layout;
    NavigationView nav_view;
    Toolbar bar;
    Menu menu;

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
        menu = nav_view.getMenu();
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
        UserDAO userDAO = new UserDAO();
        OSDao osDao = new OSDao();
        switch (item.getItemId()){
            case (R.id.menu_icon):
                break;
            case (R.id.menu_user):
                getAlertText("Qual o seu novo nome de usuário?", txt -> {
                    item.setTitle(txt);
                    userDAO.updateUserField("_nickname", txt, t -> {
                        if(t){
                            Toast.makeText(this, "Atualizado no banco de dados"
                                    , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(this, "Não foi possível atualizar no banco de dados"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                break;
            case (R.id.menu_sound):
                getAlertText("Qual o seu app para controlar a saída de áudio?", txt -> {
                    item.setTitle(txt);
                    osDao.updateOSField("app_sound", txt, t->{
                        if(t){
                            Toast.makeText(this, "Atualizado no banco de dados"
                                    , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(this, "Não foi possível atualizar no banco de dados"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                break;
            case (R.id.menu_net):
                getAlertText("Qual o seu app para gerenciar a conexão em rede?", txt -> {
                    item.setTitle(txt);
                    osDao.updateOSField("app_net", txt, t->{
                        if(t){
                            Toast.makeText(this, "Atualizado no banco de dados"
                                    , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(this, "Não foi possível atualizar no banco de dados"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                break;
            default:
                Toast.makeText(this, "Clicou em algo?", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void getAlertText(String message, Callbacks.Menu callback){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        EditText edtTxt = new EditText(this);
        alert.setMessage(message);
        alert.setTitle("Preencha o item");
        alert.setView(edtTxt);

        alert.setPositiveButton("Ok", (dialogInterface, i) -> {
            callback.onMenuClickCallback(edtTxt.getText().toString());
        });
        alert.setNegativeButton("Cancelar", (dialogInterface, i) -> {
            Toast.makeText(this, "Alteração não efetuada!", Toast.LENGTH_SHORT).show();
        });

        alert.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        UserDAO userDAO = new UserDAO();
        OSDao osDao = new OSDao();
        MenuItem item_user = this.menu.findItem(R.id.menu_user);
        MenuItem item_sound = this.menu.findItem(R.id.menu_sound);
        MenuItem item_net = this.menu.findItem(R.id.menu_net);
        ImageView icon = findViewById(R.id.menu_icon);

        userDAO.getCurrentUser(user -> {
            item_user.setTitle(user.get_nickname());

            int id = getResources().getIdentifier(user.get_icon(), "drawable", getPackageName());
            icon.setImageResource(id);
        });
        osDao.getOS(os -> {
            item_net.setTitle(os.getApp_net());
            item_sound.setTitle(os.getApp_sound());
        });
        return true;
    }
}