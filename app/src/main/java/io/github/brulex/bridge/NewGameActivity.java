package io.github.brulex.bridge;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import io.github.brulex.bridge.Adapter.NewGameTabAdapter;

public class NewGameActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.MainMenu);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        initToolbar();
        initTabs();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.newgame_menu);
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        NewGameTabAdapter adapter = new NewGameTabAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void showNotificationTab() {
        viewPager.setCurrentItem(1);
    }
}
