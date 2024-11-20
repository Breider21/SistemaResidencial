package models;

import java.text.SimpleDateFormat;
import java.util.Date;

import controllers.MercanciaController;   // Importa la clase MercanciaController

public class Mercancia {

    private int idMercancia; // Identificador único para la mercancía
    private String descripcion; // Descripción de la mercancía
    private Date fechaLlegada; // Fecha en la que llegó la mercancía
    private Propietario propietario; // Relación: un propietario puede tener varias mercancías
    private MercanciaController mercanciaController; // Controlador para manejar las operaciones de mercancía

    // Constructor para inicializar los atributos de la mercancía
    public Mercancia(int idMercancia, String descripcion, Date fechaLlegada, Propietario propietario) {
        this.idMercancia = idMercancia;
        this.descripcion = descripcion;
        this.fechaLlegada = fechaLlegada;
        this.propietario = propietario;
        this.mercanciaController = new MercanciaController();
    }

    // Método para obtener detalles de una mercancía
    public String obtenerDetalles() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "ID: " + idMercancia + "\nDescripción: " + descripcion + "\nFecha de llegada: "
                + sdf.format(fechaLlegada) + "\nPropietario: " + propietario.getNombre() + "\n";
    }

    // Método para actualizar la fecha de llegada de una mercancía
    public boolean actualizarFechaLlegada() {
        return mercanciaController.actualizarFechaLlegada(idMercancia);
    }

    // Getters y setters
    public int getIdMercancia() {
        return idMercancia;
    }

    public void setIdMercancia(int idMercancia) {
        this.idMercancia = idMercancia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Object getId() {
        return idMercancia;
    }

    public Object getProveedor() {
        return propietario;
    }

    public Object getFechaEntrada() {
        return fechaLlegada;
    }

    public Object getFechaSalida() {
        return fechaLlegada;
    }

    public Object isEntregada() {
       return fechaLlegada != null;
    }
}
