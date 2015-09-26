package com.example.administrator.ssnote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.util.HandleTimeUtil;

/**
 * Created by Administrator on 2015/9/12.
 */
public class NoticeActivity extends EditNoteActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_notice_note);
        init();
        title.setFocusable(false);
        ques.setFocusable(false);
        ans.setText("");
        ans.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ans.setText(selectNote.getNote_answer());
                return true;
            }
        });
        ans.setFocusable(false);
    }

    @Override
    public void doEdit(View view) {
        startActivity(new Intent(NoticeActivity.this, EditNoteActivity.class));
    }

    public void doGood(View view) {
        Integer note_style = selectNote.getNote_style();
        Integer note_level = selectNote.getNote_level();
        if (note_style > 2) {
            if (note_level > 8) {
                selectNote.setNote_level(9);
            } else {
                selectNote.setNote_level(note_level + 1);
            }
        } else {
            selectNote.setNote_style(note_style + 1);
        }

        doAct(view);
    }

    public void doOk(View view) {
        Integer note_style = selectNote.getNote_style();
        Integer note_level = selectNote.getNote_level();
            if (note_level > 8) {
                selectNote.setNote_style(note_style>2?3:note_style+1);
            } else {
                selectNote.setNote_level(note_level + 1);
            }

        doAct(view);
    }
    public void doNearly(View view) {
        Integer note_style = selectNote.getNote_style();
        Integer note_level = selectNote.getNote_level();
        if (note_level < 1) {
            selectNote.setNote_style(note_style<2?1:note_style+1);
        } else {
            selectNote.setNote_level(note_level + 1);
        }
        doAct(view);
    }
    public void doForget(View view) {
        Integer note_style = selectNote.getNote_style();
            Integer note_level = selectNote.getNote_level();
        if (note_style < 2) {
            if (note_level < 1) {
                selectNote.setNote_level(0);
            } else {
                selectNote.setNote_level(note_level - 1);
            }
        } else {
            selectNote.setNote_style(note_style - 1);
        }

        doAct(view);
    }

    private void doAct(View view) {
        Long x=
                HandleTimeUtil.handle(selectNote.getNote_style(), selectNote.getNote_level(), selectNote.getNote_next_time());
        selectNote.setNote_next_time(x);
        doSave(view);
    }
}
