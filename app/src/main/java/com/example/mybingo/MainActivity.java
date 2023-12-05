package com.example.mybingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //最大値
    private int maxNumber=75;
    //数字の履歴
    private ArrayList<String> history=new ArrayList<>();
    //最大値の入力欄
    private EditText maxNumberEditText;
    //最大値の設定ボタン
    private Button registerMaxNumberButton;
    //次の数字を出すボタン
    private Button nextNumberButton;
    //現在の数字を表示するtextview
    private TextView currentNumberTextView;
    //履歴を表示するtextview
    private TextView historyTextView;
    //リセットボタン
    private Button resetButton;
    //最大値を表示するtextview
    private TextView currentmaxNumberTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ビューの変数を初期化する
        maxNumberEditText = findViewById(R.id.max_number);
        registerMaxNumberButton = findViewById(R.id.register_max_number);
        nextNumberButton = findViewById(R.id.next_number);
        currentNumberTextView=findViewById(R.id.current_number);
        historyTextView=findViewById(R.id.history);
        resetButton=findViewById(R.id.reset);
        currentmaxNumberTextView=findViewById(R.id.current_max_number);


        //最大値の初期値をEditTextにセットする
        maxNumberEditText.setText("" + maxNumber);
        currentmaxNumberTextView.setText("最大値:"+maxNumber);
        //最大値を更新する
        registerMaxNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力値を文字列で取り出す
                String maxNumberString = maxNumberEditText.getText().toString();
                //int型に変換してから代入する
                maxNumber = Integer.valueOf(maxNumberString);
                currentmaxNumberTextView.setText("最大値:"+maxNumber);

                Log.d("MainActivity", "maxNumber:" + maxNumber);
            }
        });
        //表示中の数字を更新する
        nextNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNextNumber();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxNumber=75;
                history.clear();
                historyTextView.setText(history.toString());
                currentNumberTextView.setText(""+0);
                currentmaxNumberTextView.setText("最大値:"+maxNumber);
            }
        });
    }
    //next_numberがタップされたときの処理
    private void onClickNextNumber(){
        Log.d("MainActivity","onClickNextNumber");
        //すべての数字が出尽くしたときの処理
        if (history.size()==maxNumber){
        }
        else {
            //maxNumberを考慮したランダムな数値
            int nextNumber = createRandomNumber();
            //重複していたらやりなおし
            //nextNumberが0以下、もしくは(出ることはほぼあり得ないが)maxNumber+1が出たらやりなおし
            while ((history.contains("" + nextNumber)) || nextNumber <= 0 || nextNumber==maxNumber+1) {
                Log.d("MainActivity", "重複したのでやりなおし");
                nextNumber = createRandomNumber();
            }
            //nextNumberを文字列に変換する
            String nextNumberStr = "" + nextNumber;
            //nextNumberを画面に表示する
            currentNumberTextView.setText("" + nextNumber);
            //履歴を残す
            history.add(nextNumberStr);
            //履歴を表示する
            historyTextView.setText(history.toString());
        }
    }
    private int createRandomNumber(){
        //maxNumberに1を加えることでキャストしたときにmaxNumberまで出るようになる。(random()が1.000000....ピッタリを出す確率はほぼ0であることに注意
        double randomNumber=Math.random()*(maxNumber+1);
        Log.d("MainActivity",""+randomNumber);

        return (int)randomNumber;
    }
}