package io.github.brulex.bridge.Fragment;

import java.util.ArrayList;

import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.DataTransferObject.Player;

public interface getNewGameSettingInterface {
    ArrayList<Player> getPlayerInfo();

    GameSetting getCreatedGameSetting();

    boolean globalIsEmpty();
}
