package com.example.administrator.ssnote.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.adapter.NoteAdapter;
import com.example.administrator.ssnote.dao.SSNoteDb;
import com.example.administrator.ssnote.entity.Note;
import com.example.administrator.ssnote.entity.NoteBook;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/9/9.
 */
public class TestDao extends Activity {

    private SSNoteDb db;
    private Button btn_add,btn_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_dao);
        db = SSNoteDb.getInstance(this);
        btn_add = (Button) findViewById(R.id.btn_add_test);
        btn_show= (Button) findViewById(R.id.btn_show_test);
    }

    public void doAdd(View view) {
        Random ra = new Random();


        NoteBook noteBook = new NoteBook()
                .setNotebook_name("Number one").setNotebook_desc("1111");
        db.insertNotebook(noteBook);
        noteBook.setNotebook_name("Number two").setNotebook_desc("22");
        db.insertNotebook(noteBook);
        noteBook.setNotebook_name("Number three").setNotebook_desc("3");
        db.insertNotebook(noteBook);
        for (int i = 0; i < 100; i++) {

            Note n = new Note().setNotebook_id(ra.nextInt(3) + 1).setNote_name("name" + ra.nextInt(100))
                    .setNote_content("content" + ra.nextInt(100)).setNote_answer("answer" + ra.nextBoolean())
                    .setNote_create_time(new Date().getTime() - ra.nextInt(500000))
                    .setNote_next_time(new Date().getTime() + ra.nextInt(50000000))
                    .setNote_style(ra.nextInt(3)).setNote_level(ra.nextInt(9));
            db.insertNote(n);
        }
        Toast.makeText(this,"add complite",Toast.LENGTH_SHORT).show();
    }

    public void doShow(View view) {
        List<Note> list = db.queryAllNotes();
        ArrayAdapter<Note> adapter = new NoteAdapter(TestDao.this, R.layout.note_item, list);
        ((ListView) findViewById(R.id
                .test_list_view)).setAdapter(adapter);

    }
}
