package com.capgemini.census;

import org.junit.Assert;
import org.junit.Test;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

public class StateCensusAnalyserTest {
	private final String STATE_CENSUS_CSV_FILE = "./src/main/resources/StateCensusCSVData.csv";
	private final String INCORRECT_STATE_CENSUS_CSV_FILE = "./src/main/resources/_StateCensusCSVData.csv";
	private final String INCORRECT_HEADER_STATE_CENSUS_CSV_FILE = "./src/main/resources/StateCensusCSVDataIncorrectHeader.csv";

	@Test
	public void givenTheStatesCensusCSVFile_WhenRead_NoOfRecordsShouldMatch() throws StateCensusException  {
		StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
		MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
		mappingStrategy.setType(CSVStateCensus.class);
		int numOfRecords = stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		Assert.assertEquals(6, numOfRecords);
	}

	@Test
	public void givenIncorrectCSVFile_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
			mappingStrategy.setType(CSVStateCensus.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		} catch(StateCensusException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.STATE_CENSUS_FILE_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenIncorrectCSVType_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, null, null, ',');
		} catch(StateCensusException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.STATE_CENSUS_PARSE_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenCorrectCSVFileIncorrectDelimiter_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
			mappingStrategy.setType(CSVStateCensus.class);
			stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, '|');
		} catch(StateCensusException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.STATE_CENSUS_HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenCorrectCSVFileIncorrectHeader_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
			mappingStrategy.setType(CSVStateCensus.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_HEADER_STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		} catch(StateCensusException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.STATE_CENSUS_HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
	}
}
