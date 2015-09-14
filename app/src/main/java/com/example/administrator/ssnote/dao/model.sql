CREATE TABLE note(note_id INTEGER PRIMARY KEY autoincrement,notebook_id INTEGER,note_name text,note_content text,note_answer text,note_createtime INTEGER,note_nexttime INTEGER,note_style INTEGER,note_style INTEGER,note_level INTEGER)

CREATE TABLE notebook(notebook_id INTEGER PRIMARY KEY autoincrement,notebook_name text,notebook_desc text)

INSERT INTO notebook VALUES(null,"defult_book","")