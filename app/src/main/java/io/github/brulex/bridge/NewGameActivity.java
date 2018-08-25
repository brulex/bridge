package io.github.brulex.bridge;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import io.github.brulex.bridge.Adapter.NewGameTabAdapter;
import io.github.brulex.bridge.DataTransferObject.DatabaseHandler;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.Fragment.PlayersTabFragment;
import io.github.brulex.bridge.Fragment.RuleTabMenuFragment;

public class NewGameActivity extends FragmentActivity {

    private NewGameTabAdapter adapter;
    private DatabaseHandler db;
    private PlayersTabFragment players;
    private RuleTabMenuFragment rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.MainMenu);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        db = new DatabaseHandler(this);
        initToolbar();
        initTabs();
        players = adapter.getPlayerFragment();
        rule = adapter.getRuleFragment();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.new_game_setting);
        toolbar.setOnMenuItemClickListener(menuListener);
        toolbar.inflateMenu(R.menu.newgame_menu);
    }

    private void initTabs(){
        ViewPager viewPager = findViewById(R.id.viewPager);
        adapter = new NewGameTabAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private final Toolbar.OnMenuItemClickListener menuListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (rule.globalIsEmpty() || players.globalIsEmpty()) {
                Toast.makeText(NewGameActivity.this,
                        "fill all required fields and add more then 1 player",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                GameSetting game = rule.getCreatedGameSetting();
                game.setPlayers(players.getPlayerInfo());
                Toast.makeText(NewGameActivity.this,
                                "name: " + game.getGame_name(),
                        Toast.LENGTH_SHORT)
                        .show();
                    db.addNewGame(game);
            }
            return true;
        }
    };
}
