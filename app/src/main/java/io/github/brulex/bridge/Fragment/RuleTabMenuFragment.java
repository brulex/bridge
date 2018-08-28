package io.github.brulex.bridge.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import io.github.brulex.bridge.Constants;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.R;

public class RuleTabMenuFragment extends AbstractFragment {
    private EditText gameName, pointToFinish;
    private Switch flag_lower, flag_sJack, flag_sQueen, flag_changeMode, setting_cost;
    private EditText cost_the6, cost_the7, cost_the8, cost_the9, cost_the10;
    private EditText cost_Jack, cost_SJack, cost_Queen, cost_SQueen, cost_King, cost_Ace;
    private LinearLayout lower_fields, setting_cost_fields;

    public static RuleTabMenuFragment getInstance(Context context) {
        Bundle args = new Bundle();
        RuleTabMenuFragment fragment = new RuleTabMenuFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.rule));
        return fragment;
    }

    private int converterInt(EditText field) {
        return Integer.valueOf(converterStr(field));
    }

    private String converterStr(EditText field) {
        return field.getText().toString();
    }

    private boolean fieldIsEmpty(EditText field) {
        return converterStr(field).isEmpty();
    }

    @Override
    public boolean globalIsEmpty() {
        boolean res = false;
        if (fieldIsEmpty(gameName)) {
            gameName.setError(context.getString(R.string.error_game_name));
            res = true;
        }
        return res;
    }

    @Override
    public GameSetting getCreatedGameSetting() {
        GameSetting game = new GameSetting();
        game.setCurrent_round(0);
        game.setGame_name(converterStr(gameName));
        game.setPoints_to_finish(fieldIsEmpty(pointToFinish) ? Constants.TO_FINISH : converterInt(pointToFinish));
        game.setFlag_lower_card(flag_lower.isChecked());
        game.setFlag_spades_jack(flag_sJack.isChecked());
        game.setFlag_spades_queen(flag_sQueen.isChecked());
        game.setFlag_point_change(flag_changeMode.isChecked());
        game.setCost_the6(fieldIsEmpty(cost_the6) ? Constants.LOWER : converterInt(cost_the6));
        game.setCost_the7(fieldIsEmpty(cost_the7) ? Constants.LOWER : converterInt(cost_the7));
        game.setCost_the8(fieldIsEmpty(cost_the8) ? Constants.LOWER : converterInt(cost_the8));
        game.setCost_the9(fieldIsEmpty(cost_the9) ? Constants.LOWER : converterInt(cost_the9));
        game.setCost_the10(fieldIsEmpty(cost_the10) ? Constants.COST_10 : converterInt(cost_the10));
        game.setCost_Jack(fieldIsEmpty(cost_Jack) ? Constants.COST_JACK : converterInt(cost_Jack));
        game.setCost_Spades_of_Jack(fieldIsEmpty(cost_SJack) ? Constants.COST_S_JACK : converterInt(cost_SJack));
        game.setCost_Queen(fieldIsEmpty(cost_Queen) ? Constants.COST_QUEEN : converterInt(cost_Queen));
        game.setCost_Spades_of_Queen(fieldIsEmpty(cost_SQueen) ? Constants.COST_S_QUEEN : converterInt(cost_SQueen));
        game.setCost_King(fieldIsEmpty(cost_King) ? Constants.COST_KING : converterInt(cost_King));
        game.setCost_Ace(fieldIsEmpty(cost_Ace) ? Constants.COST_ACE : converterInt(cost_Ace));
        return game;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rule_setting_menu, container, false);
        gameName = view.findViewById(R.id.game_name);
        pointToFinish = view.findViewById(R.id.point_to_finish);
        flag_lower = view.findViewById(R.id.flag_lower);
        flag_sJack = view.findViewById(R.id.flag_spadesOfJack);
        flag_sQueen = view.findViewById(R.id.flag_spadesOfQueen);
        flag_changeMode = view.findViewById(R.id.flag_changeMode);
        setting_cost = view.findViewById(R.id.setting_cost);
        cost_the6 = view.findViewById(R.id.cost_the6);
        cost_the6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_6,0,0,0);
        cost_the7 = view.findViewById(R.id.cost_the7);
        cost_the7.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_7,0,0,0);
        cost_the8 = view.findViewById(R.id.cost_the8);
        cost_the8.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_8,0,0,0);
        cost_the9 = view.findViewById(R.id.cost_the9);
        cost_the9.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_9,0,0,0);
        cost_the10 = view.findViewById(R.id.cost_the10);
        cost_the10.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_10,0,0,0);
        cost_Jack = view.findViewById(R.id.cost_Jack);
        cost_Jack.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_j,0,0,0);
        cost_SJack = view.findViewById(R.id.cost_spadesOfJack);
        cost_SJack.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sj,0,0,0);
        cost_Queen = view.findViewById(R.id.cost_Queen);
        cost_Queen.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_q,0,0,0);
        cost_SQueen = view.findViewById(R.id.cost_SpadesOfQueen);
        cost_SQueen.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sq,0,0,0);
        cost_King = view.findViewById(R.id.cost_King);
        cost_King.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_k,0,0,0);
        cost_Ace = view.findViewById(R.id.cost_Ace);
        cost_Ace.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_a,0,0,0);
        lower_fields = view.findViewById(R.id.cost_lower);
        setting_cost_fields = view.findViewById(R.id.setting_cost_fields);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("gameName", gameName.getText().toString());
        outState.putString("pointToFinish", pointToFinish.getText().toString());
        outState.putString("cost_the6", cost_the6.getText().toString());
        outState.putString("cost_the7", cost_the7.getText().toString());
        outState.putString("cost_the8", cost_the8.getText().toString());
        outState.putString("cost_the9", cost_the9.getText().toString());
        outState.putString("cost_the10", cost_the10.getText().toString());
        outState.putString("cost_Jack", cost_Jack.getText().toString());
        outState.putString("cost_SJack", cost_SJack.getText().toString());
        outState.putString("cost_Queen", cost_Queen.getText().toString());
        outState.putString("cost_SQueen", cost_SQueen.getText().toString());
        outState.putString("cost_King", cost_King.getText().toString());
        outState.putString("cost_Ace", cost_Ace.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            gameName.setText(savedInstanceState.getString("gameName"));
            pointToFinish.setText(savedInstanceState.getString("pointToFinish"));
            cost_the6.setText(savedInstanceState.getString("cost_the6"));
            cost_the7.setText(savedInstanceState.getString("cost_the7"));
            cost_the8.setText(savedInstanceState.getString("cost_the8"));
            cost_the9.setText(savedInstanceState.getString("cost_the9"));
            cost_the10.setText(savedInstanceState.getString("cost_the10"));
            cost_Jack.setText(savedInstanceState.getString("cost_Jack"));
            cost_SJack.setText(savedInstanceState.getString("cost_SJack"));
            cost_Queen.setText(savedInstanceState.getString("cost_Queen"));
            cost_SQueen.setText(savedInstanceState.getString("cost_SQueen"));
            cost_King.setText(savedInstanceState.getString("cost_King"));
            cost_Ace.setText(savedInstanceState.getString("cost_Ace"));
        }
        flag_lower.setOnClickListener(onCheckBoxClick);
        flag_sQueen.setOnClickListener(onCheckBoxClick);
        flag_sJack.setOnClickListener(onCheckBoxClick);
        flag_changeMode.setOnClickListener(onCheckBoxClick);
        setting_cost.setOnClickListener(onCheckBoxClick);
    }

    private final View.OnClickListener onCheckBoxClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.flag_lower:
                    lower_fields.setVisibility(flag_lower.isChecked() ? View.VISIBLE : View.GONE);
                    break;
                case R.id.flag_spadesOfJack:
                    cost_SJack.setVisibility(flag_sJack.isChecked() ? View.VISIBLE : View.GONE);
                    break;
                case R.id.flag_spadesOfQueen:
                    cost_SQueen.setVisibility(flag_sQueen.isChecked() ? View.VISIBLE : View.GONE);
                    break;
                case R.id.setting_cost:
                    setting_cost_fields.setVisibility(setting_cost.isChecked() ? View.VISIBLE : View.GONE);
                    break;
                case R.id.flag_changeMode:
                    //TODO
                    break;
                default:
                    break;
            }
        }
    };
}
