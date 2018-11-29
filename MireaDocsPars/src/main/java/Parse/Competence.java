package Parse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Competence extends General{

    public ArrayList<Src.Competence> parse(String fileName) throws SQLException {
        Logger log = Logger.getLogger(Plan.class);
        //инициализируем потоки
        Integer i = 0;
        Integer j = 0;
        boolean bool = false;
        String Name;
        String result = "";
        ArrayList<String> point = new ArrayList<String>();   // Первая запись - компетенция, последующие - дисциплины в ней
        ArrayList<ArrayList<String>> Competences = new ArrayList();
        ArrayList<Src.Competence> returnedArray = new ArrayList<>();

        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
        try {
            workBook = new HSSFWorkbook(new FileInputStream(fileName));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new SQLException("Не найдено файла");
        }

        Sheet sheet = workBook.getSheetAt(4);
        Iterator<Row> it = sheet.iterator();

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            i++;
            Name="";
            j=0;

            if (!bool && point.size()>0){
                Competences.add(point);
                point = new ArrayList<String>();
            }

            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                j++;

                if (j==2){
                    if (!get(cell).equals("0.0")){
                        bool=true;
                    }else {
                        bool = false;
                    }
                }

                if (bool) {
                    if (point.size() == 0) {
                        switch (j) {
                            case 3:
                                Name += get(cell) + " ";
                                point.add(Name);
                                Name = "";
                                cell = cells.next();
                                cell = cells.next();
                                Name += get(cell) + " ";
                                point.add(Name);
                                break;
                            case 5:
                                Name += get(cell) + " ";
                                point.add(Name);
                                break;
                        }
                    } else {
                        switch (j) {
                            case 4:
                                Name += get(cell) + " ";
                                point.add(Name);
                                Name = "";
                                break;
                            case 6:
                                Name += get(cell) + " ";
                                point.add(Name);
                                break;
                        }
                    }
                }

            }
        }
        //Display(Competences);
        for (int i1 = 0; i1 < Competences.size(); i1++) {
            returnedArray.add(new Src.Competence(Competences.get(i1)));
        }
        return returnedArray;
    }


    public  void Display(ArrayList<ArrayList<String>> indexes) {
        System.out.println();
        for (int i =0;i<indexes.size();i++){
            for (int j =0;j<indexes.get(i).size();j++){
                System.out.print(indexes.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }

    public void get_arr(ArrayList<ArrayList<String>> Competences,Cell cell,Iterator<Cell> cells,String name) {

        }
}
