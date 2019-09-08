package com.example.dbs_livelah;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private AppFragment appFragment;
    private StoreFragment storeFragment;
    private DiscoverFragment discoverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        appFragment = new AppFragment();
        storeFragment = new StoreFragment();
        discoverFragment = new DiscoverFragment();


        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_app:
                        setFragment(appFragment);
                        return true;

                    case R.id.nav_store:
                        setFragment(storeFragment);
                        return true;

                    case R.id.nav_discover:
                        setFragment(discoverFragment);
                        return true;

                    default:
                        return false;


                }
            }
        });


    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();


    }

    public void demoButtonClickHandler(View view) {
        Button button = (Button) view;
        String buttonString = button.getText().toString();
        ((paylahApplication) this.getApplication()).setUID(buttonString.substring(buttonString.length() - 1));
        Toast.makeText(this, "Current user:" + ((paylahApplication) this.getApplication()).getUID(), Toast.LENGTH_SHORT).show();
    }
}
