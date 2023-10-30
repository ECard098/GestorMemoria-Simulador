package proyectoesd115;

// Clase que representa un nodo en una lista enlazada
class Nodo {

    Objeto objeto;   // Almacena un objeto en el nodo
    Nodo siguiente;  // Referencia al siguiente nodo en la lista

    public Nodo(Objeto objeto) {
        this.objeto = objeto;
        this.siguiente = null;
    }
}
