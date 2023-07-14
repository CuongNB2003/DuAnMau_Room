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
import cuongnbph22662.poly.thuvienpoly.model.ThanhVien;

public class SpinnerThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ArrayList<ThanhVien> listTV;
    TextView tvMaThanhVien, tvTenThanhVien;

    public SpinnerThanhVienAdapter(@NonNull Context context, ArrayList<ThanhVien> listTV) {
        super(context, 0, listTV);
        this.context = context;
        this.listTV = listTV;
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

        final ThanhVien item = listTV.get(position);
        if(item != null){
            tvMaThanhVien = v.findViewById(R.id.tv_sp_MaLoai);
            tvTenThanhVien = v.findViewById(R.id.tv_sp_TenLoai);

            tvMaThanhVien.setText(item.getMaTV()+". ");
            tvTenThanhVien.setText(item.getHoTen());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        // kiểm tra xem view có bằng null ko
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner, null);
        }

        final ThanhVien item = listTV.get(position);
        if(item != null){
            tvMaThanhVien = v.findViewById(R.id.tv_sp_MaLoai);
            tvTenThanhVien = v.findViewById(R.id.tv_sp_TenLoai);

            tvMaThanhVien.setText(item.getMaTV()+". ");
            tvTenThanhVien.setText(item.getHoTen());
        }
        return v;
    }
}
