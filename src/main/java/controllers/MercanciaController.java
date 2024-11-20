package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SistemaResidencial.FileHandler;
import models.Mercancia;
import models.Propietario;
import models.Usuario;

public class MercanciaController {

    private static final String RUTA_MERCANCIAS = "src/resources/data/mercancias.txt";
    private static final String RUTA_USUARIOS = "src/resources/data/usuarios.txt";

    // Método para registrar una nueva mercancía
    public boolean registrarMercancia(int idMercancia, String descripcion, String fechaLlegadaStr, int idPropietario) {
        Date fechaLlegada = parseFecha(fechaLlegadaStr);

        if (idMercancia < 0 || descripcion.isEmpty() || fechaLlegada == null || idPropietario < 0) {
            System.out.println("Error: Datos inválidos para registrar la mercancía.");
            return false;
        }

        List<Usuario> usuarios = FileHandler.leerUsuarios(RUTA_USUARIOS);
        Propietario propietario = buscarPropietario(idPropietario, usuarios);

        if (propietario == null) {
            System.out.println("Error: Propietario con ID " + idPropietario + " no encontrado.");
            return false;
        }

        Mercancia mercancia = new Mercancia(idMercancia, descripcion, fechaLlegada, propietario);

        FileHandler.agregarMercancia(RUTA_MERCANCIAS, mercancia);
        System.out.println("Mercancía registrada exitosamente.");
        return true;
    }

    // Método para mostrar todas las mercancías
    public void mostrarTodaMercancia() {
        List<Mercancia> mercancias = FileHandler.leerMercancias(RUTA_MERCANCIAS);

        System.out.println("\n-------- Lista de Mercancías --------");
        if (mercancias.isEmpty()) {
            System.out.println("No hay mercancías registradas.");
        } else {
            for (Mercancia mercancia : mercancias) {
                System.out.println(mercancia.obtenerDetalles());
            }
        }
    }

    // Método para buscar mercancía por ID
    // Método para obtener todas las mercancías de un propietario específico
public List<Mercancia> obtenerMercanciaPorIdPropietario(int idPropietario) {
    List<Mercancia> mercancias = FileHandler.leerMercancias(RUTA_MERCANCIAS);
    List<Mercancia> mercanciasDelPropietario = new ArrayList<>();

    for (Mercancia mercancia : mercancias) {
        if (mercancia.getPropietario().getId() == idPropietario) {
            mercanciasDelPropietario.add(mercancia);
        }
    }

    return mercanciasDelPropietario;
}


    // Método para buscar un propietario por ID
    private Propietario buscarPropietario(int id, List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Propietario) {
                Propietario propietario = (Propietario) usuario;
                if (propietario.getId() == id) {
                    return propietario;
                }
            }
        }
        return null;
    }

    // Método para convertir una fecha desde un String
    private Date parseFecha(String fechaStr) {
        String[] formatos = { "yyyy-MM-dd", "yyyy/MM/dd" };

        for (String formato : formatos) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                sdf.setLenient(false);
                return sdf.parse(fechaStr);
            } catch (Exception e) {
                // Ignorar excepciones y continuar probando formatos
            }
        }
        System.out.println("Error: Formato de fecha inválido.");
        return null;
    }

    // Método para actualizar la fecha de llegada de una mercancía
    public boolean actualizarFechaLlegada(int idMercancia) {
        List<Mercancia> mercancias = FileHandler.leerMercancias(RUTA_MERCANCIAS);

        for (Mercancia mercancia : mercancias) {
            if (mercancia.getIdMercancia() == idMercancia) {
                mercancia.actualizarFechaLlegada();
                FileHandler.escribirMercancias(RUTA_MERCANCIAS, mercancias);
                System.out.println("Fecha de llegada actualizada para la mercancía con ID " + idMercancia);
                return true;
            }
        }

        System.out.println("Error: No se encontró mercancía con ID " + idMercancia);
        return false;
    }

}
