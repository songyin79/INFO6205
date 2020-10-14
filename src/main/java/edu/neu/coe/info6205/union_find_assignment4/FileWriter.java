package edu.neu.coe.info6205.union_find_assignment4;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWriter {
	
	String CSV_FILE_NAME;
	
	public FileWriter(String CSV_FILE_NAME){
		this.CSV_FILE_NAME = CSV_FILE_NAME;
	}
	
	 /**
     * write output data to csv
     * @param data
     */
    public String convertToCSV(String[] data) {
        return Stream.of(data).collect(Collectors.joining(","));
    }
    
    /**
     * convert each row with convertToCSV and write it to a file
     * 
     * @param dataLines
     */
    public void toCSV(List<String[]> dataLines) throws IOException {
        File csvOutputFile = new File(CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
              .map(this::convertToCSV)
              .forEach(pw::println);
        }
        assertTrue(csvOutputFile.exists());
    }

}