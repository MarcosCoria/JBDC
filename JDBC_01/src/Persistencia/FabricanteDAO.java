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

import Entidad.Fabricante;
import java.util.ArrayList;
import java.util.Collection;

/* @author G96xyFast */
public class FabricanteDAO extends DAO {
    // Guarda Producto en la Base de Datos.
    public void guardarFabricante(Fabricante fab) throws Exception {
        try {
            if (fab == null) { // Si no hay producto a insertar
                throw new Exception("Debe indicar el fabricante");
            }
            //Insertar valores de los atributos mediante sql
            // IMPORTANTE: PARA DATOS 'DOUBLE' NO SE EMPLEA '' PARA LA PETICIÓN SQL
            String sql = "INSERT INTO fabricante (codigo, nombre)" 
                    + "VALUES ( '" + fab.getCodigo() + " ' , ' " + fab.getNombre() + " ' );";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
    // Modifica Producto en la Base de Datos
    public void modificarFabricante(Fabricante fab) throws Exception {
        try { // Si no hay producto
            if (fab == null) {
                throw new Exception("Debe indicar el fabricante que desea modificar");
            }
            // Modificar SÓLO el código del fabricante
            String sql = "UPDATE fabricante SET "
                    + "codigo = '" + fab.getCodigo() + "' WHERE nombre = '" + fab.getNombre() + "'";
            insertarModificarEliminar(sql); // Método 'insertarModificarEliminar()' heredado del DAO
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarFabricante(String nombre) throws Exception {
        try { // Borrar el fabricante por el nombre ingresado
            String sql = "DELETE FROM fabricante WHERE nombre = ' " + nombre + " ' ";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM fabricante "
                    + " WHERE nombre = ' " + nombre + " ' ";
            consultarBase(sql);
            // Setea un objeto vacío con todos los atributos que encuentra en la tabla
            // Setea con los datos del 1-2(cantidad de atributos)
            Fabricante fab= null;
            while (resultado.next()) {
                fab = new Fabricante();
                fab.setCodigo(resultado.getInt(1));
                fab.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fab; // Devuelve el objeto nuevo de la consulta
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Fabricante buscarFabricantePorCodigo(Integer codigo) throws Exception {
        try {
            String sql = "SELECT * FROM fabricante "
                    + " WHERE codigo = ' "+codigo+" ' ";
            consultarBase(sql);
            // Setea un objeto vacío con todos los atributos que encuentra en la tabla
            // Setea con los datos del 1-2(cantidad de atributos)
            Fabricante fab = null;
            while (resultado.next()) {
                fab = new Fabricante();
                fab.setCodigo(resultado.getInt(1));
                fab.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fab; // Devuelve el objeto nuevo de la consulta
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Collection<Fabricante> listarFabricante() throws Exception {
        try {
            String sql = "SELECT codigo, nombre FROM fabricante ";
            consultarBase(sql);
            // Setea un objeto vacío con todos los atributos que encuentra en la tabla
            // Setea con los datos del 1-2(cantidad de atributos)
            // SUMA A LA COLECCIÓN EL OBJETO CREADO
            Fabricante fab;
            Collection<Fabricante> lista = new ArrayList();
            while (resultado.next()) {
                fab = new Fabricante();
                fab.setCodigo(resultado.getInt(1));
                fab.setNombre(resultado.getString(2));
                lista.add(fab);
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