package com.example.sehirtanitim;

public class Tablo3 {
    public Tablo3() {
    }

    public Tablo3(int nufus_id, String ilce_name, int nufus_2021, int nufus_2022, int nufus_2023, String eser_name) {
        this.nufus_id = nufus_id;
        this.ilce_name = ilce_name;
        this.nufus_2021 = nufus_2021;
        this.nufus_2022 = nufus_2022;
        this.nufus_2023 = nufus_2023;
        this.eser_name = eser_name;
    }

    private int nufus_id;
    private String ilce_name;
    private int nufus_2021;
    private int nufus_2022;
    private int nufus_2023;
    private String eser_name;

    public int getNufus_id() {
        return nufus_id;
    }

    public void setNufus_id(int nufus_id) {
        this.nufus_id = nufus_id;
    }

    public String getIlce_name() {
        return ilce_name;
    }

    public void setIlce_name(String ilce_name) {
        this.ilce_name = ilce_name;
    }

    public int getNufus_2021() {
        return nufus_2021;
    }

    public void setNufus_2021(int nufus_2021) {
        this.nufus_2021 = nufus_2021;
    }

    public int getNufus_2022() {
        return nufus_2022;
    }

    public void setNufus_2022(int nufus_2022) {
        this.nufus_2022 = nufus_2022;
    }

    public int getNufus_2023() {
        return nufus_2023;
    }

    public void setNufus_2023(int nufus_2023) {
        this.nufus_2023 = nufus_2023;
    }

    public String getEser_name() {
        return eser_name;
    }

    public void setEser_name(String eser_name) {
        this.eser_name = eser_name;
    }
}
