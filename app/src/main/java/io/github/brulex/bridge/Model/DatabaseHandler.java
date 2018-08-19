package io.github.brulex.bridge.Model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bridgeSetting";
    private static final String TABLE_GAME_RULE = "game_rule";
    private static final String TABLE_PLAYERS = "players";
    //    -----------------------------------------------------------------------
    //    Fields for game_rule table
    //    -----------------------------------------------------------------------
    private static final String KEY_I_SETTING = "i_setting";
    private static final String KEY_GAME_NAME = "game_name";
    private static final String KEY_FLAG_L = "flag_lower_card";
    private static final String KEY_FLAG_SJ = "flag_spades_jack";
    private static final String KEY_FLAG_SQ = "flag_spades_queen";
    private static final String KEY_FLAG_PC = "flag_point_change";
    private static final String KEY_CURRENT_ROUND = "flag_point_change";
    private static final String KEY_6 = "the6";
    private static final String KEY_7 = "the7";
    private static final String KEY_8 = "the8";
    private static final String KEY_9 = "the9";
    private static final String KEY_10 = "the10";
    private static final String KEY_J = "Jack";
    private static final String KEY_SJ = "Spades_of_Jack";
    private static final String KEY_Q = "Queen";
    private static final String KEY_SQ = "Spades_of_Queen";
    private static final String KEY_K = "King";
    private static final String KEY_A = "Ace";
    //    -----------------------------------------------------------------------
    //    Fields for players table
    //    -----------------------------------------------------------------------
    private static final String KEY_I_PLAYER = "i_player";
    // KEY_I_SETTING = "i_setting"
    private static final String KEY_NICKNAME = "nickname";
    private static final String KEY_POINTS = "points";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAME_RULE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_GAME_RULE + "("
                + KEY_I_SETTING + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_GAME_NAME + " VARCHAR NOT NULL,"
                + KEY_CURRENT_ROUND + " INTEGER NOT NULL,"
                + KEY_FLAG_L + " INTEGER NOT NULL,"
                + KEY_FLAG_SJ + " INTEGER NOT NULL,"
                + KEY_FLAG_SQ + " INTEGER NOT NULL,"
                + KEY_FLAG_PC + " INTEGER NOT NULL,"
                + KEY_6 + " INTEGER NOT NULL,"
                + KEY_7 + " INTEGER NOT NULL,"
                + KEY_8 + " INTEGER NOT NULL,"
                + KEY_9 + " INTEGER NOT NULL,"
                + KEY_10 + " INTEGER NOT NULL,"
                + KEY_J + " INTEGER NOT NULL,"
                + KEY_SJ + " INTEGER NOT NULL,"
                + KEY_Q + " INTEGER NOT NULL,"
                + KEY_SQ + " INTEGER NOT NULL,"
                + KEY_K + " INTEGER NOT NULL,"
                + KEY_A + " INTEGER NOT NULL"
                +")";
        String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAYERS + "("
                + KEY_I_PLAYER + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_I_SETTING + "INTEGER NOT NULL,"
                + KEY_NICKNAME + "INTEGER NOT NULL,"
                + KEY_POINTS + "INTEGER NOT NULL"
                +")";
        db.execSQL(CREATE_GAME_RULE_TABLE);
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME_RULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }

    public void addRuleSetting(GameSetting ruleSetting) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_GAME_NAME, ruleSetting.getGame_name());
        values.put(KEY_CURRENT_ROUND, ruleSetting.getCurrent_round());
        values.put(KEY_FLAG_L, ruleSetting.getFlag_lower_card() ? 1 : 0);
        values.put(KEY_FLAG_SJ, ruleSetting.getFlag_spades_jack() ? 1 : 0);
        values.put(KEY_FLAG_SQ, ruleSetting.getFlag_spades_queen() ? 1 : 0);
        values.put(KEY_FLAG_PC, ruleSetting.getFlag_point_change() ? 1 : 0);
        values.put(KEY_6, ruleSetting.getCost_the6());
        values.put(KEY_7, ruleSetting.getCost_the7());
        values.put(KEY_8, ruleSetting.getCost_the8());
        values.put(KEY_9, ruleSetting.getCost_the9());
        values.put(KEY_10, ruleSetting.getCost_the10());
        values.put(KEY_J, ruleSetting.getCost_Jack());
        values.put(KEY_SJ, ruleSetting.getCost_Spades_of_Jack());
        values.put(KEY_Q, ruleSetting.getCost_Queen());
        values.put(KEY_SQ, ruleSetting.getCost_Spades_of_Queen());
        values.put(KEY_K, ruleSetting.getCost_King());
        values.put(KEY_A, ruleSetting.getCost_Ace());
        db.insert(TABLE_GAME_RULE, null, values);
        db.close();
    }


    public GameSetting getGameSetting(int i_setting) {
        SQLiteDatabase db = this.getReadableDatabase();
        GameSetting ruleSetting;
        String selectQuery = "SELECT * " +
                "FROM " + TABLE_GAME_RULE +
                "WHERE" + KEY_I_SETTING +"="+i_setting;

        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor != null){
            cursor.moveToFirst();
            List<Player> playerList = getAllPlayers(cursor.getInt(0));
            return new GameSetting(
                    cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                    cursor.getInt(3) == 1 ,
                    cursor.getInt(4) == 1 ,
                    cursor.getInt(5) == 1 ,
                    cursor.getInt(6) == 1 ,
                    cursor.getInt(7), cursor.getInt(8), cursor.getInt(9),
                    cursor.getInt(10), cursor.getInt(11), cursor.getInt(12),
                    cursor.getInt(13), cursor.getInt(14), cursor.getInt(15),
                    cursor.getInt(16), cursor.getInt(17), playerList
            );
        }
        else return null;
    }

    public void deleteGameSeting(int i_setting) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GAME_RULE, KEY_I_SETTING + "=" + i_setting, null);
        db.delete(TABLE_PLAYERS, KEY_I_SETTING + "=" + i_setting, null);
        db.close();
    }

    private List<Player> getAllPlayers(int i_setting) {
        List<Player> playerList = new ArrayList<Player>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS +
                "WHERE" + KEY_I_SETTING + "=" + i_setting;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                playerList.add(new Player(cursor.getString(3),cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        return playerList;
    }

}
