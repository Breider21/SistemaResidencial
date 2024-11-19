package controllers;

import java.util.List;

import SistemaResidencial.FileHandler;
import models.Usuario;

public class UsuarioController {

    public static UsuarioController crearConUsuariosDeArchivo(String ruta) {
        return new UsuarioController();
    }

    public void registrarUsuario(Usuario usuario) {
        FileHandler.agregarUsuario("src/resources/data/usuarios.txt", usuario);
    }

    public void eliminarUsuario(int id) {
        List<Usuario> usuarios = FileHandler.leerUsuarios("src/resources/data/usuarios.txt");
        usuarios.removeIf(u -> u.getId() == id);
        FileHandler.escribirUsuarios("src/resources/data/usuarios.txt", usuarios);
    }

    public Usuario obtenerUsuario(int id) {
        List<Usuario> usuarios = FileHandler.leerUsuarios("src/resources/data/usuarios.txt");
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return FileHandler.leerUsuarios("src/resources/data/usuarios.txt");
    }

    public Usuario iniciarSesion(int id, String rol) {
        List<Usuario> usuarios = FileHandler.leerUsuarios("src/resources/data/usuarios.txt");
        return usuarios.stream()
                .filter(u -> u.getId() == id && u.getRol().equalsIgnoreCase(rol))
                .findFirst()
                .orElse(null);
    }

    public Usuario obtenerUsuarioPorId(int idEliminar) {
        List<Usuario> usuarios = FileHandler.leerUsuarios("src/resources/data/usuarios.txt");
        return usuarios.stream().filter(u -> u.getId() == idEliminar).findFirst().orElse(null);
    }
}