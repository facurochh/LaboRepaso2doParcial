package Clases;

import java.io.Serializable;
import java.util.Objects;

public class Tarea implements Serializable {
    private int id;
    private static int idContador = 0;
    public String fecha;
    public String descripcion;
    public boolean completada;

    public Tarea(String fecha, String descripcion) {
        this.id = idContador++;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.completada = false;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarea tarea)) return false;
        return id == tarea.id && completada == tarea.completada && Objects.equals(fecha, tarea.fecha) && Objects.equals(descripcion, tarea.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, descripcion, completada);
    }
}
