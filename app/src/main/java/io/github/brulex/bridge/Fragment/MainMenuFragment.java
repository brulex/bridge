package io.github.brulex.bridge.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.github.brulex.bridge.NewGameActivity;
import io.github.brulex.bridge.R;

public class MainMenuFragment extends AbstractFragment {

    private Button b_newGame;
    private Button b_activeGames;
    private Fragment activeGamesMenu;

    public static final String TAG = "MainMenuFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fview = inflater.inflate(R.layout.fragment_main_menu, container, false);
        b_newGame = Fview.findViewById(R.id.start_new_game_button);
        b_activeGames = Fview.findViewById(R.id.active_games_button);
        this.context = getContext();
        b_newGame.setOnClickListener(onClick);
        b_activeGames.setOnClickListener(onClick);

        return Fview;
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.start_new_game_button:
                    Intent newGame = new Intent(context, NewGameActivity.class);
                    startActivity(newGame);
                    break;
                case R.id.active_games_button:
//                    activeGamesMenu = new MainMenuFragment();
//                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.mainMenu_fragment, activeGamesMenu,MainMenuFragment.TAG);
                    break;
                default:
                    break;
            }
        }
    };
}
