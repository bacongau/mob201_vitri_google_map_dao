package com.example.googlemaptt6.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.googlemaptt6.Adapter.KhachHangAdapter;
import com.example.googlemaptt6.Object.KhachHang;
import com.example.googlemaptt6.R;
import com.example.googlemaptt6.SQLIte.DAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<KhachHang> khachHangList;
    DAO dao;
    FloatingActionButton fab_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       Ánh xạ
        fab_add = findViewById(R.id.fab_add);
//        đặt dữ liệu vào listview
        loadData();
//      Thêm khách hàng (fab)
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                khai báo
                TextInputLayout ed_id,ed_name,ed_long,ed_lat;
                Button btn_add;
//                ánh xạ
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.fab);

                dialog.setCanceledOnTouchOutside(false);

                ed_id = dialog.findViewById(R.id.ed_id);
                ed_name = dialog.findViewById(R.id.ed_name);
                ed_long = dialog.findViewById(R.id.ed_long);
                ed_lat = dialog.findViewById(R.id.ed_lat);
                btn_add = dialog.findViewById(R.id.btn_add);

//              đặt sự kiện xoá lỗi khi nhập kí tự vào edtext
                ed_id.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        ed_id.setError("");
                        ed_name.setError("");
                        ed_long.setError("");
                        ed_lat.setError("");
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                ed_name.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        ed_id.setError("");
                        ed_name.setError("");
                        ed_long.setError("");
                        ed_lat.setError("");
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                ed_long.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        ed_id.setError("");
                        ed_name.setError("");
                        ed_long.setError("");
                        ed_lat.setError("");
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                ed_lat.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        ed_id.setError("");
                        ed_name.setError("");
                        ed_long.setError("");
                        ed_lat.setError("");
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });


//              nút xác nhận thêm khác hàng (nằm trong fab)
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        kiểm tra edtext
                        Boolean err = false;
                        if(ed_id.getEditText().getText().toString().equals("")){
                            ed_id.setError("Vui lòng nhập vào id");
                            err=true;
                        }
                        if(ed_name.getEditText().getText().toString().equals("")){
                            ed_name.setError("Vui lòng nhập vào tên");
                            err=true;
                        }
                        if(ed_long.getEditText().getText().toString().equals("")){
                            ed_long.setError("Vui lòng nhập vào kinh độ");
                            err=true;
                        }
                        if(ed_lat.getEditText().getText().toString().equals("")){
                            ed_lat.setError("Vui lòng nhập vào vĩ độ");
                            err=true;
                        }

                        try{
                            float longtitude = Float.parseFloat(ed_long.getEditText().getText().toString());
                        }catch (NumberFormatException error){
                            ed_long.setError("Mời bạn nhập vào kiểu int hoặc float");
                            err =true;
                        }

                        try{
                            float lattitude = Float.parseFloat(ed_lat.getEditText().getText().toString());
                        }catch (NumberFormatException error){
                            ed_lat.setError("Mời bạn nhập vào kiểu int hoặc float");
                            err = true;
                        }
//                      khi kiểm tra không có lỗi
                        if(!err){
                           long result = dao.insert(new KhachHang(
                                   ed_id.getEditText().getText().toString(),
                                   ed_name.getEditText().getText().toString(),
                                   ed_long.getEditText().getText().toString(),
                                   ed_lat.getEditText().getText().toString()
                           ));
//                          nếu thê thành công
                           if(result>0){
                               Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                               dialog.dismiss();
//                               ObjectAnimator animator = ObjectAnimator.ofFloat(fab_add,
//                                       "translationX",
//                                       20f);
//                               animator.setDuration(100);
//                               animator.setRepeatCount(10);
//                               animator.setRepeatMode(ValueAnimator.REVERSE);
//                               animator.start();
//                              hiệu ứng rung
                               Animation animation;
                               animation = AnimationUtils.loadAnimation(getApplicationContext(),
                                       R.anim.hieuungrung);
                               fab_add.startAnimation(animation);
//                              đặt dữ liệu vào listview
                               loadData();
                           }
//                           nếu thêm thất bại
                           else{
                               Toast.makeText(MainActivity.this, "ID đã tồn tại", Toast.LENGTH_SHORT).show();
                           }
                        }
                    }
                });
            dialog.show();
            }
        });
    }
// set Adapter
    public void loadData() {
        listView = findViewById(R.id.listView);
        dao = new DAO(MainActivity.this);
        khachHangList = dao.getAllData();
        KhachHangAdapter adapter = new KhachHangAdapter(MainActivity.this, khachHangList);
        listView.setAdapter(adapter);
    }
//  chuyển đến activity map
    public void switchPage(float longtitude,float lattitude){
        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtra("longtitude",longtitude);
        intent.putExtra("lattitude",lattitude);
        startActivity(intent);
    }

}