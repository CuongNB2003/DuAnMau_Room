package cuongnbph22662.poly.thuvienpoly.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cuongnbph22662.poly.thuvienpoly.model.LoaiSach;

@Dao
public interface LoaiSachDao {
    @Insert
    void insertLoaiSach(LoaiSach ls);

    @Delete
    void deleteLoaiSach(LoaiSach ls);

    @Update
    void updateLoaiSach(LoaiSach ls);

    @Query("Delete From sach where maLoai=:maLoai")
    void deleteTheoMaLoai(int maLoai);

    @Query("Select * From LoaiSach")
    List<LoaiSach> getAllData();

    @Query("Select tenLoai From loaisach Where maLoai =:maLoai")
    String getNameTheoID(int maLoai);

    @Query("Update LoaiSach set tenLoai =:name Where maLoai =:maLoai")
    void updateTheoID(int maLoai,String name);
}
