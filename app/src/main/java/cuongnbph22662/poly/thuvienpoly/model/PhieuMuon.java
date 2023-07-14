package cuongnbph22662.poly.thuvienpoly.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverter;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity(tableName = "PhieuMuon")
public class PhieuMuon {
    @PrimaryKey(autoGenerate = true)
    private int maPM;

    private int maTV;
    private int maSach;
    private String maTT;
    private String ngay;
    private int traSach;
    private int tienThue;

    public PhieuMuon() {
    }

    public PhieuMuon(int maTV, int maSach, String maTT, String ngay, int traSach, int tienThue) {
        this.maTV = maTV;
        this.maSach = maSach;
        this.maTT = maTT;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }

    public int getMaPM() {
        return maPM;
    }
    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }
    public int getMaTV() {
        return maTV;
    }
    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }
    public int getMaSach() {
        return maSach;
    }
    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }
    public String getMaTT() {
        return maTT;
    }
    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }
    public String getNgay() {
        return ngay;
    }
    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
    public int getTraSach() {
        return traSach;
    }
    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }
    public int getTienThue() {
        return tienThue;
    }
    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }
    @Override
    public String toString() {
        return "PhieuMuon{" +
                "maPM=" + maPM +
                ", maTV=" + maTV +
                ", maSach=" + maSach +
                ", maTT='" + maTT + '\'' +
                ", ngay='" + ngay + '\'' +
                ", traSach=" + traSach +
                ", tienThue=" + tienThue +
                '}';
    }
}

//class Converters{
//    @TypeConverter
//     void toNgay(value: )
//}
