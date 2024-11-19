package models;

import java.util.ArrayList;
import java.util.List;

import SistemaResidencial.FileHandler;

public class Sistema {
    private static Sistema instancia;
    private List<Usuario> usuarios; // Composición: Un Sistema tiene muchos Usuarios
    private List<Vehiculo> vehiculos; // Composición: Un Sistema tiene muchos Vehiculos
    private List<Incidente> incidentes; // Composición: Un Sistema puede tener muchos Incidentes
    private List<AccesoVehicular> accesosVehiculares; // Agregación: Un Sistema puede tener muchos AccesosVehiculares
    private List<Pago> pagos; // Agregación: Un Sistema puede tener muchos Pagos    

    private Sistema() {
        usuarios = new ArrayList<>();
        vehiculos = new ArrayList<>();
        incidentes = new ArrayList<>();
        pagos = new ArrayList<>();
        accesosVehiculares = new ArrayList<>();
    }

    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public void cargarDatos() {
        usuarios = FileHandler.leerUsuarios("src/resources/data/usuarios.txt");
        vehiculos = FileHandler.leerVehiculos("src/resources/data/vehiculos.txt");
        incidentes = FileHandler.leerIncidentes("src/resources/data/incidentes.txt");
        pagos = FileHandler.leerPagos("src/resources/data/pagos.txt");
    }

    public void guardarDatos() {
        FileHandler.escribirUsuarios("src/resources/data/usuarios.txt", usuarios);
        FileHandler.escribirVehiculos("src/resources/data/vehiculos.txt", vehiculos);
        FileHandler.escribirIncidentes("src/resources/data/incidentes.txt", incidentes);
        FileHandler.escribirPagos("src/resources/data/pagos.txt", pagos);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        FileHandler.agregarUsuario("src/resources/data/usuarios.txt", usuario);
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        FileHandler.agregarVehiculo("src/resources/data/vehiculos.txt", vehiculo);
    }

    public void registrarIncidente(Incidente incidente) {
        incidentes.add(incidente);
        FileHandler.agregarIncidente("src/resources/data/incidentes.txt", incidente);
    }

    public void MostrarIncidentes() {
        List<Incidente> incidentes = FileHandler.leerIncidentes("src/resources/data/incidentes.txt");
        for (Incidente incidente : incidentes) {
            System.out.println(incidente);
        }
    }

    public void realizarPago(Usuario usuario, float monto) {
        if (usuario instanceof Propietario) {
            Propietario propietario = (Propietario) usuario;
            propietario.realizarPago(monto);
            guardarDatos(); // Guardar los datos después de realizar el pago
        } else {
            System.out.println("El usuario no es un propietario y no puede realizar pagos.");
        }
    }

    public void notificarIncidente(Incidente incidente) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Vigilante || usuario instanceof Administrador) {
                // Notificar al vigilante o administrador sobre el incidente
                System.out.println(
                        "Notificando a " + usuario.getNombre() + " sobre el incidente: " + incidente.getDescripcion());
            }
        }
    }

    // Getters para las listas de usuarios, vehículos e incidentes
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public List<AccesoVehicular> getAccesosVehiculares() {
        return accesosVehiculares;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }
}

