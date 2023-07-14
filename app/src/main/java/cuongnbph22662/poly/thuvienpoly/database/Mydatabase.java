package cuongnbph22662.poly.thuvienpoly.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cuongnbph22662.poly.thuvienpoly.dao.LoaiSachDao;
import cuongnbph22662.poly.thuvienpoly.dao.PhieuMuonDao;
import cuongnbph22662.poly.thuvienpoly.dao.SachDao;
import cuongnbph22662.poly.thuvienpoly.dao.ThanhVienDao;
import cuongnbph22662.poly.thuvienpoly.dao.ThuThuDao;
import cuongnbph22662.poly.thuvienpoly.model.LoaiSach;
import cuongnbph22662.poly.thuvienpoly.model.PhieuMuon;
import cuongnbph22662.poly.thuvienpoly.model.Sach;
import cuongnbph22662.poly.thuvienpoly.model.ThanhVien;
import cuongnbph22662.poly.thuvienpoly.model.ThuThu;

@Database(entities = {LoaiSach.class, PhieuMuon.class, Sach.class, ThanhVien.class, ThuThu.class}, version = 7)
public abstract class Mydatabase extends RoomDatabase {
    public static final String NAME_DATABAESE = "thuvienphuongnam.db";
    public abstract LoaiSachDao loaiSachDao();
    public abstract PhieuMuonDao phieuMuonDao();
    public abstract SachDao sachDao();
    public abstract ThanhVienDao thanhVienDao();
    public abstract ThuThuDao thuThuDao();

    public static Mydatabase instance;
    public static synchronized Mydatabase getInstance(Context mContext){
        if (instance ==null){
            instance = Room.databaseBuilder(mContext,Mydatabase.class,
                            NAME_DATABAESE).
                    allowMainThreadQueries().build();
        }
        return instance;
    }
}
