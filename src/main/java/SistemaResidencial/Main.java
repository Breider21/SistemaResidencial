package SistemaResidencial;

import java.util.List;
import java.util.Scanner;

import controllers.IncidenteController;
import controllers.PagoController;
import controllers.UsuarioController;
import controllers.VehiculoController;
import models.Administrador;
import models.Incidente;
import models.Propietario;
import models.Sistema;
import models.Usuario;
import models.Vehiculo;
import models.Vigilante;


public class Main {
    private static UsuarioController usuarioController;
    private static IncidenteController incidenteController;

    public static void main(String[] args) {
        usuarioController = UsuarioController.crearConUsuariosDeArchivo("src/resources/data/usuarios.txt");
        incidenteController = IncidenteController.crearConIncidentesDeArchivo("src/resources/data/incidentes.txt");
        Sistema sistema = Sistema.getInstancia();
        Scanner scanner = new Scanner(System.in);

        // Cargar usuarios desde el archivo
        sistema.cargarDatos();

        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar nuevo usuario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    iniciarSesion(scanner);
                    break;
                case 2:
                    registrarNuevoUsuario(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void iniciarSesion(Scanner scanner) {
        System.out.println("\nIngrese su ID de usuario:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        System.out.println("Ingrese su rol (Propietario, Administrador, Vigilante):");
        String rol = scanner.nextLine();
    
        Usuario usuario = usuarioController.iniciarSesion(id, rol);
        if (usuario != null) {
            if (usuario instanceof Administrador) {
                Administrador admin = new Administrador(
                    id, usuario.getNombre(), rol, usuario.getDireccion(),
                    usuario.getTelefono(), usuario.getEmail()
                );
                mostrarMenuAdministrador(admin, scanner);
            } else if (usuario instanceof Propietario) {
                Propietario propietario = new Propietario(
                    id, usuario.getNombre(), rol, usuario.getDireccion(),
                    usuario.getTelefono(), usuario.getEmail()
                );
                mostrarMenuPropietario(propietario, scanner);
            } else if (usuario instanceof Vigilante) {
                Vigilante vigilante = new Vigilante(
                    id, usuario.getNombre(), rol, usuario.getDireccion(),
                    usuario.getTelefono(), usuario.getEmail(), "Zona A"
                );
                //mostrarMenuVigilante(vigilante, scanner);
            } else {
                System.out.println("Rol no soportado para iniciar sesión.");
            }
        } else {
            System.out.println("Usuario no encontrado o credenciales incorrectas.");
        }
    }
    

    private static void registrarNuevoUsuario(Scanner scanner) {
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
            return;
        }
        usuarioController.registrarUsuario(nuevoUsuario);
        System.out.println("Usuario registrado: " + nuevoUsuario.getNombre());
    }

    private static void mostrarMenuAdministrador(Administrador admin, Scanner scanner) {
        while (true) {
            System.out.println("\nMenú Administrador:");
            System.out.println("1. Registrar propietario");
            System.out.println("2. Registrar incidente");
            System.out.println("3. Gestionar pagos");
            System.out.println("4. Visualizar todos los incidentes");
            System.out.println("5. Gestionar usuarios y vehículos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.println("\nIngrese los datos del propietario:");
                    System.out.println("ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.println("Nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Dirección:");
                    String direccion = scanner.nextLine();
                    System.out.println("Teléfono:");
                    String telefono = scanner.nextLine();
                    System.out.println("Email:");
                    String email = scanner.nextLine();

                    Propietario nuevoPropietario = new Propietario(id, nombre, "Propietario", direccion, telefono, email);
                    usuarioController.registrarUsuario(nuevoPropietario);
                    System.out.println("Propietario registrado: " + nuevoPropietario.getNombre());
                    break;
                case 2:
                    System.out.println("\nIngrese la descripción del incidente:");
                    String descripcion = scanner.nextLine();
                    Incidente incidente = new Incidente(admin.getId(), descripcion, admin);
                    incidenteController.registrarIncidente(incidente);
                    System.out.println("Incidente registrado: " + incidente.getDescripcion());
                case 3:
                    // Lógica para gestionar pagos
                    break;
                case 4:
                    System.out.println("\nLista de todos los incidentes:");
                    for (Incidente inc : incidenteController.leerIncidentes("src/resources/data/incidentes.txt")) {
                        System.out.println("ID: " + inc.getId() + ", Descripción: " + inc.getDescripcion());
                    }
                    break;
                case 5:
                    admin.gestionarUsuariosYVehiculos(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    private static void mostrarMenuPropietario(Propietario propietario, Scanner scanner) {
        while (true) {
            System.out.println("\nMenú Propietario:");
            System.out.println("1. Realizar pago");
            System.out.println("2. Reportar incidente");
            System.out.println("3. Ver lista de vehículos registrados");
            System.out.println("4. Agregar un nuevo vehículo");
            System.out.println("5. Ver incidentes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                System.out.println("\nIngrese el monto del pago:");
                double monto = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea
                System.out.println("Ingrese la descripción del pago:");
                String descripcionPago = scanner.nextLine();
        
                // Asumiendo que hay un PagoController y un método registrarPago
                PagoController pagoController = new PagoController();
                pagoController.registrarPago(propietario, (float) monto, descripcionPago);
        
                System.out.println("Pago realizado con éxito.");
                    break;
                case 2:
                    System.out.println("\nIngrese la descripción del incidente:");
                    String descripcion = scanner.nextLine();
                    Incidente incidente = new Incidente(propietario.getId(), descripcion, propietario);
                    propietario.reportarIncidente(incidente);
                    break;
                case 3:
                    System.out.println("\nVehículos registrados:");

                    // Usar el controlador para leer los vehículos desde el archivo
                    VehiculoController vehiculoController = new VehiculoController();
                    List<Vehiculo> vehiculosDesdeArchivo = vehiculoController.obtenerVehiculos("src/resources/data/vehiculos.txt");

                    if (vehiculosDesdeArchivo.isEmpty()) {
                        System.out.println("No hay vehículos registrados en el sistema.");
                    } else {
                        System.out.println("Listado de vehículos:");
                        for (Vehiculo vehiculo : vehiculosDesdeArchivo) {
                            System.out.println("- Placa: " + vehiculo.getPlaca() +
                                            "\n  Modelo: " + vehiculo.getModelo() +
                                            "\n  Color: " + vehiculo.getColor() +
                                            "\n  Propietario ID: " + vehiculo.getPropietario().getId() +
                                            "\n  Fecha de entrada: " + 
                                            (vehiculo.getFechaEntrada() != null ? vehiculo.getFechaEntrada().toString() : "Sin registrar") +
                                            "\n  Fecha de salida: " + 
                                            (vehiculo.getFechaSalida() != null ? vehiculo.getFechaSalida().toString() : "Sin registrar") +
                                            "\n");
                        }
                    }
                    break;

                    case 4:
                        System.out.println("\nIngrese los datos del vehículo:");
                        System.out.print("Placa: ");
                        String placa = scanner.nextLine();
                        System.out.print("Modelo: ");
                        String modelo = scanner.nextLine();
                        System.out.print("Color: ");
                        String color = scanner.nextLine();
                    
                        Vehiculo nuevoVehiculo = new Vehiculo(placa, propietario, modelo, color);
                        propietario.agregarVehiculo(nuevoVehiculo);
                
                    break;
                    
                
                case 5:
                    System.out.println("\nIncidentes registrados:");
                    for (Incidente inc : propietario.verIncidentes()) {
                        System.out.println(inc.getDescripcion());
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("\nOpción no válida.");
            }
        }
    }

    /*
    private static void mostrarMenuVigilante(Vigilante vigilante, Scanner scanner) {
        while (true) {
            System.out.println("\nMenú Vigilante:");
            System.out.println("1. Registrar un incidente");
            System.out.println("2. Gestionar acceso vehicular");
            System.out.println("3. Ver lista de incidentes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.println("\nIngrese la descripción del incidente:");
                    String descripcion = scanner.nextLine();
                    Incidente incidente = new Incidente(vigilante.getId(), descripcion, vigilante);
                    vigilante.registrarIncidente(incidente);
                    sistema.registrarIncidente(incidente);
                    break;
                case 2:
                    System.out.println("\nIngrese la placa del vehículo:");
                    String placa = scanner.nextLine();
                    Vehiculo vehiculo = sistema.getVehiculos().stream()
                            .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                            .findFirst()
                            .orElse(null);
                    if (vehiculo != null) {
                        vigilante.gestionarAccesoVehiculo(vehiculo);
                    } else {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("\nIncidentes registrados:");
                    for (Incidente inc : vigilante.verIncidentes()) {
                        System.out.println(inc.getDescripcion());
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\nOpción no válida.");
            }
        }
    }
    */

}

