/* Ejercicio_01: 
LOS OBJETOS SON LAS TABLAS
LOS ATRIBUTOS COINCIDEN CON LAS COLUMNAS DE LAS TABLAS

PAQUETE ENTIDADES:
Dentro de este paquete se deben crear todas las clases necesarias que vamos a usar de la 
base de datos. Crear dentro de este paquete las clases: 

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
nada más, no como objeto */
package Entidad;

/* @author G96xyFast */
public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private int codigo_Fabricante;

    public Producto() {
    }

    public Producto(int codigo, String nombre, double precio, int codigo_Fabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.codigo_Fabricante = codigo_Fabricante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigoFabricante() {
        return codigo_Fabricante;
    }

    public void setCodigoFabricante(int codigo_Fabricante) {
        this.codigo_Fabricante = codigo_Fabricante;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", codigoFabricante=" + codigo_Fabricante + '}';
    }
    
}