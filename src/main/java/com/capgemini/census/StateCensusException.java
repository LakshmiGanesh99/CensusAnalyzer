package com.capgemini.census;

enum ExceptionType{
	STATE_CENSUS_FILE_PROBLEM, STATE_CENSUS_PARSE_PROBLEM, STATE_CENSUS_HEADER_OR_DELIMITER_PROBLEM
}

@SuppressWarnings("serial")
public class StateCensusException extends Exception {
	public StateCensusException(ExceptionType exceptionType) {
		super(exceptionType.toString());
	}
}