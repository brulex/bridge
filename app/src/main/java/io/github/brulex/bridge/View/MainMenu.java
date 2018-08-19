package io.github.brulex.bridge.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.github.brulex.bridge.R;

public class MainMenu extends AppCompatActivity {

    private Button b_newGame;
    private Button b_activeGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        b_newGame = findViewById(R.id.start_new_game_button);
        b_activeGames = findViewById(R.id.active_games_button);

        b_newGame.setOnClickListener(onClick);
        b_activeGames.setOnClickListener(onClick);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.start_new_game_button:
                    Intent newGame = new Intent(view.getContext(), NewGameMenu.class);
                    startActivity(newGame);
                    break;
                case R.id.active_games_button:
                    break;
                default:
                    break;
            }
        }
    };

}
