import DAO.DataSourceSample;
import Parse.Title;
import Parse.Competence;
import Parse.Plan;
import Src.Discipline;
import Src.EducationPlan;
import Threads.ThreadDelete;
import Threads.ThreadParse;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public Scanner in;
    public int action;
    public String path;
    public boolean exit;
    public Logger log;

    Main() {
        exit = false;
        in = new Scanner(System.in);
        log = Logger.getLogger(Main.class);
    }

    public static void main(String[] args) {

        Main main = new Main();
        String docPath = "C:/Users/Alexandr/Downloads/Project_uni/Exel/09.03.02_ИТСАиБ_ИКБСП_2017.plm.xml.xls";
        //Добавление нового учебного плана
        while (!main.exit) {
            main.Console();
        }
        //Удаление учебного плана с введенным названием
        //mireaDB.deleteEdplan(processedEdPlan.getName());

    }


    public void Console() {
        System.out.println("1) Добавление файла в базу ");
        System.out.println("2) Добавление файлов из папки в базу(Мы не несем ответственности за сторонние файлы в папке) ");
        System.out.println("3) Удалить план из базы ");
        System.out.println("4) Удалить планы в папке из базы");
        System.out.println("5) Сказать что вы молодец ");
        System.out.println("6) Посмотреть файлы в папке ");
        System.out.println("exit (7) - Выход из этого цикла бытия ");
        System.out.print("Введите действие : ");
        action = in.nextInt();
        switch (action) {
            case 1:
                System.out.println("Укажите путь к файлу с именем файла : ");
                path = in.next();
                new ThreadParse(path).run();
                break;
            case 2:
                System.out.println("Укажите путь к папке : ");
                path = in.next();
                ParseFolder(path);
                break;
            case 3:
                System.out.println("Укажите путь к файлу с именем файла : ");
                path = in.next();
                new ThreadDelete(path).run();
                break;
            case 4:
                System.out.println("Укажите путь к папке : ");
                path = in.next();
                DeleteFolder(path);
                break;
            case 5:
                System.out.println("MOLODIEZ");
                break;
            case 6:
                System.out.println("Укажите путь к папке :");
                path = in.next();
                ShowFilesInFolder(path);
                String folder = path;
                while(!exit) {
                    System.out.println("1) Удалить файл");
                    System.out.println("2) Добавить файл");
                    System.out.println("3) Выйти из папки файл");
                    action = in.nextInt();
                    if (action == 1) {
                        System.out.println("Укажите файл : ");
                        path = in.next();
                        new ThreadParse(folder + "/" + path).run();
                    } else if (action == 2) {
                        System.out.println("Укажите файл : ");
                        path = in.next();
                        new ThreadDelete(folder + "/" + path).run();
                    } else {
                        exit = true;
                    }
                }
                exit = false;
                break;
            case 7:
                exit = true;

        }
    }

    public void ShowFilesInFolder(String folder){
        File Folder = new File(folder);
        for (File file : Objects.requireNonNull(Folder.listFiles())) {
            System.out.println(file.getName());
        }
    }

    private void ParseFolder(String folder) {
        ExecutorService executeIt = Executors.newFixedThreadPool(5);
        File Folder = new File(folder);
        for (File file : Objects.requireNonNull(Folder.listFiles())) {
            try {
                if (file.getName().contains("xml")) {
                    log.info("Сфоткай типа отпарсил файл " + file.getName());
                    executeIt.execute(new ThreadParse(folder + "/" + file.getName()));
                } else {
                    log.info("Хуета какайта " + file.getName());
                }
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());

            }
        }
        executeIt.shutdown();
        try {
            executeIt.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }catch (InterruptedException e){
            log.error(e.getLocalizedMessage());
        }
    }

    private void DeleteFolder(String folder) {

        ExecutorService executeIt = Executors.newFixedThreadPool(5);
        File Folder = new File(folder);
        for (File file : Objects.requireNonNull(Folder.listFiles())) {
            try {
                executeIt.execute(new ThreadDelete(folder + "/" + file.getName()));
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            }
        }
        executeIt.shutdown();
        try {
            executeIt.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }catch (InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

    }

}
