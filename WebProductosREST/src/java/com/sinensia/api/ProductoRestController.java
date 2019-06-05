package com.sinensia.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet ( asyncSupported = true, urlPatterns = "/api/productos")
public class ProductoRestController extends HttpServlet {
    
    private ServicioProductoSingleton servProd;
    
    @Override
    public void init(){
        servProd = ServicioProductoSingleton.getInstancia();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException 
    {
        //Instanciamos un printWriter a partir de la respuesta, para luego guardar lo que vamos devolver al cliente
        /* Clase que nos permite escribir con formato texto tanto en la salida estándar, como en ficheros, en cadenas, o en streams.*/
        PrintWriter escritorRespuesta = response.getWriter();
        //vamos a devolver un JSON
        response.setContentType("application/json;charset=UTF-8");      
        
        //Despues de añadir el jar de gson, importamos e instanciamos
        Gson gson = new Gson();
        
        ArrayList<Producto> listaProductos = servProd.obtenerTodos();
       /*
        //Hacemos la modificación usando nuestra clase Singleton
        JsonArray arrayProd = new JsonArray();
        
        for (Producto prod : listaProductos) {
            String strProd = "{ nombre : \"" + prod.getNombre() + "\" , \\n";
            strProd += "\"precio\" : \"" + prod.getPrecio() + "\" \\n }";
            arrayProd.add(strProd);
        }
        
        //Concertimos el objeto producto a JSON*/
        String jsonRespuesta = gson.toJson(listaProductos);
        //Devolvemos al cliente el JSON de lo que hemos hecho
        escritorRespuesta.println(jsonRespuesta);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException 
    {
        //Instanciamos un printWriter a partir de la respuesta, para luego guradar
        //lo que vamos devolver al cliente
        /* Clase que nos permite escribir con formato texto tanto en la salida estándar, como en ficheros, en cadenas, o en streams.*/
        PrintWriter escritorRespuesta = response.getWriter();
        //vamos a devolver un JSON
        response.setContentType("application/json;charset=UTF-8");
        
        //La propia petición crea un buffer al llamar al método getReader
        BufferedReader bufRead = request.getReader();
        //Creamos un objeto StringBuilder para guardar el texto
        /* La clase StringBuilder es similar a la clase String en el sentido de que sirve para almacenar cadenas de caracteres. 
        No obstante, presenta algunas diferencias relevantes. Señalaremos como características de StringBuilder a tener en cuenta:
        - Su tamaño y contenido pueden modificarse. Los objetos de éste tipo son mutables. Esto es una diferencia con los String.
        - Debe crearse con alguno de sus costructores asociados. No se permite instanciar directamente a una cadena como los String.
        - Un StringBuilder está indexado. Cada uno de sus caracteres tiene un índice: 0 para el primero,1 para el segundo, etc.
        - Los métodos de StringBuilder no están sincronizados. Esto implica que es más eficiente que StringBuffer siempre que no 
         se requiera trabajar con múltiples hilos (threads), que es lo más habitual. */
        StringBuilder textoJson = new StringBuilder();
        //Con un bucle for vamos leyento el buffer hasta que devuelva null (fin del buffer)
        for (String lineaJSON = bufRead.readLine();
                lineaJSON != null; 
                lineaJSON =bufRead.readLine()){
            textoJson.append(lineaJSON);
        }
        bufRead.close();
        
        System.out.println(">>> " + textoJson.toString().toUpperCase());
        //Despues de añadir el jar de gson, importamos e instanciamos
        Gson gson = new Gson();
        //el metodo fromJson recibe un string con el Json y como segundo parámetro 
        //el tipo de objeto en el que lo queremos convertir. Es un método genérico
        Producto producto = gson.fromJson(textoJson.toString(), Producto.class);
        
        System.out.println(">>>> " + producto.getNombre());
        //hacemos modificaciones en el producto
        /*producto.setNombre(producto.getNombre().toUpperCase());
        producto.setPrecio("5000 bolívares");*/
        //Hacemos la modificación usando nuestra clase Singleton
        //ServicioProductoSingleton sps = ServicioProductoSingleton.getInstancia();
        
        servProd.modificar(producto);
        
        //Concertimos el objeto producto a JSON
        String jsonRespuesta = gson.toJson(producto);
        //Devolvemos al cliente el JSON de lo que hemos hecho
        escritorRespuesta.println(jsonRespuesta);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        //Instanciamos un printWriter a partir de la respuesta, para luego guardar
        //lo que vamos devolver al cliente
        /* Clase que nos permite escribir con formato texto tanto en la salida estándar, como en ficheros, en cadenas, o en streams.*/
        PrintWriter escritorRespuesta = response.getWriter();
        //vamos a devolver un JSON
        response.setContentType("application/json;charset=UTF-8");
        
        //La propia petición crea un buffer al llamar al método getReader
        BufferedReader bufRead = request.getReader();
        //Creamos un objeto StringBuilder para guardar el texto
        /* La clase StringBuilder es similar a la clase String en el sentido de que sirve para almacenar cadenas de caracteres. 
        No obstante, presenta algunas diferencias relevantes. Señalaremos como características de StringBuilder a tener en cuenta:
        - Su tamaño y contenido pueden modificarse. Los objetos de éste tipo son mutables. Esto es una diferencia con los String.
        - Debe crearse con alguno de sus costructores asociados. No se permite instanciar directamente a una cadena como los String.
        - Un StringBuilder está indexado. Cada uno de sus caracteres tiene un índice: 0 para el primero,1 para el segundo, etc.
        - Los métodos de StringBuilder no están sincronizados. Esto implica que es más eficiente que StringBuffer siempre que no 
         se requiera trabajar con múltiples hilos (threads), que es lo más habitual. */
        StringBuilder textoJson = new StringBuilder();
        //Con un bucle for vamos leyento el buffer hasta que devuelva null (fin del buffer)
        for (String lineaJSON = bufRead.readLine();
                lineaJSON != null; 
                lineaJSON =bufRead.readLine()){
            textoJson.append(lineaJSON);
        }
        bufRead.close();
        
        System.out.println(">>> " + textoJson.toString());
        //Despues de añadir el jar de gson, importamos e instanciamos
        Gson gson = new Gson();
        //el metodo fromJson recibe un string con el Json y como segundo parámetro 
        //el tipo de objeto en el que lo queremos convertir. Es un método genérico
        Producto producto = gson.fromJson(textoJson.toString(), Producto.class);
        ArrayList<Producto> listaProducto = servProd.obtenerTodos();
        
        listaProducto.add(producto);
            
        System.out.println(">>>> " + producto.getNombre());
        //hacemos modificaciones en el producto
        /*producto.setNombre(producto.getNombre().toUpperCase());
        producto.setPrecio("5000 bolívares");*/
        //Hacemos la modificación usando nuestra clase Singleton
        //ServicioProductoSingleton sps = ServicioProductoSingleton.getInstancia();
        
        
        //Convertimos el objeto producto a JSON
        String jsonRespuesta = gson.toJson(listaProducto);
        
        //Devolvemos al cliente el JSON de lo que hemos hecho
        escritorRespuesta.println(jsonRespuesta);
    }
}
