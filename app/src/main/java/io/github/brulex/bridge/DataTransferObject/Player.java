package io.github.brulex.bridge.DataTransferObject;

public class Player {

    private long i_player;
    private String nickname;
    private int points;

    public Player( long i_player, String nickname, int points) {
        this.i_player = i_player;
        this.nickname = nickname;
        this.points = points;
    }

    public long getI_player() {
        return i_player;
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
