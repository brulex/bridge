package io.github.brulex.bridge.DataTransferObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import io.github.brulex.bridge.Constants;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAME_RULE_TABLE = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_GAME_RULE + "("
                + Constants.KEY_I_SETTING + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constants.KEY_GAME_NAME + " VARCHAR NOT NULL,"
                + Constants.KEY_DATE + " VARCHAR NOT NULL,"
                + Constants.KEY_POINTS_TO_FINISH + " INTEGER NOT NULL,"
                + Constants.KEY_CURRENT_ROUND + " INTEGER NOT NULL,"
                + Constants.KEY_FLAG_L + " INTEGER NOT NULL,"
                + Constants.KEY_FLAG_SJ + " INTEGER NOT NULL,"
                + Constants.KEY_FLAG_SQ + " INTEGER NOT NULL,"
                + Constants.KEY_FLAG_PC + " INTEGER NOT NULL,"
                + Constants.KEY_6 + " INTEGER NOT NULL,"
                + Constants.KEY_7 + " INTEGER NOT NULL,"
                + Constants.KEY_8 + " INTEGER NOT NULL,"
                + Constants.KEY_9 + " INTEGER NOT NULL,"
                + Constants.KEY_10 + " INTEGER NOT NULL,"
                + Constants.KEY_J + " INTEGER NOT NULL,"
                + Constants.KEY_SJ + " INTEGER NOT NULL,"
                + Constants.KEY_Q + " INTEGER NOT NULL,"
                + Constants.KEY_SQ + " INTEGER NOT NULL,"
                + Constants.KEY_K + " INTEGER NOT NULL,"
                + Constants.KEY_A + " INTEGER NOT NULL"
                + ")";
        String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_PLAYERS + "("
                + Constants.KEY_I_PLAYER + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + Constants.KEY_I_SETTING + " INTEGER NOT NULL,"
                + Constants.KEY_NICKNAME + " INTEGER NOT NULL,"
                + Constants.KEY_POINTS + " INTEGER NOT NULL"
                + ")";
        db.execSQL(CREATE_GAME_RULE_TABLE);
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_GAME_RULE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PLAYERS);
        onCreate(db);
    }

    public long addNewGame(GameSetting ruleSetting) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GAME_NAME, ruleSetting.getGame_name());
        values.put(Constants.KEY_DATE,
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss a", Locale.getDefault())
                        .format(Calendar.getInstance().getTime())
        );
        values.put(Constants.KEY_CURRENT_ROUND, ruleSetting.getCurrent_round());
        values.put(Constants.KEY_POINTS_TO_FINISH, ruleSetting.getPoints_to_finish());
        values.put(Constants.KEY_FLAG_L, ruleSetting.getFlag_lower_card() ? 1 : 0);
        values.put(Constants.KEY_FLAG_SJ, ruleSetting.getFlag_spades_jack() ? 1 : 0);
        values.put(Constants.KEY_FLAG_SQ, ruleSetting.getFlag_spades_queen() ? 1 : 0);
        values.put(Constants.KEY_FLAG_PC, ruleSetting.getFlag_point_change() ? 1 : 0);
        values.put(Constants.KEY_6, ruleSetting.getCost_the6());
        values.put(Constants.KEY_7, ruleSetting.getCost_the7());
        values.put(Constants.KEY_8, ruleSetting.getCost_the8());
        values.put(Constants.KEY_9, ruleSetting.getCost_the9());
        values.put(Constants.KEY_10, ruleSetting.getCost_the10());
        values.put(Constants.KEY_J, ruleSetting.getCost_Jack());
        values.put(Constants.KEY_SJ, ruleSetting.getCost_Spades_of_Jack());
        values.put(Constants.KEY_Q, ruleSetting.getCost_Queen());
        values.put(Constants.KEY_SQ, ruleSetting.getCost_Spades_of_Queen());
        values.put(Constants.KEY_K, ruleSetting.getCost_King());
        values.put(Constants.KEY_A, ruleSetting.getCost_Ace());
        long i_setting = db.insert(Constants.TABLE_GAME_RULE, null, values);
        addPlayerList(ruleSetting.getPlayers(), i_setting);
        db.close();
        return i_setting;
    }

    private void addPlayerList(ArrayList<Player> players, long i_setting) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Player i : players) {
            ContentValues values = new ContentValues();
            values.put(Constants.KEY_I_SETTING, i_setting);
            values.put(Constants.KEY_NICKNAME, i.getNickname());
            values.put(Constants.KEY_POINTS, i.getPoints());
            db.insert(Constants.TABLE_PLAYERS, null, values);
        }
        db.close();
    }

    public GameSetting getGameSetting(long i_setting) {
        SQLiteDatabase db = this.getReadableDatabase();
        GameSetting ruleSetting;
        String selectQuery = "SELECT * " +
                " FROM " + Constants.TABLE_GAME_RULE +
                " WHERE " + Constants.KEY_I_SETTING + " = " + i_setting;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return getGame(cursor);
        } else return null;
    }

    int getInt(Cursor c, String key) {
        return c.getInt(c.getColumnIndex(key));
    }

    String getString(Cursor c, String key) {
        return c.getString(c.getColumnIndex(key));
    }

    private GameSetting getGame(Cursor cursor) {
        GameSetting gameSetting = new GameSetting();
        gameSetting.setI_setting(cursor.getLong(cursor.getColumnIndex(Constants.KEY_I_SETTING)));
        gameSetting.setGame_name(getString(cursor, Constants.KEY_GAME_NAME));
        gameSetting.setDate(getString(cursor, Constants.KEY_DATE));
        gameSetting.setPoints_to_finish(getInt(cursor, Constants.KEY_POINTS_TO_FINISH));
        gameSetting.setCurrent_round(getInt(cursor, Constants.KEY_CURRENT_ROUND));
        gameSetting.setFlag_lower_card(getInt(cursor, Constants.KEY_FLAG_L) == 1);
        gameSetting.setFlag_spades_jack(getInt(cursor, Constants.KEY_FLAG_SJ) == 1);
        gameSetting.setFlag_spades_queen(getInt(cursor, Constants.KEY_FLAG_SQ) == 1);
        gameSetting.setFlag_point_change(getInt(cursor, Constants.KEY_FLAG_PC) == 1);
        gameSetting.setCost_the6(getInt(cursor, Constants.KEY_6));
        gameSetting.setCost_the7(getInt(cursor, Constants.KEY_7));
        gameSetting.setCost_the8(getInt(cursor, Constants.KEY_8));
        gameSetting.setCost_the9(getInt(cursor, Constants.KEY_9));
        gameSetting.setCost_the10(getInt(cursor, Constants.KEY_10));
        gameSetting.setCost_Jack(getInt(cursor, Constants.KEY_J));
        gameSetting.setCost_Spades_of_Jack(getInt(cursor, Constants.KEY_SJ));
        gameSetting.setCost_Queen(getInt(cursor, Constants.KEY_Q));
        gameSetting.setCost_Spades_of_Queen(getInt(cursor, Constants.KEY_SQ));
        gameSetting.setCost_King(getInt(cursor, Constants.KEY_K));
        gameSetting.setCost_Ace(getInt(cursor, Constants.KEY_A));
        gameSetting.setPlayers(getAllPlayers(gameSetting.getI_setting()));
        return gameSetting;
    }

    public ArrayList<GameSetting> getAllGames() {
        ArrayList<GameSetting> gameSettings = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_GAME_RULE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                gameSettings.add(getGame(cursor));
            } while (cursor.moveToNext());
        }
        return gameSettings;
    }

    private ArrayList<Player> getAllPlayers(long i_setting) {
        ArrayList<Player> playerList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_PLAYERS +
                " WHERE " + Constants.KEY_I_SETTING + " = " + i_setting;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                playerList.add(new Player(
                        cursor.getLong(cursor.getColumnIndex(Constants.KEY_I_PLAYER)),
                        getString(cursor, Constants.KEY_NICKNAME),
                        getInt(cursor, Constants.KEY_POINTS)));
            } while (cursor.moveToNext());
        }
        return playerList;
    }

    public void deleteGameSetting(long i_setting) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_GAME_RULE, Constants.KEY_I_SETTING + " = " + i_setting, null);
        db.delete(Constants.TABLE_PLAYERS, Constants.KEY_I_SETTING + " = " + i_setting, null);
        db.close();
    }
}
