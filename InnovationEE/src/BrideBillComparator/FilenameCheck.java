/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrideBillComparator;

import java.awt.List;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author HAGRAWA
 */
public class FilenameCheck {

    private File[] eEListOFFiles,bBListOFFiles;
    private Map resultTable;
    
    FilenameCheck(File eEDir, File bBDir) {
        FilenameFilter fileNameFilter = new FilenameFilter() {
        
            @Override
            public boolean accept(File dir, String name) {
                if(name.lastIndexOf('.')>0) {
                    int lastIndex = name.lastIndexOf('.');
                    String str = name.substring(lastIndex);
                    if(str.equals(".pdf")) {
                        return true;
                    }
                }
                return false;
            }
        };
    
        eEListOFFiles = eEDir.listFiles(fileNameFilter);
        bBListOFFiles = bBDir.listFiles(fileNameFilter);
       
    }

    //Display Error Message when no PDF Files are present in Directory Choosen
    boolean checkFilesIfPresent() {
    
        if(eEListOFFiles.length==0){
            JOptionPane.showMessageDialog(null, "No PDF files are present in the EE Bill Directory Choosen", "Error", WIDTH);
            if(bBListOFFiles.length==0){
                JOptionPane.showMessageDialog(null, "No PDF files are present in the Brite Bill Directory Choosen", "Error", WIDTH);
            }
            return false;
        }
        
     return true;   
        
    }

    Map startComparison() {
       
        JOptionPane.showMessageDialog(null, "Comparison Supported upto file name only, \n thanks for your Patience.", "Error", WIDTH);
        
        resultTable = new HashMap<String,String>(); //will Store the results
                       
        ArrayList<String> bBBillname = new ArrayList<String>();
        for (File Bill : bBListOFFiles){
            bBBillname.add(Bill.getName());
        }
        String comparisonResult;
        for(File Bill : eEListOFFiles){
            
            comparisonResult = "";
            if(bBBillname.contains(Bill.getName())){
                comparisonResult = "File Found";        
                
            }
            else {
                comparisonResult = "File Not Found";
            }
            
            resultTable.put(Bill.getName(), comparisonResult);
        }
       // JOptionPane.showMessageDialog(null, "Comparison Not Supported yet, \n thanks for your Patience.", "Error", WIDTH); 
        return resultTable;
    }
    
 //   void FormatResultTable
    
    
}
