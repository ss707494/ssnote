package com.example.administrator.ssnote.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/9/9.
 */
public class SSNoteDBOpenHelp extends SQLiteOpenHelper {

    /**
     * 建表语句
     */
    public static final String CREATA_NOTE = "CREATE TABLE note(note_id INTEGER PRIMARY KEY autoincrement,notebook_id INTEGER,note_name text,note_content text,note_answer text,note_create_time INTEGER,note_next_time INTEGER,note_style INTEGER,note_level INTEGER) ";
    public static final String CREATA_NOTEBOOK = "CREATE TABLE notebook(notebook_id INTEGER PRIMARY KEY autoincrement,notebook_name text,notebook_desc text)";

    public static final String DEFULT_NOTEBOOK = "INSERT INTO notebook VALUES(null,'defult_book','')";
    public SSNoteDBOpenHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATA_NOTE);
        db.execSQL(CREATA_NOTEBOOK);
        db.execSQL(DEFULT_NOTEBOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
