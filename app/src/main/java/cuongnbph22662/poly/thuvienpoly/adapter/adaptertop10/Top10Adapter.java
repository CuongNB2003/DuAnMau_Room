package cuongnbph22662.poly.thuvienpoly.adapter.adaptertop10;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.fragmentthongke.Top10SachFragment;
import cuongnbph22662.poly.thuvienpoly.model.modelthongke.Top;

public class Top10Adapter extends ArrayAdapter<Top> {
    private Context context;
    private Top10SachFragment fragment;
    ArrayList<Top> listTop;
    TextView tvSach, tvSoluong;

    public Top10Adapter(@NonNull Context context, Top10SachFragment fragment, ArrayList<Top> listTop) {
        super(context, 0, listTop);
        this.context = context;
        this.fragment = fragment;
        this.listTop = listTop;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_top, null);
        }

        final Top item  = listTop.get(position);
        if(item != null){
            tvSach = v.findViewById(R.id.tvSachTop);
            tvSoluong = v.findViewById(R.id.tvSoLuongTop);
            String sach = Mydatabase.getInstance(context).sachDao().getNameTheoID(item.getMaSach());
            tvSach.setText("Tên Sách: "+sach);
            tvSoluong.setText("Số lượng: "+item.getSoLuong());
        }
        return v;
    }
}
