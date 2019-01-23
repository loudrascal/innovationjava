/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrideBillComparator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HAGRAWA
 */
public class Formatter {
    
        public static String getTimeStamp(){
        
        String timestamp = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date());
        return timestamp;
    }
    
    
    static JTable createTable(File eEDir,File bBDir,JTable ResultTable ){
        FileOperations filename = new FileOperations(eEDir, bBDir);
        Map result = filename.startComparison();
        DefaultTableModel model = (DefaultTableModel) ResultTable.getModel();
        Iterator iterator = result.keySet().iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Object value = result.get(key);
            Object[] row = {counter + 1, key, value};
            model.addRow(row);
            ++counter;
        }
        
      
        
        return ResultTable;
    }
    
    
    
}
