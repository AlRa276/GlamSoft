package org.pi;
import io.javalin.Javalin;
import org.pi.Config.configModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.json.JavalinJackson;
public class Main {
   
    public static void main(String[] args) {
       ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Javalin app = Javalin.create(config -> {

            config.jsonMapper(new JavalinJackson(mapper, false));
            
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> {
                    rule.reflectClientOrigin = true;
                    rule.allowCredentials = true;
                });
            });

        });

        // Registrar rutas ANTES de iniciar el servidor
        configModule.initCategoriaRouter().register(app);
        configModule.initCitaRouter().register(app);
        configModule.initComentarioRouter().register(app);
        configModule.initUsuarioRouter().register(app);
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

        // Ahora sí iniciar
        app.start(7001);

        app.events(event -> {
            event.serverStartFailed(() -> {});
            event.serverStarted(() -> {
                app.jettyServer().server().getConnectors()[0]
                        .getConnectionFactories()
                        .removeIf(cf -> cf.getProtocol().startsWith("h2"));
            });
        });
    }
}
