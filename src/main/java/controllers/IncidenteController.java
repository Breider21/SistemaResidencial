package controllers;

import java.util.List;

import SistemaResidencial.FileHandler;
import models.Incidente;    

public class IncidenteController {

    public static IncidenteController crearConIncidentesDeArchivo(String ruta) {
        return new IncidenteController();
    }

    private FileHandler fileHandler;

    public IncidenteController() {
        this.fileHandler = new FileHandler();
    }


    public List<Incidente> leerIncidentes(String ruta) {
        return FileHandler.leerIncidentes(ruta="src/resources/data/incidentes.txt");
    }

    
    

    public void registrarIncidente(Incidente incidente) {
        String ruta = "src/resources/data/incidentes.txt";
        FileHandler.agregarIncidente(ruta, incidente);
    }

    public void guardarIncidentes(List<Incidente> incidentes, String ruta) {
        fileHandler.guardarIncidentes(incidentes, ruta);
    }
}
