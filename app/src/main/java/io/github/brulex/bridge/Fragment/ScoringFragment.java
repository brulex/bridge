package io.github.brulex.bridge.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import io.github.brulex.bridge.DataTransferObject.DatabaseHandler;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.R;

public class ScoringFragment extends AbstractFragment {

    private TextView seekBar_the6_text, seekBar_the7_text, seekBar_the8_text, seekBar_the9_text, seekBar_the10_text;
    private TextView seekBar_jack_text, seekBar_sjack_text, seekBar_queen_text, seekBar_squeen_text, seekBar_king_text, seekBar_ace_text;

    GameSetting gameSetting;

    public ScoringFragment() {
    }
    public static ScoringFragment getInstance(Context context, Bundle args) {
        ScoringFragment fragment = new ScoringFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.players));
        return fragment;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scoring, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(getContext());
        gameSetting = db.getGameSetting(getArguments().getLong("i_setting"));

        final SeekBar seekBar_the6 = view.findViewById(R.id.seekBar_the6);
        final SeekBar seekBar_the7 = view.findViewById(R.id.seekBar_the7);
        final SeekBar seekBar_the8 = view.findViewById(R.id.seekBar_the8);
        final SeekBar seekBar_the9 = view.findViewById(R.id.seekBar_the9);
        final SeekBar seekBar_the10 = view.findViewById(R.id.seekBar_the10);
        final SeekBar seekBar_jack = view.findViewById(R.id.seekBar_jack);
        final SeekBar seekBar_sjack = view.findViewById(R.id.seekBar_sjack);
        final SeekBar seekBar_queen = view.findViewById(R.id.seekBar_queen);
        final SeekBar seekBar_squeen = view.findViewById(R.id.seekBar_squeen);
        final SeekBar seekBar_king = view.findViewById(R.id.seekBar_king);
        final SeekBar seekBar_ace = view.findViewById(R.id.seekBar_ace);
        seekBar_the6_text = view.findViewById(R.id.seekBar_the6_text);
        seekBar_the7_text = view.findViewById(R.id.seekBar_the7_text);
        seekBar_the8_text = view.findViewById(R.id.seekBar_the8_text);
        seekBar_the9_text = view.findViewById(R.id.seekBar_the9_text);
        seekBar_the10_text = view.findViewById(R.id.seekBar_the10_text);
        seekBar_jack_text = view.findViewById(R.id.seekBar_jack_text);
        seekBar_sjack_text = view.findViewById(R.id.seekBar_sjack_text);
        seekBar_queen_text = view.findViewById(R.id.seekBar_queen_text);
        seekBar_squeen_text = view.findViewById(R.id.seekBar_squeen_text);
        seekBar_king_text = view.findViewById(R.id.seekBar_king_text);
        seekBar_ace_text = view.findViewById(R.id.seekBar_ace_text);
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
        seekBar_jack.setMax(gameSetting.getFlag_spades_jack() ? 3 : 4);
        seekBar_queen.setMax(gameSetting.getFlag_spades_queen() ? 3 : 4);
        view.findViewById(R.id.dialog_lower).setVisibility(gameSetting.getFlag_lower_card() ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.dialog_sjack).setVisibility(gameSetting.getFlag_spades_jack() ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.dialog_squeen).setVisibility(gameSetting.getFlag_spades_queen() ? View.VISIBLE : View.GONE);
    }

    SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
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
