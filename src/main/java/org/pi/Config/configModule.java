package org.pi.Config;

import org.pi.Controllers.*;
import org.pi.Models.Servicio;
import org.pi.Repositories.*;
import org.pi.Routers.*;
import org.pi.Services.*;


public class configModule {
    public static CategoriaRouter initCategoriaRouter(){
        CategoriaRepository cr = new CategoriaRepository();
        CategoriaService cs = new CategoriaService(cr);
        CategoriaController cc = new CategoriaController(cs);
        CategoriaRouter rc = new CategoriaRouter(cc);
        return rc;
    }

    public static CitaRouter initCitaRouter(){
        CitaRepository cr = new CitaRepository();
        CitaService cs = new CitaService(cr);
        CitaController cc = new CitaController(cs);
        CitaRouter rc = new CitaRouter(cc);
        return rc;
    }

    public static CitaServicioRouter initCitaServicioRouter(){
        CitaServicioRepository csr = new CitaServicioRepository();
        CitaServicioServices css = new CitaServicioServices(csr);
        CitaServicioController csc = new CitaServicioController(css);
        CitaServicioRouter rcs = new CitaServicioRouter(csc);
        return rcs;
    }

    public static ComentarioRouter initComentarioRouter(){
        ComentarioRepository cr = new ComentarioRepository();
        ComentarioService cs = new ComentarioService(cr);
        ComentarioController cc = new ComentarioController(cs);
        ComentarioRouter rc = new ComentarioRouter(cc);
        return rc;
    }

    public static EmpleadoRouter intiEmpleadoRouter(){
        EmpleadoRepository er = new EmpleadoRepository();
        EmpleadoService es = new EmpleadoService(er);
        EmpleadoController ec = new EmpleadoController(es);
        EmpleadoRouter re = new EmpleadoRouter(ec);
        return re;
    }

    public static EstilistaHorarioRouter initEstilistaHorarioRouter(){
        EstilistaHorarioRepository ehr = new EstilistaHorarioRepository();
        EstilistaHorarioService ehs = new EstilistaHorarioService(ehr);
        EstilistaHorarioController ehc = new EstilistaHorarioController(ehs);
        EstilistaHorarioRouter rhs = new EstilistaHorarioRouter(ehc);
        return rhs;
    }

    public static EstilistaServicioRouter initEstilistaServicioRouter(){
        EstilistaServicioRepository esr = new EstilistaServicioRepository();
        EstilistaServicioService ess = new EstilistaServicioService(esr);
        EstilistaServicioController esc = new EstilistaServicioController(ess);
        EstilistaServicioRouter rse = new EstilistaServicioRouter(esc);
        return rse;
    }

    public static FormularioRouter initFormularioRepository(){
        FormularioRepository fr = new FormularioRepository();
        FormularioService fs = new FormularioService(fr);
        FormularioController fc = new FormularioController(fs);
        FormularioRouter rf = new FormularioRouter(fc);
        return rf;
    }

    public static HorarioRouter initHorarioRouter(){
        HorarioRepository hr = new HorarioRepository();
        HorarioService hs = new HorarioService(hr);
        HorarioController hc = new HorarioController(hs);
        HorarioRouter rh = new HorarioRouter(hc);
        return rh;
    }

    public static PortafolioRouter initPortafolioRouter(){
        PortafolioRepository pr = new PortafolioRepository();
        PortafolioService ps = new PortafolioService(pr);
        PortafolioController pc = new PortafolioController(ps);
        PortafolioRouter rp = new PortafolioRouter(pc);
        return rp;
    }

    public static PreguntaRouter initPreguntaRouter(){
        PreguntaRepository pr = new PreguntaRepository();
        PreguntaService ps = new PreguntaService(pr);
        PreguntaController pc = new PreguntaController(ps);
        PreguntaRouter rp = new PreguntaRouter(pc);
        return rp;
    }

    public static PromocionRouter initPromocionRouter(){
        PromocionRepository pr = new PromocionRepository();
        PromocionService ps = new PromocionService(pr);
        PromocionController pc = new PromocionController(ps);
        PromocionRouter rp = new PromocionRouter(pc);
        return rp;
    }

    public static RolRouter initRolRouter(){
        RolRepository rr = new RolRepository();
        RolService rs = new RolService(rr);
        RolController rc = new RolController(rs);
        RolRouter rou = new RolRouter(rc);
        return rou;
    }

    public static ServicioPromocionRouter initServicioPromocionRouter(){
        ServicioPromocionRepository spr = new ServicioPromocionRepository();
        ServicioPromocionService sps = new ServicioPromocionService(spr);
        ServicioPromocionController spc = new ServicioPromocionController(sps);
        ServicioPromocionRouter rps = new ServicioPromocionRouter(spc);
        return rps;
    }

    public static ServicioRouter initServicioRouter(){
        ServicioRepository sr = new ServicioRepository();
        ServicioService ss = new ServicioService(sr);
        ServicioController sc = new ServicioController(ss);
        ServicioRouter rs = new ServicioRouter(sc);
        return rs;
    }

    public static ValoracionRouter initValoracionRouter(){
        ValoracionRepository vr = new ValoracionRepository();
        ValoracionService vs = new ValoracionService(vr);
        ValoracionController vc = new ValoracionController(vs);
        ValoracionRouter rv = new ValoracionRouter(vc);
        return rv;
    }

}
