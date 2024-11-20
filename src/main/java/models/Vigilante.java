package models;

import java.util.List;

import controllers.IncidenteController;
import controllers.MercanciaController;

public class Vigilante extends Usuario {
    private String zonaAsignada;
    private List<Incidente> incidentesRegistrados; // Agregación: Un Vigilante puede registrar muchos Incidentes
    private boolean cuotaPaga;
    private IncidenteController incidenteController;
    
    public Vigilante(int id, String nombre, String rol, String direccion, String telefono, String email,
            String zonaAsignada) {
        super(id, nombre, rol, direccion, telefono, email);
        this.zonaAsignada = zonaAsignada;
        this.incidentesRegistrados = Sistema.getInstancia().getIncidentes();
        this.incidenteController = new IncidenteController();
        this.cuotaPaga = false;
    }

    public void registrarIncidente(Incidente incidente) {
        incidenteController.registrarIncidente(incidente);
        incidentesRegistrados.add(incidente);
        System.out.println("Incidente registrado: " + incidente.getDescripcion());
        
    }

    public List<Incidente> verIncidentes() {
            return incidenteController.leerIncidentes(zonaAsignada);
    }

    public void gestionarAccesoVehiculo(Vehiculo vehiculo) {
        // Implementar la lógica para gestionar el acceso de vehículos y visitantes
        AccesoVehicular acceso = new AccesoVehicular(vehiculo);
        if (acceso.verificarAcceso(vehiculo)) {
            acceso.registrarAcceso(vehiculo);
            System.out.println("Acceso permitido para el vehículo con placa: " + vehiculo.getPlaca());
        } else {
            System.out.println("Acceso denegado para el vehículo con placa: " + vehiculo.getPlaca());
        }
    }
    
    @Override
    public String toString() {
        return super.toString();
    }

    // Getter y setter para zonaAsignada
    public String getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(String zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }

    private MercanciaController mercanciaController = new MercanciaController();

    public void registrarMercancia(Mercancia mercancia) {
        mercanciaController.registrarMercancia(getId(), zonaAsignada, zonaAsignada, getId());
        System.out.println("Mercancía registrada: " + mercancia.getDescripcion());
    }
}