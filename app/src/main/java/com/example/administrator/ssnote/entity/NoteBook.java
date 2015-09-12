package com.example.administrator.ssnote.entity;

/**
 * Created by Administrator on 2015/9/9.
 */
public class NoteBook {
    private Integer notebook_id;
    private String notebook_name;
    private String notebook_desc;

    public Integer getNotebook_id() {
        return notebook_id;
    }

    public NoteBook setNotebook_id(Integer notebook_id) {
        this.notebook_id = notebook_id;
        return this;
    }

    public String getNotebook_name() {
        return notebook_name;
    }

    public NoteBook setNotebook_name(String notebook_name) {
        this.notebook_name = notebook_name;
        return this;
    }

    public String getNotebook_desc() {
        return notebook_desc;
    }

    public NoteBook setNotebook_desc(String notebook_desc) {
        this.notebook_desc = notebook_desc;
        return this;
    }
}
