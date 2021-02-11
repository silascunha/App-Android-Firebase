package com.coldy.organizzeclone.util;

import android.util.Base64;

import java.nio.charset.StandardCharsets;


public class MyBase64 {

    public static String codificar(String str) {

        return Base64.encodeToString(str.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT)
                .replaceAll("(\\n|\\r)", "");
    }

    public static String decodificar(String str) {
        return new String(Base64.decode(str, Base64.DEFAULT), StandardCharsets.UTF_8);
    }

}
