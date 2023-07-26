package com.example.duanmau_t2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ManHinhChaoActivity extends AppCompatActivity {
    TextInputEditText edt_maSV;
    Button btn_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_man_hinh_chao);
//
//        ImageView ivlogo = findViewById(R.id.ivlogo);
//
//        Glide.with(this).load(R.mipmap.book).into(ivlogo);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//               startActivity(new Intent(ManHinhChaoActivity.this,DangNhapActivity.class));
//
//            }
//        },3000);
//    }
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_man_hinh_chao);

        edt_maSV = findViewById(R.id.edt_maSV);
        btn_join = findViewById(R.id.btn_join);


        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_maSV.getText().toString().equalsIgnoreCase("PH19332")){
                    startActivity(new Intent(ManHinhChaoActivity.this, DangNhapActivity.class));
                    finish();
                }else {
                    Toast.makeText(ManHinhChaoActivity.this, "Mã sinh viên không đúng vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },5000);


    }
}
