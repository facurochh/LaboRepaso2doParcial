package Clases;

import java.io.Serializable;

public class Social extends Tarea implements Serializable {
    public enum Grupo{FAMILIA, AMIGOS, COMPAÑEROS}
    public Grupo grupo;
    public boolean requiereDinero;

    public Social(String fecha, String descripcion, Grupo grupo, boolean requiereDinero) {
        super(fecha, descripcion);
        this.grupo = grupo;
        this.requiereDinero = requiereDinero;
    }

    @Override
    public String toString() {
        return descripcion+" "+fecha+" "+grupo;
    }
}
