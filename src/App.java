import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //Creamos los nodos
        Nodo[] n=new Nodo[7];
        for (int i = 0; i < 7; i++) {
                n[i]=new Nodo(i);// Los inicializamos
        }//for

            //Conexiones
            n[0].conectarCon(n[1], 1);
            n[0].conectarCon(n[2], 1);
            n[1].conectarCon(n[3], 1);
            n[1].conectarCon(n[4], 1);
            n[2].conectarCon(n[5], 1);
            n[3].conectarCon(n[6], 1);
            n[4].conectarCon(n[5], 1);
            n[4].conectarCon(n[6], 1);

            //resolvemos con algoritmo CSP - con backtracking
            // Satisfacción de restricciones con vuelta atrás


            /*Parámetros que le vamos a pasar:
             - Nodo inicial
             - Nodo meta -- estado final
             - ruta 
             - tiempo total 
             */
            //Variables para la resolución con CSPO
            List<Nodo> rutaInicial=new ArrayList<>();
            rutaInicial.add(n[0]);//añadimos el nodo inicial
            int tiempoInicial=0;

            boolean exito=resolverCSP(n[0],n[6],rutaInicial, tiempoInicial); 

            if(!exito) System.out.println("No hay posible solución, flor de loto. ");

        

    }//main


    // CSP - Backtracking

    private static boolean resolverCSP(Nodo actual, Nodo meta, List<Nodo> rutaInicial, int tiempoInicial) {
        
        //Empezmos con las restricciones
        
        //Restricción de número de saltos
        if(rutaInicial.size()>4) //mayor que 4 
            return false;

        //¿hemos llegado a la meta?
        if(actual.id==meta.id){
            //hemos llegado al final 
            //Comprobamos que son 3 saltos, no 4
            if(rutaInicial.size()==4){
                System.out.println("Hemos encontrado solución, alma de pollo!!!");
                imprimirRuta(rutaInicial, tiempoInicial);
                return true; //Devolvemos éxito!
            }//2if
            return false;
        }//1if

        //que no! miramos los vecinos...
        for(Arista arista:actual.conexiones){
            Nodo vecino=arista.destino;
            System.out.println("Nodo que estamos visitando: "+ vecino.id);

            //Restricción. Si en la ruta, ese destino ya está metido... 
            if(rutaInicial.contains(vecino)) continue;

            //restricción nodo 1 y 3 no purula
            if((actual.id==1 && vecino.id==3)||(actual.id==3 && vecino.id==1)) continue;
        
            //restrición 
            if(vecino.id==4 && estaEnLaRuta(rutaInicial,1)) continue;

            //Asignamos al padre
            vecino.padre=actual;
            rutaInicial.add(vecino);

            //Llamada recursiva 
            if(resolverCSP( vecino, meta, rutaInicial, tiempoInicial+arista.tiempo)){
                return true;
            }

            // Si ha devuelto false.
            // BAcktracking de libro
            rutaInicial.remove(rutaInicial.size()-1) ;
            vecino.padre=null;

        }//for

        
        return false;
    }//resolverCSP


    private static boolean estaEnLaRuta(List<Nodo> rutaInicial, int nodoBuscado) {
      for(Nodo n:rutaInicial){
        if(n.id==nodoBuscado) return true;
      }
      return false;


    }//estaEnLaRuta


    private static void imprimirRuta(List<Nodo> rutaInicial, int tiempoInicial) {
        for (int i = 0; i < rutaInicial.size(); i++) {
            System.out.println("Nodo: "+ rutaInicial.get(i).id );
        }//for

        System.out.println(" Tiempo que hemos tardado ----> " + tiempoInicial);

    }//imprimirRuta



}//App
