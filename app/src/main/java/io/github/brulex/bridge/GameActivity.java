package io.github.brulex.bridge;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import io.github.brulex.bridge.Fragment.GameProcessFragment;

public class GameActivity extends FragmentActivity {

    private GameProcessFragment gameProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            gameProcess = new GameProcessFragment();
            gameProcess.setArguments(getIntent().getExtras());
            fragmentManager.beginTransaction()
                    .add(R.id.game_container, gameProcess, GameProcessFragment.TAG)
                    .commit();
        }
    }
}
