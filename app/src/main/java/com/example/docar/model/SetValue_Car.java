package com.example.docar.model;

public class SetValue_Car {

    private String id, tipe, merek, plate, tahun, foto;
    private int harga;

    public SetValue_Car(String id, String tipe, String merek, String plate, String tahun, int harga, String foto){
        this.id = id;
        this.tipe = tipe;
        this.merek = merek;
        this.plate = plate;
        this.tahun = tahun;
        this.harga = harga;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public String getTipe() {
        return tipe;
    }

    public String getMerek() {
        return merek;
    }

    public String getPlate() { return plate; }

    public String getTahun() { return tahun; }

    public int getHarga() {
        return harga;
    }

    public String getFoto() {
        return foto;
    }
}