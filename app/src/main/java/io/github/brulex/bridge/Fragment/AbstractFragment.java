package io.github.brulex.bridge.Fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;

import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.DataTransferObject.Player;

public abstract class AbstractFragment extends Fragment implements getNewGameSettingInterface {
    private String title;
    Context context;
    View view;

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    @Override
    public ArrayList<Player> getPlayerInfo() {
        return null;
    }

    @Override
    public GameSetting getCreatedGameSetting() {
        return null;
    }

    @Override
    public boolean globalIsEmpty() {
        return false;
    }
}
