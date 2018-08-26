package io.github.brulex.bridge;

public class Constants {

    //    -----------------------------------------------------------------------
    //    Default cost values
    //    -----------------------------------------------------------------------
    public final static int TO_FINISH = 260;
    public final static int LOWER = 0;
    public final static int COST_10 = 10;
    public final static int COST_JACK = 10;
    public final static int COST_S_JACK = 40;
    public final static int COST_QUEEN = 25;
    public final static int COST_S_QUEEN = 50;
    public final static int COST_KING = 10;
    public final static int COST_ACE = 10;
    //    -----------------------------------------------------------------------
    //    DATABASE constants
    //    -----------------------------------------------------------------------
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bridgeSetting";
    public static final String TABLE_GAME_RULE = "game_rule";
    public static final String TABLE_PLAYERS = "players";
    //    -----------------------------------------------------------------------
    //    Fields for game_rule table
    //    -----------------------------------------------------------------------
    public static final String KEY_I_SETTING = "i_setting";
    public static final String KEY_GAME_NAME = "game_name";
    public static final String KEY_DATE = "date";
    public static final String KEY_POINTS_TO_FINISH = "points_to_finish";
    public static final String KEY_FLAG_L = "flag_lower_card";
    public static final String KEY_FLAG_SJ = "flag_spades_jack";
    public static final String KEY_FLAG_SQ = "flag_spades_queen";
    public static final String KEY_FLAG_PC = "flag_point_change";
    public static final String KEY_CURRENT_ROUND = "flag_current_round";
    public static final String KEY_6 = "the6";
    public static final String KEY_7 = "the7";
    public static final String KEY_8 = "the8";
    public static final String KEY_9 = "the9";
    public static final String KEY_10 = "the10";
    public static final String KEY_J = "Jack";
    public static final String KEY_SJ = "Spades_of_Jack";
    public static final String KEY_Q = "Queen";
    public static final String KEY_SQ = "Spades_of_Queen";
    public static final String KEY_K = "King";
    public static final String KEY_A = "Ace";
    //    -----------------------------------------------------------------------
    //    Fields for players table
    //    -----------------------------------------------------------------------
    public static final String KEY_I_PLAYER = "i_player";
    public static final String KEY_NICKNAME = "nickname";
    public static final String KEY_POINTS = "points";
}
