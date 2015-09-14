package com.example.administrator.ssnote.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.activity.EditNoteActivity;
import com.example.administrator.ssnote.base.BaseApplication;
import com.example.administrator.ssnote.dao.SSNoteDb;
import com.example.administrator.ssnote.entity.Note;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/9/13.
 */
public class RemindService extends IntentService{

    private SSNoteDb db;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public RemindService() {
        super("RemindService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long currentTime=new Date().getTime();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder=new Notification.Builder(this);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_reminder))
                .setAutoCancel(true);
        List<Note> noteList = db.queryAllNotes();
        for (int i = 0; i < noteList.size(); i++) {
            if (noteList.get(i).getNote_next_time() > currentTime) {
//                Toast.makeText(BaseApplication.getContext(), "nothing", Toast.LENGTH_SHORT).show();

                return;
            } else {
//                manager.cancel("note",noteList.get(i).getNote_id());
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext()).edit();
                editor.putInt("note_id", noteList.get(i).getNote_id()).commit();
                intent = new Intent(BaseApplication.getContext(), EditNoteActivity.class);

//                intent.putExtra("note_id", noteList.get(i).getNote_id());
                PendingIntent pi = PendingIntent.getActivity(BaseApplication.getContext(), UUID.randomUUID().hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentTitle(noteList.get(i).getNote_name())
                        .setContentIntent(pi)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_reminder)
                        .setTicker("test")
                ;

                Notification notification = builder.getNotification();
                manager.notify("note", noteList.get(i).getNote_id(), notification);
            }
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = SSNoteDb.getInstance(this);
    }
}
