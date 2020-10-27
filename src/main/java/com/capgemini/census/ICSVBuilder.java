package com.capgemini.census;

import java.io.Reader;
import java.util.Iterator;
import com.opencsv.bean.MappingStrategy;

public interface ICSVBuilder {
	public Iterator getCSVFileIterator(Reader reader, Class csvBinderClass,
			MappingStrategy mappingStrategy, final char separator);
}