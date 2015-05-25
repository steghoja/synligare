package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public class GridLayout {
	
	int nGridPositionX;
	int nMaxHeightInGridRow;
	Point currentLocation;
	Point topLeft = new Point(0,0);
	
	Point bottomRight = new Point(0,0);
	int nMaxGridColumns;
	
			
	/***
	 * Simple grid layout, although column width is not fixed.
	 * 
	 * @param size
	 */
	public void calculateNextGridPosition(Dimension size) {
		//currentgridLocation;

		if (size.equals(0, 0)) return;
		
		if ((nGridPositionX + 1) == nMaxGridColumns){
			//step down
			bottomRight.x = Math.max(bottomRight.x, currentLocation.x + size.width);
			bottomRight.y = Math.max(bottomRight.y, currentLocation.y + size.height);

			currentLocation.y = currentLocation.y + nMaxHeightInGridRow + 100;
			currentLocation.x = topLeft.x;
			nGridPositionX = 0;
			nMaxHeightInGridRow = 0;
		}
		else {
			//step right
			
			bottomRight.x = Math.max(bottomRight.x, currentLocation.x + size.width);
			bottomRight.y = Math.max(bottomRight.y, currentLocation.y + size.height);
			
			currentLocation.x = currentLocation.x + size.width + 100;
			nGridPositionX++;
			nMaxHeightInGridRow = Math.max(nMaxHeightInGridRow, size.height);
		}
	}

	
	public void reset(Point topLeft){
		this.topLeft = new Point(topLeft);
		this.bottomRight = new Point(topLeft);

		this.currentLocation= new Point(topLeft);

		nGridPositionX = 0;
		nMaxHeightInGridRow = 0;
	}
	
	public Point getCurrentLocation(){
		return new Point(currentLocation);
	}
	
	/***
	 * Current Size (i.e before calculating next grid position)
	 * @return
	 */
	public Dimension getCurrentSize(){
		return new Dimension(bottomRight.getDifference(topLeft));
	}

	public void setColumns(int nColumns) {
		// TODO Auto-generated method stub
		nMaxGridColumns = nColumns;
	}
	
}
