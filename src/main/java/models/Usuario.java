/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Usuario {
    private int id;
    private String nombre;
    private String rol;
    private String direccion;
    private String telefono;
    private String email;

    public Usuario(int id, String nombre, String rol, String direccion, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public boolean iniciarSesion(String username, String password) {
        // Implementar la lógica de verificación de credenciales
        // Aquí podrías agregar la lógica para verificar las credenciales del usuario
        return true; // Retornar true si las credenciales son correctas
    }

    public String obtenerRol() {
        return this.rol;
    }

    public void reportarIncidente(Incidente incidente) {
        // Implementar la lógica para reportar un incidente
        Sistema sistema = Sistema.getInstancia();
        sistema.registrarIncidente(incidente);
    }

    public void realizarPago(float monto) {
        // Implementar la lógica para realizar un pago
        if (this instanceof Propietario) {
            Propietario propietario = (Propietario) this;
            propietario.realizarPago(monto);
        } else {
            System.out.println("El usuario no es un propietario y no puede realizar pagos.");
        }
    }
    
    @Override
    public String toString() {
        return id + "," + nombre + "," + rol + "," + direccion + "," + telefono + "," + email;
    }

    // Getters y setters para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}