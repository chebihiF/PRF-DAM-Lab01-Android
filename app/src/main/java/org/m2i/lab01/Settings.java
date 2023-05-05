package org.m2i.lab01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    EditText rang_min_txt, rang_max_txt, attempt_max_txt;
    Button validate_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rang_max_txt = findViewById(R.id.max_range_txt);
        rang_min_txt = findViewById(R.id.min_range_txt);
        attempt_max_txt = findViewById(R.id.attempt_txt);
        validate_btn = findViewById(R.id.btn_valid);

        validate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min_rang = Integer.parseInt(rang_min_txt.getText().toString());
                int max_rang = Integer.parseInt(rang_max_txt.getText().toString());
                int attempt_max = Integer.parseInt(attempt_max_txt.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("rang_min",min_rang);
                intent.putExtra("rang_max",max_rang);
                intent.putExtra("attempt_max",attempt_max);
                startActivity(intent);
            }
        });

    }
}