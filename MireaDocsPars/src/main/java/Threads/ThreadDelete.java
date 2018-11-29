package Threads;

import DAO.DataSourceSample;
import Parse.Title;
import Src.EducationPlan;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class ThreadDelete implements Runnable{

    String path;
    public ThreadDelete(String docPath){
        path=docPath;
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
        } catch (SQLException e) {
            log.error(e.getLocalizedMessage());
            System.out.println("Файл не найден");
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
    }
}
