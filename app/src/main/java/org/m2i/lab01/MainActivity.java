package org.m2i.lab01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static final int COUNT_LIMIT = 5;
    Random random = new Random();
    int random_nbr, count=0; // 5 ?
    EditText nbr_txt;
    TextView response_txt, count_txt;
    Button submit_btn, new_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // generate random number
        random_nbr = random.nextInt(100);
    }

    @Override
    protected void onStart() {
        super.onStart();
        nbr_txt = findViewById(R.id.nbr_txt);
        response_txt = findViewById(R.id.response);
        submit_btn = findViewById(R.id.submit_btn);
        new_btn = findViewById(R.id.new_btn);
        count_txt = findViewById(R.id.count_txt);
        count_txt.setText(String.valueOf(COUNT_LIMIT - count));
        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random_nbr = random.nextInt(100);
                response_txt.setText("");
                submit_btn.setEnabled(true);
                count = 0 ;
                count_txt.setText(String.valueOf(COUNT_LIMIT ));
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count >= 4) {
                    Toast.makeText(getApplicationContext(), "You Lose",
                            Toast.LENGTH_LONG).show();
                    nbr_txt.setText("");
                    response_txt.setText("the random number was "+random_nbr);
                    submit_btn.setEnabled(false);
                    count = 0 ;
                    count_txt.setText(String.valueOf(COUNT_LIMIT ));
                    return;
                }
                int user_number = Integer.parseInt(nbr_txt.getText().toString());
                if(user_number == random_nbr)
                    response_txt.setText("Excellent !!!");
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