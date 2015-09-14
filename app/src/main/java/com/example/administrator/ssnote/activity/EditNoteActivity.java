package com.example.administrator.ssnote.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.base.BaseActivity;
import com.example.administrator.ssnote.service.RemindService;

/**
 * Created by Administrator on 2015/9/12.
 */
public class EditNoteActivity extends BaseActivity {

    private TextView message;
    private Spinner spinnerBook;
    private EditText title, ques, ans;

    private void init() {
        message = (TextView) findViewById(R.id.edit_msg);
        title = (EditText) findViewById(R.id.edit_title);
        ques = (EditText) findViewById(R.id.edit_ques);
        ans = (EditText) findViewById(R.id.edit_answer);
        initSpinner();
        initData();
    }

    private void initSpinner() {
        spinnerBook = (Spinner) findViewById(R.id.spinner_book);
        spinnerBook.setPrompt("book");
        String[] books = new String[noteBookList.size()];
        for (int i = 0; i < noteBookList.size(); i++) {
            if (noteBookList.get(i).getNotebook_id() == selectNotebook.getNotebook_id()) {
                noteBookList.set(i, noteBookList.get(0));
                noteBookList.set(0, selectNotebook);
            }
        }

        for (int i = 0; i < noteBookList.size(); i++) {
            books[i] = noteBookList.get(i).getNotebook_name() + "," + noteBookList.get(i).getNotebook_id();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, books);
        spinnerBook.setAdapter(adapter);
    }

    private void initData() {
        title.setText(selectNote.getNote_name());
        String msg = "";
        msg += selectNotebook.getNotebook_name();
        message.setText(msg);
        ques.setText(selectNote.getNote_content());
        ans.setText(selectNote.getNote_answer());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent =
                this.getIntent();
//        Bundle bundle = intent.getExtras();
        int note_id =
                intent.getIntExtra("note_id", -1);
//                bundle.getInt("note_id");
//                (preferences.getInt("note_id", 0));
        if (
                note_id != -1
                ) {
            selectNote = db.queryNoteById(note_id);
            selectNotebook = db.queryByNoteId(selectNote.getNotebook_id());
        }
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


    /**
     * save 数据
     *
     * @param view
     */
    public void doSave(View view) {
        selectNotebook=
        noteBookList.get(spinnerBook.getSelectedItemPosition());
        selectNote.setNote_name(title.getText().toString()).setNote_content(ques.getText().toString()).setNote_answer(ans.getText().toString()).setNotebook_id(selectNotebook.getNotebook_id());
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel("note", selectNote.getNote_id());
        super.doSave(view);

        startService(new Intent(this, RemindService.class));
        finish();
    }

    public void doEdit(View view) {
        startActivity(new Intent(EditNoteActivity.this, Edit_State.class));
    }
}
