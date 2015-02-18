package org.eclipse.eatop.examples.tableview.ui;

import java.util.List;

import org.eclipse.eatop.examples.tableview.TableViewHelpers;

public class CommaSeparatedDisplayConverter extends GEatopDisplayConverter {
	
	@Override
	protected String getConvertedMultiLine(List<Object> displayCollection) {
		return TableViewHelpers.toCommaSeparatedString(displayCollection);
	}
}
