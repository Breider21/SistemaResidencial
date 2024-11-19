/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

public class Vigilante extends Usuario {
    private String zonaAsignada;
    private List<Incidente> incidentesRegistrados; // Agregación: Un Vigilante puede registrar muchos Incidentes
    private boolean cuotaPaga;
    
    public Vigilante(int id, String nombre, String rol, String direccion, String telefono, String email,
            String zonaAsignada) {
        super(id, nombre, rol, direccion, telefono, email);
        this.zonaAsignada = zonaAsignada;
        this.incidentesRegistrados = Sistema.getInstancia().getIncidentes();
        this.cuotaPaga = false;
    }

    public void registrarIncidente(Incidente incidente) {
        // Implementar la lógica para registrar un incidente en el sistema
        incidentesRegistrados.add(incidente);
        Sistema sistema = Sistema.getInstancia();
        sistema.registrarIncidente(incidente);
    }

    public List<Incidente> verIncidentes() {
        if (incidentesRegistrados == null || incidentesRegistrados.isEmpty()) {
            System.out.println("No hay incidentes registrados.");
        }
        return incidentesRegistrados;
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
}
