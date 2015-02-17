package org.eclipse.eatop.examples.tableview.ui;

import java.util.ArrayList;
import org.eclipse.eatop.examples.tableview.TableViewHelpers;

public class CommaSeparatedDisplayConverter extends GEatopDisplayConverter {
	
	@Override
	protected Object getConvertedMultiLine(ArrayList<Object> displayCollection) {
		return TableViewHelpers.toCommaSeparatedString(displayCollection);
	}
}
