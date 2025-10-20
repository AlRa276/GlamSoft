package org.pi.models;
import java.util.List;
public class Clienta extends Persona{
    //El cliente puede hacer n citas
    private List<Cita> historial;
    public Clienta() {
    }

    public Clienta(int idPersona, String nombre, String apellido, String email, String telefono, String rol, String contrasenia, List<Cita> historial) {
        super(idPersona, nombre, apellido, email, telefono, rol, contrasenia);
        this.historial = historial;
    }

    public List<Cita> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Cita> historial) {
        this.historial = historial;
    }
}
