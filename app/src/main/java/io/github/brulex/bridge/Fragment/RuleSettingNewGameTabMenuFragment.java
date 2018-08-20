package io.github.brulex.bridge.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import io.github.brulex.bridge.R;

public class RuleSettingNewGameTabMenuFragment extends AbstractFragment {


    EditText gameName;
    EditText pointToFinish;

    CheckBox flag_lower;
    CheckBox flag_sJack;
    CheckBox flag_sQueen;
    CheckBox flag_changeMode;

    EditText cost_the6;
    EditText cost_the7;
    EditText cost_the8;
    EditText cost_the9;
    EditText cost_the10;
    EditText cost_Jack;
    EditText cost_SJack;
    EditText cost_Queen;
    EditText cost_SQueen;
    EditText cost_King;
    EditText cost_Ace;

    public static RuleSettingNewGameTabMenuFragment getInstance(Context context) {
        Bundle args = new Bundle();
        RuleSettingNewGameTabMenuFragment fragment = new RuleSettingNewGameTabMenuFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.rule));
        return fragment;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fview = inflater.inflate(R.layout.fragment_rule_setting_menu, container, false);

        gameName = Fview.findViewById(R.id.game_name);
        pointToFinish = Fview.findViewById(R.id.point_to_finish);

        flag_lower = Fview.findViewById(R.id.flag_lower);
        flag_sJack = Fview.findViewById(R.id.flag_spadesOfJack);
        flag_sQueen = Fview.findViewById(R.id.flag_spadesOfQueen);
        flag_changeMode = Fview.findViewById(R.id.flag_changeMode);

        cost_the6 = Fview.findViewById(R.id.cost_the6);
        cost_the7 = Fview.findViewById(R.id.cost_the7);
        cost_the8 = Fview.findViewById(R.id.cost_the8);
        cost_the9 = Fview.findViewById(R.id.cost_the9);
        cost_the10 = Fview.findViewById(R.id.cost_the10);
        cost_Jack = Fview.findViewById(R.id.cost_Jack);
        cost_SJack = Fview.findViewById(R.id.cost_spadesOfJack);
        cost_Queen = Fview.findViewById(R.id.cost_Queen);
        cost_SQueen = Fview.findViewById(R.id.cost_SpadesOfQueen);
        cost_King = Fview.findViewById(R.id.cost_King);
        cost_Ace = Fview.findViewById(R.id.cost_Ace);

        flag_lower.setOnClickListener(onCheckBoxClick);
        flag_sQueen.setOnClickListener(onCheckBoxClick);
        flag_sJack.setOnClickListener(onCheckBoxClick);
        flag_changeMode.setOnClickListener(onCheckBoxClick);
        return Fview;
    }

    private View.OnClickListener onCheckBoxClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.flag_lower:
                    if(flag_lower.isChecked()){
                        Fview.findViewById(R.id.cost_the6_field).setVisibility(View.VISIBLE);
                        Fview.findViewById(R.id.cost_the7_field).setVisibility(View.VISIBLE);
                        Fview.findViewById(R.id.cost_the8_field).setVisibility(View.VISIBLE);
                        Fview.findViewById(R.id.cost_the9_field).setVisibility(View.VISIBLE);

                    }else {
                        Fview.findViewById(R.id.cost_the6_field).setVisibility(View.GONE);
                        Fview.findViewById(R.id.cost_the7_field).setVisibility(View.GONE);
                        Fview.findViewById(R.id.cost_the8_field).setVisibility(View.GONE);
                        Fview.findViewById(R.id.cost_the9_field).setVisibility(View.GONE);
                    }
                    break;
                case R.id.flag_spadesOfJack:
                    if(flag_sJack.isChecked()){
                        Fview.findViewById(R.id.cost_SJack_field).setVisibility(View.VISIBLE);
                    }else {
                        Fview.findViewById(R.id.cost_SJack_field).setVisibility(View.GONE);
                    }
                    break;
                case R.id.flag_spadesOfQueen:
                    if(flag_sQueen.isChecked()){
                        Fview.findViewById(R.id.cost_SQueen_field).setVisibility(View.VISIBLE);
                    }else {
                        Fview.findViewById(R.id.cost_SQueen_field).setVisibility(View.GONE);
                    }
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
