package com.example.duanmau_t2.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanmau_t2.R;
import com.example.duanmau_t2.dao.ThanhVienDAO;
import com.example.duanmau_t2.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO=thanhVienDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.itam_recycler_thanhvien,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaTV.setText("Mã TV: "+list.get(position).getMatv());
        holder.txtHoTen.setText("Họ Tên: "+list.get(position).getHoten());
        holder.txtNamSinh.setText("Năm Sinh: "+list.get(position).getNamsinh());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCapNhatTT(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check= thanhVienDAO.xoaThongTinTV(list.get(holder.getAdapterPosition()).getMatv());
                switch (check) {
                    case 1:
                        Toast.makeText(context, "Xóa thành viên thành công", Toast.LENGTH_SHORT).show();
                        loadDaTa();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Thành viên tồn tại phiếu mượn , không được xóa", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtMaTV,txtHoTen,txtNamSinh;
        ImageView ivEdit,ivDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaTV=itemView.findViewById(R.id.txtMaTV);
            txtHoTen=itemView.findViewById(R.id.txtHoTen);
            txtNamSinh=itemView.findViewById(R.id.txtNamSinh);
            ivEdit=itemView.findViewById(R.id.ivEdit);
            ivDel=itemView.findViewById(R.id.ivDel);
        }
    }
    private void showDialogCapNhatTT(ThanhVien thanhVien){

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.dialog_chinhsua_thanhvien,null);
        builder.setView(view);
        TextView txtMatv=view.findViewById(R.id.txtMaTV);
        EditText edtHoTen=view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh=view.findViewById(R.id.edtNamSinh);

        txtMatv.setText("Mã TV: " +thanhVien.getMatv());
        edtHoTen.setText(thanhVien.getHoten());
        edtNamSinh.setText(thanhVien.getNamsinh());

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String hoten=edtHoTen.getText().toString();
                String namsinh=edtNamSinh.getText().toString();
                int id =thanhVien.getMatv();

                boolean check=thanhVienDAO.capNhatThongTinTV(id,hoten,namsinh);
                if (check) {
                    Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    loadDaTa();
                }else {
                    Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    private void loadDaTa(){

        list.clear();
        list=thanhVienDAO.getDSThanhVien();
        notifyDataSetChanged();
    }
}
