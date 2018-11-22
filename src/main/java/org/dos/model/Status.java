package org.dos.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Status {
    private int id;
    private int idrastreio;
    private Timestamp horario;
    private String descricao;
    private String localização;

    public Status() {
        this.id = 0;
        this.idrastreio = 0;
        this.horario = null;
        this.descricao = null;
        this.localização = null;
    }

    public Status(int id, int idrastreio, Timestamp horario, String descricao, String localização) {
        this.id = id;
        this.idrastreio = idrastreio;
        this.horario = horario;
        this.descricao = descricao;
        this.localização = localização;
    }

    public Timestamp getHorario() {
        return horario;
    }

    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalização() {
        return localização;
    }

    public void setLocalização(String localização) {
        this.localização = localização;
    }
}
