package cuongnbph22662.poly.thuvienpoly.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cuongnbph22662.poly.thuvienpoly.model.ThanhVien;

@Dao
public interface ThanhVienDao {

    @Insert
    void insertThanhVien(ThanhVien tv);

    @Delete
    void deleteThanhVien(ThanhVien tv);

    @Update
    void updateThanhVien(ThanhVien tv);

    @Query("Delete From thanhvien where maTV=:matv")
    void deleteTheoMaTV(int matv);

    @Query("Select * From ThanhVien")
    List<ThanhVien> getAllData();

    @Query("Update ThanhVien set hoTen=:hoTen , namSinh=:namSinh Where maTV=:maTV")
    void updateTheoID(int maTV,String hoTen,String namSinh);

    @Query("Select hoTen From ThanhVien Where maTV=:maTV")
    String getNameTheoID(int maTV);
}
