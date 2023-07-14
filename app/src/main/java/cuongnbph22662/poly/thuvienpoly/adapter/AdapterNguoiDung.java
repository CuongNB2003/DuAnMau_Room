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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.fragmentnguoidung.AddNguoiDungFragment;
import cuongnbph22662.poly.thuvienpoly.loaddata.LoadThuThu;
import cuongnbph22662.poly.thuvienpoly.model.ThuThu;

public class AdapterNguoiDung extends RecyclerView.Adapter<AdapterNguoiDung.viewThuThu> {
    Context context;
    LoadThuThu loadThuThu;
    ArrayList<ThuThu> listTT;
    ThuThu thuThu;

    public AdapterNguoiDung(Context context, LoadThuThu loadThuThu) {
        this.context = context;
        this.loadThuThu = loadThuThu;
    }

    public void setArray(ArrayList<ThuThu> list){
        this.listTT = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public viewThuThu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nguoidung, parent, false);
        return new viewThuThu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewThuThu holder, int position) {
        thuThu = listTT.get(position);
        if(thuThu == null)
            return;
        holder.user.setText(thuThu.getMaTT());
        holder.people.setText(thuThu.getHoTen());
        holder.pass.setText(thuThu.getMatKhau());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaXoaNguoiDung();
            }
        });
    }

    private void suaXoaNguoiDung() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ql_nguoidung);
        EditText taiKhoan = dialog.findViewById(R.id.ed_TaiKhoan);
        EditText nguoiDung = dialog.findViewById(R.id.ed_tenNgDung);
        EditText matKhau = dialog.findViewById(R.id.ed_MatKhau);
        Button sua = dialog.findViewById(R.id.btn_SuaThuThu);
        Button xoa = dialog.findViewById(R.id.btn_XoaThuThu);

        taiKhoan.setText(thuThu.getMaTT());
        matKhau.setText(thuThu.getMatKhau());
        nguoiDung.setText(thuThu.getHoTen());
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mydatabase.getInstance(context).thuThuDao().deleteThuThu(thuThu);
                loadThuThu.loadDataThuThu();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuThu.setHoTen(nguoiDung.getText().toString());
                Mydatabase.getInstance(context).thuThuDao().updateThuThu(thuThu);
                loadThuThu.loadDataThuThu();
                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        if(listTT != null)
            return listTT.size();
        return 0;
    }

    public class viewThuThu extends RecyclerView.ViewHolder{
        TextView user, people, pass;
        public viewThuThu(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.tv_TaiKhoan);
            pass = itemView.findViewById(R.id.tv_MatKhau);
            people = itemView.findViewById(R.id.tv_TenNguoiDung);
        }
    }
}
