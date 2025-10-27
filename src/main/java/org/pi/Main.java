package org.pi;
import io.javalin.Javalin;
import org.pi.Config.configModule;
public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*configuraciones*/).start(7000);

        configModule.initCategoriaRouter().register(app);
        configModule.initRolRouter().register(app);
        configModule.initPersonaRouter().register(app);
        configModule.initValoracionRouter().register(app);
    }
}