/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrideBillComparator;

import de.redsix.pdfcompare.CompareResult;
import de.redsix.pdfcompare.PdfComparator;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author HAGRAWA
 */
public class FileOperations {

    private File[] eEListOFFiles,bBListOFFiles;
    private Map resultTable;
    final String timeStamp = Formatter.getTimeStamp();
    
    FileOperations(File eEDir, File bBDir) {
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

    public FileOperations() {
        
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
       
      //  JOptionPane.showMessageDialog(null, "Comparison Supported upto file name only, \n thanks for your Patience.", "Error", WIDTH);
        try{
        resultTable = new HashMap<>(); //will Store the results
        String Dir = "" ;
        String CompDir = "C:\\PDFComparatorResults\\"+Formatter.getTimeStamp();
        
        File CompareDirectory = new File(CompDir);  
         if(CompareDirectory.mkdirs()){
            System.out.println("Dir Created");
        }
        ArrayList<String> bBBillname = new ArrayList<>();
        for (File Bill : bBListOFFiles){
            bBBillname.add(Bill.getName());
            Dir = Bill.getParent();
        }
        String comparisonResult;
        for(File EBill : eEListOFFiles){            
            
            if(bBBillname.contains(EBill.getName())){
                String BBillname = Dir + "\\" + EBill.getName();
                
                comparisonResult = pdfComparison(EBill,new File(BBillname),CompareDirectory);        
                
            }
            else {
                comparisonResult = "File Not Found";
            }
            
            resultTable.put(EBill.getName(), comparisonResult);
        }
       
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString(), "Error", WIDTH);
        }
            return resultTable;
        
    }
    
    String getDirectory(){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
   
        chooser.setAcceptAllFileFilterUsed(false);
            
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getCurrentDirectory().toString();
             
        }
        
        return null;
    }
    
 //   void FormatResultTable

    private String pdfComparison(File EBill, File BBill,File CompareDirectory) throws IOException {
        
        String compresult = "";
        
           final CompareResult result = new PdfComparator(EBill, BBill).compare();
        if (result.isEqual()) {
            compresult = "File are same";
        }
        else if(result.isNotEqual()){
            String resultpdf = CompareDirectory.getAbsolutePath() + "\\" + BBill.getName() +"Comparisonpdf";
            resultpdf = resultpdf.replace("\\", "\\\\");
            result.writeTo(resultpdf);
            compresult+="Difference are found please refer to file " + resultpdf;
            
        }
        
        return compresult;
    }
    
    
}
