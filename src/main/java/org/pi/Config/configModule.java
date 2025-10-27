package org.pi.Config;

import org.pi.Repositories.RolRepository;
import org.pi.Routers.RolRouter;
import org.pi.Services.RolService;
import org.pi.Controllers.RolController;
import org.pi.Repositories.PersonaRepository;
import org.pi.Routers.PersonaRouter;
import org.pi.Services.PersonaService;
import org.pi.Controllers.PersonaController;
import org.pi.Repositories.CategoriaRepository;
import org.pi.Routers.CategoriaRouter;
import org.pi.Services.CategoriaService;
import org.pi.Controllers.CategoriaController;
import org.pi.Repositories.ValoracionRepository;
import org.pi.Routers.ValoracionRouter;
import org.pi.Services.ValoracionService;
import org.pi.Controllers.ValoracionController;


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
    public static PersonaRouter initPersonaRouter(){
        PersonaRepository pr = new PersonaRepository();
        PersonaService ps = new PersonaService(pr);
        PersonaController pc = new PersonaController(ps);
        PersonaRouter rp = new PersonaRouter(pc);
        return rp;
    }
    public static ValoracionRouter initValoracionRouter(){
        ValoracionRepository vr = new ValoracionRepository();
        ValoracionService vs = new ValoracionService(vr);
        ValoracionController vc = new ValoracionController(vs);
        ValoracionRouter rv = new ValoracionRouter(vc);
        return rv;
    }
}
