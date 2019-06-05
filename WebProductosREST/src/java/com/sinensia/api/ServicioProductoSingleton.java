/*
 * Clase patrón Singleton, sólo puede haber una instancia (objeto)
 * en toda la aplicación de este tipo de clase
 */
package com.sinensia.api;

import java.util.ArrayList;

public class ServicioProductoSingleton {
    
    private ArrayList<Producto> listaProductos;
    
    public void insertar(Producto p){
        listaProductos.add(p);
    }
    
    public Producto modificar(Producto p){
        p.setNombre(p.getNombre() + " - Modificado");
        p.setPrecio(p.getPrecio() + " - Modificado");
        return p;
    }
    
    public ArrayList<Producto> obtenerTodos(){
        return listaProductos;
    }
    
    //Necesitamos una única instancia de esta clase (privada)
    private static ServicioProductoSingleton instancia = null;
    //Constructor privado: Nadie puede hacer new excepto dentro de esta clase.
    //Tb podría ser protected
    private ServicioProductoSingleton(){
        this.listaProductos = new ArrayList<>();
        
    }
    //La 1ª vez que se llama al método se crea la instancia. A partir de ese momento
    //hasta que la aplicación termine, la instancia seguirá "viva" y es devuelta por el método
    //Accedemos a la estancia única con un método de clase
    public static ServicioProductoSingleton getInstancia(){
        if(instancia == null){
            instancia = new ServicioProductoSingleton();
        }
        return instancia;
    }
}
