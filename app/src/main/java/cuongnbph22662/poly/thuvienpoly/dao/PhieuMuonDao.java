package cuongnbph22662.poly.thuvienpoly.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.text.SimpleDateFormat;
import java.util.List;

import cuongnbph22662.poly.thuvienpoly.model.PhieuMuon;
import cuongnbph22662.poly.thuvienpoly.model.modelthongke.Top;

@Dao
public interface PhieuMuonDao {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Insert
    void insertPhieuMuon(PhieuMuon pm);

    @Delete
    void deletePhieuMuon(PhieuMuon pm);

    @Update
    void updatePhieuMuon(PhieuMuon pm);

    @Query("Select * From PhieuMuon Order by maPM Desc")
    List<PhieuMuon> getAllData();

    @Query("Update PhieuMuon set maTV=:maTV, maSach=:maSach,ngay=:ngay, traSach =:traSach Where maPM =:maPM ")
    void updateTheoID(String maPM,String maTV,String maSach,String ngay,String traSach);

    @Query("Update PhieuMuon set traSach=1 Where maPM =:mapm")
    int thayDoiTrangThai(int mapm);

    //thống kê

    @Query("SELECT SUM(tienThue) FROM PhieuMuon ")
    int DoanhThu();

    @Query("SELECT maSach, maTT, COUNT(maSach) AS soLuong FROM phieumuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10")
    List<Top> getTop10();
}
