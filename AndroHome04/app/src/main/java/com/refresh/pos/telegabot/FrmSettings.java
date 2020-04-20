package com.refresh.pos.telegabot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FrmSettings extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_settings);

        // Доступ к переменным другого класса
        FrmMain.telegrammToken = "";
        FrmMain.telegrammUser= "";

        Button cmdSave = findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cmdSave:

                EditText txtToken = findViewById(R.id.txtToken);
                EditText txtUser = findViewById(R.id.txtUser);
                // Доступ к переменным другого класса


                FrmMain.telegrammToken = txtToken.getText().toString().trim();
                FrmMain.telegrammUser= txtUser.getText().toString().trim();

                Toast.makeText(this, "Settings Save!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("telegrammToken", txtToken.getText().toString().trim());
                setResult(RESULT_OK, intent);
                finish();

                return;
            default:
                return;
        }
    }

}
