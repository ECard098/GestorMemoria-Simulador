package proyectoesd115;


// Clase que representa un objeto en memoria
public class Objeto {
    static int contId = 1;
    
    int id;         // Identificador único del objeto
    int tamaño;     // Tamaño del objeto en memoria
    boolean enUso;  // Indica si el objeto está en uso o no

    public Objeto(int tamaño, boolean t) {
        this.id = contId;
        this.tamaño = tamaño;
        this.enUso = t;
    }

    // Método para marcar el objeto como no en uso
    public void liberar() {
        enUso = false;
    }
}
