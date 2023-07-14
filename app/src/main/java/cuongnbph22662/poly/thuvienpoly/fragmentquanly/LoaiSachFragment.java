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
import cuongnbph22662.poly.thuvienpoly.adapter.AdapterLoaiSach;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadLoaiSach;
import cuongnbph22662.poly.thuvienpoly.model.LoaiSach;
import cuongnbph22662.poly.thuvienpoly.model.Sach;

public class LoaiSachFragment extends Fragment implements LoadLoaiSach {
    private RecyclerView recyclerView;
    private FloatingActionButton addLS;
    private ArrayList<LoaiSach> listLS = new ArrayList<>();
    private AdapterLoaiSach adapterLoaiSach;


    public LoaiSachFragment() {
        // Required empty public constructor
    }

    public static LoaiSachFragment newInstance() {
        LoaiSachFragment fragment = new LoaiSachFragment();
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
        return inflater.inflate(R.layout.fragment_loai_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.id_RecyLoaiSach);
        addLS = view.findViewById(R.id.id_AddLoaiSach);
//        LoaiSach Lsach = new LoaiSach("it");
//        Mydatabase.getInstance(getActivity()).loaiSachDao().insertLoaiSach(Lsach);
        loadDataLoaiSach();
        addLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLogLoaiSach();
            }
        });
    }

    private void showDiaLogLoaiSach() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_loaisach);

        EditText tenLoai = dialog.findViewById(R.id.ed_tenLoai);
        Button huy = dialog.findViewById(R.id.btn_huyLS);
        Button them = dialog.findViewById(R.id.btn_themLS);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach ls = new LoaiSach();
                ls.setTenLoai(tenLoai.getText().toString());
                if(tenLoai.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }else {
                    Mydatabase.getInstance(getActivity()).loaiSachDao().insertLoaiSach(ls);
                    loadDataLoaiSach();
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void loadDataLoaiSach() {
        listLS.clear();
        listLS = (ArrayList<LoaiSach>) Mydatabase.getInstance(getActivity()).loaiSachDao().getAllData();
        adapterLoaiSach = new AdapterLoaiSach(getActivity(), this);
        adapterLoaiSach.setArrayLoaiSach(listLS);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterLoaiSach);
        Log.i("//=====", "list "+listLS.toString());
    }
}