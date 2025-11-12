package org.pi.dto;

public class EstilistaDTO {
        private int idEmpleado;
        private String nombre;
        private String telefono;
        private String emailUsuario; // Opcional, si quieres ver el email
        private String horarios;      // Ejemplo: "Lunes 09:00-17:00, Martes 10:00-18:00"
        private String servicios;     // Ejemplo: "Corte Caballero, Tinte, Manicura"

        // ... Constructor, Getters y Setters


    public EstilistaDTO() {
    }

    public EstilistaDTO(int idEmpleado, String nombre, String telefono, String emailUsuario, String horarios, String servicios) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.emailUsuario = emailUsuario;
        this.horarios = horarios;
        this.servicios = servicios;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }
}
