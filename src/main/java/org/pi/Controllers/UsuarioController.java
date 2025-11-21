package org.pi.Controllers;
import io.javalin.http.Context;
import org.pi.Models.Usuario;
import org.pi.Models.Empleado;
import org.pi.Services.UsuarioService;
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
            int id = usuarioService.saveUser(usuario);
            usuario.setIdUsuario(id);
            String token = tokenManager.issueToken("" + id);
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
                // 404 no encontrado si el email no existe
                ctx.status(404).json(Map.of("error", "Usuario no encontrado"));
                return;
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
                ctx.status(403).json(Map.of("error", "Credenciales inválidas"));
            }
        } catch (SQLException e) {
            ctx.status(500).json(Map.of(
                    "success", false,
                    "message",e.getMessage()
            ));
        }
    }

    public void registrarEmpleadoCompleto(Context ctx) {
        try {
            Empleado empleado = ctx.bodyAsClass(Empleado.class);
            String hashedPass = Password.hash(empleado.getPassword()).withBcrypt().getResult();
            empleado.setPassword(hashedPass);

            int idUsuario = usuarioService.saveEmpleadoCompleto(empleado);
            empleado.setIdUsuario(idUsuario);
            String token = tokenManager.issueToken("" + idUsuario);

            ctx.status(201).json(Map.of(
                    "success", true,
                    "message", "Empleado registrado con éxito",
                    "idUsuario", idUsuario,
                    "token", token
            ));
        }
        catch (IllegalArgumentException e) {
            ctx.status(400).json(Map.of("success", false, "message", e.getMessage()));
        }
        catch (SQLException e) {
            ctx.status(500).json(Map.of("success", false, "message", "Error BD: " + e.getMessage()));
        }
        catch (Exception e) {
            ctx.status(500).json(Map.of("success", false, "message", e.getMessage()));
        }
    }

    public void updateEmpleadoCompleto(Context ctx) {
        try {
            Empleado empleado = ctx.bodyAsClass(Empleado.class);
            // Hashear password si se envía uno nuevo
            if(empleado.getPassword() != null && !empleado.getPassword().isBlank()){
                String hashedPass = Password.hash(empleado.getPassword()).withBcrypt().getResult();
                empleado.setPassword(hashedPass);
            }

            Usuario usuario = new Usuario(
                    empleado.getIdUsuario(),
                    empleado.getEmail(),
                    empleado.getPassword(),
                    empleado.getIdRol()
            );

            usuarioService.updateEmpleadoCompleto(usuario, empleado);
            ctx.json(Map.of("success", true, "message", "Empleado actualizado correctamente"));

        } catch (IllegalArgumentException e) {
            ctx.status(400).json(Map.of("error", e.getMessage()));
        } catch (SQLException e) {
            ctx.status(500).json(Map.of("error", "Error en base de datos: " + e.getMessage()));
        } catch (Exception e) {
            ctx.status(500).json(Map.of("error", e.getMessage()));
        }
    }

    // NUEVO: Listar todos
    public void findAll(Context ctx) {
        try {
            List<Usuario> usuarios = usuarioService.findAll();
            ctx.json(usuarios);
        } catch (SQLException e) {
            ctx.status(500).result("Error interno: " + e.getMessage());
        }
    }

    // MODIFICADO: Buscar por ID
    public void findById(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Usuario usuario = usuarioService.findById(id);
            if(usuario != null) {
                ctx.json(usuario);
            } else {
                ctx.status(404).result("No se encontro el usuario");
            }
        }catch (NumberFormatException e){
            ctx.status(400).result("El ID debe ser un número entero");
        }catch (SQLException e){
            ctx.status(500).result("Error interno");
        }
    }

    public void deleteUser(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            usuarioService.deleteUser(id);
            ctx.status(200).result("Se elimino el recurso con exito"); // 200 o 204
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID inválido");
        } catch (SQLException e) {
            ctx.status(500).result("Error interno");
        }
    }

    public void updateUser(Context ctx){
        try{
            Usuario usuario = ctx.bodyAsClass(Usuario.class);
            // Hash password si es necesario
            if(usuario.getPassword() != null && !usuario.getPassword().isBlank()){
                String hashedPass = Password.hash(usuario.getPassword()).withBcrypt().getResult();
                usuario.setPassword(hashedPass);
            }
            usuarioService.updateUser(usuario);
            ctx.status(200).result("Usuario actualizado con exito");
        } catch (Exception e) {
            ctx.status(400).result("Error: " + e.getMessage());
        }
    }
}