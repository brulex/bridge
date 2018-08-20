package io.github.brulex.bridge.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import io.github.brulex.bridge.Adapter.PlayerListAdapter;
import io.github.brulex.bridge.R;

public class PlayersNewGameTabFragment extends AbstractFragment implements PlayerListAdapter.ItemClickListener{
    FloatingActionButton addPlayer;
    PlayerListAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<String> animalNames;

    public static PlayersNewGameTabFragment getInstance(Context context) {
        Bundle args = new Bundle();
        PlayersNewGameTabFragment fragment = new PlayersNewGameTabFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.players));
        return fragment;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fview = inflater.inflate(R.layout.fragment_add_players, container, false);

        // data to populate the RecyclerView with
        animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
        // set up Floating button
        FloatingActionButton floatingActionButton = Fview.findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(onFloatingButtonClick);
        // set up adapter
        adapter = new PlayerListAdapter(this.context, animalNames);
        adapter.setClickListener(this);
        // set up the RecyclerView
        recyclerView = Fview.findViewById(R.id.players_add_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        recyclerView.setAdapter(adapter);

        return Fview;
    }

    @Override
    public void onItemClick(View view, int position) {
        animalNames.remove(position);
        adapter.notifyItemRemoved(position);
        recyclerView.scrollToPosition(position);
    }

    private View.OnClickListener onFloatingButtonClick = new View.OnClickListener() {
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
    };

}
