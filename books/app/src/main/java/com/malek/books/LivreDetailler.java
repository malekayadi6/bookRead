package com.malek.books;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.malek.books.Adapters.ViewPagerAdapter;
import com.malek.books.entity.Userr;
import com.malek.books.fragmentDetailler.ProprietaireFragmeny;
import com.malek.books.entity.Livre;
import com.malek.books.fragmentDetailler.FragmentLivreDetailler;
import com.malek.books.fragmentDetailler.fragmentComment;
import com.malek.books.request.MyRequest;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LivreDetailler extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ImageView imageView;
    private Livre livre;
    private Userr userr;
    private List<Livre> livres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livre_detailler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        imageView= (ImageView) findViewById(R.id.image_livre);
        setSupportActionBar(toolbar);

        NestedScrollView scrollView = (NestedScrollView) findViewById (R.id.nest_scrollview);
        scrollView.setFillViewport (true);

        Bundle extras = getIntent().getExtras();
          if(extras==null){

          }

         livre= (Livre) extras.get("livre");
         userr= (Userr) extras.get("user");


        Log.d("app","email"+userr.getEmail());

        getSupportActionBar().setTitle(livre.getNom());
        Picasso.with(this).load(MyRequest.DOMAINE+"/android/img/"+livre.getImg()).into(imageView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentLivreDetailler fragmentLivreDetailler=new FragmentLivreDetailler(livre);
        ProprietaireFragmeny proprietaireFragmeny=new ProprietaireFragmeny(userr);



        tabLayout=(TabLayout)findViewById(R.id.tabs);
        viewPager=(ViewPager)findViewById(R.id.viewpager);

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(fragmentLivreDetailler,"Livre");
        viewPagerAdapter.addFragment(new fragmentComment(),"Avis et commentaire");
        viewPagerAdapter.addFragment(proprietaireFragmeny,"Propriétaire");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setupWithViewPager(viewPager);















    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
