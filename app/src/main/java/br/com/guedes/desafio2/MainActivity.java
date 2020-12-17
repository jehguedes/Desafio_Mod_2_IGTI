package br.com.guedes.desafio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.guedes.desafio2.models.Question;

public class MainActivity extends AppCompatActivity {

    private AppCompatTextView tvQuestion;
    private AppCompatButton btnTrue, btnFalse;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);

        getJsonFile("perguntas.json");
        Quiz(questionList, 0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("RESTART", "Chegou aqui");
        getJsonFile("perguntas.json");
        Quiz(questionList, 0);

    }

    private void getJsonFile(String fileName){
        questionList = new ArrayList<>();
        String jsonFileString = Utils.getJsonFromAssets(this, fileName);
        Type listQuestionType = new TypeToken<List<Question>>() { }.getType();
        Gson gson = new Gson();

        questionList = gson.fromJson(jsonFileString, listQuestionType);
    }

    private void Quiz(List<Question> questionList, Integer questionIndex){
        if (questionIndex > questionList.size() - 1){
            endQuiz();
            return;
        }

        Question question = questionList.get(questionIndex);

        tvQuestion.setText(question.getPergunta());

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.getResposta()) {
                    questionList.get(questionIndex).setRespostaUser(1);
                    toastMessage("Acertou! :)");
                }else {
                    questionList.get(questionIndex).setRespostaUser(0);
                    toastMessage("errou! :(");
                }
                int nextIndex = questionIndex + 1;
                Quiz(questionList, nextIndex);

            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.getResposta()) {
                    questionList.get(questionIndex).setRespostaUser(0);
                    toastMessage("errou! :(");
                }else {
                    questionList.get(questionIndex).setRespostaUser(1);
                    toastMessage("Acertou! :)");
                }
                int nextIndex = questionIndex + 1;
                Quiz(questionList, nextIndex);
            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(MainActivity.this,"Você " + message, Toast.LENGTH_SHORT).show();
    }

    private void endQuiz() {
        tvQuestion.setText("Fim das perguntas");

        int result = calcResult(questionList);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("resultado", result);
        startActivity(intent);

    }

    private int calcResult (List<Question> questionList){
        int count = 0;
        for (Question question : questionList){
            if( question.getRespostaUser() == 1){
                count++;
            }
        }
        int result = (count * 100) / questionList.size();

        return result;
    }


}