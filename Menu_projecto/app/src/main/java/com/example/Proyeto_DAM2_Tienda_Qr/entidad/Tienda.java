package com.example.Proyeto_DAM2_Tienda_Qr.entidad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

public class Tienda {

    private String Idproducto;
    private String nombre;
    private String precio;
    private String cantidad;
    private String fecha_fabricacion;
    private String fecha_caducidad;
    private String descripcion;
    private String fecha_inscripcion;

    private String urlImg;

    public Tienda(String nombre, String precio, String cantidad, String fecha_fabricacion, String fecha_caducidad, String descripcion, String fecha_inscripcion, Bitmap imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fecha_fabricacion = fecha_fabricacion;
        this.fecha_caducidad = fecha_caducidad;
        this.descripcion = descripcion;
        this.fecha_inscripcion = fecha_inscripcion;
        this.imagen = imagen;
    }


    public Tienda() {

    }

    public String getUrlImg() {
        return String.valueOf( urlImg );
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    private String dato;

    private Bitmap imagen;
    private String rutaImagen;

    public String getIdproducto() {
        return Idproducto;
    }

    public void setIdproducto(String idproducto) {
        Idproducto = idproducto;
    }

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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(String fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public String getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(String fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(String fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;

        try {
            byte[] byteCode = Base64.decode( dato, Base64.DEFAULT );

            int alto = 150;//alto en pixeles
            int ancho = 200;//ancho en pixeles

            Bitmap foto = BitmapFactory.decodeByteArray( byteCode, 0, byteCode.length );
            this.imagen = Bitmap.createScaledBitmap( foto, alto, ancho, true );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }


}
