/* Ejercicio_01: 
CONEXIÓN:
Window -> Services -> MySQL Server at localhost...  -> 'tienda.sql'

PAQUETE PERSISTENCIA:
En este paquete estará la clase DAO encarga de conectarse con la base de datos y de 
comunicarse con la base de datos para obtener sus datos. 

EXTIENDE O HERDEA DEL 'DAO.Java'
Además, estará las clases de EntidadDaoExt para cada entidad / tabla de nuestro proyecto.
Es importante tener la conexión creada a la base de datos, como lo explica el Instructivo en la 
pestaña de Services en Netbeans */
package Persistencia;

import Entidad.Producto;
import java.util.ArrayList;
import java.util.Collection;

/* @author G96xyFast */
public class ProductoDAO extends DAO {
    // Guarda Producto en la Base de Datos.
    public void guardarProducto(Producto p) throws Exception {
        try {
            if (p == null) {  // Si no hay producto a insertar
                throw new Exception("Debe indicar el producto");
            }
            //Insertar valores de los atributos mediante sql
            String sql = "INSERT INTO Producto (codigo, nombre, precio, codigo_Fabricante)"
                    + "  VALUES(' " + p.getCodigo() + "  ' , ' " + p.getNombre() + " ',  " + p.getPrecio() + "  , ' " + p.getCodigoFabricante() + " ');  ";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
    // Modifica Producto en la Base de Datos
    public void modificarProducto(Producto p) throws Exception {
        try { // Si no hay producto
            if (p == null) {
                throw new Exception("Debe indicar el producto que desea modificar");
            }
            String sql = "UPDATE Producto SET " // Setear código dónde el nombre sea el mismo que del objeto ingresado
                    + "codigo = '" + p.getCodigo() + "' WHERE nombre = '" + p.getNombre() + "'";
            // Método 'insertarModificarEliminar()' heredado del DAO
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
    
    public void eliminarProducto(String nombre) throws Exception {
        try { // Borrar el producto por el nombre ingresado
            String sql = "DELETE FROM Producto WHERE nombre = ' " + nombre + " ' ";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
    
    public Producto buscarProductoPorCodigo(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM producto "
                    + " WHERE codigo = '" + codigo + "'";
            consultarBase(sql);
            // Setea un objeto vacío con todos los atributos que encuentra en la tabla
            // Setea con los datos del 1-4(cantidad de atributos)
            Producto p = null;
            while (resultado.next()) {
                p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodigoFabricante(resultado.getInt(4));
            }
            desconectarBase();
            return p; // Devuelve el objeto nuevo de la consulta
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Producto buscarProductoPorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM producto "
                    + " WHERE nombre = '" + nombre + "'";
            consultarBase(sql);
            // Setea un objeto vacío con todos los atributos que encuentra en la tabla
            // Setea con los datos del 1-4(cantidad de atributos)
            Producto p = null;
            while (resultado.next()) {
                p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodigoFabricante(resultado.getInt(4));
            }
            desconectarBase();
            return p; // Devuelve el objeto nuevo de la consulta
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Collection<Producto> listarProductos() throws Exception {
        try {
            String sql = "SELECT codigo, nombre, precio, codigo_fabricante FROM Producto ";
            consultarBase(sql);
            // Setea un objeto vacío con todos los atributos que encuentra en la tabla
            // Setea con los datos del 1-4(cantidad de atributos)
            // SUMA A LA COLECCIÓN EL OBJETO CREADO
            Producto p;
            Collection<Producto> lista = new ArrayList();
            while (resultado.next()) {
                p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodigoFabricante(resultado.getInt(4));
                lista.add(p);
            }
            desconectarBase();
            return lista; // DEVUELVE LA COLLECIÓN CREADA
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }
}
