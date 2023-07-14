package cuongnbph22662.poly.thuvienpoly.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.MainActivity;
import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.model.ThuThu;

public class LoginActivity extends AppCompatActivity {
    Button btnDangNhap, btnThoat;
    EditText edTK, edMK;
    CheckBox luuMK;
    String taiKhoan, matKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnDangNhap = findViewById(R.id.btn_DangNhap);
        btnThoat = findViewById(R.id.btn_Thoat);
        edMK = findViewById(R.id.ed_matKhauDN);
        edTK = findViewById(R.id.ed_tenDN);
        luuMK = findViewById(R.id.ckb_luuMK);
//        thuThuDAO = new ThuThuDAO(this);
        // đọc tài khoản mật khẩu trong SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORB", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);

        edMK.setText(pass);
        edTK.setText(user);
        luuMK.setChecked(rem);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // System.exit(1);
                finishAffinity();
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("user", taiKhoan);
//                startActivity(intent);
            }
        });
    }
    public void luuMatKhau(String user, String pass, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            //xóa tình trạng lưu trc đó
            edit.clear();
        }else{
            // lưu dữ liệu
            edit.putString("USERNAME", user);
            edit.putString("PASSWORB", pass);
            edit.putBoolean("REMEMBER", status);
        }
        // lưu loại toàn bộ
        edit.commit();
    }

    public void checkLogin(){
        //thêm tài khoản admin
        ArrayList<ThuThu> checkTonTai = (ArrayList<ThuThu>) Mydatabase.getInstance(LoginActivity.this).thuThuDao().getAllData();
        if (checkTonTai.isEmpty()){
            ThuThu thuThu = new ThuThu("admin", "admin", "Nguyễn Bá Cường");
            Mydatabase.getInstance(LoginActivity.this).thuThuDao().insertThuThu(thuThu);
        }

        taiKhoan = edTK.getText().toString();
        matKhau = edMK.getText().toString();
        ArrayList<ThuThu> listTT = (ArrayList<ThuThu>) Mydatabase.getInstance(getApplicationContext()).thuThuDao().checkDangNhap(taiKhoan, matKhau);
        if(taiKhoan.isEmpty() || matKhau.isEmpty()){
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else {
            if(listTT.size() > 0){
                Toast.makeText(getApplicationContext(), "Đăng nhâp thành công", Toast.LENGTH_SHORT).show();
                luuMatKhau(taiKhoan, matKhau, luuMK.isChecked());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", taiKhoan);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}