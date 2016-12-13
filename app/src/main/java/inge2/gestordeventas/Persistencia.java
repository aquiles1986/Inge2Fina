package inge2.gestordeventas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import misobjetos.*;


/**
 * Created by mundo on 21/10/2016.
 */

public class Persistencia extends SQLiteOpenHelper {

    public Persistencia(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Cliente(cedula integer primary key, nombre text, apellido text, direccion text, telefono text)");

        db.execSQL("create table Producto(id_producto integer primary key, descripcion text, precio_unitario integer, cantidad integer)");

        db.execSQL("create table Pedido(id_pedido integer primary key, cedula_cliente integer, monto_total integer, " +
                "foreign key(cedula_cliente) references Cliente(cedula))");

        db.execSQL("create table DetallePedido(id_pedido integer, id_producto integer, cantidad integer, " +
                "primary key(id_pedido, id_producto), foreign key(id_pedido) references Pedido(id_pedido)" +
                "foreign key(id_producto) references Producto(id_producto))");

        cargar_de_clientes(db);
        cargar_de_productos(db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Cliente");
        db.execSQL("create table Cliente(cedula integer primary key, nombre text, apellido text, direccion text, telefono text)");

        db.execSQL("drop table if exists Producto");
        db.execSQL("create table Producto(id_producto integer primary key, descripcion text, precio_unitario integer, cantidad integer)");

    }

    private void cargar_de_productos(SQLiteDatabase db) {
        List<Producto> productos = new ArrayList<Producto>();
        productos.add(new Producto(0,"Seleccione un producto",0,0));
        productos.add(new Producto(1, "Choclo Norte", 2500, 5));
        productos.add(new Producto(2,"Pulp 2L",10000,10));        productos.add(new Producto(3, "Leche Trebol 1L", 4000, 7));


        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            for(int i = 0; i < productos.size(); i++){
                values.put("id_producto",productos.get(i).getId_producto());
                values.put("descripcion", productos.get(i).getDescripcion());
                values.put("precio_unitario", productos.get(i).getPrecio_unitario());
                values.put("cantidad", productos.get(i).getCantidad());
                db.insert("Producto",null,values);
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    private void cargar_de_clientes(SQLiteDatabase db) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(0,"Seleccione un Cliente","","",""));
        clientes.add(new Cliente(1234567,"Roberto","Perez","Avda Eusebio Ayala 1230","0981234567"));
        clientes.add(new Cliente(7654321,"Mario","Gonzalez","Avda Felix Bogado 1222 ","222000"));
        clientes.add(new Cliente(338755,"Juana","Lopez","Herrera casi Chile 2000","0991903421"));

        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            for(int i = 0; i < clientes.size(); i++){
                values.put("cedula", clientes.get(i).getCedula());
                values.put("nombre", clientes.get(i).getNombre());
                values.put("apellido", clientes.get(i).getApellido());
                values.put("direccion", clientes.get(i).getDireccion());
                values.put("telefono", clientes.get(i).getTelefono());
                db.insert("Cliente",null,values);
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

}
