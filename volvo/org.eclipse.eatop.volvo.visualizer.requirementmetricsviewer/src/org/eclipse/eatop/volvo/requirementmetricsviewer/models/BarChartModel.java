package org.eclipse.eatop.volvo.requirementmetricsviewer.models;

import java.util.ArrayList;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;



/**
 * A model representing a bar chart
 * 
 * @author Joanna Svantesson
 *
 */
public class BarChartModel {

	private Rectangle bounds;
	
	private ArrayList<BarModel> barModels = new ArrayList<BarModel>();
	private ArrayList<ChartLabelModel> labels = new ArrayList<ChartLabelModel>();
	
	private ArrayList<Color> colors = new ArrayList<Color>();
	
	private int nrOfUnits = 0;
	
	/**
	 * 
	 * @param labels The labels for the semantics of the colors on a bar
 	 * @param labelColors The colors available for bars
	 * @param nrOfUnits The height of the y axis in <U>units</U>
	 */
	public BarChartModel(ArrayList<String> labels, ArrayList<Color> labelColors, int nrOfUnits) {
		if (labels.size() != labelColors.size()) {
			throw new IllegalArgumentException("List of labels not equal size as list of label colors");
		}
		
		bounds = new Rectangle(20, 20, 400, 200);
		

		for (int i = 0; i < labelColors.size(); i++) {
			colors.add(labelColors.get(i));
			
			this.labels.add(new ChartLabelModel(labels.get(i), labelColors.get(i)));
			
		}
		this.nrOfUnits = nrOfUnits;
	}
	
	public void addBar(String label, ArrayList<Integer> quantities, ArrayList<Color> barColors) {
		int sum = 0;
		for (Integer i : quantities) {
			sum += i;
		}
		if (sum > nrOfUnits) {
			throw new IllegalArgumentException("Can't add a bar larger than the chart.");
		}
		if (!(colors.containsAll(barColors))) {
			throw new IllegalArgumentException("Not all given bar colors are available in the bar chart");
		}
		if (quantities.size() != barColors.size()) {
			throw new IllegalArgumentException("List of quantities not equal size as list of bar colors");
		}
		
		barModels.add(new BarModel(this, label, quantities, barColors));
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public ArrayList<BarModel> getBars() {
		return barModels;
	}
	
	public ArrayList<ChartLabelModel> getLabels() {
		return labels;
	}
	
	public int getNrOfUnits() {
		return nrOfUnits;
	}
	
	/**
	 * A model representing a single bar in a bar chart
	 * 
	 * @author Joanna Svantesson
	 *
	 */
	public class BarModel {
		private BarChartModel parent; // TODO remove this if turns out not needed
		
		private String barLabel = "";
		private ArrayList<Integer> quantities = new ArrayList<Integer>();
		private ArrayList<Color> colors = new ArrayList<Color>();
		
		public BarModel(BarChartModel parent, ArrayList<Integer> quantities, ArrayList<Color> barColors) {
			for (Integer q : quantities) {
				this.quantities.add(q);
			}
			for (Color c : barColors) {
				this.colors.add(c);
			}
			
			this.parent = parent;
		}
		
		public BarModel(BarChartModel parent, String label, ArrayList<Integer> quantities, ArrayList<Color> barColors) {
			this(parent, quantities, barColors);
			barLabel = label;
		}
		
		public String getBarLabel() {
			return barLabel;
		}
		
		public ArrayList<Integer> getQuantities() {
			return quantities;
		}
		
		public ArrayList<Color> getColors() {
			return colors;
		}
		
		public BarChartModel getParent() {
			return parent;
		}
	}
}
