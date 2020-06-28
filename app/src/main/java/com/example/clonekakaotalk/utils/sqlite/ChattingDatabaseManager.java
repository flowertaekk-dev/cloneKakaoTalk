package com.example.clonekakaotalk.utils.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ChattingDatabaseManager {
    private final String DB_NAME = "chatting"; // It will be only used in chatting room for now.
    private final String TABLE_NAME = "chattingRoom";

    private Context _currentContext;
    private SQLiteDatabase _sqLiteDatabase;
    private static ChattingDatabaseManager _self;

    private ChattingDatabaseManager(Context context) {
        _currentContext = context;

        _sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, context.MODE_PRIVATE, null);

        _sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "(seq INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT,"
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
        return _sqLiteDatabase.insert(TABLE_NAME, null, addRowValue);
    }

    public Cursor query(String[] columns, String groupBy, String having, String orderBy) {
        return _sqLiteDatabase.query(TABLE_NAME,
                columns,
                null,
                null,
                groupBy,
                having,
                orderBy);
    }

}
