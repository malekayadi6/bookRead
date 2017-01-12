package com.malek.books.FragmentsPrincipale.fragmentTab;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.malek.books.Adapters.ViewPagerAdapter;
import com.malek.books.FragmentsPrincipale.Fr1;
import com.malek.books.MySingleton;
import com.malek.books.R;
import com.malek.books.request.MyRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTabPrincipale extends Fragment {
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private MyRequest request;
    private RequestQueue queue;


    public FragmentTabPrincipale() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_tab_principale, container, false);



        queue= MySingleton.getInstance(getActivity().getApplication().getApplicationContext()).getRequestQueue();
        request=new MyRequest(getContext(),queue);
        Fr1 fr1=new Fr1(request,queue);


        tabLayout=(TabLayout)view.findViewById(R.id.tabs_main);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager_main);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(fr1,"Accueil");
        viewPagerAdapter.addFragment(new AuteurFragment(),"Auteurs");
        viewPagerAdapter.addFragment(new CategorieFragment(),"Categories");
        viewPagerAdapter.addFragment(new RegionFragment(),"Region");
        viewPagerAdapter.addFragment(new RechercheAvanceeFragment(),"Recherche Avancée");
        viewPagerAdapter.addFragment(new AjouterLivreFragment(),"Poste Livre");



        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setupWithViewPager(viewPager);








        return view;
    }

}
