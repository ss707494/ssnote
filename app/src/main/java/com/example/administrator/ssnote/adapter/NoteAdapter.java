package com.example.administrator.ssnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.entity.Note;

import java.util.List;

/**
 * Created by Administrator on 2015/9/9.
 */
public class NoteAdapter extends ArrayAdapter<Note> {

    private Integer sourseId;


    public NoteAdapter(Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
        sourseId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note n = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(sourseId, null);
            viewHolder = new ViewHolder();
            viewHolder.id = (TextView) view.findViewById(R.id.note_id);
            viewHolder.name = (TextView) view.findViewById(R.id.note_name);
            viewHolder.msg = (TextView) view.findViewById(R.id.note_msg);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.id.setText(n.getNote_id().toString());
        viewHolder.name.setText(n.getNote_name());
        String msg =
                "bookID=" + n.getNotebook_id() +
                        "content=" + n.getNote_content()
                        + "\t" + n.getNote_answer() + "\t" + n.getNote_create_time();
        viewHolder.msg.setText(msg);
        return view;
    }

    class ViewHolder {
        TextView id,name, msg;
    }
}
