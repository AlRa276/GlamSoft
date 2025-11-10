package org.pi;
import io.javalin.Javalin;
import org.pi.Config.configModule;
public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*configuraciones*/).start(7000);

        configModule.initCategoriaRouter().register(app);
        configModule.initCitaRouter().register(app);
        configModule.initCitaServicioRouter().register(app);
        configModule.intiEmpleadoRouter().register(app);
        configModule.initEstilistaHorarioRouter().register(app);
        configModule.initEstilistaServicioRouter().register(app);
        configModule.initFormularioRepository().register(app);
        configModule.initHorarioRouter().register(app);
        configModule.initPortafolioRouter().register(app);
        configModule.initPreguntaRouter().register(app);
        configModule.initPromocionRouter().register(app);
        configModule.initRolRouter().register(app);
        configModule.initServicioPromocionRouter().register(app);
        configModule.initServicioRouter().register(app);
        configModule.initValoracionRouter().register(app);
    }
}