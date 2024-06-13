package Excepciones;

public class ErrorGestorTareas extends Exception{
    @Override
    public String getMessage() {
        return "Error en el gestor de tareas!";
    }
}
