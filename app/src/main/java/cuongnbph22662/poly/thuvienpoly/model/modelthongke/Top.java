package cuongnbph22662.poly.thuvienpoly.model.modelthongke;

public class Top {
    private int maSach;
    private String tenSach;
    private int soLuong;

    public Top() {
    }

    public Top(int maSach, String tenSach, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "Top{" +
                "maSach=" + maSach +
                ", tenSach='" + tenSach + '\'' +
                ", soLuong=" + soLuong +
                '}';
    }
}
