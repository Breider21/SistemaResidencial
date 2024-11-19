package controllers;

import java.util.List;

import SistemaResidencial.FileHandler;
import models.Pago;
import models.Propietario;

public class PagoController {

    public static PagoController crearConPagosDeArchivo(String ruta) {
        return new PagoController();
    }

    private FileHandler fileHandler;

    public PagoController() {
        this.fileHandler = new FileHandler();
    }

    public List<Pago> leerPagos(String ruta) {
        return FileHandler.leerPagos(ruta);
    }

    public void registrarPago(Propietario propietario, float monto, String descripcionPago) {
        int nuevoId = FileHandler.obtenerUltimoIdPago() + 1;
        Pago nuevoPago = new Pago(nuevoId, monto, propietario);
        fileHandler.agregarPago("src/resources/data/pagos.txt", nuevoPago);
        
    }
}