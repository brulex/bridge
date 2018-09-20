package io.github.brulex.bridge.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.DataTransferObject.Player;
import io.github.brulex.bridge.Fragment.ScoringTabFragment;

public class ScoringTabAdapter extends FragmentPagerAdapter {

    private Map<Integer, ScoringTabFragment> tabs;
    private final ArrayList<ScoringTabFragment> fragments;

    public ScoringTabAdapter(Context context, FragmentManager fm, GameSetting gameSetting, int mult) {
        super(fm);
        fragments = new ArrayList<>();
        for (Player i : gameSetting.getPlayers()) {
            fragments.add(ScoringTabFragment.getInstance(context, i, gameSetting, mult));
        }
        initTabsMap();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public ScoringTabFragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap() {
        tabs = new HashMap<>();
        int i = 0;
        for (ScoringTabFragment s : fragments) {
            tabs.put(i++, s);
        }
    }
}
