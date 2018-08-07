package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testdata.yaml.SearchData;

public class BasePage {
	public Action getAction(){
		return DriverBase.getThread().getAction();
		
	}
	public static List <SearchData> getDataFromExcel() {
		List <SearchData> searchList=new ArrayList<SearchData>();

		try {
			FileInputStream excel=new FileInputStream(new File(System.getProperty("user.dir")+"/resources/TESTDATA.xlsx"));
			 XSSFWorkbook book=new XSSFWorkbook(excel);
			 XSSFSheet datasheet= book.getSheet("datasheet") ;
			 
			 Iterator<Row> sheetIterator=datasheet.iterator();
			while(sheetIterator.hasNext()) {
				
				Row row=sheetIterator.next();
				 Iterator<Cell> cellIterator=row.iterator();
				 
				 while(cellIterator.hasNext()) {
					 Cell cell=cellIterator.next();
					 switch(cell.getCellType()){
					 case Cell.CELL_TYPE_NUMERIC:
						 break;
					 case Cell.CELL_TYPE_STRING:
						 SearchData data=new SearchData();
						 data.searchSku=cell.getStringCellValue();
						 searchList.add(data);
						 
						 break;
					 }
				 }

			}
			excel.close();
			book.close();
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchList;
	}
}
