package org.pi.models;
import java.time.LocalTime;
import java.time.LocalDate;

public class Horario {
    private int idHorario;
    private LocalDate dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Horario() {
    }

    public Horario(int idHorario, LocalDate dia, LocalTime horaInicio, LocalTime horaFin) {
        this.idHorario = idHorario;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

}
