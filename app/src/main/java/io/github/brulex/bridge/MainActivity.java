package io.github.brulex.bridge;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.ArrayList;

import io.github.brulex.bridge.DataTransferObject.Player;
import io.github.brulex.bridge.Fragment.MainMenuFragment;

public class MainActivity extends FragmentActivity {

    ArrayList<Player> player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            Fragment mainMenu = new MainMenuFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main_screen_container, mainMenu, MainMenuFragment.TAG)
                    .commit();
        }
    }
}
