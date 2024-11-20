package models;

import controllers.AccesoVehicularController;

public class AccesoVehicular {
    private Vehiculo vehiculo;
    private boolean entradaPermitida;
    private AccesoVehicularController accesoVehicularController;

    public AccesoVehicular(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.entradaPermitida = false;
        this.accesoVehicularController = new AccesoVehicularController();
    }

    public boolean verificarAcceso(Vehiculo vehiculo) {
        // Implementar la lógica para verificar si un vehículo tiene acceso permitido
        // Por ejemplo, podrías verificar si el propietario ha pagado la cuota
        return vehiculo.getPropietario().isCuotaPaga();
    }

    public void registrarAcceso(Vehiculo vehiculo) {
        if (verificarAcceso(vehiculo)) {
            vehiculo.registrarEntrada();
            this.entradaPermitida = true;
            System.out.println("Acceso permitido para el vehículo con placa: " + vehiculo.getPlaca());
            accesoVehicularController.registrarAccesoVehiculoInvitado(new Invitado("", vehiculo));
        } else {
            this.entradaPermitida = false;
            System.out.println("Acceso denegado para el vehículo con placa: " + vehiculo.getPlaca());
        }
    }

    public void registrarSalida(Vehiculo vehiculo) {
        vehiculo.registrarSalida();
        System.out.println("Salida registrada para el vehículo con placa: " + vehiculo.getPlaca());
        accesoVehicularController.registrarSalidaVehiculoInvitado(new Invitado("", vehiculo));
    }

    // Getters y setters para los atributos
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public boolean isEntradaPermitida() {
        return entradaPermitida;
    }

    public void setEntradaPermitida(boolean entradaPermitida) {
        this.entradaPermitida = entradaPermitida;
    }
}