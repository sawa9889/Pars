import Parse.Competence;
import Parse.Plan;
import Parse.Title;
import Src.Discipline;
import Src.EducationPlan;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Plan plan = new Plan();
        ArrayList<Discipline> processedDisciplineArray
                = plan.parse("MireaDocsPars/09.03.02_ИТСАиБ_ИКБСП_2017.plm.xml.xls");
        Competence competence = new Competence();
        ArrayList<Src.Competence> processedCompetenceArray =
                competence.parse("MireaDocsPars/09.03.02_ИТСАиБ_ИКБСП_2017.plm.xml.xls");
        Title title = new Title();
        EducationPlan processedEdPlan =
                title.parse("MireaDocsPars/09.03.02_ИТСАиБ_ИКБСП_2017.plm.xml.xls");

    }
}
