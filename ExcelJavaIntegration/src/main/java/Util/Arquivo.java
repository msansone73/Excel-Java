package Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arquivo {

    private Object obj;
    private List lista;
    private String path;
    public Arquivo(List Lista) throws Exception {

        if (Lista.size()<1)
        {
            throw new Exception("No information found.");
        }
        lista=Lista;
        this.obj=Lista.get(0);

    }
    public void GerarArquivo(String Path) throws IllegalAccessException, IOException {
        this.path=Path;
        FileWriter arquivo = new FileWriter(new File(Path));
        this.GerarCabecalho(arquivo);
        this.GerarConteudo(arquivo);
        arquivo.close();
    }
    private void GerarCabecalho(FileWriter arquivo) throws IOException {
        Class c1 = obj.getClass();
        String saida = "";

        Map fieldMap = new HashMap();
        Field[] valueObjFields = c1.getDeclaredFields();

        for (int i = 0; i < valueObjFields.length; i++)
        {
            String fieldName = valueObjFields[i].getName();
            saida+=valueObjFields[i].getName();
            if ((i+1)<valueObjFields.length) {
                saida+=";";
            }else {
                saida+="\n";
            }
        }
        arquivo.write(saida);
    }
    private void GerarConteudo(FileWriter arquivo) throws IllegalAccessException, IOException {

        for (int i=0; i<this.lista.size();i++){
            this.obj= this.lista.get(i);
            this.GerarLinha(arquivo);
        }
    }

    private void GerarLinha(FileWriter arquivo) throws IllegalAccessException, IOException {
        Class c1 = obj.getClass();
        String saida="";

        Map fieldMap = new HashMap();
        Field[] valueObjFields = c1.getDeclaredFields();

        for (int i = 0; i < valueObjFields.length; i++)
        {

            String fieldName = valueObjFields[i].getName();
            valueObjFields[i].setAccessible(true);
            Object newObj = valueObjFields[i].get(this.obj);

            String campo=newObj==null?" ":newObj.toString().replace("\n","");
            campo=campo.replace("\n", "").replace("\r", "");;
            //System.out.print( campo);
            saida+=campo;
            fieldMap.put(fieldName, newObj);

            if ((i+1)<valueObjFields.length) {
                saida+=";";
            }else {
                saida+="\n";
            }
        }
        arquivo.write(saida);
    }
}
