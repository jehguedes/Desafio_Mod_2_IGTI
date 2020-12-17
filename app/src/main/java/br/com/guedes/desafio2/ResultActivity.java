package br.com.guedes.desafio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;

public class ResultActivity extends AppCompatActivity {

    private AppCompatTextView tvResult;
    private AppCompatButton btnReinicia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.tvResult);
        btnReinicia = findViewById(R.id.btnReinicia);

        Integer result = getIntent().getIntExtra("resultado", 0);

        tvResult.setText(result.toString() + "%");

        btnReinicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultActivity.this.finish();
            }
        });
    }
}