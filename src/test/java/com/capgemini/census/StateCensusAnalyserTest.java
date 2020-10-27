package com.capgemini.census;

import com.capgemini.opencsvbuilder.*;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

public class StateCensusAnalyserTest {
	private final String STATE_CENSUS_CSV_FILE = "./src/main/resources/StateCensusCSVData.csv";
	private final String INCORRECT_STATE_CENSUS_CSV_FILE = "./src/main/resources/_StateCensusCSVData.csv";
	private final String INCORRECT_HEADER_STATE_CENSUS_CSV_FILE = "./src/main/resources/StateCensusCSVDataIncorrectHeader.csv";

	@Test
	public void givenTheStatesCensusCSVFile_WhenRead_NoOfRecordsShouldMatch() throws CustomFileIOException, CustomCSVBuilderException  {
		StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
		MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
		mappingStrategy.setType(CSVStateCensus.class);
		int numOfRecords = stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		Assert.assertEquals(6, numOfRecords);
	}

	@Test
	public void givenIncorrectCSVFile_ShouldReturnCustomException() throws CustomCSVBuilderException {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
			mappingStrategy.setType(CSVStateCensus.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		} catch(CustomFileIOException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionTypeIO.FILE_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenIncorrectCSVType_ShouldReturnCustomException() throws CustomFileIOException {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, null, null, ',');
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
			MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
			mappingStrategy.setType(CSVStateCensus.class);
			stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, '|');
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
			MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
			mappingStrategy.setType(CSVStateCensus.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_HEADER_STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		} catch(CustomCSVBuilderException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenCensusData_WhenSortedAlphabetically_ShouldGiveSortedResult() throws CustomCSVBuilderException, CustomFileIOException {
		StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
		MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
		mappingStrategy.setType(CSVStateCensus.class);
		stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		String sortedCensusData = stateCensusAnalyser.getAlpahebeticalStateWiseCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		Assert.assertEquals("Assam", censusCSV[0].state);
	}
}