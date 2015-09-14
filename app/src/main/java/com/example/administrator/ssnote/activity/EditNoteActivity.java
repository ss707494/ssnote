package com.example.administrator.ssnote.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.base.BaseActivity;

/**
 * Created by Administrator on 2015/9/12.
 */
public class EditNoteActivity extends BaseActivity {

    private TextView message;
    private EditText title,ques,ans;

    private void init() {
        message = (TextView) findViewById(R.id.edit_msg);
        title = (EditText) findViewById(R.id.edit_title);
        ques = (EditText) findViewById(R.id.edit_ques);
        ans = (EditText) findViewById(R.id.edit_answer);
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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Integer note_id=
                (preferences.getInt("note_id", 0));
        if (
                note_id != null
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
     * @param view
     */
    public void doSave(View view) {
        selectNote.setNote_name(title.getText().toString()).setNote_content(ques.getText().toString()).setNote_answer(ans.getText().toString());
        super.doSave(view);
    }

    public void doEdit(View view) {
        startActivity(new Intent(EditNoteActivity.this,Edit_State.class));
    }
}
