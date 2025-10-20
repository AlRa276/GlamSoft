package org.pi.services;
import org.pi.models.Estilista;
import java.util.*;

public class EstilistaService {
    private final List<Estilista> estilistas = new ArrayList<>();
    private int generarId;

    public Estilista crearEstilista(Estilista estilista){
        estilista.setIdPersona(generarId++);
        estilistas.add(estilista);
        return estilista;
    }

    public List<Estilista> verEstilistas(){
        return estilistas;
    }

    public Estilista obtenerPorId(int id){
        return estilistas.stream().filter(e -> e.getIdPersona() == id).findFirst().orElse(null);
    }

    public Boolean eliminarEstilista(int id){
        return estilistas.removeIf(e -> e.getIdPersona() == id);
    }

    public Estilista actualizarDatos(int id, Estilista datos){
        Estilista estilista = obtenerPorId(id);
        if (estilista != null){
            estilista.setEspecialidad(datos.getEspecialidad());
            estilista.setHorarios(datos.getHorarios());
            estilista.setContrasenia(datos.getContrasenia());
            estilista.setEmail(datos.getEmail());
            estilista.setTelefono(datos.getTelefono());
        }
        return estilista;
    }
}
