package org.pi.Models;

import java.util.List;

public class EstilistaHorario {
    private int idEstilista;
    private Empleado estilista;
    private int idHorario;
    private List<Horario> horarios;

    public EstilistaHorario(int idEstilista, Empleado estilista, int idHorario, List<Horario> horarios) {
        this.idEstilista = idEstilista;
        this.estilista = estilista;
        this.idHorario = idHorario;
        this.horarios = horarios;
    }

    public EstilistaHorario() {
    }
    public  EstilistaHorario(int idEstilista, int idHorario){
        this.idEstilista = idEstilista;
        this.idHorario = idHorario;
    }

    public int getIdEstilista() {
        return idEstilista;
    }

    public void setIdEstilista(int idEstilista) {
        this.idEstilista = idEstilista;
    }

    public Empleado getEstilista() {
        return estilista;
    }

    public void setEstilista(Empleado estilista) {
        this.estilista = estilista;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
