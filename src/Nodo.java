import java.util.*;


class Nodo {
    int id;// Identificador
    List<Arista> conexiones; //Cada nodo viene con sus pesos
    Nodo padre;

    public Nodo(int id) {
        this.id = id;
        this.conexiones = new ArrayList<>();
        this.padre=null;
    }

    public void conectarCon(Nodo destino, int tiempo) {
        this.conexiones.add(new Arista(destino, tiempo));
        
    }

    public Nodo(int id, List<Arista> conexiones, Nodo padreaux) {
        this.id = id;
        this.conexiones = conexiones;
        this.padre = padreaux;
    }

    @Override
    public String toString() { return "Nodo " + id; }
}//Nodo