import Parse.Competence;
import Parse.Plan;
import Src.Discipline;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Plan plan = new Plan();
        ArrayList<Discipline> processedDisciplineArray
                = plan.parse("MireaDocsPars/09.03.02_ИТСАиБ_ИКБСП_2017.plm.xml.xls");
        Competence competence = new Competence();
        ArrayList<Src.Competence> processedCompetenceArray =
                competence.parse("MireaDocsPars/09.03.02_ИТСАиБ_ИКБСП_2017.plm.xml.xls");
    }
}
