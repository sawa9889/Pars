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

public abstract class General {

    public abstract String parse(String fileName);
    public abstract void Display(ArrayList<ArrayList<String>> indexes);


    public static String get(Cell cell){
        String result="";
        int cellType =cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                result += cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                result += Double.toString(cell.getNumericCellValue());
                break;

            case Cell.CELL_TYPE_FORMULA:
                result += Double.toString(cell.getNumericCellValue());
                break;
            default:
                result += Double.toString(cell.getNumericCellValue());
                break;
        }
        return result.equals("")? "0":result;
    }

    public static String parse(String fileName, int shet) {
        //инициализируем потоки
        Integer i = 0;
        Integer j = 0;
        boolean bool = false;

        String result = "";
        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workBook.getSheetAt(shet);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            i++;
            result += i+"\n";
            j=0;
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                j++;
                //перебираем возможные типы ячеек
                switch (cellType) {
                    case Cell.CELL_TYPE_STRING:
                        result += j+" := "+cell.getStringCellValue() + "=";
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        result +=  j+" := "+"[" + cell.getNumericCellValue() + "]";
                        break;

                    case Cell.CELL_TYPE_FORMULA:
                        result +=  j+" := "+"[" + cell.getNumericCellValue() + "]";
                        break;
                    default:
                        result +=  j+" := "+"|";
                        break;
                }
            }
            result += "\n";
        }
        return result;
    }
}
