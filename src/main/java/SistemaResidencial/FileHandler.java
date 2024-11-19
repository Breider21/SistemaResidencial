package SistemaResidencial;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import models.Administrador;
import models.Incidente;
import models.Pago;
import models.Propietario;
import models.Usuario;
import models.Vehiculo;
import models.Vigilante;

public class FileHandler {

    private static final String ID_FILE_PATH = "src/resources/data/ultimo_id_pago.txt";

    public static int obtenerUltimoIdPago() {
        try (BufferedReader br = new BufferedReader(new FileReader(ID_FILE_PATH))) {
            String linea = br.readLine();
            return linea != null ? Integer.parseInt(linea) : 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    

    public static List<Usuario> leerUsuarios(String ruta) {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 6) {
                    System.out.println("Formato incorrecto en la linea: " + linea);
                    continue;
                }
                try {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String rol = datos[2];
                    String direccion = datos[3];
                    String telefono = datos[4];
                    String email = datos[5];

                    Usuario usuario;
                    if (rol.equalsIgnoreCase("Propietario")) {
                        usuario = new Propietario(id, nombre, rol, direccion, telefono, email);
                    } else if (rol.equalsIgnoreCase("Administrador")) {
                        usuario = new Administrador(id, nombre, rol, direccion, telefono, email);
                    } else if (rol.equalsIgnoreCase("Vigilante")) {
                        usuario = new Vigilante(id, nombre, rol, direccion, telefono, email, "Zona A");
                    } else {
                        System.out.println("Rol no válido en la línea: " + linea);
                        continue;
                    }
                    usuarios.add(usuario);
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir el ID a número en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static void agregarUsuario(String ruta, Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(usuario.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escribirUsuarios(String ruta, List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Usuario usuario : usuarios) {
                bw.write(usuario.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void eliminarUsuario(String ruta, int usuarioId) {
        List<Usuario> usuarios = leerUsuarios(ruta);
        usuarios.removeIf(usuario -> usuario.getId() == usuarioId);
        escribirUsuarios(ruta, usuarios);
    }

    public static List<Vehiculo> leerVehiculos(String ruta) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 5) { // Ahora esperamos 5 campos
                    System.out.println("Formato incorrecto en la línea: " + linea);
                    continue;
                }
                try {
                    String placa = datos[0];
                    int propietarioId = Integer.parseInt(datos[1]);
                    String modelo = datos[2];
                    String color = datos[3];
                    LocalDateTime fechaEntrada = datos[4].isEmpty() ? null : LocalDateTime.parse(datos[4]);
    
                    // Crear objeto propietario
                    Propietario propietario = new Propietario(propietarioId, "", "", "", "", "");
                    
                    // Crear y configurar el vehículo
                    Vehiculo vehiculo = new Vehiculo(placa, propietario, modelo, color);
                    vehiculo.setFechaEntrada(fechaEntrada);
    
                    vehiculos.add(vehiculo);
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.out.println("Error al convertir los datos en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
    
    
    public static void agregarVehiculo(String ruta, Vehiculo vehiculo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            // Formato: placa,idPropietario,modelo,color,fechaEntrada,fechaSalida
            bw.write(String.format("%s,%d,%s,%s,%s,%s",
                vehiculo.getPlaca(),
                vehiculo.getPropietario().getId(),
                vehiculo.getModelo(),
                vehiculo.getColor(),
                vehiculo.getFechaEntrada() != null ? vehiculo.getFechaEntrada().toString() : "",
                vehiculo.getFechaSalida() != null ? vehiculo.getFechaSalida().toString() : ""
            ));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void escribirVehiculos(String ruta, List<Vehiculo> vehiculos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Vehiculo vehiculo : vehiculos) {
                bw.write(vehiculo.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Incidente> leerIncidentes(String ruta) {
        List<Incidente> incidentes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 5) {
                    System.out.println("Formato incorrecto en la l��nea: " + linea);
                    continue;
                }
                try {
                    int id = Integer.parseInt(datos[0]);
                    String descripcion = datos[1];
                    int reportadoPorId = Integer.parseInt(datos[2]);
                    LocalDateTime fecha = LocalDateTime.parse(datos[3]);
                    boolean resuelto = Boolean.parseBoolean(datos[4]);
                    Incidente incidente = new Incidente(id, descripcion, new Usuario(reportadoPorId, "", "", "", "", ""));
                    incidente.setFecha(fecha);
                    incidente.setResuelto(resuelto);
                    incidentes.add(incidente);
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.out.println("Error al convertir los datos en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return incidentes;
    }

    public static void agregarIncidente(String ruta, Incidente incidente) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(incidente.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escribirIncidentes(String ruta, List<Incidente> incidentes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Incidente incidente : incidentes) {
                bw.write(incidente.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Incidente obtenerIncidentePorId(String ruta, int id) {
        List<Incidente> incidentes = leerIncidentes(ruta);
        return incidentes.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    public static void eliminarIncidente(String ruta, int id) {
        List<Incidente> incidentes = leerIncidentes(ruta);
        incidentes.removeIf(i -> i.getId() == id);
        escribirIncidentes(ruta, incidentes);
    }

    public static List<Pago> leerPagos(String ruta) {
        List<Pago> pagos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    try {
                        int id = Integer.parseInt(datos[0]);
                        float monto = Float.parseFloat(datos[1]);
                        int propietarioId = Integer.parseInt(datos[2]);
                        LocalDateTime fecha = LocalDateTime.parse(datos[3]);
                        Propietario propietario = new Propietario(propietarioId, "", "", "", "", "");
                        Pago pago = new Pago(id, monto, propietario);
                        pago.setFecha(fecha);
                        pagos.add(pago);
                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.out.println("Error al procesar línea: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer los pagos: " + e.getMessage());
        }
        return pagos;
    }
    

    public static void agregarPago(String ruta, Pago pago) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(pago.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el pago en el archivo: " + e.getMessage());
        }
    }

    public static void escribirPagos(String srcresourcesdatapagostxt, List<Pago> pagos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(srcresourcesdatapagostxt))) {
            for (Pago pago : pagos) {
                bw.write(pago.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir los pagos: " + e.getMessage());
        }
    }
}