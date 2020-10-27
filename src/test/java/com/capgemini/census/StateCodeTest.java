package com.capgemini.census;

import org.junit.Assert;
import org.junit.Test;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

public class StateCodeTest {
	private final String STATE_CODE_CSV_FILE = "./src/main/resources/StateCodeCSVData.csv";
	private final String INCORRECT_STATE_CODE_CSV_FILE = "./src/main/resources/_StateCodeCSVData.csv";
	private final String INCORRECT_HEADER_STATE_CODE_CSV_FILE = "./src/main/resources/StateCodeCSVDataIncorrectHeader.csv";

	@Test
	public void givenTheStatesCodeCSVFile_WhenRead_NoOfRecordsShouldMatch() throws StateCodeException  {
		StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
		MappingStrategy<CSVStates> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStates>();
		mappingStrategy.setType(CSVStates.class);
		int numOfRecords = stateCensusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE, mappingStrategy, CSVStates.class, ',');
		Assert.assertEquals(5, numOfRecords);
	}

	@Test
	public void givenIncorrectCSVFile_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			MappingStrategy<CSVStates> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStates>();
			mappingStrategy.setType(CSVStates.class);
			stateCensusAnalyser.loadStateCodeData(INCORRECT_STATE_CODE_CSV_FILE, mappingStrategy, CSVStates.class, ',');
		} catch(StateCodeException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionTypeStateCode.STATE_CODE_FILE_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenIncorrectCSVType_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			stateCensusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE, null, null, ',');
		} catch(StateCodeException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionTypeStateCode.STATE_CODE_PARSE_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenCorrectCSVFileIncorrectDelimiter_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			MappingStrategy<CSVStates> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStates>();
			mappingStrategy.setType(CSVStates.class);
			stateCensusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE, mappingStrategy, CSVStates.class, '|');
		} catch(StateCodeException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionTypeStateCode.STATE_CODE_HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenCorrectCSVFileIncorrectHeader_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			MappingStrategy<CSVStates> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStates>();
			mappingStrategy.setType(CSVStates.class);
			stateCensusAnalyser.loadStateCodeData(INCORRECT_HEADER_STATE_CODE_CSV_FILE, mappingStrategy, CSVStates.class, ',');
		} catch(StateCodeException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionTypeStateCode.STATE_CODE_HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
	}
}	