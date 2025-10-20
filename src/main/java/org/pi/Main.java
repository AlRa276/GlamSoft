package org.pi;
import io.javalin.Javalin;
import org.pi.controllers.ClientaController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*configuraciones*/).start(7000);

        ClientaController clientaControlador = new ClientaController();

        app.post("/Clientas", clientaControlador::crearClient);
        app.get("/Clientas", clientaControlador::verClients);
        app.get("/Clienta/{id}", clientaControlador::obtenerClienta);
        app.put("/Clienta/{id}", clientaControlador::actualizarDatos);
        app.delete("/Clienta/{id}", clientaControlador::eliminarClient);
    }
}