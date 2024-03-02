package Trabajos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class EscritorArchivo {
    private FileWriter escritor;
    private final String nombre;
    private String línea;
    private int num;
    
    public EscritorArchivo(){
        nombre = "Personas ";
        num = 1;
    }
    
    public void escribirArchivo(ArrayList<Persona> listado){
        String name = asignarNombre();
        try {
            escritor = new FileWriter("src/Datos/" + name + ".txt");
            for (Persona p : listado) {
                línea = p.toString() + "\n";
                escritor.write(línea);
            }
            escritor.close();
        } catch (IOException ex) {
            System.err.println("Archivo no encontrado");
        }
    }
    
    public ArrayList<Persona> cargarDatos(String nomA){
        ArrayList<Persona> lista = new ArrayList<>(); String [] x;
        try {
            Scanner s = new Scanner(new File("src/Datos/" + nomA + ".txt"));
            while(s.hasNext()){
                x = s.nextLine().split(",");
                lista.add(new Persona(x[0], Integer.parseInt(x[1]), Double.parseDouble(x[2]), Double.parseDouble(x[3]), Double.parseDouble(x[4])));
            }
                return lista;
       } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, "Archivo no encontrado");
        }
        return null;
    }
    
    private String asignarNombre(){
        String nombreArchivo = nombre + num;
        while(new File("src/Datos/" + nombreArchivo + ".txt").exists()){
            nombreArchivo = nombre + ++num;
        }
        return nombreArchivo;
    }
    
    public static void main(String[] args) {
        ArrayList<Persona> listado = new ArrayList<>();
        listado.add(new Persona("Federico", 25, 58.0, 1.80, 43.0));
        new EscritorArchivo().escribirArchivo(listado);
    }
}
