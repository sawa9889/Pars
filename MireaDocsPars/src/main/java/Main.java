import DAO.DataSourceSample;
import Src.Discipline;
import Src.EducationPlan;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        String docPath = "MireaDocsPars/09.03.02_ИТСАиБ_ИКБСП_2017.plm.xml.xls";
        Plan plan = new Plan();
        ArrayList<Discipline> processedDisciplineArray
                = plan.parse(docPath);
        Competence competence = new Competence();
        ArrayList<Src.Competence> processedCompetenceArray =
                competence.parse(docPath);
        Title title = new Title();
        EducationPlan processedEdPlan =
                title.parse(docPath);

        DataSourceSample mireaDB = new DataSourceSample();
        mireaDB.createDBConnect();
        mireaDB.addEducationPlan(processedEdPlan);
    }
}
