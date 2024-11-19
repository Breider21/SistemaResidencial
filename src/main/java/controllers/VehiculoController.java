package controllers;

import java.util.List;

import SistemaResidencial.FileHandler;
import models.Propietario;
import models.Vehiculo;




public class VehiculoController {

    private FileHandler fileHandler;
    private Vehiculo vehiculo;

    public VehiculoController() {
        this.fileHandler = new FileHandler();
        this.vehiculo = new Vehiculo();
    }

    public void registrarVehiculo(Vehiculo vehiculo, Propietario propietario) {
        vehiculo.setPropietario(propietario);
        FileHandler.agregarVehiculo("src/resources/data/vehiculos.txt", vehiculo);
    }

    public List<Vehiculo> obtenerVehiculos(String srcresourcesdatavehiculostxt) {
        return fileHandler.leerVehiculos(srcresourcesdatavehiculostxt);
    }   
}