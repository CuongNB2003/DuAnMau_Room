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
import cuongnbph22662.poly.thuvienpoly.model.Sach;

public class SpinnerSachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    TextView tvMaSach, tvTenSach;

    public SpinnerSachAdapter(@NonNull Context context, ArrayList<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tv_sp_MaLoai);
            tvMaSach.setText(item.getMaSach()+". ");
            tvTenSach = v.findViewById(R.id.tv_sp_TenLoai);
            tvTenSach.setText(item.getTenSach());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tv_sp_MaLoai);
            tvMaSach.setText(item.getMaSach()+". ");
            tvTenSach = v.findViewById(R.id.tv_sp_TenLoai);
            tvTenSach.setText(item.getTenSach());
        }
        return v;
    }
}
