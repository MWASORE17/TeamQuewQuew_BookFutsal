package quewquewcrew.appngasal.model.entity;

/**
 * Created by Calwin on 15/06/2017.
 */
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class History implements Serializable{

    private String namaLap;
    private String namaPemesan;
    private String kecamatan;
    private String status;
    private int gambarLap;
    private int harga;
    public static int _id = 1;
    public static int idcancel =0;
    public static int idok = 0;
    public static ArrayList<History> History = new ArrayList<>();
    public History(String namalapan, String namaPemesan, String kecamatan,String status,int harga, int gambar) {
        this.namaLap = namalapan;
        this.namaPemesan = namaPemesan;
        this.kecamatan = kecamatan;
        this.status = status;
        this.harga = harga;
        this.gambarLap = gambar;
    }

    public String getNamaLap() {
        return namaLap;
    }

    public void setNamaLap(String namaLap) {
        this.namaLap = namaLap;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGambarLap() {
        return gambarLap;
    }

    public void setGambarLap(int gambarLap) {
        this.gambarLap = gambarLap;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}

