package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Common {
    public static String LerParametro(String parametro) throws IOException {
        //String parametro="project_id";

        String valor="";
        BufferedReader br = new BufferedReader(new
                FileReader("parametros.tmp"));
        String linha;
        while ((linha = br.readLine()) != null) {
            if (linha.contains(parametro)){
                valor= linha.split("=")[1];
            }
        }
        br.close();
        return valor;
    }
}
