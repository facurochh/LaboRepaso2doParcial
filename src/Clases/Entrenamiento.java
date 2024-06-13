package Clases;

import java.io.Serializable;

public class Entrenamiento extends Tarea implements Serializable{
    public double tiempo;
    public enum Categoria{DEPORTE, GIMNASIO, ESTIRAMIENTO, AEROBICO}
    public Categoria categoria;

    public Entrenamiento(String fecha, String descripcion, double tiempo, Categoria categoria) {
        super(fecha, descripcion);
        this.tiempo = tiempo;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return descripcion+" "+fecha+" "+tiempo;
    }
}
