package io.github.brulex.bridge;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.brulex.bridge.Fragment.MainMenuFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private Fragment mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mainMenu = new MainMenuFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.mainMenu_fragment, mainMenu, MainMenuFragment.TAG)
                    .commit();
        }
    }

}
