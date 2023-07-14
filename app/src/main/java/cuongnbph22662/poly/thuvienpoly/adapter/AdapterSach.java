package cuongnbph22662.poly.thuvienpoly.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.adapter.adapterspinner.SpinnerLoaiSachAdapter;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadSach;
import cuongnbph22662.poly.thuvienpoly.model.LoaiSach;
import cuongnbph22662.poly.thuvienpoly.model.Sach;
import kotlin.LateinitKt;

public class AdapterSach extends RecyclerView.Adapter<AdapterSach.ViewSach> {
    Context context;
    LoadSach loadSach;
    ArrayList<Sach> listSach = new ArrayList<>();

    int maLoaiSach, positionLS;
    SpinnerLoaiSachAdapter adapterLSSpinner;
    ArrayList<LoaiSach> listLoaiSach = new ArrayList<>();

    public AdapterSach(Context context, LoadSach loadSach) {
        this.context = context;
        this.loadSach = loadSach;
    }

    public void setArraySach(ArrayList<Sach> list){
        this.listSach = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewSach onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        return new ViewSach(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewSach holder, int position) {
        Sach sach = listSach.get(position);
        if (sach == null)
            return;
        holder.tvTenSach.setText(sach.getTenSach());
        holder.tvGiaThue.setText(String.valueOf(sach.getGiaThue()));
        String loaiSach = Mydatabase.getInstance(context).loaiSachDao().getNameTheoID(sach.getMaLoai());
        holder.tvLoaiSach.setText(loaiSach);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_ql_sach);

                EditText tenSach = dialog.findViewById(R.id.ed_tenS);
                EditText giaThue = dialog.findViewById(R.id.ed_giaT);
                Spinner loaiSach = dialog.findViewById(R.id.sp_LoaiS);
                Button sua = dialog.findViewById(R.id.btn_SuaSach);
                Button xoa = dialog.findViewById(R.id.btn_XoaSach);

                listLoaiSach = (ArrayList<LoaiSach>) Mydatabase.getInstance(context).loaiSachDao().getAllData();
                adapterLSSpinner = new SpinnerLoaiSachAdapter(context, listLoaiSach);
                loaiSach.setAdapter(adapterLSSpinner);
                // lấy mã loại sách
                loaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        maLoaiSach = listLoaiSach.get(i).getMaLoai();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                // đẩy ra dialog show
                tenSach.setText(sach.getTenSach());
                giaThue.setText(String.valueOf(sach.getGiaThue()));
                for (int i = 0; i < listLoaiSach.size(); i++) {
                    if (sach.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                        positionLS = i;
                    }
                }
                loaiSach.setSelection(positionLS);
                dialog.show();

                xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Mydatabase.getInstance(context).sachDao().deleteSach(sach);
                        Mydatabase.getInstance(context).sachDao().deleteTheoMaLoai(sach.getMaSach());
                        listSach.remove(sach);
                        setArraySach(listSach);
                        loadSach.loadDataSach();
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sach.setTenSach(tenSach.getText().toString());
                        sach.setGiaThue(Integer.parseInt(giaThue.getText().toString()));
                        sach.setMaLoai(maLoaiSach);
                        Mydatabase.getInstance(context).sachDao().updateSach(sach);
                        loadSach.loadDataSach();
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        if(listSach != null)
            return listSach.size();
        return 0;
    }

    public class ViewSach extends RecyclerView.ViewHolder{
        TextView tvTenSach, tvGiaThue, tvLoaiSach;
        public ViewSach(@NonNull View itemView) {
            super(itemView);
            tvTenSach = itemView.findViewById(R.id.tv_tenSach);
            tvLoaiSach = itemView.findViewById(R.id.tv_loaiSach);
            tvGiaThue = itemView.findViewById(R.id.tv_giaThue);
        }
    }
}
