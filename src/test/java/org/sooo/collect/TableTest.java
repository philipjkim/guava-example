package org.sooo.collect;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TableTest {

	Table<Integer, Integer, Double> weightedGraph;

	@Before
	public void setUp() {
		weightedGraph = HashBasedTable.create();
		weightedGraph.put(1, 2, 4d);
		weightedGraph.put(1, 3, 20d);
		weightedGraph.put(2, 3, 5d);
	}

	@Test
	public void rowMap() {
		// when
		Map<Integer, Map<Integer, Double>> rowMap = weightedGraph.rowMap();

		// then
		assertThat(rowMap.get(1).get(2), is(4.0));
		assertThat(rowMap.get(1).get(3), is(20.0));
		assertThat(rowMap.get(2).get(3), is(5.0));
		assertThat(rowMap.get(2).get(1), is(nullValue()));
		assertThat(rowMap.get(3), is(nullValue()));
	}

	@Test
	public void rowKeySet() {
		// when
		Set<Integer> rowKeySet = weightedGraph.rowKeySet();

		// then
		assertThat(rowKeySet.contains(1), is(true));
		assertThat(rowKeySet.contains(2), is(true));
		assertThat(rowKeySet.contains(3), is(false));
	}

	@Test
	public void row() {
		// when
		Map<Integer, Double> mapForRow1 = weightedGraph.row(1);
		Map<Integer, Double> mapForRow2 = weightedGraph.row(2);
		Map<Integer, Double> mapForRow3 = weightedGraph.row(3);

		// then
		assertThat(mapForRow1.get(2), is(4.0));
		assertThat(mapForRow1.get(3), is(20.0));
		assertThat(mapForRow2.get(3), is(5.0));
		assertThat(mapForRow2.get(1), is(nullValue()));
		assertThat(mapForRow3.isEmpty(), is(true));
	}

	@Test
	public void columnMap() {
		// when
		Map<Integer, Map<Integer, Double>> columnMap = weightedGraph
				.columnMap();

		// then
		assertThat(columnMap.get(2).get(1), is(4.0));
		assertThat(columnMap.get(2).get(3), is(nullValue()));
		assertThat(columnMap.get(3).get(1), is(20.0));
		assertThat(columnMap.get(3).get(2), is(5.0));
		assertThat(columnMap.get(1), is(nullValue()));
	}

	@Test
	public void columnKeySet() {
		// when
		Set<Integer> columnKeySet = weightedGraph.columnKeySet();

		// then
		assertThat(columnKeySet.contains(1), is(false));
		assertThat(columnKeySet.contains(2), is(true));
		assertThat(columnKeySet.contains(3), is(true));
	}

	@Test
	public void column() {
		// when
		Map<Integer, Double> mapForColumn1 = weightedGraph.column(1);
		Map<Integer, Double> mapForColumn2 = weightedGraph.column(2);
		Map<Integer, Double> mapForColumn3 = weightedGraph.column(3);

		// then
		assertThat(mapForColumn1.isEmpty(), is(true));
		assertThat(mapForColumn2.get(1), is(4.0));
		assertThat(mapForColumn2.get(3), is(nullValue()));
		assertThat(mapForColumn3.get(1), is(20.0));
		assertThat(mapForColumn3.get(2), is(5.0));
	}
}
