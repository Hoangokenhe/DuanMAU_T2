package com.example.duanmau_t2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau_t2.dao.ThuThuDAO;


public class DangNhapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        EditText edtUser=findViewById(R.id.edtUser);
        EditText edtPass=findViewById(R.id.edtPass);
        Button btnDangNhap=findViewById(R.id.btnDangNhap);

            ThuThuDAO thuThuDAO=new ThuThuDAO(this);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=edtUser.getText().toString();
                String pass=edtPass.getText().toString();
                if (!thuThuDAO.checkDangNhap(user, pass)) {
                    Toast.makeText(DangNhapActivity.this, "Username và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                } else {


                    startActivity(new Intent(DangNhapActivity.this,MainActivity.class));

                }
            }
        });

    }
}