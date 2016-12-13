package misobjetos;

/**
 * Created by mundo on 23/10/2016.
 */

public class Producto {
    private int id_producto;
    private String descripcion;
    private int precio_unitario;
    private int cantidad;

    public Producto(int id_producto, String descripcion, int precio_unitario, int cantidad) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
        this.cantidad = cantidad;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
