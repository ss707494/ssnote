package com.example.administrator.ssnote.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.administrator.ssnote.R;
import com.example.administrator.ssnote.base.BaseActivity;
import com.example.administrator.ssnote.util.DateUtil;

import java.text.ParseException;

public class Edit_State extends BaseActivity {

    private TextView state_name;
    private EditText
    state_level, state_style;
    private TimePicker timePicker;
    private DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_state);
        init();
    }

    private void init() {
        state_name = (TextView) findViewById(R.id.state_name);
        state_level = (EditText) findViewById(R.id.state_level);
        state_style = (EditText) findViewById(R.id.state_style);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        initData();
    }

    private void initData() {
        state_name.setText(selectNote.getNote_name());
        state_level.setText(String.valueOf(selectNote.getNote_level()));
        state_style.setText(String.valueOf(selectNote.getNote_style()));
        String[] timeArr = DateUtil.sixformatToArray(selectNote.getNote_next_time());

        datePicker.updateDate(Integer.valueOf(timeArr[0]), Integer.valueOf(timeArr[1])-1, Integer.valueOf(timeArr[2]));
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(Integer.valueOf(timeArr[3]));
        timePicker.setCurrentMinute(Integer.valueOf(timeArr[4]));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit__state, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUpdate(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doUpdate(View view) {
        if (!timePicker.is24HourView()) {
            timePicker.setIs24HourView(true);
        }
        String date = datePicker.getYear() + "," +( datePicker.getMonth()+1) + "," +
                datePicker.getDayOfMonth() + "," +
                timePicker.getCurrentHour() + "," + timePicker.getCurrentMinute()
                + ",00";
        try {
            selectNote.setNote_level(Integer.valueOf(state_level.getText().toString()))
                    .setNote_style(Integer.valueOf(state_style.getText().toString()))
                    .setNote_next_time(
                            DateUtil.six_dataformate.parse(date).getTime()
                    );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finish();
    }
}
