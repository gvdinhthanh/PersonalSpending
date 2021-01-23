package vn.com.dinhthanh.personalspending;

import java.util.Date;

public class ObjectThuChi {

    private int id;
    private int doiTuong;
    private String tieuDe;
    private String moTa;
    private int soTien;
    private String ngayThucHien;

    public ObjectThuChi(int ID, int DoiTuong, String TieuDe, String MoTa, int SoTien, String NgayThucHien){
        this.id = ID;
        this.doiTuong = DoiTuong;
        this.tieuDe = TieuDe;
        this.moTa = MoTa;
        this.soTien = SoTien;
        this.ngayThucHien = NgayThucHien;
    }

    public ObjectThuChi(int DoiTuong, String TieuDe, String MoTa, int SoTien, String NgayThucHien){
        this.id = -1;
        this.doiTuong = DoiTuong;
        this.tieuDe = TieuDe;
        this.moTa = MoTa;
        this.soTien = SoTien;
        this.ngayThucHien = NgayThucHien;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoiTuong(){
        return doiTuong;
    }

    public void setDoiTuong(int doiTuong) {
        this.doiTuong = doiTuong;
    }

    public String getTieuDe(){
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getMoTa(){
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getNgayThucHien() {
        return ngayThucHien;
    }

    public void setNgayThucHien(String ngayThucHien) {
        this.ngayThucHien = ngayThucHien;
    }
}
