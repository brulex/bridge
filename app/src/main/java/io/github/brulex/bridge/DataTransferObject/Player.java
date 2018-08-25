package io.github.brulex.bridge.DataTransferObject;

public class Player {

    private int i_player;
    private String nickname;
    private int points;

    public Player() {

    }

    public Player(String nickname, int points) {
        this.nickname = nickname;
        this.points = points;
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
