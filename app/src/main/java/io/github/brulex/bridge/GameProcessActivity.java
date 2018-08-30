package io.github.brulex.bridge;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.github.brulex.bridge.Adapter.GameProccessListAdapter;
import io.github.brulex.bridge.DataTransferObject.DatabaseHandler;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.R;
import io.github.brulex.bridge.ScoringActivity;

public class GameProcessActivity extends FragmentActivity {

    private RecyclerView active_game_List;
    private GameSetting gameSettings;
    private GameProccessListAdapter adapter;
    private TextView current_round;
    private Button next_round;
    DatabaseHandler db;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proccess);
        db = new DatabaseHandler(this);
        gameSettings = db.getGameSetting(getIntent().getLongExtra("i_setting",0));
        adapter = new GameProccessListAdapter(this, gameSettings.getPlayers());
        active_game_List = findViewById(R.id.active_games_list);
        active_game_List.setLayoutManager(new LinearLayoutManager(this));
        active_game_List.setAdapter(adapter);
        initToolbar();
        current_round = findViewById(R.id.game_current_round);
        current_round.setText(String.valueOf(gameSettings.getCurrent_round()));
        next_round = findViewById(R.id.game_next_round);
        next_round.setOnClickListener(nextRoundClick);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.game_toolbar);
        toolbar.setTitle(gameSettings.getGame_name());
        toolbar.setOnMenuItemClickListener(menuListener);
        toolbar.inflateMenu(R.menu.game_proccess_menu);
    }

    private void openSetting(){
        Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
    }

    private final Toolbar.OnMenuItemClickListener menuListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.setting:
                    openSetting();
                    break;
                case R.id.reset:
                    break;
            }
            return true;
        }
    };

    private void finishThis(){
        this.finish();
    }
    private final View.OnClickListener nextRoundClick = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
            LayoutInflater li = LayoutInflater.from(view.getContext());
            View promptsView = li.inflate(R.layout.dialog_scoring, null, false);
            final EditText multiplier = promptsView.findViewById(R.id.multiplier);

            alertDialogBuilder.setView(promptsView);
            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("GO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (multiplier.getText().toString().isEmpty() |
                                            Integer.valueOf(multiplier.getText().toString()) == 0) {
                                        Toast.makeText(view.getContext(), "no 0 multiplier or empty", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent start_game = new Intent(getBaseContext(), ScoringActivity.class);
                                        start_game.putExtra("i_setting", gameSettings.getI_setting());
                                        start_game.putExtra("multiplier",Integer.valueOf(multiplier.getText().toString()));
                                        startActivity(start_game);
                                        finishThis();
                                    }
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    };
}