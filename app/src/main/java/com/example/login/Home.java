package com.example.login;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    RelativeLayout h,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        h=(RelativeLayout)findViewById(R.id.mainpage) ;
        p=(RelativeLayout)findViewById(R.id.personpage) ;
        BottomNavigationView navView = findViewById(R.id.nav_view);

       navView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.navigation_home:
                h.setVisibility(View.VISIBLE);
                p.setVisibility(View.GONE);
                break;

            case R.id.navigation_personal:
                h.setVisibility(View.GONE);
                p.setVisibility(View.VISIBLE);
                break;


        }
        return true;
    }
}