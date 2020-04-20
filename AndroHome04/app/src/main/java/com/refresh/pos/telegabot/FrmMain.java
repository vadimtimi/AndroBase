package com.refresh.pos.telegabot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pengrad.telegrambot.impl.TelegramBotClient;
import com.pengrad.telegrambot.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.pengrad.telegrambot.TelegramBot;

public class FrmMain extends AppCompatActivity implements paramSettings   {

    public static String telegrammToken;
    public static String telegrammUser;

    private int REQCODESETTINGS = 1;

    // Показать всплывающее сообщение и пишет Logcat
    private void showToast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
        Log.i( "showToast", txt );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_main);

        if(savedInstanceState != null)
        {
            showToast("savedInstanceState");

            if(savedInstanceState.getString( "telegrammToken", "" ).equals( "" ) ) {
                showToast(" onCreatetelegrammToken NO FREE");
            }
        }

        showToast("onCreate");
    }

    @Override
    protected void  onStart()
    {
        super.onStart();

        showToast("onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();

        showToast("onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(outState != null) {
            outState.putString( "telegrammToken", telegrammToken );
            outState.putString( "telegrammUser", telegrammUser );
        }

        super.onSaveInstanceState(outState);
        showToast("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        showToast("onRestoreInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        showToast("onRestart");
    }
    @Override
    protected void onResume() {
        super.onResume();

        showToast("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();

        showToast("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        showToast("onDestroy");
    }

    // Обработка нажатия кнопки "Settings"
    public void onClickSettings(View v) {
        Intent intent = new Intent(this, FrmSettings.class);
        startActivityForResult(intent, REQCODESETTINGS);

        showToast("onClickSettings");
    }

    // Обработка нажатия кнопки "Info"
    public void onClickInfo(View v) {
        String url = getString(R.string.url_telegram);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        showToast("onClickInfo");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult( requestCode,  resultCode, data);
        if(requestCode == REQCODESETTINGS){
            if(resultCode == Activity.RESULT_OK){
                String msg = data.getStringExtra("telegrammToken");

                showToast("onActivityResult " + msg);

            }if(resultCode == Activity.RESULT_CANCELED){
                String msg = ("something went wrong");

                showToast("onActivityResult " + msg);
            }

        }
    }
}
