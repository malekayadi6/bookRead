package com.malek.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.malek.books.Adapters.MyAdapter;
import com.malek.books.FragmentsPrincipale.BlankFragment;
import com.malek.books.FragmentsPrincipale.InscriptionFragment;
import com.malek.books.FragmentsPrincipale.LoginFragment;
import com.malek.books.FragmentsPrincipale.fragmentTab.FragmentTabPrincipale;
import com.malek.books.request.MyRequest;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionManager sessionManager;
    private TextView textView;
    private ImageView imageView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         sessionManager =new SessionManager(this);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        textView= (TextView) findViewById(R.id.prenom_main);
        imageView= (ImageView) findViewById(R.id.img_user);
        relativeLayout= (RelativeLayout) findViewById(R.id.relative);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (sessionManager.isLooged()) {
           Log.d("app","............................"+sessionManager.getEmail());
            Toast.makeText(this, sessionManager.getEmail(), Toast.LENGTH_LONG).show();
           // textView.setText(sessionManager.getNom()+ "  "+sessionManager.getPrenom() );
            Log.d("app","nome ==="+sessionManager.getNom());



            if (sessionManager.getImage()!=null)
                Log.d("app","nome ==="+sessionManager.getPrenom());


        }


        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_content,new FragmentTabPrincipale()).commit();








    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager=getSupportFragmentManager();
        if (id == R.id.nav_camera) {
            fragmentManager.beginTransaction().replace(R.id.frame_content,new FragmentTabPrincipale()).commit();
            // fragmentManager.beginTransaction().replace(R.id.frame_content,new BlankFragment()).commit();
            // Handle the camera action


        } else if (id == R.id.nav_gallery) {
            Intent intent=new Intent(MainActivity.this,PsoteLivre.class);
            startActivity(intent);
            //fragmentManager.beginTransaction().replace(R.id.frame_content,new BlankFragment()).commit();


        } else if (id == R.id.nav_slideshow) {
            Intent intent=new Intent(MainActivity.this,InscriptionActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            fragmentManager.beginTransaction().replace(R.id.frame_content,new LoginFragment()).commit();


        } else if (id == R.id.nav_send) {
            fragmentManager.beginTransaction().replace(R.id.frame_content,new InscriptionFragment()).commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
