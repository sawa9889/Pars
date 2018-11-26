import DAO.DataSourceSample;
import Parse.Competence;
import Parse.Plan;
import Parse.Title;
import Src.Discipline;
import Src.EducationPlan;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataSourceSample mireaDB = new DataSourceSample();
        try {
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
            mireaDB.createDBConnect();
            //Добавление нового учебного плана
            mireaDB.addNewPlan(processedEdPlan, processedDisciplineArray, processedCompetenceArray);
            //Удаление учебного плана с введенным названием
//            mireaDB.deleteEdplan(processedEdPlan.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mireaDB.closeDBConnection();
        }
    }

}
