package proyectoesd115;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class Memoria {
    
    Nodo cabeza;                   // El primer nodo de la lista enlazada
    List<Objeto> objetosEliminados; // Lista para almacenar objetos eliminados

    public Memoria() {
        objetosEliminados = new ArrayList<>(); // Inicializa la lista de objetos eliminados
    }

    // Método para asignar un objeto a la memoria
    public void asignarObjeto(Objeto objeto) {
        // Agrega un nuevo nodo con el objeto al principio de la lista enlazada
        Nodo nuevoNodo = new Nodo(objeto);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
    }

    // Método para recolectar objetos no utilizados y agregarlos a la lista de objetos eliminados
    public void recolectarBasura() {
        Nodo actual = cabeza;
        Nodo previo = null;

        while (actual != null) {
            if (!actual.objeto.enUso) {
                objetosEliminados.add(actual.objeto); // Agrega objeto eliminado a la lista
                if (previo == null) {
                    cabeza = actual.siguiente;
                } else {
                    previo.siguiente = actual.siguiente;
                }
            } else {
                previo = actual;
            }
            actual = actual.siguiente;
        }
    }

    // Método para mostrar los objetos eliminados
    public void mostrarObjetosEliminados() {
        System.out.println("Objetos eliminados:");
        for (Objeto objeto : objetosEliminados) {
            System.out.println("Objeto " + objeto.id + " - Tamaño: " + objeto.tamaño);
        }
    }
}
