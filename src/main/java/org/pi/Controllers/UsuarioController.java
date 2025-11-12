package org.pi.Controllers;
import io.javalin.http.Context;
import org.pi.Models.Usuario;
import org.pi.Services.UsuarioService;
import io.javalin.http.HttpStatus;
import com.password4j.Password;
import java.util.Map;
import java.sql.SQLException;
import java.util.List;

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final TokenManager tokenManager;

    public UsuarioController(UsuarioService usuarioService, TokenManager tokenManager) {
        this.usuarioService = usuarioService;
        this.tokenManager = tokenManager;
    }

    public void registrarUsuario(Context ctx){
        try{
            Usuario usuario = ctx.bodyAsClass(Usuario.class);
            String passHased = Password.hash(usuario.getPassword()).withBcrypt().getResult();
            usuario.setPassword(passHased);
            usuarioService.saveUser(usuario);
            String token = tokenManager.issueToken(""+usuario.getIdUsuario());
            ctx.status(201).json(Map.of(
                    "userId", usuario.getIdUsuario(),
                    "token", token,
                    "success", true,
                    "message", "Usuario registrado con exito"
            ));
        }catch (SQLException e){
            ctx.status(500).result("Error al registrar el usuario" + e.getMessage());
        }catch (Exception e){
            ctx.status(500).result(e.getMessage());
        }
    }
    public void verificarUsuario(Context ctx){
        Usuario usuario = ctx.bodyAsClass(Usuario.class);
        try{
            Usuario user = usuarioService.findByUser(usuario);
            if (user == null){
                throw new SQLException("Usuario no encontrado");
            }
            boolean rs = Password.check(usuario.getPassword(),user.getPassword()).withBcrypt();
            if (rs){
                String token = tokenManager.issueToken(user.getIdUsuario()+"");
                ctx.json(Map.of(
                        "userID",user.getIdUsuario(),
                        "token",token,
                        "success",true,
                        "mensage","Usuario verificado"
                ));
            }  else {
                ctx.status(404).result("Usuario no encontrado");
            }
        } catch (SQLException e) {
            ctx.status(403).json(Map.of(
                    "error","Credenciales invalidad",
                    "success", false,
                    "message",e.getMessage()
            ));
        }
    }
    public void findAllUsers(Context ctx){
        try{
            List<Usuario> usuarios = usuarioService.findAllUsers();
            ctx.json(usuarios);
        }catch (SQLException e){
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void findUser(Context ctx){
        try{
            String email = ctx.pathParam("email");
            Usuario usuario = usuarioService.findUser(email);
            ctx.json(usuario);
        }catch (SQLException e){
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void deleteUser(Context ctx){
        try{
            String email = ctx.pathParam("email");
            usuarioService.deleteUser(email);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void updateUser(Context ctx){
        try{
            String email = ctx.pathParam("email");
            Usuario usuario = ctx.bodyAsClass(Usuario.class);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (Exception e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
