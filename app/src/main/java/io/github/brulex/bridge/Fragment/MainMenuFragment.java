package io.github.brulex.bridge.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amitshekhar.DebugDB;

import java.util.Objects;

import io.github.brulex.bridge.NewGameActivity;
import io.github.brulex.bridge.R;

public class MainMenuFragment extends AbstractFragment {

    public static final String TAG = "MainMenuFragment";
    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.start_new_game_button:
                    Intent newGame = new Intent(getContext(), NewGameActivity.class);
                    startActivity(newGame);
                    break;
                case R.id.active_games_button:
                    DebugDB.getAddressLog();
                    Fragment activeGamesMenu = new ActiveGamesFragment();
                    FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                    fragmentTransaction.replace(R.id.main_screen_container, activeGamesMenu, ActiveGamesFragment.TAG)
                            .addToBackStack("active")
                            .commit();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_main_menu, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b_newGame = Objects.requireNonNull(getActivity()).findViewById(R.id.start_new_game_button);
        Button b_activeGames = getActivity().findViewById(R.id.active_games_button);
        b_newGame.setOnClickListener(onClick);
        b_activeGames.setOnClickListener(onClick);

    }
}
