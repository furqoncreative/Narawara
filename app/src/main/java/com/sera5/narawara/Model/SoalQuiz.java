package com.sera5.narawara.Model;

public class SoalQuiz {
    public String soal, jawaban, pil1, pil2, pil3, pil4;

    public SoalQuiz(String soal, String jawaban, String pil1, String pil2, String pil3, String pil4) {
        this.soal = soal;
        this.jawaban = jawaban;
        this.pil1 = pil1;
        this.pil2 = pil2;
        this.pil3 = pil3;
        this.pil4 = pil4;
    }

    public SoalQuiz(){}

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getPil1() {
        return pil1;
    }

    public void setPil1(String pil1) {
        this.pil1 = pil1;
    }

    public String getPil2() {
        return pil2;
    }

    public void setPil2(String pil2) {
        this.pil2 = pil2;
    }

    public String getPil3() {
        return pil3;
    }

    public void setPil3(String pil3) {
        this.pil3 = pil3;
    }

    public String getPil4() {
        return pil4;
    }

    public void setPil4(String pil4) {
        this.pil4 = pil4;
    }
}
