package quewquewcrew.appngasal.model.entity;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

import quewquewcrew.appngasal.R;

/**
 * Created by User on 5/22/2017.
 */

public class Lapangan implements Serializable{
    private int idLap;
    private String namalapan;
    private String alamat;
    private String kecamatan;
    private String notel;
    private String jenis;
    private int img;
    public int harga;
    public static ArrayList<Lapangan> lapangans = new ArrayList<>();
    public Lapangan(String namalapan, String alamat, String kecamatan,String notel,int harga,String jenis) {
        this.namalapan = namalapan;
        this.alamat = alamat;
        this.kecamatan = kecamatan;
        this.notel = notel;
        this.harga = harga;
        this.jenis = jenis;
    }
    public int getIdLap() {
        return idLap;
    }

    public String getNameLap() {
        return namalapan;
    }

    public void setNameLap(String namalapan) {
        this.namalapan = namalapan;
    }

    public String getAlamatLap() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getNotel(){return notel;}

    public void setNotel(String notel){this.notel = notel;}

    public int getHarga(){return harga;}

    public void setHarga(int harga){this.harga = harga;}

    public int getImg(){return img;}
    public void setImg(int img) {this.img = img;}
    public String getJenis(){return jenis;}
    public void setJenis(String jenis){this.jenis = jenis;}
}
