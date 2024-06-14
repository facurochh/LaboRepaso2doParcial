package Clases;

import Excepciones.ErrorGestorTareas;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
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

    //// ARCHIVOS

    /// metodo del power del campus
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

    //// JSON

    // ObjectMapper
    // escribir con ObjectMapper
    public void guardarTareasEnJson(String rutaArchivo) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(rutaArchivo);
            objectMapper.writeValue(file, listaTareas);
            System.out.println("Tareas guardadas en " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas en JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // leer con ObjectMapper
    public void cargarTareasDesdeJson(String rutaArchivo) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(rutaArchivo);
            listaTareas = objectMapper.readValue(file, new TypeReference<ArrayList<T>>() {});
            System.out.println("Tareas cargadas desde " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al cargar las tareas desde JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // escribir con Gson
    public void guardarArchivosJson(String ruta) {
        Gson gson = new Gson();
        ArrayList<ArrayList<? extends Tarea>> listaOrdenada = ordenarLista();
        for (ArrayList<? extends Tarea> a : listaOrdenada) {
            if (!a.isEmpty()) {
                Tarea tareaEjemplo = a.get(0);
                String nombreArchivo = ruta + "/" + tareaEjemplo.getClass().getSimpleName() + ".json";
                try (FileWriter writer = new FileWriter(nombreArchivo)) {
                    gson.toJson(a, writer);
                    System.out.println("Tareas guardadas en " + nombreArchivo);
                } catch (IOException e) {
                    System.out.println("Error al guardar las tareas: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    // leer con Gson (creo que está mal)
    public void leerArchivosJson(String ruta) {
        Gson gson = new Gson();
        ArrayList<ArrayList<? extends Tarea>> listaOrdenada = ordenarLista();
        for (ArrayList<? extends Tarea> a : listaOrdenada) {
            if (!a.isEmpty()) {
                Tarea tareaEjemplo = a.get(0);
                String nombreArchivo = ruta + "/" + tareaEjemplo.getClass().getSimpleName() + ".json";
                try (FileReader reader = new FileReader(nombreArchivo)) {
                    Type listType = new TypeToken<ArrayList<Tarea>>() {}.getType();
                    ArrayList<Tarea> tareasLeidas = gson.fromJson(reader, listType);
                    for (Tarea tarea : tareasLeidas) {
                        System.out.println(tarea);
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer las tareas: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

}
