package cuongnbph22662.poly.thuvienpoly.fragmentquanly;

import static android.content.Intent.getIntent;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.adapter.AdapterPhieuMuon;
import cuongnbph22662.poly.thuvienpoly.adapter.adapterspinner.SpinnerSachAdapter;
import cuongnbph22662.poly.thuvienpoly.adapter.adapterspinner.SpinnerThanhVienAdapter;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadPhieuMuon;
import cuongnbph22662.poly.thuvienpoly.model.PhieuMuon;
import cuongnbph22662.poly.thuvienpoly.model.Sach;
import cuongnbph22662.poly.thuvienpoly.model.ThanhVien;

public class PhieuMuonFragment extends Fragment implements LoadPhieuMuon {
    RecyclerView recyclerView;
    ArrayList<PhieuMuon> listPM = new ArrayList<>();
    AdapterPhieuMuon adapterPhieuMuon;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    FloatingActionButton addPhieuMuon;

    SpinnerThanhVienAdapter adapterThanhVienSpinner;
    ArrayList<ThanhVien> listTV = new ArrayList<>();

    SpinnerSachAdapter adapterSachSpinner;
    ArrayList<Sach> listSach = new ArrayList<>();
    int maSach, tienThue, maThanhVien;



    public PhieuMuonFragment() {
        // Required empty public constructor
    }

    public static PhieuMuonFragment newInstance() {
        PhieuMuonFragment fragment = new PhieuMuonFragment();
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
        return inflater.inflate(R.layout.fragment_phieu_muon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.id_RecyPhieuMuon);
        addPhieuMuon = view.findViewById(R.id.id_AddPhieuMuon);
        // thêm dữ liệu
//        PhieuMuon phieuMuon = new PhieuMuon( 1, 1, "admin", "20/10/2022", 0, 2000);
//        Mydatabase.getInstance(getActivity()).phieuMuonDao().insertPhieuMuon(phieuMuon);
        loadDataPhieuMuon();
        //thêm phiếu mượn
        addPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLogPhieuMuon();
            }
        });
    }

    private void showDiaLogPhieuMuon() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_phieumuon);
        //anhxa
        Spinner spSach = dialog.findViewById(R.id.sp_TenSachPM);
        Spinner spThanhVien = dialog.findViewById(R.id.sp_ThanhVienPM);
        TextView tvNgay = dialog.findViewById(R.id.tv_NgayPM);
        TextView tvTienThue = dialog.findViewById(R.id.tv_tienthuepm);
        Button btnHuy = dialog.findViewById(R.id.btn_huyPM);
        Button btnThem = dialog.findViewById(R.id.btn_themPM);
        //spiner sach và thành viên
        listSach = (ArrayList<Sach>) Mydatabase.getInstance(getActivity()).sachDao().getAllData();
        adapterSachSpinner = new SpinnerSachAdapter(getActivity(), listSach);
        spSach.setAdapter(adapterSachSpinner);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                maSach = listSach.get(index).getMaSach();
                tienThue = listSach.get(index).getGiaThue();
                Log.i("//=====", "chon ma sach: "+listSach.get(index).getMaSach());
                tvTienThue.setText(tienThue+"");
                Log.i("//=====", "chon tiền thuê: "+listSach.get(index).getGiaThue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listTV = (ArrayList<ThanhVien>) Mydatabase.getInstance(getActivity()).thanhVienDao().getAllData();
        adapterThanhVienSpinner = new SpinnerThanhVienAdapter(getContext(), listTV);
        spThanhVien.setAdapter(adapterThanhVienSpinner);
        spThanhVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                maThanhVien = listTV.get(index).getMaTV();
                Log.i("//=====", "chon mã tv: "+listTV.get(index).getMaTV());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // set ngày
        tvNgay.setText(sdf.format(new Date()));

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themPhieuMuon(maSach, maThanhVien, tienThue);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void themPhieuMuon(int maSach, int maThanhVien, int tienThue) {
        //lấy mã thủ thư
        SharedPreferences spf = getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String matt = spf.getString("USERNAME", "");
        //lấy ngày hiện tại
        String ngay = sdf.format(new Date());
        //add vào
        PhieuMuon phieuMuon = new PhieuMuon();
        phieuMuon.setMaTT(matt);
        phieuMuon.setMaTV(maThanhVien);
        phieuMuon.setMaSach(maSach);
        phieuMuon.setTienThue(tienThue);
        phieuMuon.setTraSach(0);
        phieuMuon.setNgay(ngay);
        Mydatabase.getInstance(getActivity()).phieuMuonDao().insertPhieuMuon(phieuMuon);
        loadDataPhieuMuon();
        Toast.makeText(getActivity(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onStart() {
        super.onStart();
        loadDataPhieuMuon();
    }

    @Override
    public void loadDataPhieuMuon() {
        listPM.clear();
        listPM = (ArrayList<PhieuMuon>) Mydatabase.getInstance(getActivity()).phieuMuonDao().getAllData();
        adapterPhieuMuon = new AdapterPhieuMuon(getActivity(), this);
        adapterPhieuMuon.setArrayPhieuMuon(listPM);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterPhieuMuon);
        Log.i("//=====", "list "+listPM.toString()+"\n");
    }
}