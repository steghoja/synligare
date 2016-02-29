package org.eclipse.eatop.examples.tableview.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.eclipse.eatop.examples.tableview.accessors.IEObjectAccessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.sort.ISortModel;
import org.eclipse.nebula.widgets.nattable.sort.SortDirectionEnum;

import ca.odell.glazedlists.SortedList;

public class SortModel extends Observable implements ISortModel {

	private SortedList<EObject> sortedList;
	private IEObjectAccessor accessor;
	private Map<Integer, Boolean> isSorted;
	private Map<Integer, SortDirectionEnum> sortDirections;


	public SortModel(SortedList<EObject> sortedList) {
		this.sortedList = sortedList;
		this.isSorted = new HashMap<Integer, Boolean>();
		this.sortDirections = new HashMap<Integer, SortDirectionEnum>();
	}

	@Override
	public List<Integer> getSortedColumnIndexes() {
		List<Integer> sortedColumns = new LinkedList<Integer>();
		for (Integer index : isSorted.keySet()) {
			if(isColumnIndexSorted(index)) sortedColumns.add(index);
		}
		return sortedColumns;
	}

	@Override
	public boolean isColumnIndexSorted(int columnIndex) {
		boolean sorted = false;
		if (isSorted.containsKey(columnIndex)) {
			sorted = isSorted.get(columnIndex);
		} else {
			isSorted.put(columnIndex, sorted);
		}
		return sorted;
	}

	@Override
	public SortDirectionEnum getSortDirection(int columnIndex) {
		SortDirectionEnum direction = SortDirectionEnum.NONE;
		if (sortDirections.containsKey(columnIndex)) {
			direction = sortDirections.get(columnIndex);
		} else {
			sortDirections.put(columnIndex, direction);
		}
		return direction;
	}

	@Override
	public int getSortOrder(int columnIndex) {
		// this implementation of SortModel handles only one sorting for the whole table
		return 0;
	}

	@SuppressWarnings("rawtypes")
	private List<Comparator> getComparatorsForColumnIndex(int columnIndex, SortDirectionEnum sortDirection) {
		// this implementation of SortModel handles only one sorting, each column should only hold one comparator since they are the same type.
		List<Comparator> list = new ArrayList<Comparator>();
		list.add(accessor.getComparator(columnIndex, getModifier(sortDirection)));
		return list;
	}


	private int getModifier(SortDirectionEnum sortDirection) {
		switch (sortDirection) {
		case NONE:
		case DESC: return -1;
		case ASC: return 1;
		default: return -1;
		}
	}

	/**
	 * This is not implemented.
	 * @see #getComparatorsForColumnIndex(int, SortDirectionEnum))
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Comparator> getComparatorsForColumnIndex(int columnIndex) { return null;}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void sort(int columnIndex, SortDirectionEnum sortDirection, /* this value is ignored, not relevant for this implementation */ boolean accumulate) {
		clear();
		sortedList.getReadWriteLock().writeLock().lock();
		try {
			List<Comparator> comparatorsForColumnIndex = getComparatorsForColumnIndex(columnIndex, sortDirection);
			Comparator<EObject> comparator = null;
			if (!comparatorsForColumnIndex.isEmpty()) {
				comparator = (Comparator<EObject>) comparatorsForColumnIndex.get(0);
			}
			sortedList.setComparator(comparator);
			isSorted.put(columnIndex, true);
			sortDirections.put(columnIndex, sortDirection);
			setChanged();
		} finally {
			sortedList.getReadWriteLock().writeLock().unlock();
		}
		notifyObservers();
	}


	@Override
	public void clear() {
		isSorted.clear();
		sortDirections.clear();
	}

	public void setAccessor(IEObjectAccessor newAccessor) {
		this.accessor = newAccessor;
	}

	@Override
	public Comparator<?> getColumnComparator(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
