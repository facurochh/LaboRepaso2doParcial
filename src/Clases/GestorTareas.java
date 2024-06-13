package Clases;

import Excepciones.ErrorGestorTareas;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GestorTareas <T extends Tarea> implements Serializable {

    public ArrayList<T> listaTareas;

    public GestorTareas() {
        listaTareas = new ArrayList<>();
    }

    public void agregarTarea(T tareaNueva) throws ErrorGestorTareas
    {
        for(T a : listaTareas)
        {
            if(listaTareas.contains(tareaNueva))
                throw new ErrorGestorTareas();
        }
        listaTareas.add(tareaNueva);
        System.out.println("Tarea agregada");
    }

    public void mostrarLista()
    {
        ArrayList<ArrayList<? extends Tarea>> listaOrdenada= ordenarLista();
        for(ArrayList<? extends Tarea> a : listaOrdenada)
        {
            for(Tarea b : a)
            {
                System.out.println(b.toString());
            }
        }
    }

    public ArrayList<ArrayList<? extends Tarea>> ordenarLista()
    {
        ArrayList<Estudio> listaEstudio = new ArrayList<>();
        ArrayList<Entrenamiento> listaEntrenamiento = new ArrayList<>();
        ArrayList<Social> listaSocial = new ArrayList<>();

        for(T a : listaTareas)
        {
            if(a instanceof Estudio)
                listaEstudio.add((Estudio)a);
            if(a instanceof Entrenamiento)
                listaEntrenamiento.add((Entrenamiento)a);
            if(a instanceof Social)
                listaSocial.add((Social)a);
        }
        ArrayList<ArrayList<? extends Tarea>> listaOrdenada = new ArrayList<>();
        listaOrdenada.add(listaEstudio);
        listaOrdenada.add(listaEntrenamiento);
        listaOrdenada.add(listaSocial);

        return listaOrdenada;
    }

    /// ARCHIVOS

    public void crearCarpeta(String rutaElegida)
    {
        Path ruta = Paths.get(rutaElegida);
        try
        {
            if(!Files.exists(ruta))
                Files.createDirectories(ruta);
        }
        catch (IOException e)
        {
            System.out.println("No se pudo crear la carpeta.");
            System.out.println(e.getMessage());
        }
    }

    public void guardarArchivos(String ruta)
    {
        ArrayList<ArrayList<? extends Tarea>> listaOrdenada = ordenarLista();
        for(ArrayList<? extends Tarea> a : listaOrdenada)
        {
            for(Tarea b : a)
            {
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream (new FileOutputStream(ruta+"/"+b.getClass()+".dat")))
                {
                    objectOutputStream.writeObject(a);
                    System.out.println("Tareas guardadas en " + ruta);
                }
                catch (Exception e) {
                    System.out.println("Error al guardar las tareas: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public void leerArchivos(String ruta)
    {
        ArrayList<ArrayList<? extends Tarea>> listaOrdenada = ordenarLista();
        for(ArrayList<? extends Tarea> a : listaOrdenada)
        {
            for(Tarea b : a)
            {
                try (ObjectInputStream objectIntputStream = new ObjectInputStream (new FileInputStream(ruta+"/"+b.getClass()+".dat")))
                {
                    Object aux = objectIntputStream.readObject();
                    if(aux!=null)
                        System.out.println(aux.toString());
                }
                catch (Exception e) {
                    System.out.println("Error al leer las tareas: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public void manejeArchivos(String rutaElegida)
    {
        crearCarpeta(rutaElegida);

    }

    private void guardarTareasEnArchivo(String rutaArchivo, ArrayList<? extends Tarea> tareas)
    {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream (new FileOutputStream(rutaArchivo))) {
            for (Tarea a : tareas) {
                objectOutputStream.writeObject(a);
            }
            System.out.println("Tareas guardadas en " + rutaArchivo);
        } catch (Exception e) {
            System.out.println("Error al guardar las tareas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
