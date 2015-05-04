package com.layarapp.androidapp.utils;

/**
 * Clase de Constantes
 */
public class Constants {

    public static final String TWITTER_KEY = "uuOG7MvmMf7K9h6xgBseGUa7Q";
    public static final String TWITTER_SECRET = "zRqOROEV4wJNnPRGQdbIjUoVIMRZhe2m81ek8EOwG7LFulHpEZ";

    public static String OAUTH_KEY = "LxRazopOjQPNXIck";
    public static String OAUTH_SECRET = "iUqFxtKDkhsSJAujTrzybGRQYpPdBZgN";

    public static final String URI_1 = "http://images.math.cnrs.fr/IMG/jpg/villa-de-leyva.jpg";
    public static final String URI_2 = "http://clande-products.s3.amazonaws.com/428533-006c000000rg6viiay1-thumb.jpg";
    public static final String URI_3 = "http://www.rutacol.com/wp-content/uploads/2010/12/VILLA-DE-LEYVA1.jpg";
    public static final String URI_4 = "http://www.villadeleyva.net.co/imag/villa-de-leyva.jpg";
    public static final String URI_5 = "http://www.villadeleyva.net/images/hoteles/viviana/viviana_8.jpg";
    public static final String URI_6 = "http://www.villadeleyva.net/images/fotos/aman.jpg";
    public static final String URI_7 = "http://www.villadeleyva.net/images/hoteles/02.jpg";
    public static final String URI_8 = "http://www.depaseo.com.co/wp-content/uploads/2013/07/Villa-de-Leyva-6.jpg";

    /**
     * Retorna una URL de acuerdo al numero random que ingresa por parámetro
     * @param index
     * @return
     */
    public static String getUrl(int index){

        String uri=null;

        switch (index){
            case 1:
                uri = URI_1;
                return uri;
            case 2:
                uri = URI_2;
                return uri;
            case 3:
                uri = URI_3;
                return uri;
            case 4:
                uri = URI_4;
                return uri;
            case 5:
                uri = URI_5;
                return uri;
            case 6:
                uri = URI_6;
                return uri;
            case 7:
                uri = URI_7;
                return uri;
            case 8:
                uri = URI_8;
                return uri;
        }

        return uri;
    }

}
