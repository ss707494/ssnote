package com.example.administrator.ssnote.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.ssnote.entity.Note;
import com.example.administrator.ssnote.entity.NoteBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/9.
 */
public class SSNoteDb {
    /**
     * 数据库版本及名字
     */
    public static final String DB_NAME = "SSNote_Database";
    public static final int VERSION = 1;

    private static SSNoteDb ssNoteDb;
    private SQLiteDatabase db;

    private SSNoteDb(Context context) {
        SSNoteDBOpenHelp ssNoteDBOpenHelp = new SSNoteDBOpenHelp(context, DB_NAME, null, VERSION);
        db = ssNoteDBOpenHelp.getWritableDatabase();
    }


    /**
     * 单例模式
     *
     * @param context
     * @return
     */
    public synchronized static SSNoteDb getInstance(Context context) {
        if (ssNoteDb == null) {
            ssNoteDb = new SSNoteDb(context);
        }
        return ssNoteDb;
    }

    /**
     * 数据库操作
     */


    /**
     * 插入数据 Note
     */
    public SSNoteDb insertNote(Note note) {
        if (note != null) {
            ContentValues values = new ContentValues();
            values.put("notebook_id", note.getNotebook_id());
            values.put("note_name", note.getNote_name());
            values.put("note_content", note.getNote_content());
            values.put("note_answer", note.getNote_answer());
            values.put("note_create_time", note.getNote_create_time());
            values.put("note_next_time", note.getNote_next_time());
            values.put("note_style", note.getNote_style());
            values.put("note_level", note.getNote_level());
            db.insert("note", null, values);
        }
        return ssNoteDb;
    }

    /**
     * \
     * 插入数据 Notebook
     */
    public SSNoteDb insertNotebook(NoteBook noteBook) {
        if (noteBook != null) {
            ContentValues values = new ContentValues();
            values.put("notebook_name", noteBook.getNotebook_name());
            values.put("notebook_desc", noteBook.getNotebook_desc());
            db.insert("notebook", null, values);
        }
        return ssNoteDb;
    }

    /**
     * 查询数据
     */

//    private

    /**
     * 查  note by id
     */
    public Note queryNoteById(int id) {
        Note note = new Note();
        Cursor cursor = db.query("note", null, "note_id=?", new String[]{String .valueOf(id)}, null, null, "note_next_time");
        if (cursor.moveToFirst()) {
            do {
                note.setNote_id(cursor.getInt(cursor.getColumnIndex("note_id")))
                        .setNotebook_id(cursor.getInt(cursor.getColumnIndex("notebook_id")))
                        .setNote_name(cursor.getString(cursor.getColumnIndex("note_name")))
                        .setNote_content(cursor.getString(cursor.getColumnIndex("note_content")))
                        .setNote_answer(cursor.getString(cursor.getColumnIndex("note_answer")))
                        .setNote_create_time(cursor.getLong(cursor.getColumnIndex("note_create_time")))
                        .setNote_next_time(cursor.getLong(cursor.getColumnIndex("note_next_time")))
                        .setNote_style(cursor.getInt(cursor.getColumnIndex("note_style")))
                        .setNote_level(cursor.getInt(cursor.getColumnIndex("note_level")));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }

        return note;
    }
    /**
     * 查 所有的 note
     */
    public List<Note> queryAllNotes() {
        List<Note> list = new ArrayList<>();
        Cursor cursor = db.query("note", null, null, null, null, null, "note_next_time");
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setNote_id(cursor.getInt(cursor.getColumnIndex("note_id")))
                        .setNotebook_id(cursor.getInt(cursor.getColumnIndex("notebook_id")))
                        .setNote_name(cursor.getString(cursor.getColumnIndex("note_name")))
                        .setNote_content(cursor.getString(cursor.getColumnIndex("note_content")))
                        .setNote_answer(cursor.getString(cursor.getColumnIndex("note_answer")))
                        .setNote_create_time(cursor.getLong(cursor.getColumnIndex("note_create_time")))
                        .setNote_next_time(cursor.getLong(cursor.getColumnIndex("note_next_time")))
                        .setNote_style(cursor.getInt(cursor.getColumnIndex("note_style")))
                        .setNote_level(cursor.getInt(cursor.getColumnIndex("note_level")));
                list.add(note);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }

        return list;
    }

    /**
     * 查询 所有 notebook
     */
    public List<NoteBook> queryAllNotebooks() {
        List<NoteBook> list = new ArrayList<>();
        Cursor c = db.query("notebook", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                NoteBook book = new NoteBook();
                book.setNotebook_id(c.getInt(c.getColumnIndex("notebook_id")))
                        .setNotebook_name(c.getString(c.getColumnIndex("notebook_name")))
                        .setNotebook_desc(c.getString(c.getColumnIndex("notebook_desc")));
                list.add(book);

            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }
        return list;
    }


    /**
     * 根据noteid 查询 book
     */
    public NoteBook queryByNoteId(int id) {
        NoteBook noteBook = null;
        Cursor c = db.query("notebook", null, "notebook_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToNext()) {
            noteBook = new NoteBook();
            noteBook.setNotebook_id(id).setNotebook_name(c.getString(c.getColumnIndex("notebook_name")))
                    .setNotebook_desc(c.getString(c.getColumnIndex("notebook_desc")))
            ;
        }
        if (c != null) {
            c.close();
        }
        return noteBook;
    }

    public static final int SUCCESS = 1;
    public static final int FAIL = 0;


    /**
     * 更新Note数据
     */
    public int updateNote(Note note) {
        ContentValues values = new ContentValues();
        values.put("note_id", note.getNote_id());
        values.put("notebook_id", note.getNotebook_id());
        values.put("note_name", note.getNote_name());
        values.put("note_content", note.getNote_content());
        values.put("note_answer", note.getNote_answer());
        values.put("note_create_time", note.getNote_create_time());
        values.put("note_next_time", note.getNote_next_time());
        values.put("note_style", note.getNote_style());
        values.put("note_level", note.getNote_level());
        if (
                db.update("note", values, "note_id=?", new String[]{String.valueOf(note.getNote_id())})
                        == 1) {
            return SUCCESS;
        } else return FAIL;
    }

    /**
     * delete note
     */
    public boolean deleteNote(int id) {
        int n=
        db.delete("note", "note_id=?", new String[]{String.valueOf(id)});
        return n == 1 ? true : false;
    }

}
