package com.refresh.pos.app03;

/*
1 Используйте Toast для вывода последовательности вызовов методов жизненного цикла при различных действиях пользователей. При запуске приложения, при повороте экрана, при нажатии кнопки “Домой”, восстановлении приложения, кнопки возврат, при удалении приложения из памяти…
2 Используйте Log.d для вывода этих же данных в консоль.
3 Имплементируйте функционал сохранения данных в вашем приложении: сохраняйте и восстанавливайте при пересоздании экрана данные о температуре или данные о параметрах чекбоксов.
4 Используйте паттерн Singleton для хранения данных.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "App03Logs";
    final String STATE_COUNT = "COUNT";
    private int cnt = 0;

    TextView text1 ;
    TextView text2 ;

    SingleData singleData = SingleData.getInstance();

    private void LogAndToast(String message)
    {
        Log.d(LOG_TAG, message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView)findViewById(R.id.editText1);
        text2 = (TextView)findViewById(R.id.editText2);

        LogAndToast("onCreate");
    }

    protected void onDestroy() {
        super.onDestroy();
        LogAndToast("onDestroy");
    }

    protected void onPause() {
        super.onPause();
        LogAndToast("onPause");
    }

    protected void onRestart() {
        super.onRestart();
        LogAndToast("onRestart");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_COUNT, cnt);
        LogAndToast("onSaveInstanceState");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cnt = savedInstanceState.getInt(STATE_COUNT);
        LogAndToast("onRestoreInstanceState");
    }

    protected void onResume() {
        super.onResume();
        LogAndToast("onResume ");
    }

    protected void onStart() {
        super.onStart();
        LogAndToast("onStart");
    }

    protected void onStop() {
        super.onStop();
        LogAndToast("onStop");
    }

    public void onclick(View v) {

        LogAndToast("Count Local = " + (++cnt));
        int tmp_cnt = singleData.getCnt();
        singleData.setCnt(++tmp_cnt);
        LogAndToast("Count singleData = " + singleData.getCnt());

        text1.setText(Integer.toString(cnt));
        text2.setText(Integer.toString(singleData.getCnt()));
    }
}
