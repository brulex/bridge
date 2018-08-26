package io.github.brulex.bridge.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.github.brulex.bridge.Adapter.GameProccessListAdapter;
import io.github.brulex.bridge.Adapter.ScoringTabAdapter;
import io.github.brulex.bridge.DataTransferObject.DatabaseHandler;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.R;

public class GameProcessFragment extends AbstractFragment {
    public static final String TAG = "GameProccessFragment";
    private RecyclerView active_game_List;
    private GameSetting gameSettings;
    private GameProccessListAdapter adapter;
    private TextView current_round;
    private Button next_round;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game_proccess_view, container, false);
        return view;
    }

    private void initToolbar() {
        Toolbar toolbar = view.findViewById(R.id.game_toolbar);
        toolbar.setTitle(gameSettings.getGame_name());
        toolbar.setOnMenuItemClickListener(menuListener);
        toolbar.inflateMenu(R.menu.game_proccess_menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(getContext());
        gameSettings = db.getGameSetting(this.getArguments().getLong("i_setting"));
        adapter = new GameProccessListAdapter(getContext(), gameSettings.getPlayers());
        active_game_List = view.findViewById(R.id.active_games_list);
        active_game_List.setLayoutManager(new LinearLayoutManager(getContext()));
        active_game_List.setAdapter(adapter);
        initToolbar();
        current_round = view.findViewById(R.id.game_current_round);
        current_round.setText(String.valueOf(gameSettings.getCurrent_round()));
        next_round = view.findViewById(R.id.game_next_round);
        next_round.setOnClickListener(nextRoundClick);
    }

    private final Toolbar.OnMenuItemClickListener menuListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.setting:
                    Toast.makeText(getContext(), "setting", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.reset:
                    Toast.makeText(getContext(), "reset", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    };

    View.OnClickListener nextRoundClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "next round", Toast.LENGTH_SHORT).show();

            LayoutInflater li = LayoutInflater.from(view.getContext());
            View promptsView = li.inflate(R.layout.dialog_scoring, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
            alertDialogBuilder.setView(promptsView);
            final EditText multiplier = promptsView.findViewById(R.id.multiplier);

            ScoringTabAdapter scoringTabAdapter = new ScoringTabAdapter(view.getContext(), getChildFragmentManager(), gameSettings.getPlayers());
            ViewPager viewPager = promptsView.findViewById(R.id.dialog_tab_players);
            viewPager.setAdapter(scoringTabAdapter);

            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("Finish",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getContext(), "Saved " + multiplier.getText().toString()
                                            , Toast.LENGTH_SHORT).show();
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