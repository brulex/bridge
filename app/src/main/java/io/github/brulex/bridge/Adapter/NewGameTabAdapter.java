package io.github.brulex.bridge.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.HashMap;
import java.util.Map;
import io.github.brulex.bridge.Fragment.AbstractFragment;
import io.github.brulex.bridge.Fragment.PlayersNewGameTabFragment;
import io.github.brulex.bridge.Fragment.RuleSettingNewGameTabMenuFragment;

public class NewGameTabAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractFragment> tabs;
    private Context context;

    public NewGameTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabsMap(context);
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

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        tabs.put(0, PlayersNewGameTabFragment.getInstance(context));
        tabs.put(1, RuleSettingNewGameTabMenuFragment.getInstance(context));
    }
}
