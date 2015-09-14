package com.example.administrator.ssnote.entity;

import java.util.Date;

/**
 * Created by Administrator on 2015/9/9.
 */
public class Note {
    private Integer note_id;
    private Integer notebook_id;
    private String note_name;
    private String note_content;
    private String note_answer;
    private Long note_create_time;
    private Long note_next_time;
    private Integer note_style;
    private Integer note_level;

    public Note() {
        notebook_id = 1;
        note_name = "";
        note_content = "";
        note_answer = "";
        note_create_time = new Date().getTime();
        note_next_time = new Date().getTime() + 1L * 24 * 60 * 60 * 1000;
        note_style = 1;
        note_level = 0;
    }


    public Integer getNote_id() {
        return note_id;
    }

    public Note setNote_id(Integer note_id) {
        this.note_id = note_id;
        return this;
    }


    public String getNote_name() {
        return note_name;
    }

    public Note setNote_name(String note_name) {
        this.note_name = note_name;
        return this;
    }

    public String getNote_content() {
        return note_content;
    }

    public Note setNote_content(String note_content) {
        this.note_content = note_content;
        return this;
    }

    public String getNote_answer() {
        return note_answer;
    }

    public Note setNote_answer(String note_answer) {
        this.note_answer = note_answer;
        return this;
    }


    public Long getNote_next_time() {
        return note_next_time;
    }

    public Note setNote_next_time(Long note_next_time) {
        this.note_next_time = note_next_time;
        return this;
    }

    public Integer getNote_style() {
        return note_style;
    }

    public Note setNote_style(Integer note_style) {
        this.note_style = note_style;
        return this;
    }

    public Integer getNote_level() {
        return note_level;
    }

    public Note setNote_level(Integer note_level) {
        this.note_level = note_level;
        return this;
    }

    public Long getNote_create_time() {
        return note_create_time;
    }

    public Note setNote_create_time(Long note_create_time) {
        this.note_create_time = note_create_time;
        return this;
    }

    public Integer getNotebook_id() {
        return notebook_id;
    }

    public Note setNotebook_id(Integer notebook_id) {
        this.notebook_id = notebook_id;
        return this;
    }
}
