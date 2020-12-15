package com.example.Proyeto_DAM2_Tienda_Qr.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ValidarInpustEntrada {
    public static byte[] encriptyPass(String pass) {

        byte[] hash1 = null;
        try {
            byte[] data = pass.getBytes();
            MessageDigest md = MessageDigest.getInstance( "SHA-256" );
            hash1 = md.digest( data );
        } catch (Exception ignored) {

        }
        return hash1;
    }

    public static String ByteToHex(byte[] data) {
        BigInteger bin = new BigInteger( 1, data );
        return String.format( "%0" + (data.length << 1) + "X", bin );
    }


    public static boolean esFechaValida(String fecha) {
        boolean esFecha = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/yy", Locale.getDefault() ); // "yyyy-MM-dd"
        Date date = new Date();
        String fechaW = dateFormat.format( date );

        System.out.println( "Fecha actual Sistema:" + fechaW );
        System.out.println( "Fecha inpu user: " + fecha );

        String[] valor = fechaW.split( "/" );
        String mes = valor[0];
        String anio = valor[1];
        int anioActual = Integer.parseInt( anio );
        int mesActual = Integer.parseInt( mes );

        // fecha input
        //String anioFechaInput = fecha;
        String[] valorInpD = fecha.split( "/" );
        String mesInp = valorInpD[0];
        String anioInp = valorInpD[1];

        int anioActualInp = Integer.parseInt( anioInp );
        int mesActualInp = Integer.parseInt( mesInp );

        int sum = mesActual + mesActualInp;
        System.out.println( "Suma mes: " + sum );
        System.err.println( "mes inpUse: " + mesActualInp );
        System.err.println( "mes Sis: " + mesActual );

        if (anioActualInp > anioActual) {
            esFecha = true;
        }

        if ((anioActualInp == anioActual) && (mesActualInp > mesActual)) {

            esFecha = true;
        }
        return esFecha;

    }


    // alogatitimo de fecha anño bisiesto

    public boolean isLeap(int year) { // return el anio
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }



}
