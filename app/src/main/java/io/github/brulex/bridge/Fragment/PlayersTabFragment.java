package io.github.brulex.bridge.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import io.github.brulex.bridge.Adapter.PlayerListAdapter;
import io.github.brulex.bridge.DataTransferObject.Player;
import io.github.brulex.bridge.R;

public class PlayersTabFragment extends AbstractFragment implements PlayerListAdapter.ItemClickListener {

    private PlayerListAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Player> players;
    private final View.OnClickListener onFloatingButtonClick = new View.OnClickListener() {
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
                                public void onClick(DialogInterface dialog, int id) {
                                    players.add(new Player(-1, -1, userInput.getText().toString(), 0));
                                    adapter.notifyItemInserted(players.size());
                                    recyclerView.scrollToPosition(players.size());
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

    public static PlayersTabFragment getInstance(Context context) {
        Bundle args = new Bundle();
        PlayersTabFragment fragment = new PlayersTabFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.players));
        return fragment;
    }

    @Override
    public boolean globalIsEmpty() {
        return players.size() < 2;
    }

    @Override
    public ArrayList<Player> getPlayerInfo() {
        return players;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_players, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        players = new ArrayList<>();
        if (savedInstanceState != null) {
            List<String> temp = savedInstanceState.getStringArrayList("players");
            assert temp != null;
            for (String i : temp) {
                players.add(new Player(-1, -1, i, 0));
            }
        }

        FloatingActionButton floatingActionButton = view.findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(onFloatingButtonClick);

        adapter = new PlayerListAdapter(getContext(), players);
        adapter.setClickListener(this);

        recyclerView = view.findViewById(R.id.players_add_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<String> temp = new ArrayList<>();
        for (Player i : players) {
            temp.add(i.getNickname());
        }
        outState.putStringArrayList("players", temp);
    }

    @Override
    public void onItemClick(View view, int position) {
        players.remove(position);
        adapter.notifyItemRemoved(position);
        recyclerView.scrollToPosition(position);
    }
}
