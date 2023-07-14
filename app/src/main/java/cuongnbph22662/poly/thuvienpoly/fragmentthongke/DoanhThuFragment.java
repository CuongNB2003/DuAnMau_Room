package cuongnbph22662.poly.thuvienpoly.fragmentthongke;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;


public class DoanhThuFragment extends Fragment {
    Button btnDoanhThu;
    ImageView imgTuNgay, imgDenNgay;
    TextView tvDoanhThu, tvTuNgay, tvDenNgay;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int mYear, mMonth, mDay;

    public DoanhThuFragment() {
        // Required empty public constructor
    }


    public static DoanhThuFragment newInstance() {
        DoanhThuFragment fragment = new DoanhThuFragment();
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
        return inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDoanhThu = view.findViewById(R.id.btn_TinhDoanhThu);
        imgDenNgay = view.findViewById(R.id.img_DenNgay);
        imgTuNgay = view.findViewById(R.id.img_TuNgay);
        tvDoanhThu = view.findViewById(R.id.tv_TongDoanhThu);
        tvTuNgay = view.findViewById(R.id.tv_TuNgay);
        tvDenNgay = view.findViewById(R.id.tv_DenNgay);

        imgTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        imgDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = tvTuNgay.getText().toString();
                String denNgay = tvDenNgay.getText().toString();

                int doanhThu = Mydatabase.getInstance(getActivity()).phieuMuonDao().DoanhThu();
                Toast.makeText(getActivity(), ""+doanhThu, Toast.LENGTH_SHORT).show();
                tvDoanhThu.setText(""+doanhThu);
            }
        });

    }

    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int nam, int thang, int ngay) {
            mYear = nam;
            mMonth = thang;
            mDay = ngay;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            tvTuNgay.setText(sdf.format(c.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int nam, int thang, int ngay) {
            mYear = nam;
            mMonth = thang;
            mDay = ngay;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            tvDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}