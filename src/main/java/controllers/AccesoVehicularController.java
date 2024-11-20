package controllers;

import java.util.List;

import SistemaResidencial.FileHandler;
import models.AccesoVehicular;
import models.Invitado;
import models.Vehiculo;

public class AccesoVehicularController {

    private static final String RUTA_ACCESOS_VEHICULARES = "src/resources/data/accesos_vehiculares.txt";

    public void registrarAccesoVehiculoInvitado(Invitado invitado) {
        if (invitado.autorizacionEntrada()) {
            Vehiculo vehiculo = invitado.getVehiculo();
            vehiculo.registrarEntrada();
            System.out.println("Entrada autorizada para el vehículo del invitado: " + invitado.getNombre());
            AccesoVehicular acceso = new AccesoVehicular(vehiculo);
            acceso.setEntradaPermitida(true);
            FileHandler.agregarAccesoVehicular(acceso);
        } else {
            System.out.println("Acceso denegado para el vehículo del invitado: " + invitado.getNombre());
        }
    }

    public void registrarSalidaVehiculoInvitado(Invitado invitado) {
        Vehiculo vehiculo = invitado.getVehiculo();
        vehiculo.registrarSalida();
        System.out.println("Salida registrada para el vehículo del invitado: " + invitado.getNombre());
        // Actualizar el archivo de accesos vehiculares
        List<AccesoVehicular> accesos = FileHandler.leerAccesosVehiculares();
        for (AccesoVehicular acceso : accesos) {
            if (acceso.getVehiculo().getPlaca().equals(vehiculo.getPlaca())) {
                acceso.setEntradaPermitida(false);
                break;
            }
        }
        FileHandler.escribirAccesosVehiculares(RUTA_ACCESOS_VEHICULARES, accesos);
    }

    public List<AccesoVehicular> obtenerAccesosVehiculares() {
        return FileHandler.leerAccesosVehiculares();
    }

    public void registrarInvitado(Invitado invitado) {
        Vehiculo vehiculo = invitado.getVehiculo();
        AccesoVehicular acceso = new AccesoVehicular(vehiculo);
        acceso.setEntradaPermitida(true);
        FileHandler.agregarAccesoVehicular(acceso);
        System.out.println("Invitado registrado: " + invitado.getNombre() + " con vehículo de placa: " + vehiculo.getPlaca());
    }
}