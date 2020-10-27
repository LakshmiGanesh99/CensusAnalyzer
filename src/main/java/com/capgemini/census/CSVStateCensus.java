package com.capgemini.census;
import com.opencsv.bean.CsvBindByName; 

public class CSVStateCensus {
	@CsvBindByName(column = "State", required = true)
	public String state;
	@CsvBindByName(column = "Population", required = true)
	public long population;
	@CsvBindByName(column = "DensityPerSqKm", required = true)
	public long density;
	@CsvBindByName(column = "AreaPerSqKm", required = true)
	public long area;
	@Override
	public String toString() {
		return "CSVStateCensus [state=" + state + ", population=" + population + ", density=" + density + ", area="
				+ area + "]";
	}
}  