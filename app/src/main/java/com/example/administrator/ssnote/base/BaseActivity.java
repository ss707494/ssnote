package com.example.administrator.ssnote.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.ssnote.dao.SSNoteDb;
import com.example.administrator.ssnote.entity.Note;
import com.example.administrator.ssnote.entity.NoteBook;

import java.util.List;

/**
 * Created by Administrator on 2015/9/9.
 */
public abstract class BaseActivity extends Activity {
    protected static List<Note> noteList;
    protected static List<NoteBook> noteBookList;
    protected static NoteBook selectNotebook;
    protected static Note selectNote;
    protected  SSNoteDb db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = SSNoteDb.getInstance(this);
        noteList = db.queryAllNotes();
        noteBookList = db.queryAllNotebooks();
    }



    public void doBack(View view) {
        finish();
    }

    public void doSave(View view) {
        int result=
                db.updateNote(selectNote);
        if (result == SSNoteDb.SUCCESS) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "出错了.", Toast.LENGTH_SHORT).show();
        }
    }


    public void doAdd(View view) {
    }

    public void doShow(View view) {
    }

    public void doTestUpdate(View view) {
    }
}
