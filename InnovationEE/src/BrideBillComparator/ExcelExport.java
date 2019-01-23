/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrideBillComparator;

import static java.awt.image.ImageObserver.WIDTH;
import java.io.FileOutputStream;
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
            
            String path = (new FileOperations().getDirectory()).replace("\\", "\\\\");
            
            String name = "ComparisonResult"+Formatter.getTimeStamp()+".xls";
            String filename = path +"\\\\" + name ;
             
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Comparison result");  

            HSSFRow rowhead = sheet.createRow(0);
            rowhead.createCell(0).setCellValue("SNo.");
            rowhead.createCell(1).setCellValue("Bill Name");
            rowhead.createCell(2).setCellValue("Status");
            
            
            
            int rowsize = ResultTable.getRowCount();
            int columnsize = ResultTable.getColumnCount();
            for (int j = 0; j  < rowsize; j++) {
                HSSFRow row = sheet.createRow(j+1);
                for (int i = 0; i  < columnsize; i++) {                    
                    row.createCell(i).setCellValue(ResultTable.getValueAt(j, i).toString());
                }
            }
          
            

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            
            fileOut.close();
           
           
            JOptionPane.showMessageDialog(null, "Your excel file has been generated At " + path + " With name " + name  , "Information", WIDTH);

        } catch ( Exception ex ) {
            JOptionPane.showMessageDialog(ResultTable,ex.toString(), "Error", WIDTH);
        } finally {
            
        }
        
    }
    
}
