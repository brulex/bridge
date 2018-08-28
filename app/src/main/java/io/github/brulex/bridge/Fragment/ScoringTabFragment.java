package io.github.brulex.bridge.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import io.github.brulex.bridge.DataTransferObject.DatabaseHandler;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.DataTransferObject.Player;
import io.github.brulex.bridge.R;

public class ScoringTabFragment extends AbstractFragment {

    private TextView totalPoints;
    private TextView seekBar_the6_text, seekBar_the7_text, seekBar_the8_text, seekBar_the9_text, seekBar_the10_text;
    private TextView seekBar_jack_text, seekBar_sjack_text, seekBar_queen_text, seekBar_squeen_text, seekBar_king_text, seekBar_ace_text;
    private SeekBar seekBar_the6, seekBar_the7, seekBar_the8, seekBar_the9, seekBar_the10, seekBar_jack, seekBar_sjack,
            seekBar_queen, seekBar_squeen, seekBar_king, seekBar_ace;
    public static final String TAG = "ScoringTabFragment";

    private GameSetting gameSetting;
    private Player player;

    public ScoringTabFragment() {
    }
    public static ScoringTabFragment getInstance(Context context, Player player, GameSetting gameSetting) {
        ScoringTabFragment fragment = new ScoringTabFragment();
        fragment.setGameSetting(gameSetting);
        fragment.setPlayer(player);
        fragment.setContext(context);
        fragment.setTitle(player.getNickname());
        return fragment;
    }

    private void setGameSetting(GameSetting gameSetting) {this.gameSetting = gameSetting;}
    private void setPlayer(Player player) {this.player = player;}

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("i_setting", gameSetting.getI_setting());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null){
            DatabaseHandler db = new DatabaseHandler(getContext());
            this.gameSetting = db.getGameSetting(savedInstanceState.getLong("i_setting"));
        }
        totalPoints.setText(String.valueOf(player.getPoints()));
        seekBar_jack.setMax(gameSetting.getFlag_spades_jack() ? 3 : 4);
        seekBar_queen.setMax(gameSetting.getFlag_spades_queen() ? 3 : 4);
        view.findViewById(R.id.dialog_lower).setVisibility(gameSetting.getFlag_lower_card() ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.dialog_sjack).setVisibility(gameSetting.getFlag_spades_jack() ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.dialog_squeen).setVisibility(gameSetting.getFlag_spades_queen() ? View.VISIBLE : View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scoring_item, container, false);

        totalPoints = view.findViewById(R.id.total_points);
        seekBar_the6 = view.findViewById(R.id.seekBar_the6);
        seekBar_the7 = view.findViewById(R.id.seekBar_the7);
        seekBar_the8 = view.findViewById(R.id.seekBar_the8);
        seekBar_the9 = view.findViewById(R.id.seekBar_the9);
        seekBar_the10 = view.findViewById(R.id.seekBar_the10);
        seekBar_jack = view.findViewById(R.id.seekBar_jack);
        seekBar_sjack = view.findViewById(R.id.seekBar_sjack);
        seekBar_queen = view.findViewById(R.id.seekBar_queen);
        seekBar_squeen = view.findViewById(R.id.seekBar_squeen);
        seekBar_king = view.findViewById(R.id.seekBar_king);
        seekBar_ace = view.findViewById(R.id.seekBar_ace);
        seekBar_the6_text = view.findViewById(R.id.seekBar_the6_text);
        seekBar_the6_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_6,0,0,0);
        seekBar_the7_text = view.findViewById(R.id.seekBar_the7_text);
        seekBar_the7_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_7,0,0,0);
        seekBar_the8_text = view.findViewById(R.id.seekBar_the8_text);
        seekBar_the8_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_8,0,0,0);
        seekBar_the9_text = view.findViewById(R.id.seekBar_the9_text);
        seekBar_the9_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_9,0,0,0);
        seekBar_the10_text = view.findViewById(R.id.seekBar_the10_text);
        seekBar_the10_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_10,0,0,0);
        seekBar_jack_text = view.findViewById(R.id.seekBar_jack_text);
        seekBar_jack_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_j,0,0,0);
        seekBar_sjack_text = view.findViewById(R.id.seekBar_sjack_text);
        seekBar_sjack_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sj,0,0,0);
        seekBar_queen_text = view.findViewById(R.id.seekBar_queen_text);
        seekBar_queen_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_q,0,0,0);
        seekBar_squeen_text = view.findViewById(R.id.seekBar_squeen_text);
        seekBar_squeen_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sq,0,0,0);
        seekBar_king_text = view.findViewById(R.id.seekBar_king_text);
        seekBar_king_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_k,0,0,0);
        seekBar_ace_text = view.findViewById(R.id.seekBar_ace_text);
        seekBar_ace_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_a,0,0,0);
        seekBar_the6.setOnSeekBarChangeListener(seekBarListener);
        seekBar_the7.setOnSeekBarChangeListener(seekBarListener);
        seekBar_the8.setOnSeekBarChangeListener(seekBarListener);
        seekBar_the9.setOnSeekBarChangeListener(seekBarListener);
        seekBar_the10.setOnSeekBarChangeListener(seekBarListener);
        seekBar_jack.setOnSeekBarChangeListener(seekBarListener);
        seekBar_sjack.setOnSeekBarChangeListener(seekBarListener);
        seekBar_queen.setOnSeekBarChangeListener(seekBarListener);
        seekBar_squeen.setOnSeekBarChangeListener(seekBarListener);
        seekBar_king.setOnSeekBarChangeListener(seekBarListener);
        seekBar_ace.setOnSeekBarChangeListener(seekBarListener);

        return view;
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            switch (seekBar.getId()){
                case R.id.seekBar_the6:
                    seekBar_the6_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_the7:
                    seekBar_the7_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_the8:
                    seekBar_the8_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_the9:
                    seekBar_the9_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_the10:
                    seekBar_the10_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_jack:
                    seekBar_jack_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_sjack:
                    seekBar_sjack_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_queen:
                    seekBar_queen_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_squeen:
                    seekBar_squeen_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_king:
                    seekBar_king_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
                case R.id.seekBar_ace:
                    seekBar_ace_text.setText(String.valueOf(seekBar.getProgress()));
                    break;
            }
        }
    };
}
