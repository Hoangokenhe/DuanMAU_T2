package com.example.duanmau_t2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.duanmau_t2.dao.ThuThuDAO;
import com.example.duanmau_t2.fragment.QLLoaiSachFragment;
import com.example.duanmau_t2.fragment.QLPhieuMuonFragment;
import com.example.duanmau_t2.fragment.QLSachFragment;
import com.example.duanmau_t2.fragment.QLThanhVienFragment;
import com.example.duanmau_t2.fragment.ThongKeDoanhThuFragment;
import com.example.duanmau_t2.fragment.ThongKeTop10Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar=findViewById(R.id.toolBar);
        frameLayout=findViewById(R.id.framelayout);
        NavigationView navigationView=findViewById(R.id.navigationView);
        drawerLayout=findViewById(R.id.drawerLayout);
        View headerLayout=navigationView.getHeaderView(0);
        TextView txtTen=headerLayout.findViewById(R.id.txtTen);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.mQLPhieuMuon:
                        fragment = new QLPhieuMuonFragment();
                        break;
                    case R.id.mQLLoaiSach:
                        fragment = new QLLoaiSachFragment();
                        break;

                    case R.id.mThoat:
                        Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    case R.id.mDoiMatKhau:
                        showDialogDoiMatKhau();
                        break;

                    case R.id.mTop10:
                        fragment=new ThongKeTop10Fragment();
                        break;

                    case R.id.mDoanhThu:
                        fragment=new ThongKeDoanhThuFragment();
                        break;

                    case R.id.mQLThanhVien:
                        fragment=new QLThanhVienFragment();
                        break;
                    case R.id.mQLSach:
                        fragment=new QLSachFragment();
                        break;

                    default:
                        fragment = new QLPhieuMuonFragment();
                        break;
                }
                if (fragment != null) {


                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
                    toolbar.setTitle(item.getTitle());


                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        SharedPreferences sharedPreferences=getSharedPreferences("THONGTIN",MODE_PRIVATE);

        String loaiTK=sharedPreferences.getString("loaitaikhoan","");
        if (!loaiTK.equals("Admin")) {
            Menu menu=navigationView.getMenu();
            menu.findItem(R.id.mDoanhThu).setVisible(false);
            menu.findItem(R.id.mTop10).setVisible(false);
        }
        String hoten=sharedPreferences.getString("hoten","");
        txtTen.setText("Xin chào, "+ hoten);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDialogDoiMatKhau(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this)
                .setNegativeButton("Cập nhật",null)
                .setPositiveButton("Huy",null);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_doimatkhau,null);


        EditText edtOldPass=view.findViewById(R.id.edtPassOld);
        EditText edtNewPass=view.findViewById(R.id.edtNewPass);
        EditText edtReNewPass=view.findViewById(R.id.edtReNewPass);

        builder.setView(view);




        AlertDialog alertDialog= builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass=edtOldPass.getText().toString();
                String newPass=edtNewPass.getText().toString();
                String reNewPass=edtReNewPass.getText().toString();
                if (oldPass.equals("") || newPass.equals("") || reNewPass.equals("")) {
                    Toast.makeText(MainActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (newPass.equals(reNewPass)) {



                        SharedPreferences sharedPreferences=getSharedPreferences("THONGTIN",MODE_PRIVATE);
                        String matt=sharedPreferences.getString("matt","");
                        ThuThuDAO thuThuDAO=new ThuThuDAO(MainActivity.this);
                        int check=thuThuDAO.capNhatMatKhau(matt,oldPass,newPass);

                        if (check==1) {
                            Toast.makeText(MainActivity.this, "Cập nhât thành công", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,DangNhapActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else if (check==0) {
                            Toast.makeText(MainActivity.this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, " Cập nhật mật khẩu Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "tk mk ko trùng", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
}