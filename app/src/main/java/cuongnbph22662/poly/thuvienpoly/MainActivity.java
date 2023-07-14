package cuongnbph22662.poly.thuvienpoly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.fragmentnguoidung.AddNguoiDungFragment;
import cuongnbph22662.poly.thuvienpoly.fragmentnguoidung.DoiMatKhauFragment;
import cuongnbph22662.poly.thuvienpoly.fragmentquanly.LoaiSachFragment;
import cuongnbph22662.poly.thuvienpoly.fragmentquanly.PhieuMuonFragment;
import cuongnbph22662.poly.thuvienpoly.fragmentquanly.SachFragment;
import cuongnbph22662.poly.thuvienpoly.fragmentquanly.ThanhVienFragment;
import cuongnbph22662.poly.thuvienpoly.fragmentthongke.DoanhThuFragment;
import cuongnbph22662.poly.thuvienpoly.fragmentthongke.Top10SachFragment;
import cuongnbph22662.poly.thuvienpoly.login.LoginActivity;
import cuongnbph22662.poly.thuvienpoly.model.ThuThu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    private View mHeaderView;
    private TextView tv_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.id_drawerlayout);
        toolbar = findViewById(R.id.id_toolbar);
        navigationView = findViewById(R.id.id_navView);
        frameLayout = findViewById(R.id.id_framelayout);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(PhieuMuonFragment.newInstance());

        mHeaderView = navigationView.getHeaderView(0);
        tv_User = mHeaderView.findViewById(R.id.tv_User);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String name = Mydatabase.getInstance(getApplicationContext()).thuThuDao().getNameTheoID(user);
        tv_User.setText(name);
        // admin có quyền add user
        if(user.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.sub_addThuThu).setVisible(true);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_phieuMuon){
            setTitle("Quản Lý Phiếu Mượn");
            replaceFragment(PhieuMuonFragment.newInstance());
        }else if(id == R.id.nav_loaiSach){
            setTitle("Quản Lý Loại Sách");
            replaceFragment(LoaiSachFragment.newInstance());
        }else if(id == R.id.nav_sach){
            setTitle("Quản Lý Sách");
            replaceFragment(SachFragment.newInstance());
        }else if(id == R.id.nav_thanhVien){
            setTitle("Quản Lý Thành Viên");
            replaceFragment(ThanhVienFragment.newInstance());
        }else if(id == R.id.sub_top10sach){
            setTitle("Top 10 Sách Mượn Nhiều Nhất");
            replaceFragment(Top10SachFragment.newInstance());
        }else if(id == R.id.sub_doanhthu){
            setTitle("Doanh Thu ");
            replaceFragment(DoanhThuFragment.newInstance());
        }else if(id == R.id.sub_addThuThu){
            setTitle("Thêm người dùng");
            replaceFragment(AddNguoiDungFragment.newInstance());
        }else if(id == R.id.sub_DoiMK){
            setTitle("Đổi Mật Khẩu");
            replaceFragment(DoiMatKhauFragment.newInstance());
        }else if(id == R.id.sub_DangXuat){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent );
        }
        drawerLayout.closeDrawer(navigationView);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(navigationView)){
            drawerLayout.closeDrawer(navigationView);
        }else{
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_framelayout, fragment);
        transaction.commit();
    }
}