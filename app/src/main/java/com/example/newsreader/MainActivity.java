package com.example.newsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_bar);




         bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.load_frag,new HomeFragment()).commit();
                        break;

                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.load_frag,new SearchFragment()).commit();
                        break;
                    case R.id.categories:
                        getSupportFragmentManager().beginTransaction().replace(R.id.load_frag,new CategoriesFragment()).commit();
                        break;
                    case R.id.bookmarks:
                        getSupportFragmentManager().beginTransaction().replace(R.id.load_frag,new HomeFragment()).commit();
                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.load_frag,new HomeFragment()).commit();
                        break;

                }

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
}