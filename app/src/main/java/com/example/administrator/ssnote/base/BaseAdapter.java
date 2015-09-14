package com.example.administrator.ssnote.base;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.administrator.ssnote.entity.Note;

import java.util.List;

/**
 * Created by Administrator on 2015/9/13.
 */
public class BaseAdapter extends ArrayAdapter<Note>{



    public BaseAdapter(Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
    }



}
