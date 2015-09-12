package com.example.administrator.ssnote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.adapter.NoteAdapter;
import com.example.administrator.ssnote.base.BaseActivity;
import com.example.administrator.ssnote.dao.SSNoteDb;
import com.example.administrator.ssnote.entity.Note;

/**
 * Created by Administrator on 2015/9/9.
 */
public class FirstActivity extends BaseActivity {

    /**
     * 加载资源
     *
     * @param savedInstanceState
     */
    private SSNoteDb db;
    private ListView list_first;

    private void init() {
        db = SSNoteDb.getInstance(this);
        list_first = (ListView) findViewById(R.id.test_list_view);
        noteList = db.queryAllNotes();
        ArrayAdapter<Note> adapter = new NoteAdapter(this, R.layout.note_item, noteList);
        list_first.setAdapter(adapter);
        list_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectNote = noteList.get(position);
                selectNotebook = db.queryByNoteId(selectNote.getNotebook_id());
                startActivity(new Intent(FirstActivity.this, EditNoteActivity.class));
                overridePendingTransition(R.anim.from_right, R.anim.none);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_dao);
        init();
    }

    public void doAdd(View view) {

    }

    public void doShow(View view) {

    }
}
