package org.pi;
import io.javalin.Javalin;
import org.pi.Config.configModule;
public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*configuraciones*/).start(7000);

        configModule.initCategoriaRouter().register(app);
        configModule.initCitaRouter().register(app);
        configModule.initComentarioRouter().register(app);
        configModule.initEmpleadoRouter().register(app);
        configModule.initEstilistaRouter().register(app);
        configModule.initFormularioRouter().register(app);
        configModule.initHorarioRouter().register(app);
        configModule.initPortafolioRouter().register(app);
        configModule.initPreguntaRouter().register(app);
        configModule.initPromocionRouter().register(app);
        configModule.initRolRouter().register(app);
        configModule.initServicioRouter().register(app);
        configModule.initValoracionRouter().register(app);
    }
}