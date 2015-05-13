package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.IOException;

import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.SimulinkSystem;


public class Block {

	
	String blockType;
	String name;
	Subsystem containerSubsystem;		//null on top-level system
	SimulinkSystem containerSystem;
	static BufferedWriter bw;
	int layoutColumn;				//0 is first column
	int layoutRow;					//0 is first row
	Point topLeftPoint;
	
	int width;
	int heigth;
	
	
	
	public Block(String type) {

	blockType = type;
	layoutColumn = 0;
	layoutRow = 0;
	topLeftPoint = new Point();
	width = 50;
	heigth = 50;
	
}
	public Block() {

}

	
		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//if (getClass() != obj.getClass())
		//	return false;
		Block other = (Block) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	public String getBlockType() {
		return blockType;
	}
	public void setBlockType(String blockType) {
		this.blockType = blockType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	

	public Subsystem getContainerSubsystem() {
		return containerSubsystem;
	}
	public void setContainerSubsystem(Subsystem parent) {
		this.containerSubsystem = parent;
	}
	
		
	
	public SimulinkSystem getContainerSystem() {
		return containerSystem;
	}
	public void setContainerSystem(SimulinkSystem containerSystem) {
		this.containerSystem = containerSystem;
	}
	
	public int getLayoutColumn() {
		return layoutColumn;
	}
	public void setLayoutColumn(int layoutColumn) {
		this.layoutColumn = layoutColumn;
		setTopLeftPoint();
	}
	public Point getTopLeftPoint() {
		return topLeftPoint;
	}
	public void setTopLeftPoint(Point topLeftPoint) {
		this.topLeftPoint = topLeftPoint;

		layoutColumn = topLeftPoint.x/200;
		layoutRow = topLeftPoint.y/100;
	}

	public void setTopLeftPoint() {
		topLeftPoint.setLocation(layoutColumn * 200, layoutRow * 100);
	}

	public void setPosition(int x0, int y0, int x1, int y1){
		Point tl = new Point(x0, y0);
		setTopLeftPoint(tl);
		
		heigth = y1-y0;
		width = x1-x0;
	
	}
	
	
	//Write functions
	public static void setBufferedWriter(BufferedWriter bw) {
		Block.bw = bw;
	}
	public void Write(BufferedWriter bw) throws IOException{
		writeLn("base class block write, shall not occurr. blocktype = " + blockType);
		
	}
		
	protected void writeLn(String s) throws IOException{
		bw.write(s + "\n");
	}

	protected void write(String s) throws IOException{
		bw.write(s);
	}

	
	public String getFullPath(){
		
		String s;
		
		if (containerSubsystem == null){
			s = getContainerSystem().getName() + "/" + name;
		}
		else {
			s = getContainerSubsystem().getFullPath() + "/" + name; 
		}
		
		return s;
	}
	
	//Default port to "1"
	//Useful for all blocks that only has one port, usually identified as "1"
	public String getPortId() {
		return "1";
	}
	
	
	//default width
	public int GetWidth(){
		return width;
	}

	//default height
	public int GetHeight(){
		return heigth;
	}
	
	public String GetPositionVector()
	{
		String s = "[" + topLeftPoint.x + " " + topLeftPoint.y + " " + (topLeftPoint.x + GetWidth()) + " " + (topLeftPoint.y + GetHeight()) + "]"; 
		return s;
	}
	public void setSquare(Point square) {
		
		layoutRow = square.x;
		layoutColumn = square.y;
		setTopLeftPoint();
	}
	
	
	
}
