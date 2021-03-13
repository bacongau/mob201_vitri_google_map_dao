package com.example.googlemaptt6.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintSet;

import com.example.googlemaptt6.Activity.MainActivity;
import com.example.googlemaptt6.Object.KhachHang;
import com.example.googlemaptt6.R;
import com.example.googlemaptt6.SQLIte.DAO;

import java.util.ArrayList;
import java.util.List;

public class KhachHangAdapter extends BaseAdapter {
    Context context;
    List<KhachHang> khachHangList ;
    DAO dao;

    public KhachHangAdapter(Context context, List<KhachHang> khachHangList) {
        this.context = context;
        this.khachHangList = khachHangList;
        dao= new DAO(context);
    }

    @Override
    public int getCount() {
        return khachHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return khachHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        khai báo viewholder
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.kh_row,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_id = convertView.findViewById(R.id.tv_id);
            viewHolder.tv_name =convertView.findViewById(R.id.tv_name);
            viewHolder.tv_longtitude = convertView.findViewById(R.id.tv_long);
            viewHolder.tv_lattitude = convertView.findViewById(R.id.tv_lat);
            viewHolder.btn_xem = convertView.findViewById(R.id.btn_xem);
            viewHolder.btn_xoa = convertView.findViewById(R.id.btn_xoa);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        đặt thông tin cho danh sách
        viewHolder.tv_id.setText("ID: "+khachHangList.get(position).getId());
        viewHolder.tv_name.setText("Name: "+khachHangList.get(position).getName());
        viewHolder.tv_longtitude.setText("Longtitude: "+khachHangList.get(position).getLongitude());
        viewHolder.tv_lattitude.setText("Lattitude: "+khachHangList.get(position).getLatitude());
//      đặt sự kiện cho nút xoá
        viewHolder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//      khai báo dialog confirm
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Xoá Khách Hàng");
                builder.setMessage("Bạn xác định sẽ xoá khác hàng này ra khỏi danh sách");
//                đặt sự kiện cho nút xác nhận (trong dialog confirm)
                builder.setPositiveButton("Xác nhận",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int result = dao.delete(khachHangList.get(position).getId());
                                if(result>0){
                                    Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                                    ((MainActivity)context).loadData();
                                    dialog.dismiss();
                                }
                                else{
                                    Toast.makeText(context, "Xoá thất bại", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });
//                đặt sự kiện cho nút huỷ (trong dialog confirm)
                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
//      đặt sự kiện cho nút xem
        viewHolder.btn_xem.setOnClickListener(new View.OnClickListener() {
            @Override
//            kiểm tra lại kiểu của kinh độ vĩ độ
            public void onClick(View v) {
                float longtitude;
                float lattitude;
                try {
                    longtitude = Float.parseFloat(khachHangList.get(position).getLongitude());
                    lattitude = Float.parseFloat(khachHangList.get(position).getLatitude());
                }catch (NumberFormatException err){
                    longtitude =41;
                    lattitude  =105;
                }
//                chuyển trang sang map activity
                ((MainActivity)context).switchPage(longtitude,lattitude);
            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView tv_id;
        TextView tv_name;
        TextView tv_longtitude;
        TextView tv_lattitude;
        Button btn_xem;
        Button btn_xoa;
    }
}
