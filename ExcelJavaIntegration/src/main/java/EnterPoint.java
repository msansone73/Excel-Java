import TestRail.APIClient;
import TestRail.APIException;
import TestRail.Model.Milestone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class Enterpoint {

    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {

        try {
            Integer project_id= Integer.valueOf(LerParametro("project_id"));
            GerarArquivoMilestones(project_id);
        } catch (APIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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

    public static void GerarArquivoMilestones(int project_id) throws IOException, APIException {

        APIClient client = new APIClient("https://celfocus.testrail.net/");
        client.setUser("marcio.sansone@celfocus.com");
        client.setPassword("Senha&000");

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
}