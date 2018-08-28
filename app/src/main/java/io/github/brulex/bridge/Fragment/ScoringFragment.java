package io.github.brulex.bridge.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.github.brulex.bridge.Adapter.NewGameTabAdapter;
import io.github.brulex.bridge.Adapter.ScoringTabAdapter;
import io.github.brulex.bridge.DataTransferObject.DatabaseHandler;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.DataTransferObject.Player;
import io.github.brulex.bridge.Fragment.AbstractFragment;
import io.github.brulex.bridge.R;

public class ScoringFragment extends AbstractFragment {

    public static final String TAG = "ScoringFragment";

    private ScoringTabAdapter adapter;
    private DatabaseHandler db;
    private GameSetting gameSetting;

    public static ScoringFragment getInstance(Context context) {
        ScoringFragment fragment = new ScoringFragment();
        fragment.setContext(context);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.a_fragment_scoring, container, false);
        db = new DatabaseHandler(getContext());
        gameSetting = db.getGameSetting(getArguments().getLong("i_setting"));
        initToolbar();
        initTabs();
        FloatingActionButton floating_confirm_button = view.findViewById(R.id.floating_confirm_button);
        floating_confirm_button.setOnClickListener(onFloatingButtonClick);
        return view;
    }

    private void initToolbar() {
        Toolbar toolbar = view.findViewById(R.id.scoring_toolbar);
        toolbar.setTitle(R.string.scoring);
        TextView mult = view.findViewById(R.id.multiplier_to_view);
        mult.setText(String.valueOf(getArguments().getInt("multiplier")));
    }

    private void initTabs() {
        ViewPager viewPager = view.findViewById(R.id.scoring_pager);
        adapter = new ScoringTabAdapter(getContext(), getFragmentManager(), gameSetting);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = view.findViewById(R.id.scoring_tab);
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


