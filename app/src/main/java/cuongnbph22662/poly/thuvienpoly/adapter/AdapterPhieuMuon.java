package cuongnbph22662.poly.thuvienpoly.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.adapter.adapterspinner.SpinnerSachAdapter;
import cuongnbph22662.poly.thuvienpoly.adapter.adapterspinner.SpinnerThanhVienAdapter;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadPhieuMuon;
import cuongnbph22662.poly.thuvienpoly.model.PhieuMuon;
import cuongnbph22662.poly.thuvienpoly.model.Sach;
import cuongnbph22662.poly.thuvienpoly.model.ThanhVien;

public class AdapterPhieuMuon extends RecyclerView.Adapter<AdapterPhieuMuon.ViewPhieuMuon> {
    Context context;
    LoadPhieuMuon loadPhieuMuon;
    ArrayList<PhieuMuon> listPhieuMuon = new ArrayList<>();
    ArrayList<Sach> listSach = new ArrayList<>();
    ArrayList<ThanhVien> listThanhVien = new ArrayList<>();
    SpinnerSachAdapter adapterSachSpinner;
    SpinnerThanhVienAdapter adapterThanhVienSpinner;
    int maSach, tienThue, maThanhVien, indexThanhVien, indexSach;


    public AdapterPhieuMuon(Context context, LoadPhieuMuon loadPhieuMuon) {
        this.context = context;
        this.loadPhieuMuon = loadPhieuMuon;
    }

    public void setArrayPhieuMuon(ArrayList<PhieuMuon> list){
        this.listPhieuMuon = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewPhieuMuon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieumuon, parent, false);
        return new ViewPhieuMuon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPhieuMuon holder, int position) {
        PhieuMuon item = listPhieuMuon.get(position);
        if(item == null)
            return;
        holder.tvMaPhieuMuon.setText(item.getMaPM()+"");
        String tenTT = Mydatabase.getInstance(context).thuThuDao().getNameTheoID(String.valueOf(item.getMaTT()));
        holder.tvTenTT.setText(tenTT);
        String tenTV = Mydatabase.getInstance(context).thanhVienDao().getNameTheoID(item.getMaTV());
        holder.tvTenTV.setText(tenTV);
        String tenSach = Mydatabase.getInstance(context).sachDao().getNameTheoID(item.getMaSach());
        holder.tvTenSach.setText(tenSach);
        holder.tvNgay.setText(item.getNgay());
        holder.tvTienThue.setText(String.valueOf(item.getTienThue()));
        // chỉnh sửa trạng thái
        String trangThai = "";
        if(item.getTraSach() == 1){
            holder.tvTrangThai.setTextColor(Color.BLUE);
            trangThai = "Đã trả sách";
            holder.btnTraSach.setVisibility(View.INVISIBLE);
        }else{
            holder.tvTrangThai.setTextColor(Color.RED);
            trangThai = "Chưa trả sách";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.tvTrangThai.setText(trangThai);
        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long doiTrangThai = Mydatabase.getInstance(context).phieuMuonDao().thayDoiTrangThai(item.getMaPM());
                if(doiTrangThai == 1){
                    listPhieuMuon.clear();
                    listPhieuMuon = (ArrayList<PhieuMuon>) Mydatabase.getInstance(context).phieuMuonDao().getAllData();
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "Trả sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_ql_phieumuon);
                //anhxa
                Spinner spSach = dialog.findViewById(R.id.sp_SachPM);
                Spinner spThanhVien = dialog.findViewById(R.id.sp_TV_PM);
                TextView tvNgay = dialog.findViewById(R.id.tv_ngayP_M);
                TextView tvTienThue = dialog.findViewById(R.id.tv_tienthuePM);
                Button btnXoa = dialog.findViewById(R.id.btn_XoaPM);
                Button btnSua = dialog.findViewById(R.id.btn_SuaPM);
                //spiner sach và thành viên
                listSach = (ArrayList<Sach>) Mydatabase.getInstance(context).sachDao().getAllData();
                adapterSachSpinner = new SpinnerSachAdapter(context, listSach);
                spSach.setAdapter(adapterSachSpinner);
                spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        maSach = listSach.get(i).getMaSach();
                        tienThue = listSach.get(i).getGiaThue();
                        tvTienThue.setText(tienThue+"");
                        Log.i("//=====", "chon: "+listSach.get(i).getGiaThue());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                listThanhVien = (ArrayList<ThanhVien>) Mydatabase.getInstance(context).thanhVienDao().getAllData();
                adapterThanhVienSpinner = new SpinnerThanhVienAdapter(context, listThanhVien);
                spThanhVien.setAdapter(adapterThanhVienSpinner);
                spThanhVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        maThanhVien = listThanhVien.get(i).getMaTV();
                        Log.i("//=====", "chon: "+listThanhVien.get(i).getMaTV());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                // set dữ liệu lên dialog
                tvNgay.setText(item.getNgay());
                tvTienThue.setText(item.getTienThue()+"");

                for (int i = 0; i < listThanhVien.size(); i++){
                    if(item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                        indexThanhVien = i;
                    }
                }
                spThanhVien.setSelection(indexThanhVien);

                for (int i = 0; i < listSach.size(); i++){
                    if(item.getMaSach() == (listSach.get(i).getMaSach())){
                        indexSach = i;
                    }
                }
                spSach.setSelection(indexSach);


                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Mydatabase.getInstance(context).phieuMuonDao().deletePhieuMuon(item);
                        loadPhieuMuon.loadDataPhieuMuon();
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item.setMaSach(maSach);
                        item.setMaTV(maThanhVien);
                        item.setTienThue(tienThue);
                        Mydatabase.getInstance(context).phieuMuonDao().updatePhieuMuon(item);
                        loadPhieuMuon.loadDataPhieuMuon();
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        if(listPhieuMuon != null)
            return listPhieuMuon.size();
        return 0;
    }

    public class ViewPhieuMuon extends RecyclerView.ViewHolder{
        TextView tvTenTV,tvTenTT, tvTenSach, tvTienThue, tvNgay, tvTrangThai, tvMaPhieuMuon;
        Button btnTraSach;
        public ViewPhieuMuon(@NonNull View itemView) {
            super(itemView);
            tvTenTT = itemView.findViewById(R.id.tv_ThuThuPM);
            tvTenTV = itemView.findViewById(R.id.tv_ThanhVienPM);
            tvTenSach = itemView.findViewById(R.id.tv_TenSachPM);
            tvTienThue = itemView.findViewById(R.id.tv_TienThuePM);
            tvNgay = itemView.findViewById(R.id.tv_NgayMuon);
            tvTrangThai = itemView.findViewById(R.id.tv_TrangThaiPM);
            btnTraSach = itemView.findViewById(R.id.btn_TraSach);
            tvMaPhieuMuon = itemView.findViewById(R.id.tv_MaPhieuMuon);
        }
    }
}
