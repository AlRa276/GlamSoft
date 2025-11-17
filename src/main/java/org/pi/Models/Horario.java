package org.pi.Models;
import java.time.LocalTime;

public class Horario {
    private int idHorario;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String diaSemana; //cambiar de vachar a int en la base de datos

    public Horario() {
    }

    public Horario(LocalTime horaInicio, LocalTime horaFin, String diaSemana) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diaSemana = diaSemana;
    }

    public Horario(int idHorario, LocalTime horaInicio, LocalTime horaFin, String diaSemana) {
        this.idHorario = idHorario;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diaSemana = diaSemana;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getDiaSemana() {
        return diaSemana;
    }
}
