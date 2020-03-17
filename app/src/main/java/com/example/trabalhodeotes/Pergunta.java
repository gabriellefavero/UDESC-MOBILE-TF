package com.example.trabalhodeotes;

import android.os.Parcel;
import android.os.Parcelable;

public class Pergunta implements Parcelable {
    private String pergunta;
    private String opcao1;
    private String opcao2;
    private String opcao3;
    private int resposta;

    public Pergunta() {
    }

    public Pergunta(String pergunta, String opcao1, String opcao2, String opcao3, int resposta) {
        this.pergunta = pergunta;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.opcao3 = opcao3;
        this.resposta = resposta;
    }

    protected Pergunta(Parcel in) {
        pergunta = in.readString();
        opcao1 = in.readString();
        opcao2 = in.readString();
        opcao3 = in.readString();
        resposta = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pergunta);
        dest.writeString(opcao1);
        dest.writeString(opcao2);
        dest.writeString(opcao3);
        dest.writeInt(resposta);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pergunta> CREATOR = new Creator<Pergunta>() {
        @Override
        public Pergunta createFromParcel(Parcel in) {
            return new Pergunta(in);
        }

        @Override
        public Pergunta[] newArray(int size) {
            return new Pergunta[size];
        }
    };

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getOpcao1() {
        return opcao1;
    }

    public void setOpcao1(String opcao1) {
        this.opcao1 = opcao1;
    }

    public String getOpcao2() {
        return opcao2;
    }

    public void setOpcao2(String opcao2) {
        this.opcao2 = opcao2;
    }

    public String getOpcao3() {
        return opcao3;
    }

    public void setOpcao3(String opcao3) {
        this.opcao3 = opcao3;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }
}