package org.dos.model;

import java.util.ArrayList;

public class Rastreio {
    private int id;
    private String codRastreio;
    private String nomeProduto;
    private ArrayList<Status> status;
    private String origem;
    private String destino;

    public Rastreio() {
        this.id = 0;
        this.codRastreio = null;
        this.nomeProduto = null;
        this.status = null;
        this.origem = null;
        this.destino = null;
    }

    public Rastreio(int id, String codRastreio, String nomeProduto, ArrayList<Status> status, String origem, String destino) {
        this.id = id;
        this.codRastreio = codRastreio;
        this.nomeProduto = nomeProduto;
        this.status = status;
        this.origem = origem;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodRastreio() {
        return codRastreio;
    }

    public void setCodRastreio(String codRastreio) {
        this.codRastreio = codRastreio;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public ArrayList<Status> getStatus() {
        return status;
    }

    public void setStatus(ArrayList<Status> status) {
        this.status = status;
    }

    public void setOrigem(String origem){
    	this.origem = origem;
    }

    public String getOrigem(){
    	return origem;
    }

    public void setDestino(String destino){
    	this.destino = destino;
    }

    public String getDestino(){
    	return destino;
    }
}
