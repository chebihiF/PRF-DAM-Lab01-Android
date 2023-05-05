package org.m2i.lab01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int COUNT_LIMIT, MAX_RANG, MIN_RANG;
    Random random = new Random();
    int random_nbr, count=0, score = 0; // 5 ?
    EditText nbr_txt;
    TextView response_txt, count_txt, message_info_txt, score_txt;
    Button submit_btn, new_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        COUNT_LIMIT = intent.getIntExtra("attempt_max", 5);
        MAX_RANG = intent.getIntExtra("rang_max", 100);
        MIN_RANG = intent.getIntExtra("rang_min", 0);

        // generate random number
        random_nbr = random.nextInt(MAX_RANG - MIN_RANG) + MIN_RANG;
    }

    @Override
    protected void onStart() {
        super.onStart();
        nbr_txt = findViewById(R.id.nbr_txt);
        response_txt = findViewById(R.id.response);
        submit_btn = findViewById(R.id.submit_btn);
        new_btn = findViewById(R.id.new_btn);
        count_txt = findViewById(R.id.count_txt);
        message_info_txt = findViewById(R.id.message_info_txt);
        score_txt = findViewById(R.id.score_txt);

        count_txt.setText(String.valueOf(COUNT_LIMIT - count));
        message_info_txt.setText("the random number is generated between "+MAX_RANG+" and "+MIN_RANG);

        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random_nbr = random.nextInt(MAX_RANG - MIN_RANG) + MIN_RANG;
                response_txt.setText("");
                submit_btn.setEnabled(true);
                nbr_txt.setEnabled(true);
                count = 0 ;
                count_txt.setText(String.valueOf(COUNT_LIMIT ));
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count >= COUNT_LIMIT) {
                    Toast.makeText(getApplicationContext(), "You Lose",
                            Toast.LENGTH_LONG).show();
                    nbr_txt.setText("");
                    response_txt.setText("the random number was "+random_nbr);
                    submit_btn.setEnabled(false);
                    nbr_txt.setEnabled(false);
                    count = 0 ;
                    count_txt.setText(String.valueOf(COUNT_LIMIT ));
                    score--;
                    score_txt.setText("Score = "+score);
                    return;
                }
                int user_number = Integer.parseInt(nbr_txt.getText().toString());
                if(user_number == random_nbr) {
                    response_txt.setText("Excellent !!!");
                    nbr_txt.setEnabled(false);
                    submit_btn.setEnabled(false);
                    score++;
                    score_txt.setText("Score = "+score);
                }
                else if (user_number > random_nbr) {
                    response_txt.setText("Smaller");
                }else {
                    response_txt.setText("Bigger");
                }
                count++;
                nbr_txt.setText("");
                nbr_txt.setFocusable(true);
                count_txt.setText(String.valueOf(COUNT_LIMIT - count));
            }
        });

    }
}