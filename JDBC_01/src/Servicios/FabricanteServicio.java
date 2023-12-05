/* Ejercicio_01:
PAQUETE SERVICIOS:
En este paquete se almacenarán aquellas clases que llevarán adelante lógica del negocio. En 
general se crea un servicio para administrar cada una de las entidades y algunos servicios 
para manejar operaciones muy específicas como las estadísticas.
Realizar un menú en Java a través del cual se permita elegir qué consulta se desea realizar. 
Las consultas a realizar sobre la BD son las siguientes:
a) Lista el nombre de todos los productos que hay en la tabla producto. 
b) Lista los nombres y los precios de todos los productos de la tabla producto. 
c) Listar aquellos productos que su precio esté entre 120 y 202. 
d) Buscar y listar todos los Portátiles de la tabla producto. 
e) Listar el nombre y el precio del producto más barato. 
f) Ingresar un producto a la base de datos.
g) Ingresar un fabricante a la base de datos
h) Editar un producto con datos a elección */
package Servicios;

import Entidad.Fabricante;
import Persistencia.FabricanteDAO;
import java.util.Collection;

/* @author G96xyFast */
public class FabricanteServicio {
     private FabricanteDAO dao;
    // Asignarse a sí mismo un nuevo DAO
    public FabricanteServicio() {
        this.dao = new FabricanteDAO();
    }

    public void crearFabricante(Integer codigo, String nombre) throws Exception {
        try {
            //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el código");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            if (buscarFabricantePorCodigo(codigo) != null) { // verifica que no haya repetidos
                throw new Exception("Ya existe un fabricante con el código indicado " + codigo);
            }

            //Creamos el fabricante
            Fabricante fab = new Fabricante();
            fab.setCodigo(codigo);
            fab.setNombre(nombre);
            dao.guardarFabricante(fab);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarFabricante(Integer codigo, String nombre) throws Exception {

        try {
            //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el código");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            if (buscarFabricantePorCodigo(codigo) != null) { // verifica que no haya repetidos
                throw new Exception("Ya existe un fabricante con el código indicado " + codigo);
            }

            //Buscamos
            Fabricante fab = (Fabricante) buscarFabricantePorCodigo(codigo);

            //Validamos
            if (fab.getCodigo()!=(codigo)) {
                throw new Exception("El código actual no es el registrado en el sistema");
            }

            //Modificamos
            fab.setCodigo(codigo);

            dao.modificarFabricante(fab);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFabricante(String nombre) throws Exception {
        try {
            //Validamos 
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            dao.eliminarFabricante(nombre);
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePoNombre(String nombre) throws Exception {
        try {
            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            Fabricante fab = dao.buscarFabricantePorNombre(nombre);
            return fab;
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorCodigo(Integer codigo) throws Exception {

        try {

            //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el codigo");
            }

            Fabricante fab = dao.buscarFabricantePorCodigo(codigo);

            return fab;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Fabricante> listarFabricante() throws Exception {
        try {
            Collection<Fabricante> lista = dao.listarFabricante();
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirFabricantes() throws Exception {
        try {
            //Listamos los fabricantes
            Collection<Fabricante> lista = listarFabricante();
            //Imprimimos los fabricantes
            if (lista.isEmpty()) { // Si la lista está vacía: 
                throw new Exception("No existen fabricantes para imprimir");
            } else { // Listamos objetos con el ForEach
                for (Fabricante f : lista) {
                    System.out.println(f);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}