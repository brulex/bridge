package io.github.brulex.bridge.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.DataTransferObject.Player;
import io.github.brulex.bridge.R;

public class ScoringTabFragment extends AbstractFragment {

    public static final String TAG = "ScoringTabFragment";
    private TextView totalPoints;
    private TextView seekBar_the6_text, seekBar_the7_text, seekBar_the8_text, seekBar_the9_text, seekBar_the10_text;
    private TextView seekBar_jack_text, seekBar_sjack_text, seekBar_queen_text, seekBar_squeen_text, seekBar_king_text, seekBar_ace_text;
    private SeekBar seekBar_the6, seekBar_the7, seekBar_the8, seekBar_the9, seekBar_the10, seekBar_jack, seekBar_sjack,
            seekBar_queen, seekBar_squeen, seekBar_king, seekBar_ace;
    private GameSetting gS;
    private Player player;
    private int newPoints;
    private int mult;
    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            switch (seekBar.getId()) {
                case R.id.seekBar_the6:
                    updateSeekBarText(seekBar_the6_text, seekBar);
                    break;
                case R.id.seekBar_the7:
                    updateSeekBarText(seekBar_the6_text, seekBar);
                    break;
                case R.id.seekBar_the8:
                    updateSeekBarText(seekBar_the8_text, seekBar);
                    break;
                case R.id.seekBar_the9:
                    updateSeekBarText(seekBar_the9_text, seekBar);
                    break;
                case R.id.seekBar_the10:
                    updateSeekBarText(seekBar_the10_text, seekBar);
                    break;
                case R.id.seekBar_jack:
                    updateSeekBarText(seekBar_jack_text, seekBar);
                    break;
                case R.id.seekBar_sjack:
                    updateSeekBarText(seekBar_sjack_text, seekBar);
                    break;
                case R.id.seekBar_queen:
                    updateSeekBarText(seekBar_queen_text, seekBar);
                    break;
                case R.id.seekBar_squeen:
                    updateSeekBarText(seekBar_squeen_text, seekBar);
                    break;
                case R.id.seekBar_king:
                    updateSeekBarText(seekBar_king_text, seekBar);
                    break;
                case R.id.seekBar_ace:
                    updateSeekBarText(seekBar_ace_text, seekBar);
                    break;
            }
            update_points();
        }
    };

    public ScoringTabFragment() {
    }

    public static ScoringTabFragment getInstance(Context context, Player player, GameSetting gameSetting, int mult) {
        ScoringTabFragment fragment = new ScoringTabFragment();
        fragment.setGameSetting(gameSetting);
        fragment.setPlayer(player);
        fragment.setMult(mult);
        fragment.setContext(context);
        fragment.setTitle(player.getNickname());
        return fragment;
    }

    private void setMult(int mult) {
        this.mult = mult;
    }

    private void setGameSetting(GameSetting gameSetting) {
        this.gS = gameSetting;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("new_points", newPoints);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scoring_item, container, false);
        if (savedInstanceState != null) {
            newPoints = savedInstanceState.getInt("new_points");
        } else {
            newPoints = player.getPoints();
        }
        totalPoints = view.findViewById(R.id.total_points);
        seekBar_the6 = init_seekBar(R.id.seekBar_the6);
        seekBar_the7 = init_seekBar(R.id.seekBar_the7);
        seekBar_the8 = init_seekBar(R.id.seekBar_the8);
        seekBar_the9 = init_seekBar(R.id.seekBar_the9);
        seekBar_the10 = init_seekBar(R.id.seekBar_the10);
        seekBar_jack = init_seekBar(R.id.seekBar_jack);
        seekBar_sjack = init_seekBar(R.id.seekBar_sjack);
        seekBar_queen = init_seekBar(R.id.seekBar_queen);
        seekBar_squeen = init_seekBar(R.id.seekBar_squeen);
        seekBar_king = init_seekBar(R.id.seekBar_king);
        seekBar_ace = init_seekBar(R.id.seekBar_ace);
        seekBar_the6_text = init_seekBar_text(R.id.seekBar_the6_text, R.drawable.ic_6);
        seekBar_the7_text = init_seekBar_text(R.id.seekBar_the7_text, R.drawable.ic_7);
        seekBar_the8_text = init_seekBar_text(R.id.seekBar_the8_text, R.drawable.ic_8);
        seekBar_the9_text = init_seekBar_text(R.id.seekBar_the9_text, R.drawable.ic_9);
        seekBar_the10_text = init_seekBar_text(R.id.seekBar_the10_text, R.drawable.ic_10);
        seekBar_jack_text = init_seekBar_text(R.id.seekBar_jack_text, R.drawable.ic_j);
        seekBar_sjack_text = init_seekBar_text(R.id.seekBar_sjack_text, R.drawable.ic_sj);
        seekBar_queen_text = init_seekBar_text(R.id.seekBar_queen_text, R.drawable.ic_q);
        seekBar_squeen_text = init_seekBar_text(R.id.seekBar_squeen_text, R.drawable.ic_sq);
        seekBar_king_text = init_seekBar_text(R.id.seekBar_king_text, R.drawable.ic_k);
        seekBar_ace_text = init_seekBar_text(R.id.seekBar_ace_text, R.drawable.ic_a);
        totalPoints.setText(String.valueOf(newPoints));
        seekBar_jack.setMax(gS.getFlag_spades_jack() ? 3 : 4);
        seekBar_queen.setMax(gS.getFlag_spades_queen() ? 3 : 4);
        view.findViewById(R.id.dialog_lower).setVisibility(gS.getFlag_lower_card() ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.dialog_sjack).setVisibility(gS.getFlag_spades_jack() ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.dialog_squeen).setVisibility(gS.getFlag_spades_queen() ? View.VISIBLE : View.GONE);
        return view;
    }

    private SeekBar init_seekBar(int id) {
        SeekBar seekBar = view.findViewById(id);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(seekBarListener);
        return seekBar;
    }

    private TextView init_seekBar_text(int id, int drawable) {
        TextView textView = view.findViewById(id);
        textView.setText(String.valueOf(0));
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);
        return textView;
    }

    private void update_points() {
        newPoints = player.getPoints() + mult * (
                getInt(seekBar_the6_text) * gS.getCost_the6() +
                        getInt(seekBar_the7_text) * gS.getCost_the7() +
                        getInt(seekBar_the8_text) * gS.getCost_the8() +
                        getInt(seekBar_the9_text) * gS.getCost_the9() +
                        getInt(seekBar_the10_text) * gS.getCost_the10() +
                        getInt(seekBar_jack_text) * gS.getCost_Jack() +
                        getInt(seekBar_sjack_text) * gS.getCost_Spades_of_Jack() +
                        getInt(seekBar_queen_text) * gS.getCost_Queen() +
                        getInt(seekBar_squeen_text) * gS.getCost_Spades_of_Queen() +
                        getInt(seekBar_king_text) * gS.getCost_King() +
                        getInt(seekBar_ace_text) * gS.getCost_Ace()
        );
        totalPoints.setText(String.valueOf(newPoints));
    }

    private int getInt(TextView textView) {
        return Integer.valueOf(textView.getText().toString());
    }

    private void updateSeekBarText(TextView text, SeekBar seekBar) {
        text.setText(String.valueOf(seekBar.getProgress()));
    }

    public Player submitDataFormSeekBar() {
        player.setPoints(newPoints);
        return player;
    }
    public ArrayList<Player> getPoints() {
        ArrayList<Player> list = new ArrayList<>();
        list.add(player);
        list.add(new Player(-1,-1,player.getNickname(),newPoints));
        return list;
    }
}
