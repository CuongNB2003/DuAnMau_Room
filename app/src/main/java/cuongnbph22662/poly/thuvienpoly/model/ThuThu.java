package cuongnbph22662.poly.thuvienpoly.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ThuThu {
    @PrimaryKey @NonNull
    private String maTT;

    private String matKhau;
    private String hoTen;

    public ThuThu(String maTT, String matKhau, String hoTen) {
        this.maTT = maTT;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
    }

    public ThuThu() {
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @Override
    public String toString() {
        return "ThuThu{" +
                "maTT='" + maTT + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", hoTen='" + hoTen + '\'' +
                '}';
    }
}
