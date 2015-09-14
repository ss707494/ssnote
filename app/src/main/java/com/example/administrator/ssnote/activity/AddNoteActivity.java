package com.example.administrator.ssnote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.base.BaseActivity;
import com.example.administrator.ssnote.entity.Note;
import com.example.administrator.ssnote.service.RemindService;

import java.util.Date;

/**
 * Created by Administrator on 2015/9/12.
 */
public class AddNoteActivity extends BaseActivity {

    private TextView message;
    private EditText title,ques,ans;
    private Spinner spinnerBook;

    private void init() {
        selectNote=new Note();
        message = (TextView) findViewById(R.id.edit_msg);
        title = (EditText) findViewById(R.id.edit_title);
        ques = (EditText) findViewById(R.id.edit_ques);
        ans = (EditText) findViewById(R.id.edit_answer);
        initData();
    }

    private void initData() {
        selectNote.setNote_create_time(new Date().getTime())
                .setNotebook_id(0).setNote_style(1).setNote_level(0).setNote_next_time(new Date().getTime() + 1L * 24 * 60 * 60 * 1000);
        initSpinner();
    }

    private void initSpinner() {
        spinnerBook = (Spinner) findViewById(R.id.spinner_book);
//        spinnerBook.setPrompt("book");
        String[] books = new String[noteBookList.size()];
        noteBookList = db.queryAllNotebooks();
        for (int i = 0; i < noteBookList.size(); i++) {
            books[i] = noteBookList.get(i).getNotebook_name() + "," + noteBookList.get(i).getNotebook_id();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, books);
        spinnerBook.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }



    public void doEdit(View view) {
        startActivity(new Intent(AddNoteActivity.this,Edit_State.class));
    }


    /**
     * add data
     * @param view
     */
    public void doAdd(View view) {
        selectNotebook = noteBookList.get(spinnerBook.getSelectedItemPosition());
        selectNote.setNotebook_id(selectNotebook.getNotebook_id())
                .setNote_name(title.getText().toString())
                .setNote_content(ques.getText().toString())
                .setNote_answer(ans.getText().toString());

        db.insertNote(selectNote);
        startService(new Intent(this, RemindService.class));

    }
}
