package TestRail.Request;

import java.util.List;

import static TestRail.Request.Requisition.enInformation.Projects;

public class Requisition {

    public enum enInformation {
        Projects,
        Project
    }

    public List Resquest( enInformation  information){
        switch (information){
            case Projects: return new Project().GetProjects();
        }
        return null;
    }
}
