package com.capgemini.census;

public class StateCensusException extends Exception {
	StateCensusExceptionType exceptionType; 

	public StateCensusException(String message, StateCensusExceptionType exceptionType) {
		super(message);
		this.exceptionType = exceptionType;
	}
}

enum StateCensusExceptionType {
	CENSUS_FILE_PROBLEM, INCORRECT_TYPE, SOME_OTHER_ERRORS
}
