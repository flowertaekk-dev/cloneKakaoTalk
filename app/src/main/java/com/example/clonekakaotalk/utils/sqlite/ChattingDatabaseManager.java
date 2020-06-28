package com.example.clonekakaotalk.utils.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ChattingDatabaseManager {
    private final String DB_NAME = "chatting"; // It will be only used in chatting room for now.
    private final String TABLE_NAME = "chatting_room";
    private final int DB_VERSION = 1;

    private Context _currentContext;
    private SQLiteDatabase _sqLiteDatabase;
    private static ChattingDatabaseManager _self;

    private ChattingDatabaseManager(Context context) {
        _currentContext = context;

        _sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, context.MODE_PRIVATE, null);

        _sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "(seq INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "user_name TEXT,"
                + "message TEXT);"
        );
    }

    public static ChattingDatabaseManager getInstance(Context context) {

        if (_self == null) {
            _self = new ChattingDatabaseManager(context);
        }

        return _self;
    }

    // ---------------------------------------------------------------------------------------------
    // CRUD

    public long insert(ContentValues addRowValue) {
        return _sqLiteDatabase.insert(DB_NAME, null, addRowValue);
    }

}
