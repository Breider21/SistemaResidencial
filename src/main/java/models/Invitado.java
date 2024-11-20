/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Invitado {
    private String nombre;
    private String documentoIdentidad;
    private Propietario propietario; // Agregación: Un Invitado está asociado a un Propietario
    private Vehiculo vehiculo;

    public Invitado(String nombre, Vehiculo vehiculo) {
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.propietario = propietario;
        this.vehiculo = vehiculo;
    }

    public boolean autorizacionEntrada() {
        // Implementar la lógica para verificar si el invitado tiene acceso autorizado
        // por el propietario
        return propietario != null && propietario.isCuotaPaga();
    }

    public void registrarEntrada() {
        // Implementar la lógica para registrar la entrada del invitado al conjunto
        if (autorizacionEntrada()) {
            System.out.println("Entrada registrada para el invitado: " + nombre);
        } else {
            System.out.println("Acceso denegado para el invitado: " + nombre);
        }
    }

    // Getters y setters para los atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
