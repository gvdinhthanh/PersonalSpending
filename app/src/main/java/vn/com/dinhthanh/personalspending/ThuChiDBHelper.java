package vn.com.dinhthanh.personalspending;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThuChiDBHelper extends SQLiteOpenHelper {
    public ThuChiDBHelper(Context context) {
        super(context, "tablethuchi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tableThuChi(id INTEGER PRIMARY KEY AUTOINCREMENT, doituong INTEGER, tieude TEXT," +
                " mota TEXT, sotien INTEGER, ngaythuchien TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE tableThuChi");
    }

    public Boolean themDoiTuongThuChi(ObjectThuChi newObjectThuChi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("doituong", newObjectThuChi.getDoiTuong());
        contentValues.put("tieude", newObjectThuChi.getTieuDe());
        contentValues.put("mota", newObjectThuChi.getMoTa());
        contentValues.put("sotien", newObjectThuChi.getSoTien());
        contentValues.put("ngaythuchien", newObjectThuChi.getNgayThucHien());
        long result = db.insert("tableThuChi",null, contentValues);
        return result != -1;
    }

    public Boolean updateDoiTuongThuChi(ObjectThuChi newObjectThuChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tableThuChi WHERE id=?", new String[] {String.valueOf(newObjectThuChi.getId())});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("doituong", newObjectThuChi.getDoiTuong());
            contentValues.put("tieude", newObjectThuChi.getTieuDe());
            contentValues.put("mota", newObjectThuChi.getMoTa());
            contentValues.put("sotien", newObjectThuChi.getSoTien());
            contentValues.put("ngaythuchien", newObjectThuChi.getNgayThucHien());
            long result = db.update("tableThuChi", contentValues, "id=?", new String[] {String.valueOf(newObjectThuChi.getId())});
            return result != -1;
        }
        return false;
    }

    public Boolean deleteObjectThuChi(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tableThuChi WHERE id=?", new String[] {String.valueOf(id)});
        if (cursor.getCount() > 0) {
            long result = db.delete("tableThuChi", "id=?", new String[] {String.valueOf(id)});
            return result != -1;
        }
        return false;
    }

    public Boolean deleteObjectThuChiCuoi() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tableThuChi", null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            int id = cursor.getInt(0);
            long result = db.delete("tableThuChi", "id=?", new String[] {String.valueOf(id)});
            return result != -1;
        }
        return false;
    }

    public Cursor getListThuChi() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM tableThuChi ORDER BY ngaythuchien DESC", null);
    }

    public Cursor getListThu() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM tableThuChi WHERE doituong=1 ORDER BY ngaythuchien DESC", null);
    }

    public Cursor getListChi() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM tableThuChi WHERE doituong=-1 ORDER BY ngaythuchien DESC", null);
    }

    public int getTongSoChi(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tableThuChi WHERE doituong = -1", null);
        int tong=0;
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tong = tong + cursor.getInt(4);
                cursor.moveToNext();
            }
        }
        return tong;
    }

    public int getTongSoThu(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tableThuChi WHERE doituong = 1", null);

        int tong=0;
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tong = tong + cursor.getInt(4);
                cursor.moveToNext();
            }
        }
        return tong;
    }



    public int getTongSoChiThang(String thang, String nam){
        SQLiteDatabase db = this.getReadableDatabase();
        String dieuKien = nam +"_"+ thang + "%";
        String sql = "";
        sql = "SELECT SUM(sotien) FROM tableThuChi WHERE (doituong = -1) and (ngaythuchien LIKE '" + dieuKien +"')";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int tong=cursor.getInt(0);
        return tong;
    }

    public int getTongSoThuThang(String thang, String nam){
        SQLiteDatabase db = this.getReadableDatabase();
        String dieuKien = nam +"_"+ thang + "%";
        String sql = "";
        sql = "SELECT SUM(sotien) FROM tableThuChi WHERE (doituong = 1) and (ngaythuchien LIKE '" + dieuKien +"')";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int tong=cursor.getInt(0);
        return tong;
    }

    public int getTongSoChiNam(String nam){
        SQLiteDatabase db = this.getReadableDatabase();
        String dieuKien = nam +"%";
        String sql = "";
        sql = "SELECT SUM(sotien) FROM tableThuChi WHERE (doituong = -1) and (ngaythuchien LIKE '" + dieuKien +"')";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int tong=cursor.getInt(0);
        return tong;
    }

    public int getTongSoThuNam(String nam){
        SQLiteDatabase db = this.getReadableDatabase();
        String dieuKien = nam + "%";
        String sql = "";
        sql = "SELECT SUM(sotien) FROM tableThuChi WHERE (doituong = 1) and (ngaythuchien LIKE '" + dieuKien +"')";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int tong=cursor.getInt(0);
        return tong;
    }
}
