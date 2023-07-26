package com.example.duanmau_t2.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanmau_t2.R;
import com.example.duanmau_t2.dao.PhieuMuonDAO;
import com.example.duanmau_t2.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder>{

    private ArrayList<PhieuMuon> list;
    private Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=((Activity)context).getLayoutInflater();

        View view=inflater.inflate(R.layout.item_recycler_phieumuon,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtMaPM.setText("Ma PM: " + list.get(position).getMapm());
        holder.txtMaTV.setText("Ma TV: " + list.get(position).getMatv());
        holder.txtMaTT.setText("Ma TT: " + list.get(position).getMatt());
        holder.txtMaSach.setText("Ma Sach: " + list.get(position).getMasach());
        holder.txtTenTV.setText("Ten TV: " + list.get(position).getTentv());

        holder.txtTenTT.setText("Ten TT: " + list.get(position).getTentt());

        holder.txtTenSach.setText("Ten Sach: " + list.get(position).getTensach());
        holder.txtNgay.setText("Ngay: " + list.get(position).getNgay());
        String trangthai="";
        if(list.get(position).getTrasach()==1) {
            trangthai="Da Tra Sach";
            holder.btnTraSach.setVisibility(View.INVISIBLE);
        }else {
            trangthai="Chua Tra Sach";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.txtTrangThai.setText("Trang Thai: " + trangthai);
        holder.txtTien.setText("Tien: " + list.get(position).getTienthue());


        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonDAO phieuMuonDAO=new PhieuMuonDAO(context);
                boolean kiemtra = phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());
                if (kiemtra) {
                    list.clear();
                    list=phieuMuonDAO.getDsPhieuMuon();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thay đổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtMaPM,txtMaTV,txtMaTT,txtTenTV,txtTenTT,txtMaSach,txtTenSach,txtNgay,txtTrangThai,txtTien;
        Button btnTraSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaPM=itemView.findViewById(R.id.txtMaPM);
            txtMaTV=itemView.findViewById(R.id.txtMaTV);
            txtTenTV=itemView.findViewById(R.id.txtTenTV);
            txtMaTT=itemView.findViewById(R.id.txtMaTT);
            txtTenTT=itemView.findViewById(R.id.txtTenTT);
            txtMaSach=itemView.findViewById(R.id.txtMaSach);
            txtTenSach=itemView.findViewById(R.id.txtTenSach);
            txtNgay=itemView.findViewById(R.id.txtNgay);
            txtTrangThai=itemView.findViewById(R.id.txtTrangThai);
            txtTien=itemView.findViewById(R.id.txtTien);
            btnTraSach=itemView.findViewById(R.id.btnTraSach);
        }
    }

}
