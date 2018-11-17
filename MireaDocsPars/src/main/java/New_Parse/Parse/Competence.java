package Parse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Competence extends General{
    public String parse(String fileName) {
        //инициализируем потоки
        Integer i = 0;
        Integer j = 0;
        boolean bool = false;
        String Name;
        String result = "";
        ArrayList<String> point = new ArrayList<String>();   // Первая запись - компетенция, последующие - дисциплины в ней
        ArrayList<ArrayList<String>> Competences = new ArrayList();

        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workBook.getSheetAt(5);
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
                        if (point.size()>0){
                            Competences.add(point);
                            point = new ArrayList<String>();
                        }

                    }else {
                        cell = cells.next();
                        Name += get(cell) + " ";

                        if (!get(cell).equals("0.0")) {
                            bool = true;
                        }
                            else {
                            bool = false;
                        }

                    }
                }
                if (bool) {
                    System.out.println(point.size());
                    if (point.size() == 0) {
                        switch (j) {
                            case 2:
                                Name += get(cell) + " ";
                                point.add(Name);
                                Name = "";
                                cell = cells.next();
                                cell = cells.next();
                                Name += get(cell) + " ";
                                point.add(Name);
                                break;

                        }
                    } else {
                        switch (j) {
                            case 3:
                                Name += get(cell) + " ";
                                point.add(Name);
                                break;
                        }
                    }
                }

            }
        }
        Display(Competences);
        return result;
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
