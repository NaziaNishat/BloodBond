package com.example.nazia_000.account.homePack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.nazia_000.account.R;
import com.example.nazia_000.account.navPack.navAcceptedRequests;
import com.example.nazia_000.account.navPack.navDonationHistory;
import com.example.nazia_000.account.navPack.navMyRequests;
import com.example.nazia_000.account.navPack.navProfile;
import com.google.firebase.auth.FirebaseUser;

public class Homepage extends AppCompatActivity {

    private NavigationView navigationView;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private android.support.v7.widget.GridLayout gridHomepage;
    private CardView requSee,requDo,search,info;
    private TextView drawerName,drawerFullName;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //homepage
        gridHomepage = findViewById(R.id.homepageGrid);
        requSee = findViewById(R.id.seeRequ);
        requDo = findViewById(R.id.doRequ);
        search = findViewById(R.id.searching);
        info = findViewById(R.id.information);

        navigationView = findViewById(R.id.navigationId);

        //drawer
        drawerName = findViewById(R.id.nameDrawer);
        drawerFullName = findViewById(R.id.fullNameDrawer);

        //navigation drawer
        drawerLayout = findViewById(R.id.drawerId);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Handler handler = new Handler();
        requSee.setOnClickListener(handler);
        requDo.setOnClickListener(handler);
        search.setOnClickListener(handler);
        info.setOnClickListener(handler);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.profile:
                        updateActivity(navProfile.class);
                        break;
                    case R.id.myRequests:
                        //updateActivity(navMyRequests.class);
                        break;
                    case R.id.acceptedRequests:
                        updateActivity(navAcceptedRequests.class);
                        break;
                    case R.id.donationHistory:
                        updateActivity(navDonationHistory.class);
                        break;

                }
                return false;
            }
        });

    }



    //three dots menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dots_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return  super.onOptionsItemSelected(item);
    }

    private void updateActivity(Class cls){

        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    class Handler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.seeRequ){
                updateActivity(RequestActivity.class);
            }

            if(v.getId() == R.id.doRequ){
                updateActivity(doRequestActivity.class);
            }

            if(v.getId() == R.id.searching){
                updateActivity(SearchPre.class);
            }

            if(v.getId() == R.id.information){
                updateActivity(infoActivity.class);
            }
        }
    }

}
