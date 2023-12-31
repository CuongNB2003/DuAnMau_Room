package cuongnbph22662.poly.thuvienpoly.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class LoaiSach {
    @PrimaryKey(autoGenerate = true)
    private int maLoai;

    private String tenLoai;

    public LoaiSach(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public LoaiSach() {
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Override
    public String toString() {
        return "LoaiSach{" +
                "maLoai=" + maLoai +
                ", tenLoai='" + tenLoai + '\'' +
                '}';
    }
}
