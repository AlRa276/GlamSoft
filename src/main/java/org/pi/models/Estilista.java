package org.pi.models;
import java.util.List;

public class Estilista extends Persona{
    private String usuario;
    //El estilista tiene n cantidad de horarios asignados
    private List<Horario> horarios;
    //El estilista tiene n cantidad de citas asignadas
    private List<Cita> citas;
    //El estilista puede tener mas de una especialidas
    private String[] especialidades;

    public Estilista() {
    }

    public Estilista(int idPersona, String nombre, String apellido, String email, String telefono, String rol, String contrasenia, String usuario, List<Horario> horarios, List<Cita> citas, String[] especialidades) {
        super(idPersona, nombre, apellido, email, telefono, rol, contrasenia);
        this.usuario = usuario;
        this.horarios = horarios;
        this.citas = citas;
        this.especialidades = especialidades;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public String[] getEspecialidad() {
        return especialidades;
    }

    public void setEspecialidad(String[] especialidad) {
        this.especialidades = especialidad;
    }

    public String[] getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String[] especialidades) {
        this.especialidades = especialidades;
    }

}
