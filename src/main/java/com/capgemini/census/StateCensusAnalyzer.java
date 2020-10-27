package com.capgemini.census;

import com.capgemini.opencsvbuilder.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;
import com.opencsv.bean.MappingStrategy;

public class StateCensusAnalyzer {
	public int loadStateCensusData(String csvFilePath, MappingStrategy<CSVStateCensus> mappingStrategy, Class<? extends CSVStateCensus> csvBinderClass, final char separator) throws CustomFileIOException, CustomCSVBuilderException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){	
			List<CSVStateCensus> csvStateCensusList;
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			if(csvBinderClass != null)
				csvStateCensusList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class, mappingStrategy, separator);
			else
				csvStateCensusList = csvBuilder.getCSVFileList(reader, null, null, separator);
			return csvStateCensusList.size();
		} catch (IOException e) {
			throw new CustomFileIOException(ExceptionTypeIO.FILE_PROBLEM);
		} 
	}

	public int loadStateCodeData(String csvFilePath, MappingStrategy<CSVStates> mappingStrategy, Class<? extends CSVStates> csvBinderClass, final char separator) throws CustomFileIOException, CustomCSVBuilderException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){	
			List<CSVStates> csvStateCodeList;
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			if(csvBinderClass != null)
				csvStateCodeList = csvBuilder.getCSVFileList(reader, CSVStates.class, mappingStrategy, separator);
			else
				csvStateCodeList= csvBuilder.getCSVFileList(reader, null, null, separator);
			return csvStateCodeList.size();
		} catch (IOException e) {
			throw new CustomFileIOException(ExceptionTypeIO.FILE_PROBLEM);
		} 
	}
}