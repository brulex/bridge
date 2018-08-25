package io.github.brulex.bridge.DataTransferObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


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
    private static final String KEY_DATA = "data";
    private static final String KEY_POINTS_TO_FINISH = "points_to_finish";
    private static final String KEY_FLAG_L = "flag_lower_card";
    private static final String KEY_FLAG_SJ = "flag_spades_jack";
    private static final String KEY_FLAG_SQ = "flag_spades_queen";
    private static final String KEY_FLAG_PC = "flag_point_change";
    private static final String KEY_CURRENT_ROUND = "flag_current_round";
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
                + KEY_DATA + " VARCHAR NOT NULL,"
                + KEY_POINTS_TO_FINISH + " INTEGER NOT NULL,"
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
                + ")";
        String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAYERS + "("
                + KEY_I_PLAYER + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_I_SETTING + " INTEGER NOT NULL,"
                + KEY_NICKNAME + " INTEGER NOT NULL,"
                + KEY_POINTS + " INTEGER NOT NULL"
                + ")";
        db.execSQL(CREATE_GAME_RULE_TABLE);
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME_RULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }

    public void addNewGame(GameSetting ruleSetting) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Calendar.getInstance().getTime())
        values.put(KEY_GAME_NAME, ruleSetting.getGame_name());
        values.put(KEY_DATA, "2018");
        values.put(KEY_CURRENT_ROUND, ruleSetting.getCurrent_round());
        values.put(KEY_POINTS_TO_FINISH, ruleSetting.getPoints_to_finish());
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
        long i_setting = db.insert(TABLE_GAME_RULE, null, values);
        addPlayerList(ruleSetting.getPlayers(), i_setting);
        db.close();
    }

    private void addPlayerList(ArrayList<Player> players, long i_setting ){
        SQLiteDatabase db = this.getWritableDatabase();
        for (Player i : players) {
            ContentValues values = new ContentValues();
            values.put(KEY_I_SETTING, i_setting);
            values.put(KEY_NICKNAME, i.getNickname());
            values.put(KEY_POINTS, i.getPoints());
            db.insert(TABLE_PLAYERS, null, values);
        }
        db.close();
    }

    public GameSetting getGameSetting(int i_setting) {
        SQLiteDatabase db = this.getReadableDatabase();
        GameSetting ruleSetting;
        String selectQuery = "SELECT * " +
                " FROM " + TABLE_GAME_RULE +
                " WHERE " + KEY_I_SETTING + " = " + i_setting;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return getGame(cursor);
        } else return null;
    }
int getInt(Cursor c, String key){
        return c.getInt(c.getColumnIndex(key));
    }
    String getString(Cursor c, String key){
        return c.getString(c.getColumnIndex(key));
    }
    private GameSetting getGame(Cursor cursor){

        GameSetting gameSetting = new GameSetting();
        gameSetting.setI_setting(getInt(cursor,KEY_I_SETTING));
        gameSetting.setGame_name(getString(cursor,KEY_GAME_NAME));
        gameSetting.setPoints_to_finish(getInt(cursor,KEY_POINTS_TO_FINISH));
        gameSetting.setCurrent_round(getInt(cursor,KEY_CURRENT_ROUND));
        gameSetting.setFlag_lower_card(getInt(cursor,KEY_FLAG_L) == 1);
        gameSetting.setFlag_spades_jack(getInt(cursor,KEY_FLAG_SJ) == 1);
        gameSetting.setFlag_spades_queen(getInt(cursor,KEY_FLAG_SQ) == 1);
        gameSetting.setFlag_point_change(getInt(cursor,KEY_FLAG_PC) == 1);
        gameSetting.setCost_the6(getInt(cursor,KEY_6));
        gameSetting.setCost_the7(getInt(cursor,KEY_7));
        gameSetting.setCost_the8(getInt(cursor,KEY_8));
        gameSetting.setCost_the9(getInt(cursor,KEY_9));
        gameSetting.setCost_the10(getInt(cursor,KEY_10));
        gameSetting.setCost_Jack(getInt(cursor,KEY_J));
        gameSetting.setCost_Spades_of_Jack(getInt(cursor,KEY_SJ));
        gameSetting.setCost_Queen(getInt(cursor,KEY_Q));
        gameSetting.setCost_Spades_of_Queen(getInt(cursor,KEY_SQ));
        gameSetting.setCost_King(getInt(cursor,KEY_K));
        gameSetting.setCost_Ace(getInt(cursor,KEY_A));
        gameSetting.setPlayers(getAllPlayers(gameSetting.getI_setting()));
        return gameSetting;
    }

    public ArrayList<GameSetting> getAllGames(){
        ArrayList<GameSetting> gameSettings = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_GAME_RULE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                gameSettings.add(getGame(cursor));
            } while (cursor.moveToNext());
        }
        return gameSettings;
    }
    private ArrayList<Player> getAllPlayers(int i_setting) {
        ArrayList<Player> playerList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS +
                " WHERE " + KEY_I_SETTING + " = " + i_setting;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                playerList.add(new Player(getString(cursor,KEY_NICKNAME), getInt(cursor,KEY_POINTS)));
            } while (cursor.moveToNext());
        }
        return playerList;
    }

    public void deleteGameSetting(int i_setting) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GAME_RULE, KEY_I_SETTING + " = " + i_setting, null);
        db.delete(TABLE_PLAYERS, KEY_I_SETTING + " = " + i_setting, null);
        db.close();
    }
}
