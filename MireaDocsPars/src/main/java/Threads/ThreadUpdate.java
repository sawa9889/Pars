package Threads;

import DAO.DataSourceSample;
import Parse.Competence;
import Parse.Plan;
import Parse.Title;
import Src.Discipline;
import Src.EducationPlan;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class ThreadUpdate implements Runnable{
    String path;

    public ThreadUpdate(String docPath) {
        path = docPath;
    }

    public void run() {
        Logger log = Logger.getLogger(ThreadParse.class);
        DataSourceSample mireaDB = new DataSourceSample();
        try {
            log.info("Братки выехали " + path);
            EducationPlan processedEdPlan =
                    new Title().parse(path);
            mireaDB.createDBConnect();
            mireaDB.deleteEdplan(processedEdPlan.getName());
            mireaDB.closeDBConnection();
            log.info("Успешно вынесли мусор " + path);

            log.info("Батя в парcере " + path);
            ArrayList<Discipline> processedDisciplineArray
                    = new Plan().parse(path);
            log.info("Бэнг (План) " + path);
            ArrayList<Src.Competence> processedCompetenceArray =
                    new Competence().parse(path);
            log.info("Бэнг (Компетенции) " + path);
            log.info("Бэнг (План сам) " + path);
            mireaDB.createDBConnect();
            mireaDB.addNewPlan(processedEdPlan, processedDisciplineArray, processedCompetenceArray);
            log.info("Кинул в бд " + path);
            mireaDB.closeDBConnection();
            log.info("Батя покидает парсер " + path);
        } catch (SQLException e) {
            log.error(e.getLocalizedMessage());
            System.out.println("Файл не найден");
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
    }
}