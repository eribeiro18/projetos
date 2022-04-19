package app.br.com.listadecompras.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import app.br.com.listadecompras.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;

    NavigationView navigationView;

    Menu menu;
    MenuItem nav_meus_produtos;
    MenuItem nav_minhas_compras;
    MenuItem nav_compartilhar;
    MenuItem nav_sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =   findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action Button Clicado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_fragment, new MinhasComprasFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // TODO: opter ID para a opção selecionada no MENU DRAWER
        if (id == R.id.nav_meus_produtos) {

            menu = navigationView.getMenu();

            nav_meus_produtos = menu.findItem(R.id.nav_meus_produtos);
            nav_meus_produtos.setTitle("--> Meus Produtos <--");

            nav_minhas_compras = menu.findItem(R.id.nav_minhas_compras);
            nav_minhas_compras.setTitle("Minhas Compras");

            nav_compartilhar = menu.findItem(R.id.nav_compartilhar);
            nav_compartilhar.setTitle("Compartilhar");

            nav_sair = menu.findItem(R.id.nav_sair);
            nav_sair.setTitle("Sair do App");

            navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new MeusProdutosFragment()).commit();

        } else if (id == R.id.nav_minhas_compras) {

            menu = navigationView.getMenu();

            nav_meus_produtos = menu.findItem(R.id.nav_meus_produtos);

            nav_meus_produtos = menu.findItem(R.id.nav_meus_produtos);
            nav_meus_produtos.setTitle("Meus Produtos");

            nav_minhas_compras = menu.findItem(R.id.nav_minhas_compras);
            nav_minhas_compras.setTitle("--> Minhas Compras <--");

            nav_compartilhar = menu.findItem(R.id.nav_compartilhar);
            nav_compartilhar.setTitle("Compartilhar");

            nav_sair = menu.findItem(R.id.nav_sair);
            nav_sair.setTitle("Sair do App");

            // TODO: Mudar a cor de todos os itens do menu programaticamente
            navigationView.setItemTextColor(ColorStateList.valueOf(Color.RED));

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new MinhasComprasFragment()).commit();

        } else if (id == R.id.nav_compartilhar) {

            menu = navigationView.getMenu();

            nav_meus_produtos = menu.findItem(R.id.nav_meus_produtos);
            nav_meus_produtos.setTitle("Meus Produtos");

            nav_minhas_compras = menu.findItem(R.id.nav_minhas_compras);
            nav_minhas_compras.setTitle("Minhas Compras");

            nav_compartilhar = menu.findItem(R.id.nav_compartilhar);
            nav_compartilhar.setTitle("--> Compartilhar <--");

            nav_sair = menu.findItem(R.id.nav_sair);
            nav_sair.setTitle("Sair do App");

            navigationView.setItemTextColor(ColorStateList.valueOf(Color.GRAY));

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new CompartilharFragment()).commit();

        }else if (id == R.id.nav_sair) {

            menu = navigationView.getMenu();

            nav_sair = menu.findItem(R.id.nav_sair);
            nav_sair.setTitle("--> Sair do App <--");

            nav_meus_produtos = menu.findItem(R.id.nav_meus_produtos);
            nav_meus_produtos.setTitle("Meus Produtos");

            nav_minhas_compras = menu.findItem(R.id.nav_minhas_compras);
            nav_minhas_compras.setTitle("Minhas Compras");

            nav_compartilhar = menu.findItem(R.id.nav_compartilhar);
            nav_compartilhar.setTitle("Compartilhar");

            navigationView.setItemTextColor(ColorStateList.valueOf(Color.MAGENTA));

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new CompartilharFragment()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
