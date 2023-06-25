package com.singh.base.utility;

import java.util.ArrayList; 
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.singh.base.entity.Category;
import com.singh.base.entity.Product;
import com.singh.base.entity.Supplier;

import static com.singh.base.serviceImpl.ProductServiceImpl.completeFilePath;

public class ProductUtility {
	
	public static Integer totalRecordInSheet = 0;
		
	public  List<Product> readExcell(String completeFilePath){
		
		List<Product> list = new ArrayList<>();
		
		try(Workbook workbook = new XSSFWorkbook(completeFilePath)) {
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
				totalRecordInSheet += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public  Map<Integer, List<Integer>> compareProducts(List<Product> allProducts)
	{
		List<Integer> rowIndex = new ArrayList<Integer>();
		List<Integer> productNotInDB = new ArrayList<Integer>();
		
		try(Workbook workbook = new XSSFWorkbook(completeFilePath)) { 
			Sheet sheet = workbook.getSheet("products"); 
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				boolean found = false;
				Row row = (Row) rowIterator.next();
				if(row.getRowNum() == 0) {
					continue; 
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = (Cell) cellIterator.next();
					
					for(int i=0; i<allProducts.size(); i++) {
						if(cell.getRichStringCellValue().getString().equalsIgnoreCase(allProducts.get(i).getProductName())) {
							found = true;
							rowIndex.add(cell.getRowIndex()+1);
							break; //--> end of while
						}else {
							continue;
						}
					}// end for loop
					break;
				}// end while
				if(!found) {
					productNotInDB.add(row.getRowNum()+1);
				}else {
				continue;
				}
			}// while end
		}catch (Exception e) {
			e.printStackTrace();
		}		
		Map<Integer, List<Integer>> result = new HashedMap<>();
		result.put(1, rowIndex);
		result.put(2, productNotInDB);
		return result;

	}
}
