import Threads.ThreadDelete;
import Threads.ThreadParse;
import Threads.ThreadUpdate;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private Scanner in;
    private int action;
    public String path;
    private boolean exit;
    public Logger log;

    private Main() {
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
        System.out.println("7) Обновить план ");
        System.out.println("8) Обновить все планы в папке ");
        System.out.println("exit (9) - Выход из этого цикла бытия ");
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
                    System.out.println("1) Добавить файл ");
                    System.out.println("2) Удалить файл ");
                    System.out.println("3) Обновить файл ");
                    System.out.println("4) Выйти из папки файл");
                    action = in.nextInt();
                    String file = folder + "/" + path;
                    if (action == 1) {
                        System.out.println("Укажите файл : ");
                        path = in.next();
                        new ThreadParse(file).run();
                    } else if (action == 2) {
                        System.out.println("Укажите файл : ");
                        path = in.next();
                        new ThreadDelete(file).run();
                    } else if (action == 3) {
                        System.out.println("Укажите файл : ");
                        path = in.next();
                        new ThreadUpdate(file).run();
                    }else {
                        exit = true;
                    }
                }
                exit = false;
                break;
            case 7:
                System.out.println("Укажите путь к папке : ");
                path = in.next();
                DeleteFolder(path);
                break;
            case 8:
                System.out.println("MOLODIEZ");
                break;
            case 9:
                exit = true;

        }
    }

    private void ShowFilesInFolder(String folder){
        File Folder = new File(folder);
        try {
            for (File file : Objects.requireNonNull(Folder.listFiles())) {
                System.out.println(file.getName());
            }
        }catch (NullPointerException e){
            System.out.println("Папка не найдена");
            log.error(e.getLocalizedMessage());
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

    private void UpdateFolder(String folder) {

        ExecutorService executeIt = Executors.newFixedThreadPool(5);
        File Folder = new File(folder);
        for (File file : Objects.requireNonNull(Folder.listFiles())) {
            try {
                executeIt.execute(new ThreadUpdate(folder + "/" + file.getName()));
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
