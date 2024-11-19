package models;

public class AccesoVehicular {
    private Vehiculo vehiculo;
    private boolean entradaPermitida;

    public AccesoVehicular(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.entradaPermitida = false;
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
        } else {
            this.entradaPermitida = false;
            System.out.println("Acceso denegado para el vehículo con placa: " + vehiculo.getPlaca());
        }
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