package org.pi.services;
import org.pi.models.Clienta;
import java.util.*;

public class ClientaService {
    private final List<Clienta> clientas = new ArrayList<>();
    private int generarId = 1;

    public Clienta crarClienta(Clienta clienta){
        clienta.setIdPersona(generarId++);
        clientas.add(clienta);
        return clienta;
    }

    public List<Clienta> verClientas(){
        return clientas;
    }

    public Clienta obtenerPorId(int id){
        return clientas.stream().filter(c -> c.getIdPersona() == id).findFirst().orElse(null);
    }

    public Boolean eliminarClienta(int id){
        return clientas.removeIf(c-> c.getIdPersona() == id);
    }

    public Clienta actualizarClienta(int id, Clienta datos){
        Clienta clienta = obtenerPorId(id);
        if (clienta != null){
            clienta.setContrasenia(datos.getContrasenia());
            clienta.setEmail(datos.getEmail());
            clienta.setTelefono(datos.getTelefono());
        }
        return clienta;
    }

}
