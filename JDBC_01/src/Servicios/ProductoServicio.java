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

import Entidad.Producto;
import Persistencia.ProductoDAO;
import java.util.Collection;

/* @author G96xyFast */
public class ProductoServicio {
    private ProductoDAO dao;
    // Asignarse a sí mismo un nuevo DAO
    public ProductoServicio() {
        this.dao = new ProductoDAO();
    }

    public void crearProducto(Integer codigo, String nombre, double precio, Integer codigo_Fabricante) throws Exception {
        try {
            //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el código");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            if (buscarProductoPorCodigo(codigo) != null) { // verifica que no haya repetidos
                throw new Exception("Ya existe un producto con el código indicado " + codigo);
            }

            //Creamos el producto
            Producto p = new Producto();
            p.setCodigo(codigo);
            p.setNombre(nombre);
            p.setPrecio(precio);
            p.setCodigoFabricante(codigo_Fabricante);
            dao.guardarProducto(p);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarProducto(Integer codigo, String nombre) throws Exception {

        try {
            //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el código");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            if (buscarProductoPorCodigo(codigo) != null) { // verifica que no haya repetidos
                throw new Exception("Ya existe un producto con el código indicado " + codigo);
            }

            //Buscamos
            Producto p = (Producto) buscarProductoPorCodigo(codigo);

            //Validamos
            if (p.getCodigo()!=(codigo)) {
                throw new Exception("El código actual no es el registrado en el sistema");
            }

            //Modificamos
            p.setCodigo(codigo);

            dao.modificarProducto(p);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarProducto(String nombre) throws Exception {
        try {
            //Validamos 
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            dao.eliminarProducto(nombre);
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorNombre(String nombre) throws Exception {
        try {
            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            Producto p = dao.buscarProductoPorNombre(nombre);
            return p;
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorCodigo(Integer codigo) throws Exception {
        try {
            //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el codigo");
            }
            Producto p = dao.buscarProductoPorCodigo(codigo);
            return p;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Producto> listarProductos() throws Exception {
        try {
            Collection<Producto> lista = dao.listarProductos();
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirProductos() throws Exception {
        try {
            //Listamos los fabricantes
            Collection<Producto> lista = listarProductos();
            //Imprimimos los fabricantes
            if (lista.isEmpty()) { // Si la lista está vacía: 
                throw new Exception("No existen productos para imprimir");
            } else { // Listamos objetos con el ForEach
                for (Producto p : lista) {
                    System.out.println(p);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}