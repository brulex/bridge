package io.github.brulex.bridge.DataTransferObject;

public class Player {

    private long i_player;
    private long i_setting;
    private String nickname;
    private int points;

    public Player(long i_player, long i_setting, String nickname, int points) {
        this.i_player = i_player;
        this.i_setting = i_setting;
        this.nickname = nickname;
        this.points = points;
    }

    public long getI_setting() {
        return i_setting;
    }

    public void setI_setting(long i_setting) {
        this.i_setting = i_setting;
    }

    public long getI_player() {
        return i_player;
    }

    public void setI_player(long i_player) {
        this.i_player = i_player;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
