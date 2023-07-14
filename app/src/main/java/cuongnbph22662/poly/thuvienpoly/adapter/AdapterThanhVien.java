package cuongnbph22662.poly.thuvienpoly.adapter;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadThanhVien;
import cuongnbph22662.poly.thuvienpoly.model.ThanhVien;

public class AdapterThanhVien extends RecyclerView.Adapter<AdapterThanhVien.ThanhVienView> {
    Context context;
    LoadThanhVien loadThanhVien;

    ArrayList<ThanhVien> listThanhVien;

    public AdapterThanhVien(Context context, LoadThanhVien loadThanhVien) {
        this.context = context;
        this.loadThanhVien = loadThanhVien;
    }

    public void setArrayTV(ArrayList<ThanhVien> list){
        this.listThanhVien = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThanhVienView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanhvien, parent, false);
        return new ThanhVienView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienView holder, int position) {
        ThanhVien thanhVien = listThanhVien.get(position);
        if(thanhVien == null)
            return;
        holder.tvTenTV.setText(thanhVien.getHoTen());
        holder.tvNamSinhTV.setText(thanhVien.getNamSinh());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_ql_thanhvien);

                EditText tenTV = dialog.findViewById(R.id.ed_tenTV);
                EditText namSinhTV = dialog.findViewById(R.id.ed_namSinhTV);
                Button btnSua = dialog.findViewById(R.id.btn_SuaTV);
                Button btnXoa = dialog.findViewById(R.id.btn_XoaTV);

                tenTV.setText(thanhVien.getHoTen());
                namSinhTV.setText(thanhVien.getNamSinh());
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        thanhVien.setHoTen(tenTV.getText().toString());
                        thanhVien.setNamSinh(namSinhTV.getText().toString());
                        Mydatabase.getInstance(context).thanhVienDao().updateThanhVien(thanhVien);
                        loadThanhVien.loadDataThanhVien();
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Mydatabase.getInstance(context).thanhVienDao().deleteThanhVien(thanhVien);
                        Mydatabase.getInstance(context).thanhVienDao().deleteTheoMaTV(thanhVien.getMaTV());
                        listThanhVien.remove(thanhVien);
                        setArrayTV(listThanhVien);
                        loadThanhVien.loadDataThanhVien();
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listThanhVien != null)
            return listThanhVien.size();
        return 0;
    }

    public class ThanhVienView extends RecyclerView.ViewHolder{
        TextView tvTenTV, tvNamSinhTV;
        public ThanhVienView(@NonNull View itemView) {
            super(itemView);
            tvTenTV = itemView.findViewById(R.id.tv_tenThanhVien);
            tvNamSinhTV = itemView.findViewById(R.id.tv_namSinhThanhVien);
        }
    }
}
