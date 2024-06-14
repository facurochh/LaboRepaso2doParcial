import Clases.Entrenamiento;
import Clases.Estudio;
import Clases.GestorTareas;
import Clases.Social;
import Excepciones.ErrorGestorTareas;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        GestorTareas gestor1 = new GestorTareas();

        for(int i = 0; i<15; i++)
        {
            Estudio parcialLabo = new Estudio("1"+i+"-06-24", "Hacer parcial de repaso "+i, "Laboratorio", Estudio.Categoria.REPASO);
            Social visitaAbuelos = new Social(i+"-06-24", "Visitar abuelos", Social.Grupo.FAMILIA, false);
            try {
                gestor1.agregarTarea(parcialLabo);
                gestor1.agregarTarea(visitaAbuelos);
            }
            catch (ErrorGestorTareas e)
            {
                System.out.println(e.getMessage());
            }
            if(i==7)
            {
                for(int j=0; j<8; j++)
                {
                    Entrenamiento irGimnasio = new Entrenamiento("2"+i+"-03-25", "Anotarse al gym", 2.4, Entrenamiento.Categoria.GIMNASIO);
                    try
                    {
                        gestor1.agregarTarea(irGimnasio);
                    }
                    catch (ErrorGestorTareas e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        /// ARCHIVOS NORMAL
        gestor1.mostrarLista();
        gestor1.guardarArchivos("D:\\Usuario\\Desktop\\Probando");
        System.out.println("LEER");
        gestor1.leerArchivos("D:\\Usuario\\Desktop\\Probando");

        /// JSON
        System.out.println("Escribiendo JSON: ");
        gestor1.guardarArchivosJson("D:\\Usuario\\Desktop\\ProbandoJSON");
        System.out.println("Lectura desde JSON: ");
        gestor1.leerArchivosJson("D:\\Usuario\\Desktop\\ProbandoJSON");



    }
}