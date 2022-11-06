package com.example.guessthecolor_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.guessthecolor_v1.Room.Colors;
import com.example.guessthecolor_v1.Room.ColorsDao;
import com.example.guessthecolor_v1.Room.ColorsDatabase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button clearBtn, hintBtn;
    TextView lvlTxt;
    EditText displayTxt;
    ImageView colorImg;
    LinearLayout LLinside1, LLinside2;
    int count = 0, finalcount = 4;
    String hintTxt = "";
    String randomTxt = "";
    String answerTxt = "";
    int[] uid = {0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearBtn = findViewById(R.id.idBtnclear);
        hintBtn = findViewById(R.id.idBtnhint);
        lvlTxt = findViewById(R.id.idTVtext);
        displayTxt = findViewById(R.id.idTVdisplay1);
        colorImg = findViewById(R.id.idIVcolor);
        LLinside1 = findViewById(R.id.idLLinside1);
        LLinside2 = findViewById(R.id.idLLinside2);


        changeLevel();
        displayTxt.setText("");

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                uid[0]++;
//                changeLevel(new int[]{uid[0]});
                count = 0;
                displayTxt.setText("");
            }
        });

        hintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, hintTxt, Toast.LENGTH_SHORT).show();
            }
        });

    }

    void changeLevel() {

        if (uid[0] == 3) {
            startActivity(new Intent(getApplicationContext(), ExitScreen.class));
            return;
        }

        int divider = 0;
        ColorsDatabase db = ColorsDatabase.getDB(getApplicationContext());
        ColorsDao userDao = db.userDao();
        List<Colors> colors = userDao.loadAllByIds(new int[]{++uid[0]});
        for (Colors col :
                colors) {
            lvlTxt.setText("Level ".concat(String.valueOf(col.getUid())));
            colorImg.setBackgroundColor(Color.parseColor(col.getColor()));
            hintTxt = col.getHint();
            answerTxt = col.getAnswer();
            randomTxt = col.getRandom();
            for (Character ch :
                    col.getRandom().toCharArray()) {
                divider++;
                if (divider < 8)
                    addContentView(LLinside1, ch.toString());
                else
                    addContentView(LLinside2, ch.toString());
            }
        }
    }

    void addContentView(LinearLayout LLinside, String txt) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayoutParams.leftMargin = 5;
        linearLayoutParams.rightMargin = 5;

        final TextView textView = new TextView(this);

        textView.setLayoutParams(linearLayoutParams);
        textView.setText(txt);
        textView.setPadding(10, 10, 10, 10);
        textView.setBackground(getResources().getDrawable(R.drawable.background_space));
        textView.setTextSize(18);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setFocusable(true);
        textView.setClickable(true);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < finalcount) {
                    if (count == 0)
                        displayTxt.setText("");
                    displayTxt.setText(displayTxt.getText().toString().concat(txt));
                    textView.animate().alpha(0).setDuration(300);
                    count++;
                    if (count == finalcount) {
                        doValidate();
                    }
                }
            }
        });
        LLinside.addView(textView);
    }

    void doValidate() {
        count = 0;
        if (displayTxt.getText().toString().equals(answerTxt)) {
            Toast.makeText(this, "Correct, Horray!", Toast.LENGTH_SHORT).show();
            LLinside1.removeAllViews();
            LLinside2.removeAllViews();
            displayTxt.setText("");
            changeLevel();
        } else {
            Toast.makeText(this, "Incorrect, use hint", Toast.LENGTH_SHORT).show();
            LLinside1.removeAllViews();
            LLinside2.removeAllViews();
            displayTxt.setText("");
            int divider = 0;
            for (Character ch :
                    randomTxt.toCharArray()) {
                divider++;
                if (divider < 8)
                    addContentView(LLinside1, ch.toString());
                else
                    addContentView(LLinside2, ch.toString());
            }
        }
    }

}