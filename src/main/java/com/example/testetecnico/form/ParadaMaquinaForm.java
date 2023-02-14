package com.example.testetecnico.form;

import java.util.ArrayList;
import java.util.List;

public class ParadaMaquinaForm {

    private Long id;

    private String endTime;

    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static List<String> getValidarDadosInseridos(ParadaMaquinaForm form){
        List<String> erros = new ArrayList<>();

        if(form.id == null){
            erros.add("Id deve ser informado");
        }

        if (form.endTime == null && form.reason == null) {
            erros.add("Data do fim da parada, deve ser informada.");
        }

        if (form.endTime == null && form.reason == null) {
            erros.add("Motivo da parada, deve ser informado.");
        }

        return erros;
    }

}
