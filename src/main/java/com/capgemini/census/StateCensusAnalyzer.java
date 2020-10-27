package com.capgemini.census;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import com.opencsv.bean.MappingStrategy;

public class StateCensusAnalyzer {
	public int loadStateCensusData(String csvFilePath, MappingStrategy<CSVStateCensus> mappingStrategy, Class<? extends CSVStateCensus> csvBinderClass, final char separator) throws StateCensusException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){	
			Iterator<CSVStateCensus> csvStateCensusIterator;
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			if(csvBinderClass != null)
				csvStateCensusIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class, mappingStrategy, separator);
			else
				csvStateCensusIterator = csvBuilder.getCSVFileIterator(reader, null, null, separator);
			return getCount(csvStateCensusIterator);
		} catch (IOException e) {
			throw new StateCensusException(ExceptionType.STATE_CENSUS_FILE_PROBLEM);
		} catch(IllegalStateException e) {
			throw new StateCensusException(ExceptionType.STATE_CENSUS_PARSE_PROBLEM);
		} catch(RuntimeException e) {
			throw new StateCensusException(ExceptionType.STATE_CENSUS_HEADER_OR_DELIMITER_PROBLEM);
		}
	}

	public int loadStateCodeData(String csvFilePath, MappingStrategy<CSVStates> mappingStrategy, Class<? extends CSVStates> csvBinderClass, final char separator) throws StateCodeException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){	
			Iterator<CSVStates> csvStateCodeIterator;
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			if(csvBinderClass != null)
				csvStateCodeIterator = csvBuilder.getCSVFileIterator(reader, CSVStates.class, mappingStrategy, separator);
			else
				csvStateCodeIterator = csvBuilder.getCSVFileIterator(reader, null, null, separator);
			return getCount(csvStateCodeIterator);
		} catch (IOException e) {
			throw new StateCodeException(ExceptionTypeStateCode.STATE_CODE_FILE_PROBLEM);
		} catch(IllegalStateException e) {
			throw new StateCodeException(ExceptionTypeStateCode.STATE_CODE_PARSE_PROBLEM);
		} catch(RuntimeException e) {
			throw new StateCodeException(ExceptionTypeStateCode.STATE_CODE_HEADER_OR_DELIMITER_PROBLEM);
		}
	}
	
	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}
}
