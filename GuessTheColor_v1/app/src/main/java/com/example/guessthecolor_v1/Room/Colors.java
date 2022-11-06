package com.example.guessthecolor_v1.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "colors")
public class Colors {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "random")
    public String random;

    @ColumnInfo(name = "color")
    public String color;

    @ColumnInfo(name = "hint")
    public String hint;

    @ColumnInfo(name = "answer")
    public String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}


