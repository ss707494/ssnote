package com.example.administrator.ssnote.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.adapter.NoteAdapter;
import com.example.administrator.ssnote.base.BaseActivity;
import com.example.administrator.ssnote.entity.Note;
import com.example.administrator.ssnote.entity.NoteBook;
import com.example.administrator.ssnote.service.RemindService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/9/9.
 */
public class FirstActivity extends BaseActivity {

    /**
     * 加载资源
     *
     * @param savedInstanceState
     */
    private ListView list_first, list_view_red;
    private ArrayAdapter<Note> adapter, adapter_green;
    private
    List<Note> list_green = new ArrayList<>(), list_red = new ArrayList<>();


    private void init() {
        list_view_red = (ListView) findViewById(R.id.list_first_red);
        list_first = (ListView) findViewById(R.id.test_list_view);


        for (Note note : noteList) {
            if (note.getNote_next_time() < new Date().getTime()) {
                list_red.add(note);
            } else {
                list_green.add(note);
            }
        }
        adapter = new NoteAdapter(this, R.layout.note_item_out, list_red);
        list_view_red.setAdapter(adapter);
        list_view_red.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectNote = list_red.get(position);
                selectNotebook = db.queryByNoteId(selectNote.getNotebook_id());
                startActivity(new Intent(FirstActivity.this, EditNoteActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        adapter_green = new NoteAdapter(this, R.layout.note_item, list_green);
        list_first.setAdapter(adapter_green);
        list_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectNote = list_green.get(position);
                selectNotebook = db.queryByNoteId(selectNote.getNotebook_id());
                startActivity(new Intent(FirstActivity.this, EditNoteActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_dao);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteList = db.queryAllNotes();
        list_red.clear();
        list_green.clear();
        for (Note note : noteList) {
            if (note.getNote_next_time() < new Date().getTime()) {
                list_red.add(note);
            } else {
                list_green.add(note);
            }
        }
        adapter.notifyDataSetChanged();
        adapter_green.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startService(new Intent(FirstActivity.this, RemindService.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onStart();
    }

    /**
     * Test code
     *
     * @param view
     */
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    public void doTestUpdate(View view) {
        startService(new Intent(FirstActivity.this, RemindService.class));

    }

    public void doShow(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder =
                new Notification.Builder(this);
        builder.setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_reminder))
                .setSmallIcon(R.drawable.ic_reminder)
                .setTicker("test ticker")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("contentTitle")
                .setContentText("text ")
        ;

        Notification notification = builder.getNotification();
//        new Notification(R.drawable.ic_reminder, "test sscc", System.currentTimeMillis();
//        notification.setLatestEventInfo();
        manager.notify("ss", 2323, notification);
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
        for (int i = 0; i < 8; i++) {

            Note n = new Note().setNotebook_id(ra.nextInt(3) + 1).setNote_name("name" + ra.nextInt(100))
                    .setNote_content("content" + ra.nextInt(100)).setNote_answer("answer" + ra.nextBoolean())
                    .setNote_create_time(new Date().getTime() - ra.nextInt(4) * 24l * 60 * 60 * 1000)
                    .setNote_next_time(new Date().getTime() + (ra.nextInt(4) + 1) * 24l * 60 * 60 * 1000)
                    .setNote_style(ra.nextInt(3)).setNote_level(ra.nextInt(9));
            db.insertNote(n);
        }
        Toast.makeText(this, "add complite", Toast.LENGTH_SHORT).show();
        onResume();
    }

    public void doAddNote(View view) {
        startActivity(new Intent(FirstActivity.this, AddNoteActivity.class));
    }
}
