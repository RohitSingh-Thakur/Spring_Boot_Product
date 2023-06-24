package com.singh.base.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.singh.base.entity.Category;
import com.singh.base.entity.Product;
import com.singh.base.entity.Supplier;

public class ProductUtility {
	public static List<Product> readExcell(String filePath){
		List<Product> list = new ArrayList<>();
		try(Workbook workbook = new XSSFWorkbook(filePath)) {
			Sheet sheet = workbook.getSheet("products");
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();
				if(row.getRowNum() == 0) {
					continue; // if condition matched then continue will stop the condition and pointer will go to while loop again
				}
				Product product = new Product(); // creating project below if because if condition is excluding header and if object created above if then for 0th index which is header row one student object will be created.
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = (Cell) cellIterator.next();
					
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case 0:{
						product.setProductName(cell.getStringCellValue());
						break;
					}case 1:{
						Supplier supplier = new Supplier();
						supplier.setSupplierId((long)cell.getNumericCellValue());	
						product.setSupplierId(supplier);
						break;
					}case 2:{
						Category category = new Category();
						category.setCategoryId((long)cell.getNumericCellValue());
						product.setCategoryId(category);
						break;
					}case 3:{
						product.setProductQuantity((long)cell.getNumericCellValue());
						break;
					}case 4:{
						product.setProductPrice(cell.getNumericCellValue());
						break;
					}
				}	
				}
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
