package org.pi.Config;

import org.pi.Repositories.RolRepository;
import org.pi.Routers.RolRouter;
import org.pi.Services.RolService;
import org.pi.Controllers.RolController;
import org.pi.Repositories.CategoriaRepository;
import org.pi.Routers.CategoriaRouter;
import org.pi.Services.CategoriaService;
import org.pi.Controllers.CategoriaController;


public class configModule {
    public static CategoriaRouter initCategoriaRouter(){
        CategoriaRepository cr = new CategoriaRepository();
        CategoriaService cs = new CategoriaService(cr);
        CategoriaController cc = new CategoriaController(cs);
        CategoriaRouter rc = new CategoriaRouter(cc);
        return rc;
    }
    public static RolRouter initRolRouter(){
        RolRepository rr = new RolRepository();
        RolService rs = new RolService(rr);
        RolController rc = new RolController(rs);
        RolRouter rou = new RolRouter(rc);
        return rou;
    }
}
