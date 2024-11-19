/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.List;

import controllers.PagoController;
import controllers.VehiculoController;

public class Propietario extends Usuario {
    private boolean cuotaPaga;
    private List<Vehiculo> vehiculos; // Composición: Un Propietario puede tener muchos Vehiculos
    private List<Pago> pagos; // Agregación: Un Propietario puede realizar muchos Pagos
    private PagoController pagoController;

    public Propietario(int id, String nombre, String rol, String direccion, String telefono, String email) {
        super(id, nombre, rol, direccion, telefono, email);
        this.cuotaPaga = false;
        this.vehiculos = new ArrayList<>();
        this.pagos = new ArrayList<>();
        this.pagoController = new PagoController();
    }

    public void realizarPago(int idPago, float monto) {
        Pago pago = new Pago(idPago, monto, this);
        pagoController.registrarPago(null, monto, getDireccion());
        this.cuotaPaga = true; // Marca la cuota como pagada
        System.out.println("Pago realizado con éxito. Monto: " + monto);
    }
    

    public List<Incidente> verIncidentes() {
        // Implementar la lógica para ver los incidentes registrados
        Sistema sistema = Sistema.getInstancia();
        List<Incidente> incidentes = sistema.getIncidentes();
        if (incidentes == null || incidentes.isEmpty()) {
            System.out.println("No hay incidentes registrados.");
        }
        return incidentes;
    }

    public List<Vehiculo> verVehiculos() {
        if (vehiculos == null || vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        }
        return vehiculos;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        // Validar si el vehículo ya está registrado
        boolean yaExiste = this.vehiculos.stream()
            .anyMatch(v -> v.getPlaca().equalsIgnoreCase(vehiculo.getPlaca()));
    
        if (yaExiste) {
            System.out.println("Error: El vehículo con placa " + vehiculo.getPlaca() + " ya está registrado.");
            return;
        }
    
        // Registrar la entrada del vehículo
        vehiculo.registrarEntrada();
    
        try {
            // Usar VehiculoController para registrar el vehículo
            VehiculoController vehiculoController = new VehiculoController();
            vehiculoController.registrarVehiculo(vehiculo, this);
    
            // Agregar el vehículo a la lista del propietario
            this.vehiculos.add(vehiculo);
            System.out.println("Vehículo registrado exitosamente: " + vehiculo.getPlaca());
        } catch (Exception e) {
            System.err.println("Error al registrar el vehículo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void verTodosLosVehiculos() {
        if (this.vehiculos == null || this.vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados para este propietario.");
            return;
        }
    
        System.out.println("Vehículos registrados para el propietario " + this.getNombre() + ":");
        for (Vehiculo vehiculo : this.vehiculos) {
            System.out.println("- Placa: " + vehiculo.getPlaca() +
                               ", Modelo: " + (vehiculo.getModelo() != null ? vehiculo.getModelo() : "Desconocido") +
                               ", Color: " + (vehiculo.getColor() != null ? vehiculo.getColor() : "Desconocido") +
                               ", Fecha Entrada: " + (vehiculo.getFechaEntrada() != null ? vehiculo.getFechaEntrada() : "No registrada") +
                               ", Fecha Salida: " + (vehiculo.getFechaSalida() != null ? vehiculo.getFechaSalida() : "No registrada"));
        }
    }
    
    
    
    
    

    // Getter y setter para cuotaPaga
    public boolean isCuotaPaga() {
        return cuotaPaga;
    }

    public void setCuotaPaga(boolean cuotaPaga) {
        this.cuotaPaga = cuotaPaga;
    }

    // Getters y setters para vehiculos y pagos
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
