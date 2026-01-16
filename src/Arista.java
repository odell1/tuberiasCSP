import java.util.ArrayList;
import java.util.List;

class Arista {
    Nodo destino;
    int tiempo; // Tiempo, peso , ponderación, cuanfeo es el nodo siguiente, ... 

    public Arista(Nodo destino, int tiempo) {
        this.destino = destino;
        this.tiempo = tiempo;
    }
}//Arista