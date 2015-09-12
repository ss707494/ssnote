package com.example.administrator.ssnote.base;
import android.app.Activity;
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
}
