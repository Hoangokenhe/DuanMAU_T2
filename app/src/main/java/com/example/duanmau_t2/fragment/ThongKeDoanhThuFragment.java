package com.example.duanmau_t2.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau_t2.R;
import com.example.duanmau_t2.dao.ThongKeDAO;


import java.util.Calendar;

public class ThongKeDoanhThuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thongkedoanhthu,container,false);

        EditText edtStart=view.findViewById(R.id.edtStart);
        EditText edtEnd=view.findViewById(R.id.edtEnd);
        Button btnThongKe=view.findViewById(R.id.btnThongKe);
        TextView txtKetQua=view.findViewById(R.id.txtKetQua);

        Calendar calendar=Calendar.getInstance();


        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtStart.setText(year + "/" + (month+1) + "/" + +dayOfMonth);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtEnd.setText(year + "/" + (month+1) + "/" + +dayOfMonth);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeDAO thongKeDAO=new ThongKeDAO(getContext());
                String ngaybatdau=edtStart.getText().toString();
                String ngayketthuc=edtEnd.getText().toString();
                int doanhthu= thongKeDAO.getDoanhThu(ngaybatdau,ngayketthuc);
                txtKetQua.setText(doanhthu+" VNĐ ");
            }
        });


        return view;
    }
}


