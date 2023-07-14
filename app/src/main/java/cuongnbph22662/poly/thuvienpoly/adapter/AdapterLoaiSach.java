package cuongnbph22662.poly.thuvienpoly.adapter;

import android.app.Dialog;
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
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadLoaiSach;
import cuongnbph22662.poly.thuvienpoly.model.LoaiSach;

public class AdapterLoaiSach extends RecyclerView.Adapter<AdapterLoaiSach.ViewLoaiSach> {
    Context context;
    LoadLoaiSach loadLoaiSach;

    ArrayList<LoaiSach> listLoaiSach = new ArrayList<>();

    public AdapterLoaiSach(Context context, LoadLoaiSach loadLoaiSach) {
        this.context = context;
        this.loadLoaiSach = loadLoaiSach;
    }

    public void setArrayLoaiSach(ArrayList<LoaiSach> list){
        this.listLoaiSach = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewLoaiSach onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaisach, parent, false);
        return new ViewLoaiSach(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewLoaiSach holder, int position) {
        LoaiSach loaiSach = listLoaiSach.get(position);
        if(loaiSach == null)
            return;
        holder.tvMaLoai.setText(String.valueOf(loaiSach.getMaLoai()));
        holder.tvTenLoai.setText(loaiSach.getTenLoai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_ql_loaisach);
                TextView maLoai = dialog.findViewById(R.id.tv_MaLoaiLS);
                EditText tenLoai = dialog.findViewById(R.id.ed_TLoai);
                Button btnSua = dialog.findViewById(R.id.btn_SuaLS);
                Button btnXoa = dialog.findViewById(R.id.btn_XoaLS);

                maLoai.setText("Mã Loại Sách: "+loaiSach.getMaLoai());
                tenLoai.setText(loaiSach.getTenLoai());
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loaiSach.setTenLoai(tenLoai.getText().toString());
                        Mydatabase.getInstance(context).loaiSachDao().updateLoaiSach(loaiSach);
                        loadLoaiSach.loadDataLoaiSach();
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Mydatabase.getInstance(context).loaiSachDao().deleteLoaiSach(loaiSach);
                        Mydatabase.getInstance(context).loaiSachDao().deleteTheoMaLoai(loaiSach.getMaLoai());
                        listLoaiSach.remove(loaiSach);
                        setArrayLoaiSach(listLoaiSach);
                        loadLoaiSach.loadDataLoaiSach();
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
        if(listLoaiSach != null)
            return listLoaiSach.size();
        return 0;
    }

    public class ViewLoaiSach extends RecyclerView.ViewHolder{
        TextView tvMaLoai, tvTenLoai;
        public ViewLoaiSach(@NonNull View itemView) {
            super(itemView);
            tvMaLoai = itemView.findViewById(R.id.tv_maLoai);
            tvTenLoai = itemView.findViewById(R.id.tv_tenLoai);
        }
    }
}
