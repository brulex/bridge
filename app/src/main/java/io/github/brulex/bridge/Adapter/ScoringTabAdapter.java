package io.github.brulex.bridge.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.brulex.bridge.DataTransferObject.Player;
import io.github.brulex.bridge.Fragment.AbstractFragment;
import io.github.brulex.bridge.Fragment.ScoringFragment;

public class ScoringTabAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractFragment> tabs;
    private final ArrayList<ScoringFragment> fragments;

    public ScoringTabAdapter(Context context, FragmentManager fm, ArrayList<Player> players) {
        super(fm);
        fragments = new ArrayList<>();
        for (Player i : players) {
            Bundle data = new Bundle();
            data.putLong("i_player", i.getI_player());
            fragments.add(ScoringFragment.getInstance(context,data));
        }
        initTabsMap();
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
        int i = 0;
        for (ScoringFragment s : fragments) {
            tabs.put(i++, s);
        }
    }


}
