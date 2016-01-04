package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import eu.synligare.sgraphml.GeometryType;

public class PolicyHelpers {
	static public Point calculateMovePortDelta(GeometryType groupnode, GeometryType port, ChangeBoundsRequest request, Rectangle constraint){

		Point moveDelta = new Point(0,0);

		//The request has screen coords
		Point screenCordsDelta = new Point(request.getSizeDelta().width, request.getSizeDelta().height);//new Point(request.getSizeDelta());
		Point modelCordsDelta = Utils.screenDelta2ModelDelta(screenCordsDelta);
		
		//calculate resize scale
		//sd is positive when rectangle has expanded, so subtracting the change gives the old size
		Rectangle r1 = constraint;
		double resizeScaleX = ((double)(r1.width)) / (r1.width - modelCordsDelta.x);
		double resizeScaleY = ((double)(r1.height)) / (r1.height - modelCordsDelta.y);
		
		
		//Which side is the port attached to?
		int dxW = (int)(port.getX() - groupnode.getX());
		int dxE = (int)(groupnode.getX() + groupnode.getWidth() - (port.getX() + port.getWidth()));
		int dyN = (int)(port.getY() - groupnode.getY());
		int dyS = (int)(port.getY() + groupnode.getHeight() - (groupnode.getY() + groupnode.getHeight()));
		
	
		int side;
		if (Math.abs(dxW) <= 2) {
	   		side = PositionConstants.WEST;
		}
		else if (Math.abs(dxE) <= 2) {
	   		side = PositionConstants.EAST;
		}
		else if (Math.abs(dyN) <= 2) {
	   		side = PositionConstants.NORTH;
		}
		else if (Math.abs(dyS) <= 2) {
			side = PositionConstants.SOUTH;
		}
		else{
			//This port is not attached to any side - don't move it
			return moveDelta;
		}
		
		//Is "my" edge moved due to resize? Then move port in same way.
		
		
		//Note: Positive size delta => side is moved in the direction that expands the rectangle.
		if ((request.getResizeDirection() & side) > 0){
			
			switch (side)
			{
				case PositionConstants.WEST:
					moveDelta.x = -modelCordsDelta.x;
					break;
				case PositionConstants.EAST:
					moveDelta.x = modelCordsDelta.x;
					break;
				case PositionConstants.NORTH:
					moveDelta.y = modelCordsDelta.y;
					break;
				case PositionConstants.SOUTH:
					moveDelta.y = -modelCordsDelta.y;
					break;
				}
		}

		//Is the groupnode resized in the dimension opposite to my edge? Then rescale.
	
		if ((side == PositionConstants.WEST) || (side == PositionConstants.EAST)){
			if (modelCordsDelta.y != 0){
				moveDelta.y = (int) Math.round((resizeScaleY - 1.0)* (port.getY() - groupnode.getY()));
			}
		}
		if ((side == PositionConstants.NORTH) || (side == PositionConstants.SOUTH)){
			if (modelCordsDelta.x != 0){
				moveDelta.x = (int) Math.round((resizeScaleX - 1.0) * (port.getX() - groupnode.getX()));
			}
		}
		
/*		System.out.println("resizeScaleX = " + resizeScaleX);
		System.out.println("resizeScaleY = " + resizeScaleY);
		System.out.println("moveDelta = " + moveDelta);		
*/		
		return moveDelta;
	}
}
