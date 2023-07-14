package cuongnbph22662.poly.thuvienpoly.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cuongnbph22662.poly.thuvienpoly.model.Sach;

@Dao
public interface SachDao {
    @Insert
    void insertSach(Sach s);

    @Delete
    void deleteSach(Sach s);

    @Update
    void updateSach(Sach s);

    @Query("Delete From sach where maLoai=:maLoai")
    void deleteTheoMaLoai(int maLoai);


    @Query("Select * From Sach")
    List<Sach> getAllData();


    @Query("Update Sach set maLoai =:maLoai ,tenSach=:tenSach,giaThue =:giaThue Where maSach=:maSach")
    void updateTheoID(int maSach,int maLoai,String tenSach,double giaThue);

    @Query("Select tenSach From Sach Where maSach=:maSach")
    String getNameTheoID(int maSach);

    @Query("Select giaThue From Sach Where maSach=:maSach")
    int getGiaThueTheoID(int maSach);
}
