package Clases;

import java.io.Serializable;

public class Estudio extends Tarea implements Serializable {
    public String materia;
    public enum Categoria{TP, PARCIAL, TAREA, REPASO}
    public Categoria categoria;

    public Estudio(String fecha, String descripcion, String materia, Categoria categoria) {
        super(fecha, descripcion);
        this.materia = materia;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return descripcion+" "+fecha+" "+materia;
    }
}