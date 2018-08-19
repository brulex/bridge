package io.github.brulex.bridge.View;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import io.github.brulex.bridge.R;

public class NewGameMenu extends Activity {


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_menu);



        gameName = findViewById(R.id.game_name);
        pointToFinish = findViewById(R.id.point_to_finish);

        flag_lower = findViewById(R.id.flag_lower);
        flag_sJack = findViewById(R.id.flag_spadesOfJack);
        flag_sQueen = findViewById(R.id.flag_spadesOfQueen);
        flag_changeMode = findViewById(R.id.flag_changeMode);

        cost_the6 = findViewById(R.id.cost_the6);
        cost_the7 = findViewById(R.id.cost_the7);
        cost_the8 = findViewById(R.id.cost_the8);
        cost_the9 = findViewById(R.id.cost_the9);
        cost_the10 = findViewById(R.id.cost_the10);
        cost_Jack = findViewById(R.id.cost_Jack);
        cost_SJack = findViewById(R.id.cost_spadesOfJack);
        cost_Queen = findViewById(R.id.cost_Queen);
        cost_SQueen = findViewById(R.id.cost_SpadesOfQueen);
        cost_King = findViewById(R.id.cost_King);
        cost_Ace = findViewById(R.id.cost_Ace);

        flag_lower.setOnClickListener(onCheckBoxClick);
        flag_sQueen.setOnClickListener(onCheckBoxClick);
        flag_sJack.setOnClickListener(onCheckBoxClick);
        flag_changeMode.setOnClickListener(onCheckBoxClick);
    }
    private View.OnClickListener onCheckBoxClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.flag_lower:
                    if(flag_lower.isChecked()){
                        cost_the6.setVisibility(View.VISIBLE);
                        cost_the7.setVisibility(View.VISIBLE);
                        cost_the8.setVisibility(View.VISIBLE);
                        cost_the9.setVisibility(View.VISIBLE);

                    }else {
                        cost_the6.setVisibility(View.GONE);
                        cost_the7.setVisibility(View.GONE);
                        cost_the8.setVisibility(View.GONE);
                        cost_the9.setVisibility(View.GONE);
                    }
                    break;
                case R.id.flag_spadesOfJack:
                    if(flag_sJack.isChecked()){
                        cost_SJack.setVisibility(View.VISIBLE);
                    }else {
                        cost_SJack.setVisibility(View.GONE);
                    }
                    break;
                case R.id.flag_spadesOfQueen:
                    if(flag_sQueen.isChecked()){
                        cost_SQueen.setVisibility(View.VISIBLE);
                    }else {
                        cost_SQueen.setVisibility(View.GONE);
                    }
                    break;
                case R.id.flag_changeMode:

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newgame_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game_next:


                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }
        return true;
    }
}
