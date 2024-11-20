package models;



import java.util.List;
import java.util.Scanner;

import controllers.IncidenteController;
import controllers.UsuarioController;

public class Administrador extends Usuario {
    private List<Usuario> usuariosRegistrados; // Agregación: Un Administrador puede gestionar muchos Usuarios
    private List<Vehiculo> vehiculosRegistrados; // Agregación: Un Administrador puede registrar muchos Vehiculos
    private List<Incidente> incidentesRegistrados;
    private UsuarioController usuarioController;
        private IncidenteController incidenteController;
    
        public Administrador(int id, String nombre, String rol, String direccion, String telefono, String email) {
            super(id, nombre, rol, direccion, telefono, email);
            this.usuariosRegistrados = Sistema.getInstancia().getUsuarios();
            this.vehiculosRegistrados = Sistema.getInstancia().getVehiculos();
            this.incidentesRegistrados = Sistema.getInstancia().getIncidentes();
            this.usuarioController = new UsuarioController(); // Ahora sí se inicializa correctamente
            this.incidenteController = new IncidenteController();
    }
    
    

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void gestionarUsuariosYVehiculos(Scanner scanner) {
        while (true) {
            System.out.println("\nGestión de Usuarios y Vehículos:");
            System.out.println("1. Ver lista de usuarios");
            System.out.println("2. Ver lista de vehículos");
            System.out.println("3. Agregar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Agregar vehículo");
            System.out.println("6. Eliminar vehículo");
            System.out.println("7. Volver al menú anterior");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.println("\nUsuarios registrados:");
                    for (Usuario usuario : usuarioController.obtenerTodosLosUsuarios()) {
                        System.out.println(usuario.getNombre() + " - " + usuario.getRol());
                    }
                    break;
                case 2:
                    System.out.println("\nVehículos registrados:");
                    for (Vehiculo vehiculo : vehiculosRegistrados) {
                        System.out.println(vehiculo.getPlaca());
                    }
                    break;
                case 3:
                    System.out.println("\nIngrese los datos del nuevo usuario:");
                    System.out.println("ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.println("Nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Rol (Propietario, Administrador, Vigilante):");
                    String rol = scanner.nextLine();
                    System.out.println("Dirección:");
                    String direccion = scanner.nextLine();
                    System.out.println("Teléfono:");
                    String telefono = scanner.nextLine();
                    System.out.println("Email:");
                    String email = scanner.nextLine();
                    Usuario nuevoUsuario;
                    if (rol.equalsIgnoreCase("Propietario")) {
                        nuevoUsuario = new Propietario(id, nombre, rol, direccion, telefono, email);
                    } else if (rol.equalsIgnoreCase("Administrador")) {
                        nuevoUsuario = new Administrador(id, nombre, rol, direccion, telefono, email);
                    } else if (rol.equalsIgnoreCase("Vigilante")) {
                        nuevoUsuario = new Vigilante(id, nombre, rol, direccion, telefono, email, "Zona A");
                    } else {
                        System.out.println("Rol no válido.");
                        break;
                    }
                    usuarioController.registrarUsuario(nuevoUsuario);
                    System.out.println("Usuario registrado: " + nuevoUsuario.getNombre());
                    break;
                case 4:
                    System.out.println("\nIngrese el ID del usuario a eliminar:");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    Usuario usuarioEliminar = usuarioController.obtenerUsuarioPorId(idEliminar);
                    if (usuarioEliminar != null) {
                        usuarioController.eliminarUsuario(idEliminar);
                        System.out.println("Usuario eliminado: " + usuarioEliminar.getNombre());
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    case 5:
                    System.out.println("\nIngrese la placa del nuevo vehículo:");
                    String placa = scanner.nextLine().trim();
                
                    // Validar placa
                    if (placa.isEmpty()) {
                        System.out.println("La placa no puede estar vacía. Intente de nuevo.");
                        break;
                    }
                
                    System.out.println("Ingrese el modelo del vehículo:");
                    String modelo = scanner.nextLine().trim();
                    if (modelo.isEmpty()) {
                        System.out.println("El modelo no puede estar vacío. Intente de nuevo.");
                        break;
                    }
                
                    System.out.println("Ingrese el color del vehículo:");
                    String color = scanner.nextLine().trim();
                    if (color.isEmpty()) {
                        System.out.println("El color no puede estar vacío. Intente de nuevo.");
                        break;
                    }
                
                    System.out.println("Ingrese el ID del propietario del vehículo:");
                    int idPropietario;
                    try {
                        idPropietario = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea
                    } catch (Exception e) {
                        System.out.println("El ID del propietario debe ser un número. Intente de nuevo.");
                        scanner.nextLine(); // Limpiar el buffer
                        break;
                    }
                
                    // Buscar propietario
                    Propietario propietario = (Propietario) usuariosRegistrados.stream()
                            .filter(u -> u.getId() == idPropietario && u instanceof Propietario)
                            .findFirst()
                            .orElse(null);
                
                    if (propietario == null) {
                        System.out.println("Propietario no encontrado. Verifique el ID e intente nuevamente.");
                        break;
                    }
                
                    // Verificar si ya existe un vehículo con la misma placa
                    boolean placaDuplicada = vehiculosRegistrados.stream()
                            .anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa));
                
                    if (placaDuplicada) {
                        System.out.println("Error: Ya existe un vehículo registrado con la placa " + placa + ".");
                        break;
                    }
                
                    // Registrar el vehículo
                    try {
                        // Crear y registrar el vehículo
                        Vehiculo nuevoVehiculo = new Vehiculo(placa, propietario, modelo, color);
                        propietario.agregarVehiculo(nuevoVehiculo); // Registra en el propietario y sistema
                        vehiculosRegistrados.add(nuevoVehiculo); // Agregar a la lista general
                
                        System.out.println("Vehículo registrado exitosamente: " +
                                "\n  Placa: " + nuevoVehiculo.getPlaca() +
                                "\n  Modelo: " + nuevoVehiculo.getModelo() +
                                "\n  Color: " + nuevoVehiculo.getColor() +
                                "\n  Propietario: " + propietario.getNombre());
                    } catch (Exception e) {
                        System.err.println("Ocurrió un error al registrar el vehículo: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                
                case 6:
                    System.out.println("\nIngrese la placa del vehículo a eliminar:");
                    String placaEliminar = scanner.nextLine();
                    Vehiculo vehiculoEliminar = vehiculosRegistrados.stream()
                            .filter(v -> v.getPlaca().equalsIgnoreCase(placaEliminar))
                            .findFirst()
                            .orElse(null);
                    if (vehiculoEliminar != null) {
                        vehiculosRegistrados.remove(vehiculoEliminar);
                        System.out.println("Vehículo eliminado: " + vehiculoEliminar.getPlaca());
                    } else {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void registrarIncidente(Incidente incidente) {
        incidentesRegistrados.add(incidente);
        Sistema.getInstancia().registrarIncidente(incidente);
        System.out.println("Incidente registrado: " + incidente.getDescripcion());
    }

    public void gestionarPagos(Propietario propietario) {
        // Implementar la lógica para gestionar los pagos realizados por los propietarios
        List<Pago> pagos = propietario.getPagos();
        if (pagos == null || pagos.isEmpty()) {
            System.out.println("El propietario " + propietario.getNombre() + " no ha realizado ningún pago.");
        } else {
            for (Pago pago : pagos) {
                System.out.println("Pago realizado por " + propietario.getNombre() + ": " + pago.getMonto());
            }
        }
    }


    public void visualizarIncidentes(String ruta) {
        System.out.println("\nIncidentes registrados:");
        for (Incidente incidente : incidenteController.leerIncidentes(ruta)) {
            System.out.println("ID: " + incidente.getId() + ", Descripción: " + incidente.getDescripcion());
        }
    }

    public void cambiarEstadoIncidente(int idIncidente, String ruta) {
        List<Incidente> incidentes = incidenteController.leerIncidentes(ruta);
        boolean encontrado = false;

        for (Incidente incidente : incidentes) {
            if (incidente.getId() == idIncidente) {
                incidente.setEstado(true);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            incidenteController.guardarIncidentes(incidentes, ruta);
            System.out.println("El estado del incidente con ID " + idIncidente + " ha sido cambiado a true.");
        } else {
            System.out.println("Incidente con ID " + idIncidente + " no encontrado.");
        }
    }

    
    @Override
    public String toString() {
        return super.toString();
    }

    // Getters para las listas de usuarios, vehículos e incidentes
    public List<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public List<Vehiculo> getVehiculosRegistrados() {
        return vehiculosRegistrados;
    }

    public List<Incidente> getIncidentesRegistrados() {
        return incidentesRegistrados;
    }

    
}