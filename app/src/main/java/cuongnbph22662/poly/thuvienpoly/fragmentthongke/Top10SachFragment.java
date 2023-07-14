package cuongnbph22662.poly.thuvienpoly.fragmentthongke;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.adapter.adaptertop10.Top10Adapter;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.model.LoaiSach;
import cuongnbph22662.poly.thuvienpoly.model.modelthongke.Top;

public class Top10SachFragment extends Fragment {
    ListView listViewTop;
    ArrayList<Top> listTop = new ArrayList<>();
    Top10Adapter top10Adapter;


    public Top10SachFragment() {
        // Required empty public constructor
    }

    public static Top10SachFragment newInstance() {
        Top10SachFragment fragment = new Top10SachFragment();
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
        return inflater.inflate(R.layout.fragment_top10_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewTop = view.findViewById(R.id.lv_top10);

        listTop = (ArrayList<Top>) Mydatabase.getInstance(getActivity()).phieuMuonDao().getTop10();
        top10Adapter = new Top10Adapter(getActivity(), this, listTop);
        listViewTop.setAdapter(top10Adapter);


    }
}