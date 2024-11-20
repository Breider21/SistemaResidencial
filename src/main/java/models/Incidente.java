/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;

public class Incidente {
    private int id;
    private String descripcion;
    private LocalDateTime fecha;
    private Usuario reportadoPor; // Composición: Un Incidente es reportado por un único Usuario
    private boolean resuelto;

    public Incidente(int id, String descripcion, Usuario reportadoPor) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
        this.reportadoPor = reportadoPor;
        this.resuelto = false;
    }

    public void reportarIncidente(String descripcion) {
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
        this.resuelto = false;
    }

    public void resolverIncidente() {
        this.resuelto = true;
    }
    
    @Override
    public String toString() {
        return id + "," + descripcion + "," + reportadoPor.getId() + "," + fecha.toString() + "," + resuelto;
    }

    // Getters y setters para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getReportadoPor() {
        return reportadoPor;
    }

    public void setReportadoPor(Usuario reportadoPor) {
        this.reportadoPor = reportadoPor;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    void setEstado(boolean b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}