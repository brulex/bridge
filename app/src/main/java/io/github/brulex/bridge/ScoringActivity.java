package io.github.brulex.bridge;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.brulex.bridge.Adapter.ScoringTabAdapter;
import io.github.brulex.bridge.DataTransferObject.DatabaseHandler;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.DataTransferObject.Player;

public class ScoringActivity extends FragmentActivity {

    private ScoringTabAdapter adapter;
    private DatabaseHandler db;
    private GameSetting gameSetting;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);
        db = new DatabaseHandler(this);
        gameSetting = db.getGameSetting(getIntent().getLongExtra("i_setting",0));
        initToolbar();
        initTabs();
        Toast.makeText(this, "ss"+adapter.getCount(), Toast.LENGTH_SHORT).show();
        FloatingActionButton floating_confirm_button = findViewById(R.id.floating_confirm_button);
        floating_confirm_button.setOnClickListener(onFloatingButtonClick);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.scoring_toolbar);
        toolbar.setTitle(R.string.scoring);
        TextView mult = findViewById(R.id.multiplier_to_view);
        mult.setText(String.valueOf(getIntent().getIntExtra("multiplier",0)));
    }

    private void initTabs() {
        ViewPager viewPager = findViewById(R.id.scoring_pager);
        adapter = new ScoringTabAdapter(this, getSupportFragmentManager(), gameSetting,getIntent().getIntExtra("multiplier",0));
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.scoring_tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    private final View.OnClickListener onFloatingButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LayoutInflater li = LayoutInflater.from(view.getContext());
            View promptsView = li.inflate(R.layout.dialog_confirm, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    view.getContext());
            alertDialogBuilder.setView(promptsView);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ArrayList<Player> list_player = new ArrayList<>();
                                    for(int i = 0; i < adapter.getCount(); i++){
                                        list_player.add(adapter.getItem(i).getDataFormSeekBar());
                                    }
                                    db.updateForNewRound(list_player, gameSetting.getI_setting(), gameSetting.getCurrent_round());
                                    finishThisAndBack();
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

    private void finishThisAndBack(){
        Intent game = new Intent(getBaseContext(), GameProcessActivity.class);
        game.putExtra("i_setting",gameSetting.getI_setting());
        startActivity(game);
        this.finish();
    }
    @Override
    public void onBackPressed() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_confirm, null);
        TextView textView = promptsView.findViewById(R.id.warning_text);
        textView.setText(this.getText(R.string.confirm_back));
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finishThisAndBack();
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
}


