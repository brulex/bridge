package io.github.brulex.bridge.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.github.brulex.bridge.Adapter.ActiveGamesListAdapter;
import io.github.brulex.bridge.DataTransferObject.DatabaseHandler;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.R;

public class ActiveGamesFragment extends AbstractFragment {

    public static final String TAG = "ActiveGamesFragment";
    private RecyclerView active_game_List;
    private ArrayList<GameSetting> gameSettings;
    private ActiveGamesListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_active_games, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(getContext());
        gameSettings = db.getAllGames();
        adapter = new ActiveGamesListAdapter(getContext(),gameSettings);
        active_game_List = view.findViewById(R.id.active_games_list);
        active_game_List.setLayoutManager(new LinearLayoutManager(getContext()));
        active_game_List.setAdapter(adapter);
    }
}
