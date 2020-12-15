package com.example.Proyeto_DAM2_Tienda_Qr.entidad;

public class Usuario {
    public static String nombre;
    public static String apellidos;
    public static String email;
    public static String password;
    public static String fecha_nasc;
    public static String calle;
    public static String piso;
    public static String puerta;
    public static String ciudad;
    public static String telefono;
    public static String provincia;
    public static String imgPerfil;

    // se me habia olvidado
    public static String codigoPostal;
    public static String telefono_fijo;

    public static String nombreProprietarioTarjeta;
    public static String numeroTarjeta;
    public static String fechaCaducidadTarjeta;

    // construtor


    public Usuario() {
    }


    public static String getNombreProprietarioTarjeta() {
        return nombreProprietarioTarjeta;
    }

    public void setNombreProprietarioTarjeta(String nombreProprietarioTarjeta) {
        Usuario.nombreProprietarioTarjeta = nombreProprietarioTarjeta;
    }

    public static String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        Usuario.numeroTarjeta = numeroTarjeta;
    }

    public static String getFechaCaducidadTarjeta() {
        return fechaCaducidadTarjeta;
    }

    public void setFechaCaducidadTarjeta(String fechaCaducidadTarjeta) {
        Usuario.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
    }

    public static String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        Usuario.codigoPostal = codigoPostal;
    }

    public static String getTelefono_fijo() {
        return telefono_fijo;
    }

    public void setTelefono_fijo(String telefono_fijo) {
        Usuario.telefono_fijo = telefono_fijo;
    }

    public static String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getFecha_nasc() {
        return fecha_nasc;
    }

    public void setFecha_nasc(String fecha_nasc) {
        this.fecha_nasc = fecha_nasc;
    }

    public static String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public static String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public static String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public static String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public static String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public static String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public static String getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }
}
