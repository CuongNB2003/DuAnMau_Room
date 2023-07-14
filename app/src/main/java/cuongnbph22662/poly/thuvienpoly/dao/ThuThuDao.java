package cuongnbph22662.poly.thuvienpoly.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cuongnbph22662.poly.thuvienpoly.model.ThuThu;

@Dao
public interface ThuThuDao {

    @Insert
    void insertThuThu(ThuThu tt);

    @Delete
    void deleteThuThu(ThuThu tt);

    @Update
    void updateThuThu(ThuThu tt);

    @Query("Select * From ThuThu")
    List<ThuThu> getAllData();

    @Query("Update ThuThu set matKhau=:matKhau,hoTen=:hoTen Where maTT=:maTT")
    void updateTenThuThu(String maTT,String matKhau,String hoTen);

    @Query("Update ThuThu set matKhau=:matKhau Where maTT=:maTT")
    void doiMatKhau(String maTT,String matKhau);


    @Query("Select * From ThuThu Where maTT=:maTT AND matKhau=:matKhau")
    List<ThuThu> checkDangNhap(String maTT,String matKhau);

    @Query("Select hoTen From ThuThu Where maTT=:matt")
    String getNameTheoID(String matt);

    @Query("Select * From ThuThu Where maTT=:maTT")
    List<ThuThu> checkUnique(String maTT);
}
