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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.adapter.AdapterSach;
import cuongnbph22662.poly.thuvienpoly.adapter.adapterspinner.SpinnerLoaiSachAdapter;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadSach;
import cuongnbph22662.poly.thuvienpoly.model.LoaiSach;
import cuongnbph22662.poly.thuvienpoly.model.Sach;

public class SachFragment extends Fragment implements LoadSach {
    private RecyclerView recyclerView;
    private FloatingActionButton addSach;
    private ArrayList<Sach> listS = new ArrayList<>();
    private AdapterSach adapterSach;

    private SpinnerLoaiSachAdapter adapterLSSpinner;
    private ArrayList<LoaiSach> listLS = new ArrayList<>();
    int maLoaiSach, position;

    EditText tenSach, giaThue;
    Spinner loaiSach;


    public SachFragment() {
        // Required empty public constructor
    }

    public static SachFragment newInstance() {
        SachFragment fragment = new SachFragment();
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
        return inflater.inflate(R.layout.fragment_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.id_RecySach);
        addSach = view.findViewById(R.id.id_AddSach);
//        Sach sach  = new Sach(1, "lập trình adr", 2000);
//        Mydatabase.getInstance(getActivity()).sachDao().insertSach(sach);
        loadDataSach();
        addSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLogSach();
            }
        });
    }

    private void showDiaLogSach() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_sach);

        tenSach = dialog.findViewById(R.id.ed_tenSach);
        giaThue = dialog.findViewById(R.id.ed_giaThue);
        loaiSach = dialog.findViewById(R.id.sp_LoaiSach);
        Button huy = dialog.findViewById(R.id.btn_huyS);
        Button them = dialog.findViewById(R.id.btn_themS);
        //spiner loại sách
        getSpinerLoaiSach();
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateData()){
                    Sach sach = new Sach();
                    sach.setTenSach(tenSach.getText().toString());
                    sach.setGiaThue(Integer.parseInt(giaThue.getText().toString()));
                    sach.setMaLoai(maLoaiSach);

                    Mydatabase.getInstance(getActivity()).sachDao().insertSach(sach);
                    loadDataSach();
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void getSpinerLoaiSach() {
        listLS = (ArrayList<LoaiSach>) Mydatabase.getInstance(getContext()).loaiSachDao().getAllData();
        adapterLSSpinner = new SpinnerLoaiSachAdapter(getContext(), listLS);
        loaiSach.setAdapter(adapterLSSpinner);
        // lấy mã loại sách
        loaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoaiSach = listLS.get(i).getMaLoai();
                Log.i("//=====", "chon: "+listLS.get(i).getMaLoai());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        loadDataSach();
    }

    @Override
    public void loadDataSach() {
        listS.clear();
        listS = (ArrayList<Sach>) Mydatabase.getInstance(getActivity()).sachDao().getAllData();
        adapterSach = new AdapterSach(getContext(), this);
        adapterSach.setArraySach(listS);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterSach);
        Log.i("//=====", "list "+listS.toString());
    }

    public boolean validateData(){
        if(tenSach.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "không đc để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            int giathue =Integer.parseInt(giaThue.getText().toString());
            if(giaThue.length() == 0){
                Toast.makeText(getActivity(), "không đc để trống", Toast.LENGTH_SHORT).show();
                return false;
            }
        }catch (Exception e){
            Toast.makeText(getActivity(), "Phải nhập số", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}