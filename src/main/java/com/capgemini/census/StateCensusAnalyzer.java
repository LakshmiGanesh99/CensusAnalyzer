package com.capgemini.census;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.MappingStrategy;

public class StateCensusAnalyzer {
	public int loadStateCensusData(String csvFilePath, MappingStrategy<CSVStateCensus> mappingStrategy, Class<? extends CSVStateCensus> csvBinderClass, final char separator) throws StateCensusException {
		try {	
			Reader reader;
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withMappingStrategy(mappingStrategy);
			csvToBeanBuilder.withType(csvBinderClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			csvToBeanBuilder.withSeparator(separator);
			CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStateCensus> csvStateCensusIterator = csvToBean.iterator();
			Iterable<CSVStateCensus> csvStateCensusIterable = () -> csvStateCensusIterator;
			int numOfEntries = (int) StreamSupport.stream(csvStateCensusIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new StateCensusException(ExceptionType.STATE_CENSUS_FILE_PROBLEM);
		} catch(IllegalStateException e) {
			throw new StateCensusException(ExceptionType.STATE_CENSUS_PARSE_PROBLEM);
		} catch(RuntimeException e) {
			throw new StateCensusException(ExceptionType.STATE_CENSUS_HEADER_OR_DELIMITER_PROBLEM);
		} 
	}

	public int loadStateCodeData(String csvFilePath, MappingStrategy<CSVStates> mappingStrategy, Class<? extends CSVStates> csvBinderClass, final char separator) throws StateCodeException {
		try {	
			Reader reader;
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			CsvToBeanBuilder<CSVStates> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withMappingStrategy(mappingStrategy);
			csvToBeanBuilder.withType(csvBinderClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			csvToBeanBuilder.withSeparator(separator);
			CsvToBean<CSVStates> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStates> csvStateCodeIterator = csvToBean.iterator();
			Iterable<CSVStates> csvStateCodeIterable = () -> csvStateCodeIterator;
			int numOfEntries = (int) StreamSupport.stream(csvStateCodeIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new StateCodeException(ExceptionTypeStateCode.STATE_CODE_FILE_PROBLEM);
		} catch(IllegalStateException e) {
			throw new StateCodeException(ExceptionTypeStateCode.STATE_CODE_PARSE_PROBLEM);
		} catch(RuntimeException e) {
			throw new StateCodeException(ExceptionTypeStateCode.STATE_CODE_HEADER_OR_DELIMITER_PROBLEM);
		}
	}
}