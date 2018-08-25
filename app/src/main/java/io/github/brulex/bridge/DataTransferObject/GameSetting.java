package io.github.brulex.bridge.DataTransferObject;

import java.util.ArrayList;

public class GameSetting {

    private int i_setting;
    private String game_name;
    private String data;
    private int points_to_finish;
    private int current_round;
    private Boolean flag_lower_card;
    private Boolean flag_spades_jack;
    private Boolean flag_spades_queen;
    private Boolean flag_point_change;
    private int cost_the6;
    private int cost_the7;
    private int cost_the8;
    private int cost_the9;
    private int cost_the10;
    private int cost_Jack;
    private int cost_Spades_of_Jack;
    private int cost_Queen;
    private int cost_Spades_of_Queen;
    private int cost_King;
    private int cost_Ace;
    private ArrayList<Player> players;

    public GameSetting() {
    }

    public int getI_setting() {
        return i_setting;
    }

    public String getGame_name() {
        return game_name;
    }

    public String getData() {
        return data;
    }

    public int getPoints_to_finish() {
        return points_to_finish;
    }

    public int getCurrent_round() {
        return current_round;
    }

    public Boolean getFlag_lower_card() {
        return flag_lower_card;
    }

    public Boolean getFlag_spades_jack() {
        return flag_spades_jack;
    }

    public Boolean getFlag_spades_queen() {
        return flag_spades_queen;
    }

    public Boolean getFlag_point_change() {
        return flag_point_change;
    }

    public int getCost_the6() {
        return cost_the6;
    }

    public int getCost_the7() {
        return cost_the7;
    }

    public int getCost_the8() {
        return cost_the8;
    }

    public int getCost_the9() {
        return cost_the9;
    }

    public int getCost_the10() {
        return cost_the10;
    }

    public int getCost_Jack() {
        return cost_Jack;
    }

    public int getCost_Spades_of_Jack() {
        return cost_Spades_of_Jack;
    }

    public int getCost_Queen() {
        return cost_Queen;
    }

    public int getCost_Spades_of_Queen() {
        return cost_Spades_of_Queen;
    }

    public int getCost_King() {
        return cost_King;
    }

    public int getCost_Ace() {
        return cost_Ace;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setI_setting(int i_setting) {
        this.i_setting = i_setting;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPoints_to_finish(int points_to_finish) {
        this.points_to_finish = points_to_finish;
    }

    public void setCurrent_round(int current_round) {
        this.current_round = current_round;
    }

    public void setFlag_lower_card(Boolean flag_lower_card) {
        this.flag_lower_card = flag_lower_card;
    }

    public void setFlag_spades_jack(Boolean flag_spades_jack) {
        this.flag_spades_jack = flag_spades_jack;
    }

    public void setFlag_spades_queen(Boolean flag_spades_queen) {
        this.flag_spades_queen = flag_spades_queen;
    }

    public void setFlag_point_change(Boolean flag_point_change) {
        this.flag_point_change = flag_point_change;
    }

    public void setCost_the6(int cost_the6) {
        this.cost_the6 = cost_the6;
    }

    public void setCost_the7(int cost_the7) {
        this.cost_the7 = cost_the7;
    }

    public void setCost_the8(int cost_the8) {
        this.cost_the8 = cost_the8;
    }

    public void setCost_the9(int cost_the9) {
        this.cost_the9 = cost_the9;
    }

    public void setCost_the10(int cost_the10) {
        this.cost_the10 = cost_the10;
    }

    public void setCost_Jack(int cost_Jack) {
        this.cost_Jack = cost_Jack;
    }

    public void setCost_Spades_of_Jack(int cost_Spades_of_Jack) {
        this.cost_Spades_of_Jack = cost_Spades_of_Jack;
    }

    public void setCost_Queen(int cost_Queen) {
        this.cost_Queen = cost_Queen;
    }

    public void setCost_Spades_of_Queen(int cost_Spades_of_Queen) {
        this.cost_Spades_of_Queen = cost_Spades_of_Queen;
    }

    public void setCost_King(int cost_King) {
        this.cost_King = cost_King;
    }

    public void setCost_Ace(int cost_Ace) {
        this.cost_Ace = cost_Ace;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
