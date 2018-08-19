package io.github.brulex.bridge.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.brulex.bridge.Controll.PlayerListAdapter;
import io.github.brulex.bridge.R;

public class AddPlayers extends Activity implements PlayerListAdapter.ItemClickListener{

    FloatingActionButton addPlayer;
    PlayerListAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<String> animalNames;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        // data to populate the RecyclerView with
        animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        // set up the RecyclerView
        recyclerView = findViewById(R.id.players_add_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlayerListAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(view.getContext());
                View promptsView = li.inflate(R.layout.dialog_new_player, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        view.getContext());
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = promptsView.findViewById(R.id.new_nickname);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        animalNames.add(userInput.getText().toString());
                                        adapter.notifyItemInserted(animalNames.size());
                                        recyclerView.scrollToPosition(animalNames.size());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        animalNames.remove(position);
        adapter.notifyItemRemoved(position);
        recyclerView.scrollToPosition(animalNames.size());
    }


}
