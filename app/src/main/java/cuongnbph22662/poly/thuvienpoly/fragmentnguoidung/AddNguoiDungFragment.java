package cuongnbph22662.poly.thuvienpoly.fragmentnguoidung;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.adapter.AdapterNguoiDung;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadThuThu;
import cuongnbph22662.poly.thuvienpoly.model.ThuThu;

public class AddNguoiDungFragment extends Fragment implements LoadThuThu {
    RecyclerView recyclerView;
    FloatingActionButton addNguoiDung;
    ArrayList<ThuThu> listTT = new ArrayList<>();
    AdapterNguoiDung adapterNguoiDung;

    EditText taiKhoan, nguoiDung, matKhau, reMatKhau;

    public AddNguoiDungFragment() {
        // Required empty public constructor
    }

    public static AddNguoiDungFragment newInstance() {
        AddNguoiDungFragment fragment = new AddNguoiDungFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_nguoi_dung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.id_RecyNguoiDung);
        addNguoiDung = view.findViewById(R.id.id_AddNguoiDung);
        loadDataThuThu();
        addNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNGuoiDung();
            }
        });
    }

    private void AddNGuoiDung() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_nguoidung);
        taiKhoan = dialog.findViewById(R.id.ed_UserName);
        nguoiDung = dialog.findViewById(R.id.ed_TenNguoiDung);
        matKhau = dialog.findViewById(R.id.ed_PassWord);
        reMatKhau = dialog.findViewById(R.id.ed_RePassWord);
        Button them = dialog.findViewById(R.id.btn_themThuThu);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk = taiKhoan.getText().toString();
                String nd = nguoiDung.getText().toString();
                String mk = matKhau.getText().toString();
                String r_mk = reMatKhau.getText().toString();
                if(taiKhoan.getText().toString().isEmpty() || nguoiDung.getText().toString().isEmpty() || matKhau.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Không được để trống ", Toast.LENGTH_SHORT).show();
                }else if(mk.equalsIgnoreCase(r_mk) == false){
                    Toast.makeText(getActivity(), "Mật khẩu không trùng khớp, hãy nhập lại", Toast.LENGTH_SHORT).show();
                }else{
                    Mydatabase.getInstance(getActivity()).thuThuDao().insertThuThu(new ThuThu(tk,mk,nd));
                    loadDataThuThu();
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadDataThuThu();
    }

    @Override
    public void loadDataThuThu() {
        listTT = (ArrayList<ThuThu>) Mydatabase.getInstance(getActivity()).thuThuDao().getAllData();
        adapterNguoiDung = new AdapterNguoiDung(getActivity(), this);
        adapterNguoiDung.setArray(listTT);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterNguoiDung);
    }
}