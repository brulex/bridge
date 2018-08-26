package io.github.brulex.bridge.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import io.github.brulex.bridge.Fragment.AbstractFragment;
import io.github.brulex.bridge.Fragment.PlayersTabFragment;
import io.github.brulex.bridge.Fragment.RuleTabMenuFragment;

public class NewGameTabAdapter extends FragmentPagerAdapter implements getFragmentInterface {

    private Map<Integer, AbstractFragment> tabs;
    private final RuleTabMenuFragment ruleFragment;
    private final PlayersTabFragment playersFragment;

    public NewGameTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        ruleFragment = RuleTabMenuFragment.getInstance(context);
        playersFragment = PlayersTabFragment.getInstance(context);
        initTabsMap();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap() {
        tabs = new HashMap<>();
        tabs.put(0, ruleFragment);
        tabs.put(1, playersFragment);
    }

    @Override
    public PlayersTabFragment getPlayerFragment() {
        return playersFragment;
    }

    @Override
    public RuleTabMenuFragment getRuleFragment() {
        return ruleFragment;
    }
}
