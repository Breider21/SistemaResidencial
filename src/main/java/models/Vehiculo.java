// Vehiculo.java
package models;

import java.time.LocalDateTime;

public class Vehiculo {
    private String placa;
    private Propietario propietario;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private String modelo;  // Nuevo atributo
    private String color;   // Nuevo atributo

    public Vehiculo(String placa, Propietario propietario, String modelo, String color) {
        this.placa = placa;
        this.propietario = propietario;
        this.modelo = modelo;
        this.color = color;
    }

    public Vehiculo() {
    }

    // Métodos existentes...
    public void registrarEntrada() {
        this.fechaEntrada = LocalDateTime.now();
    }

    public void registrarSalida() {
        this.fechaSalida = LocalDateTime.now();
    }

    public String obtenerEstadoAcceso() {
        if (fechaSalida == null || fechaEntrada.isAfter(fechaSalida)) {
            return "Permitido";
        } else {
            return "Denegado";
        }
    }

    @Override
    public String toString() {
        return placa + "," + 
            propietario.getId() + "," + 
            (modelo != null ? modelo : "") + "," + 
            (color != null ? color : "") + "," + 
            (fechaEntrada != null ? fechaEntrada.toString() : "") + "," + 
            (fechaSalida != null ? fechaSalida.toString() : "");
    }


    // Getters y setters existentes...
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    // Nuevos getters y setters
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getFechaEntrada() {
        return fechaEntrada;
    }

    public Object getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}