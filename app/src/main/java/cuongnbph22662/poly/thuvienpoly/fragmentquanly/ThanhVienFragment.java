package cuongnbph22662.poly.thuvienpoly.fragmentquanly;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.adapter.AdapterThanhVien;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadThanhVien;
import cuongnbph22662.poly.thuvienpoly.model.ThanhVien;

public class ThanhVienFragment extends Fragment implements LoadThanhVien {
    private RecyclerView recyclerView;
    private FloatingActionButton addTV;
    private ArrayList<ThanhVien> listTV = new ArrayList<>();
    private AdapterThanhVien adapterThanhVien;

    public ThanhVienFragment() {
        // Required empty public constructor
    }

    public static ThanhVienFragment newInstance() {
        ThanhVienFragment fragment = new ThanhVienFragment();
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
        return inflater.inflate(R.layout.fragment_thanh_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.id_RecyThanhVien);
        addTV = view.findViewById(R.id.id_AddThanhVien);
//        ThanhVien tv = new ThanhVien("Nguyễn Bá Cường", "2003");
//        Mydatabase.getInstance(getActivity()).thanhVienDao().insertThanhVien(tv);
        loadDataThanhVien();
        addTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLogThanhVien();
            }
        });
    }

    private void showDiaLogThanhVien() {
        Dialog dialog =new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_thanhvien);

        EditText tenTV = dialog.findViewById(R.id.ed_tenThanhVien);
        EditText namSinhTV = dialog.findViewById(R.id.ed_namSinhThanhVien);
        Button huy = dialog.findViewById(R.id.btn_huyTV);
        Button them = dialog.findViewById(R.id.btn_themTV);

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVien tv = new ThanhVien();
                tv.setHoTen(tenTV.getText().toString());
                tv.setNamSinh(namSinhTV.getText().toString());
                if(tenTV.getText().toString().isEmpty() || namSinhTV.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Không đc để trống dữ liệu", Toast.LENGTH_SHORT).show();
                }else {
                    Mydatabase.getInstance(getActivity()).thanhVienDao().insertThanhVien(tv);
                    loadDataThanhVien();
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
        loadDataThanhVien();
    }

    @Override
    public void loadDataThanhVien() {
        listTV.clear();
        listTV = (ArrayList<ThanhVien>) Mydatabase.getInstance(getActivity()).thanhVienDao().getAllData();
        adapterThanhVien = new AdapterThanhVien(getContext(), this);
        adapterThanhVien.setArrayTV(listTV);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterThanhVien);

        Log.i("//=====", "list "+listTV.toString());
    }
}