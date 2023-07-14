package cuongnbph22662.poly.thuvienpoly.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Sach {
    @PrimaryKey(autoGenerate = true)
    private int maSach;

    private int maLoai;
    private String tenSach;
    private int giaThue;

    public Sach( int maLoai, String tenSach, int giaThue) {
        this.maLoai = maLoai;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
    }

    public Sach() {
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "maSach=" + maSach +
                ", maLoai=" + maLoai +
                ", tenSach='" + tenSach + '\'' +
                ", giaThue=" + giaThue +
                '}';
    }
}
