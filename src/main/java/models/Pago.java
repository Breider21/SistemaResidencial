/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;

import controllers.PagoController;

public class Pago {
    private int id;
    private float monto;
    private LocalDateTime fecha;
    private Propietario propietario; // Agregación: Un Pago está asociado a un Propietario
    private PagoController pagoController;

    public Pago(int id, float monto, Propietario propietario) {
        this.id = id;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.propietario = propietario;
        this.pagoController = new PagoController();
    }

    public void realizarPago() {
        // Implementar la lógica para realizar el pago y actualizar el estado del
        // propietario
        pagoController.registrarPago(propietario, monto, null);
        this.fecha = LocalDateTime.now();
        propietario.setCuotaPaga(true);
        System.out.println("Pago realizado por " + propietario.getNombre() + " de monto: " + monto);
    }

    // Getters y setters para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
public String toString() {
    return id + "," + monto + "," + propietario.getId() + "," + fecha.toString();
}

}
