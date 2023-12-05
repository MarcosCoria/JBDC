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
public class Fabricante {
    private int codigo;
    private String nombre;

    public Fabricante() {
    }

    public Fabricante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Fabricante{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
}