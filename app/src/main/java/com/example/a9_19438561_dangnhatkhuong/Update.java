package com.example.a9_19438561_dangnhatkhuong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        CongViec congViec = (CongViec) getIntent().getSerializableExtra("congViec");
        EditText txtCongViec = findViewById(R.id.txtCongViecInputUpdate);
        EditText txtGhiChu = findViewById(R.id.txtGhiChuInputUpdate);
        txtCongViec.setText(String.valueOf(congViec.getTenCongViec()));
        txtGhiChu.setText(String.valueOf(congViec.getGhiChu()));

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                congViec.setTenCongViec(txtCongViec.getText().toString());
                congViec.setGhiChu(txtGhiChu.getText().toString());
                MainActivity.getMainActive().update(congViec);
                txtCongViec.setText("");
                txtGhiChu.setText("");
                Intent intent = new Intent(Update.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}