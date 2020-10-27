package com.capgemini.census;

import com.capgemini.opencsvbuilder.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import com.opencsv.bean.MappingStrategy;

public class StateCensusAnalyzer {
	public int loadStateCensusData(String csvFilePath, MappingStrategy<CSVStateCensus> mappingStrategy, Class<? extends CSVStateCensus> csvBinderClass, final char separator) throws CustomFileIOException, CustomCSVBuilderException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){	
			Iterator<CSVStateCensus> csvStateCensusIterator;
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			if(csvBinderClass != null)
				csvStateCensusIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class, mappingStrategy, separator);
			else
				csvStateCensusIterator = csvBuilder.getCSVFileIterator(reader, null, null, separator);
			return getCount(csvStateCensusIterator);
		} catch (IOException e) {
			throw new CustomFileIOException(ExceptionTypeIO.FILE_PROBLEM);
		} 
	}

	public int loadStateCodeData(String csvFilePath, MappingStrategy<CSVStates> mappingStrategy, Class<? extends CSVStates> csvBinderClass, final char separator) throws CustomFileIOException, CustomCSVBuilderException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){	
			Iterator<CSVStates> csvStateCodeIterator;
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			if(csvBinderClass != null)
				csvStateCodeIterator = csvBuilder.getCSVFileIterator(reader, CSVStates.class, mappingStrategy, separator);
			else
				csvStateCodeIterator = csvBuilder.getCSVFileIterator(reader, null, null, separator);
			return getCount(csvStateCodeIterator);
		} catch (IOException e) {
			throw new CustomFileIOException(ExceptionTypeIO.FILE_PROBLEM);
		} 
	}
	
	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}
}