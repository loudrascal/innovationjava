/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrideBillComparator;

import static java.awt.image.ImageObserver.WIDTH;
import java.io.FileOutputStream;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;

/**
 *
 * @author HAGRAWA
 */
public class ExcelExport {

    static void createExcel(JTable ResultTable) {
    try {
            Date d = new Date();
            String path = "C:\\Users\\hagrawa\\Desktop\\Desktop\\AIM\\Result\\";
            String name = "ComparisonResult"+d.getYear() +d.getMonth() +d.getDay()+d.getHours()+d.getMinutes()+d.getSeconds()+".xls";
            String filename = path + name ;
             
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Comparison result");  

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("SNo.");
            rowhead.createCell(1).setCellValue("Bill Name");
            rowhead.createCell(2).setCellValue("Status");
            
            
            
            int rowsize = ResultTable.getRowCount();
            int columnsize = ResultTable.getColumnCount();
            for (int j = 1; j  <= rowsize; j++) {
                HSSFRow row = sheet.createRow(j);
                for (int i = 0; i  < columnsize; i++) {
                    //System.out.println(ResultTable.getValueAt(j, i));
                    row.createCell(i).setCellValue(ResultTable.getValueAt(j, i).toString());
                }
            }
           /* row.createCell(0).setCellValue("1");
            row.createCell(1).setCellValue("Sankumarsingh");
            row.createCell(2).setCellValue("India");*/
            

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            
            fileOut.close();
          // workbook.;
            System.out.println("Your excel file has been generated!");
            JOptionPane.showMessageDialog(null, "Your excel file has been generated At " + path + " With name " + name  , "Error", WIDTH);

        } catch ( Exception ex ) {
            JOptionPane.showMessageDialog(ResultTable,ex.toString(), "Error", WIDTH);
        }
        
    }
    
}
