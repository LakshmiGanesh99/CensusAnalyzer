package com.capgemini.census;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
	@CsvBindByName(column = "State", required = true)
	public String state;

	@CsvBindByName(column = "Code", required = true)
	public String code;

	@Override
	public String toString() {
		return "CSVStates [state=" + state + ", code=" + code + "]";
	}
}