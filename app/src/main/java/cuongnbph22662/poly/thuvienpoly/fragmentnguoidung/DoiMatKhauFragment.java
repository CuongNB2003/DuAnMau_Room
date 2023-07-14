package cuongnbph22662.poly.thuvienpoly.fragmentnguoidung;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cuongnbph22662.poly.thuvienpoly.R;
import cuongnbph22662.poly.thuvienpoly.database.Mydatabase;
import cuongnbph22662.poly.thuvienpoly.model.ThuThu;

public class DoiMatKhauFragment extends Fragment {
    EditText matKhauCu;
    EditText matKhauMoi;
    EditText matKhauMoi2;
    Button btn_DoiMK, btn_Huy;

    public DoiMatKhauFragment() {
        // Required empty public constructor
    }


    public static DoiMatKhauFragment newInstance() {
        DoiMatKhauFragment fragment = new DoiMatKhauFragment();
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
        return inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        matKhauCu = view.findViewById(R.id.ed_mKCu);
        matKhauMoi = view.findViewById(R.id.ed_mKMoi);
        matKhauMoi2 = view.findViewById(R.id.ed_mkMoi2);
        btn_DoiMK = view.findViewById(R.id.btn_DoiMK);
        btn_Huy = view.findViewById(R.id.btn_Huy);
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matKhauCu.setText("");
                matKhauMoi.setText("");
                matKhauMoi2.setText("");
            }
        });
        btn_DoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME", "");

                if(validate() > 0){
                    String mkm = matKhauMoi.getText().toString();
                    Mydatabase.getInstance(getActivity()).thuThuDao().doiMatKhau(user, mkm);
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        matKhauCu.setText("");
                        matKhauMoi.setText("");
                        matKhauMoi2.setText("");
                }
            }
        });
    }

    public int validate(){
        int check = 1;
        if(matKhauCu.getText().length() == 0 || matKhauMoi.getText().length() == 0 || matKhauMoi2.getText().length() == 0){
            Toast.makeText(getActivity(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            // đọc user, pass, trong SharedPreferences
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passCu = pref.getString("PASSWORB", "");
            String passMoi = matKhauMoi.getText().toString();
            String passMoi2 = matKhauMoi2.getText().toString();
            if(!passCu.equals(matKhauCu.getText().toString())){
                Toast.makeText(getActivity(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(!passMoi.equals(passMoi2)){
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}