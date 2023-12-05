/* Ejercicio_01: 
CONEXIÓN:
Window -> Services -> MySQL Server at localhost...  -> 'tienda.sql'

Para este ejercicio vamos a usar el script de la base de datos llamada “tienda.sql” que lo 
trabajamos en la guía de MySql.
PAQUETES DEL PROYECTO JAVA:  
Crear un nuevo proyecto en Netbeans del tipo “Java Application” con el nombre Tienda y 
agregar dentro 3 paquetes, a uno se lo llamará entidades, al otro se le llamará servicios y al 
otro persistencia.
Para crear los paquetes de esta manera, se deben crear desde el paquete principal, sería nos 
paramos en el paquete tienda -> Source Packages -> Click derecho -> New Java Package y creamos los paquetes. 
También es importante agregar en “Libraries” la librería “MySQL JDBC Driver” para permitir 
conectar la aplicación de Java con la base de datos MySQL. Esto se explica en el Instructivo 
que ya leíste al final de esta guía.
PAQUETE PERSISTENCIA:
En este paquete estará la clase DAO encarga de conectarse con la base de datos y de 
comunicarse con la base de datos para obtener sus datos. Además, estará las clases de 
EntidadDaoExt para cada entidad / tabla de nuestro proyecto.
Es importante tener la conexión creada a la base de datos, como lo explica el Instructivo en la 
pestaña de Services en Netbeans.
PAQUETE ENTIDADES:
Dentro de este paquete se deben crear todas las clases necesarias que vamos a usar de la 
base de datos. Por ejemplo, una de las clases a crear dentro de este paquete es la clase 

“Producto.java” con los siguientes atributos:
• private int codigo;
• private String nombre;
• private double precio;
• private int codigoFabricante;

"Fabricante.java" con los siguientes atributos:
• private int codigo;
• private String nombre;

Agregar a cada clase el/los constructores necesarios y los métodos públicos getters y setters 
para poder acceder a los atributos privados de la clase. La llave foránea se pondrá como dato 
nada más, no como objeto.
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
h) Editar un producto con datos a elección. */
package jdbc_01;

import Entidad.Fabricante;
import Entidad.Producto;
import Servicios.FabricanteServicio;
import Servicios.ProductoServicio;
import java.util.Scanner;

/* @author G96xyFast */
public class JDBC_01 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        boolean var = false;
        while (var == false) {
            System.out.println("SELECCIONE TABLA:\n1-Tabla Producto\n2-Tabla Fabricante\n3-Salir");
            int opt = leer.nextInt();
            switch (opt) {
                case 1:
                    MenuProducto();
                    break;
                case 2:
                    MenuFabricante();
                    break;
                case 3:
                    System.out.println("Finalizando. Gracias por utilizar el programa");
                    var = true;
                    break;
                default:
                    System.out.println("Error.");
            }
        }
    }

    public static void MenuProducto() {
        Scanner leer = new Scanner(System.in);
        boolean var = false;
        while (var == false) {
            System.out.println("MENÚ:\n1-Crear Producto\n2-Eliminar Producto\n3-Buscar Producto\n4-Salir");
            int opt = leer.nextInt();
            switch (opt) {
                case 1:
                    CrearProducto();
                    break;
                case 2:
                    EliminarProducto();
                    break;
                case 3:
                    BuscarProductoPorCodigo();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal");
                    var = true;
                    break;
                default:
                    System.out.println("Error ");
            }
        }
    }

    public static void CrearProducto() {
        Scanner leer = new Scanner(System.in);
        ProductoServicio sp = new ProductoServicio();
        // CREAMOS NUEVO PRODUCTO A INSERTAR EN LA TABLA
        try {
            System.out.println("Tabla Original: ");
            sp.imprimirProductos();
            //Creamos Producto 1
            System.out.println("Datos PRODUCTO 1:\nIngrese CÓDIGO:");
            int cod1 = leer.nextInt();
            System.out.println("Ingrese NOMBRE: ");
            String nombre1= leer.next();
            System.out.println("Ingrese PRECIO: ");
            double precio1= leer.nextDouble();
            System.out.println("Ingrese CÓDIGO DEL FABRICANTE: ");
            int codFab1= leer.nextInt();
            sp.crearProducto(cod1, nombre1, precio1, codFab1);
            //Creamos Producto 2
            System.out.println("Datos PRODUCTO 2:\nIngrese CÓDIGO:");
            int cod2 = leer.nextInt();
            System.out.println("Ingrese NOMBRE: ");
            String nombre2= leer.next();
            System.out.println("Ingrese PRECIO: ");
            double precio2= leer.nextDouble();
            System.out.println("Ingrese CÓDIGO DEL FABRICANTE: ");
            int codFab2= leer.nextInt();
            sp.crearProducto(cod2, nombre2, precio2, codFab2);
            sp.imprimirProductos();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }
    
    public static void EliminarProducto(){
        Scanner leer = new Scanner(System.in);
        ProductoServicio sp = new ProductoServicio();
        try {
            System.out.println("Tabla Original: ");
            sp.imprimirProductos();
            //Eliminamos un producto
            System.out.println("Ingrese NOMBRE a eliminar Objeto Producto 1:");
            sp.eliminarProducto(leer.next());
            sp.imprimirProductos();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No eliminamos datos de la tabla original.\nError del sistema por \n" + e.getMessage());
        }
    }
    
    public static void BuscarProductoPorCodigo(){
        Scanner leer = new Scanner(System.in);
        ProductoServicio sp = new ProductoServicio();
        try {
            System.out.println("Tabla Original: ");
            sp.imprimirProductos();
            System.out.println("Ingrese NÚMERO de código a buscar en la Tabla Producto:");
            //Buscamos el fabricante por codigo
            Producto p= sp.buscarProductoPorCodigo(leer.nextInt());
            System.out.println("Objeto: " + p);
            sp.imprimirProductos();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }

    public static void MenuFabricante() {
        Scanner leer = new Scanner(System.in);
        boolean var = false;
        while (var == false) {
            System.out.println("MENÚ:\n1-Crear fabricante\n2-Eliminar Fabricante\n3-Buscar Fabricante\n4-Salir");
            int opt = leer.nextInt();
            switch (opt) {
                case 1:
                    CrearFabricante();
                    break;
                case 2:
                    EliminarFabricante();
                    break;
                case 3:
                    BuscarFabricantePorCodigo();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal");
                    var = true;
                    break;
                default:
                    System.out.println("Error ");
            }
        }
    }

    public static void CrearFabricante() {
        Scanner leer = new Scanner(System.in);
        FabricanteServicio sf = new FabricanteServicio();
        // CREAMOS NUEVO FABRICANTE A INSERTAR EN LA TABLA
        try {
            System.out.println("Tabla Original: ");
            sf.imprimirFabricantes();
            //Creamos fabricante
            System.out.println("Ingrese NÚMERO Objeto Fabricante 1:");
            int num1 = leer.nextInt();
            System.out.println("Ingrese NOMBRE Objeto Fabricante 1:");
            String nombre1 = leer.next();
            sf.crearFabricante(num1, nombre1);
            System.out.println("Ingrese NÚMERO Objeto Fabricante 2:");
            int num2 = leer.nextInt();
            System.out.println("Ingrese NOMBRE Objeto Fabricante 2:");
            String nombre2 = leer.next();
            sf.crearFabricante(num2, nombre2);
            sf.imprimirFabricantes();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }

    public static void EliminarFabricante() {
        Scanner leer = new Scanner(System.in);
        FabricanteServicio sf = new FabricanteServicio();
        try {
            System.out.println("Tabla Original: ");
            sf.imprimirFabricantes();
            //Eliminamos un fabricante
            System.out.println("Ingrese NOMBRE a elminar Objeto Fabricante 1:");
            sf.eliminarFabricante(leer.next());
            sf.imprimirFabricantes();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No eliminamos datos de la tabla original.\nError del sistema por \n" + e.getMessage());
        }
    }

    public static void BuscarFabricantePorCodigo() {
        Scanner leer = new Scanner(System.in);
        FabricanteServicio sf = new FabricanteServicio();
        try {
            System.out.println("Tabla Original: ");
            sf.imprimirFabricantes();
            System.out.println("Ingrese NÚMERO de código a buscar en la Tabla Fabricante:");
            //Buscamos el fabricante por codigo
            Fabricante fab = sf.buscarFabricantePorCodigo(leer.nextInt());
            System.out.println("Objeto: " + fab);
            sf.imprimirFabricantes();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }
}
