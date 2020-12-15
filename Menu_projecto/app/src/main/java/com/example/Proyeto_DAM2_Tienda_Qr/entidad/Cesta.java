package com.example.Proyeto_DAM2_Tienda_Qr.entidad;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Cesta {
    private String nombre;
    private String precio;
    private String cantidad_User;
    private String descripcion;
    private String subTotal;
    private Bitmap imagen;

    // construtor

    // forzar el contrutor
    public Cesta(String nombre, String precio, String cantidad_User, String descripcion, String subTotal, Bitmap imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad_User = cantidad_User;
        this.descripcion = descripcion;
        this.subTotal = subTotal;
        this.imagen = imagen;
    }

    // contrutor 2


    public Cesta(String nombre, String precio, String descripcion, Bitmap imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad_User = cantidad_User;
        this.descripcion = descripcion;
        this.subTotal = subTotal;
        this.imagen = imagen;
    }


    public void calculosVaDatos(String cantidad, String precioP, String Subtotal) {
        cantidad_User = cantidad;
        precio = precioP;
        subTotal = Subtotal;
    }


    // forzar el contrutor

    public Cesta() {
    }

    // gettes y setter


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad_User() {
        return cantidad_User;
    }

    public void setCantidad_User(String cantidad_User) {
        this.cantidad_User = cantidad_User;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public void anadirProLista() {
    }

}
