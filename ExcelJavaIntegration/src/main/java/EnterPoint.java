import TestRail.APIClient;
import TestRail.APIException;
import TestRail.Model.Milestone;
import TestRail.Model.Project;
import Util.Arquivo;
import Util.Common;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;


import javax.rmi.CORBA.Util;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static Util.Common.LerParametro;

class Enterpoint {

    public static void main(String args[]) throws Exception {

        String information= LerParametro("information");
        String Resultado="";

        try {
            List lista = getInformation(information);
            Arquivo arq = new Arquivo(lista);
            arq.GerarArquivo(LerParametro("path_files") + information + ".txt");
        }
        catch (Exception e){
            Resultado=e.getMessage();
        }
        finally {
            String path_saida=LerParametro("path_files") +"saida.txt";
            if (Resultado==""){
                File f = new File(path_saida);
                if (f.exists()){
                    f.delete();
                }
            }else {
                FileWriter arquivo = new FileWriter(new File(path_saida));
                arquivo.write(Resultado);
                arquivo.close();
            }

        }



    }

    public static void GerarArquivoMilestones(final int project_id) throws IOException, APIException {

        APIClient client = new APIClient("https://celfocus.testrail.net/");
        client.setUser(LerParametro("testrail_user"));
        client.setPassword(LerParametro("testrail_password"));

        JSONArray jsonArray = (JSONArray) client.sendGet("get_milestones/"+ project_id);
        Gson g = new Gson();

        System.out.println(jsonArray.toJSONString());

        Type listType = new TypeToken<ArrayList<Milestone>>(){}.getType();
        List<Milestone> milestones = new Gson().fromJson(jsonArray.toJSONString(), listType);

        System.out.println(milestones.size());

        PrintWriter writer = new PrintWriter("D:\\NB24146\\Documents\\Projects\\Excel-Java\\the-file-name.txt", "UTF-8");

        for (Milestone m : milestones){

            writer.println(m.getId()+";"+m.getParent_id()+";"+m.getName()+";"+m.getDescription());
        }
        writer.close();


    }

    private static List getInformation(String information) throws IOException, APIException {

        APIClient client = new APIClient("https://celfocus.testrail.net/");
        client.setUser(LerParametro("testrail_user"));
        client.setPassword(LerParametro("testrail_password"));

        JSONArray jsonArray=null;
        Gson g = new Gson();


        if (information.equals("projects")){
            jsonArray = (JSONArray) client.sendGet("get_projects/");
            Type listType = new TypeToken<ArrayList<Project>>(){}.getType();
            return  new Gson().fromJson(jsonArray.toJSONString(), listType);

        }
        if (information.equals("get_milestones")){
            jsonArray = (JSONArray) client.sendGet("get_milestones/" + LerParametro("project_id"));
            Type listType = new TypeToken<ArrayList<Milestone>>(){}.getType();
            return   new Gson().fromJson(jsonArray.toJSONString(), listType);

        }
        return null;

    }

    public static List<Milestone> getMilestones(final int project_id) throws IOException, APIException {
        APIClient client = new APIClient("https://celfocus.testrail.net/");
        client.setUser(LerParametro("testrail_user"));
        client.setPassword(LerParametro("testrail_password"));

        JSONArray jsonArray = (JSONArray) client.sendGet("get_milestones/"+ project_id);
        Gson g = new Gson();

        //System.out.println(jsonArray.toJSONString());

        Type listType = new TypeToken<ArrayList<Milestone>>(){}.getType();
        List<Milestone> milestones = new Gson().fromJson(jsonArray.toJSONString(), listType);

        return milestones;
    }

    public static void printProperty(Object valueObj) throws IllegalAccessException {

        Class c1 = valueObj.getClass();
        System.out.println("Class name got is:: " + c1.getName());

        System.out.println("");
        Map fieldMap = new HashMap();
        Field[] valueObjFields = c1.getDeclaredFields();

        // compare values now
        for (int i = 0; i < valueObjFields.length; i++)
        {

            String fieldName = valueObjFields[i].getName();

            System.out.println("Getting Field Values for Field:: " + valueObjFields[i].getName());

        }



    }
}