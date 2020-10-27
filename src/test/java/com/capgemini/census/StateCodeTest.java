 package com.capgemini.census;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.opencsvbuilder.*;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

 public class StateCodeTest {
 	private final String STATE_CODE_CSV_FILE = "./src/main/resources/StateCodeCSVData.csv";
 	private final String INCORRECT_STATE_CODE_CSV_FILE = "./src/main/resources/_StateCodeCSVData.csv";
 	private final String INCORRECT_HEADER_STATE_CODE_CSV_FILE = "./src/main/resources/StateCodeCSVDataIncorrectHeader.csv";

 	@Test
 	public void givenTheStatesCodeCSVFile_WhenRead_NoOfRecordsShouldMatch() throws  CustomCSVBuilderException, CustomFileIOException  {
 		StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
 		MappingStrategy<CSVStates> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStates>();
 		mappingStrategy.setType(CSVStates.class);
 		int numOfRecords = stateCensusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE, mappingStrategy, CSVStates.class, ',');
 		Assert.assertEquals(5, numOfRecords);
 	}

 	@Test
 	public void givenIncorrectCSVFile_ShouldReturnCustomException() throws CustomCSVBuilderException {
 		String exceptionMessage = null;
 		try {
 			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
 			MappingStrategy<CSVStates> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStates>();
 			mappingStrategy.setType(CSVStates.class);
 			stateCensusAnalyser.loadStateCodeData(INCORRECT_STATE_CODE_CSV_FILE, mappingStrategy, CSVStates.class, ',');
 		} catch(CustomFileIOException e) {
 			exceptionMessage = e.getMessage();
 		}
 		Assert.assertEquals(ExceptionType.FILE_PROBLEM.toString(), exceptionMessage);
 	}
 	
 	@Test
 	public void givenIncorrectCSVType_ShouldReturnCustomException() throws CustomFileIOException {
 		String exceptionMessage = null;
 		try {
 			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
 			stateCensusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE, null, null, ',');
 		} catch(CustomCSVBuilderException e) {
 			exceptionMessage = e.getMessage();
 		}
 		Assert.assertEquals(ExceptionType.PARSE_PROBLEM.toString(), exceptionMessage);
 	}
 	
 	@Test
 	public void givenCorrectCSVFileIncorrectDelimiter_ShouldReturnCustomException() throws CustomFileIOException {
 		String exceptionMessage = null;
 		try {
 			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
 			MappingStrategy<CSVStates> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStates>();
 			mappingStrategy.setType(CSVStates.class);
 			stateCensusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE, mappingStrategy, CSVStates.class, '|');
 		} catch(CustomCSVBuilderException e) {
 			exceptionMessage = e.getMessage();
 		}
 		Assert.assertEquals(ExceptionType.HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
 	}
 	
 	@Test
 	public void givenCorrectCSVFileIncorrectHeader_ShouldReturnCustomException() throws CustomFileIOException {
 		String exceptionMessage = null;
 		try {
 			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
 			MappingStrategy<CSVStates> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStates>();
 			mappingStrategy.setType(CSVStates.class);
 			stateCensusAnalyser.loadStateCodeData(INCORRECT_HEADER_STATE_CODE_CSV_FILE, mappingStrategy, CSVStates.class, ',');
 		} catch(CustomCSVBuilderException e) {
 			exceptionMessage = e.getMessage();
 		}
 		Assert.assertEquals(ExceptionType.HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
 	}
 }	
