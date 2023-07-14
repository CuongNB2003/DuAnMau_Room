package cuongnbph22662.poly.thuvienpoly.adapter.adapterspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.model.LoaiSach;

public class SpinnerLoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> listLS;
    TextView tvMaLoai, tvTenLoai;

    public SpinnerLoaiSachAdapter(@NonNull Context context, ArrayList<LoaiSach> listLS) {
        super(context, 0, listLS);
        this.context = context;
        this.listLS = listLS;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        // kiểm tra xem view có bằng null ko
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner, null);
        }

        final LoaiSach item = listLS.get(position);
        if(item != null){
            tvMaLoai = v.findViewById(R.id.tv_sp_MaLoai);
            tvTenLoai = v.findViewById(R.id.tv_sp_TenLoai);

            tvMaLoai.setText(item.getMaLoai()+". ");
            tvTenLoai.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner, null);
        }

        final LoaiSach item = listLS.get(position);
        if(item != null){
            tvMaLoai = v.findViewById(R.id.tv_sp_MaLoai);
            tvTenLoai = v.findViewById(R.id.tv_sp_TenLoai);

            tvMaLoai.setText(item.getMaLoai()+". ");
            tvTenLoai.setText(item.getTenLoai());
        }
        return v;
    }
}
