package com.capgemini.census;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {
	private final String STATE_CENSUS_CSV_FILE = "./src/main/resources/StateCensusCSVData.csv";
	private final String INCORRECT_STATE_CENSUS_CSV_FILE = "./src/main/resources/_StateCensusCSVData.csv";

	@Test
	public void givenTheStatesCensusCSVFile_WhenRead_NoOfRecordsShouldMatch() throws StateCensusException {
		StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
		int numOfRecords = stateCensusAnalyser.loadTheStateCensusCSVData(STATE_CENSUS_CSV_FILE);
		Assert.assertEquals(6, numOfRecords);
	}

	@Test
	public void givenIncorrectCSVFile_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
			stateCensusAnalyser.loadTheStateCensusCSVData(INCORRECT_STATE_CENSUS_CSV_FILE);
		} catch (StateCensusException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(StateCensusExceptionType.CENSUS_FILE_PROBLEM.toString(), exceptionMessage);
	}
}