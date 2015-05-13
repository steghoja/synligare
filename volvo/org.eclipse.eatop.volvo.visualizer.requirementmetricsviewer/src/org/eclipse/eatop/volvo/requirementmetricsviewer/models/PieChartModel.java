package org.eclipse.eatop.volvo.requirementmetricsviewer.models;

import java.util.ArrayList;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.requirementmetricsviewer.ChartColor;
import org.eclipse.swt.graphics.Color;

/**
 * A model of a Pie Chart with corresponding labels.
 * If size and position are not specified it will use a standard position (20,20) 
 * and diameter 200.
 * 
 * @author Joanna Svantesson
 *
 */
public class PieChartModel {
	
	private ArrayList<PiePieceModel> pieces;
	private ArrayList<ChartLabelModel> labelModels;
	
	private ArrayList<String> labels;
	private ArrayList<Color> colors;
	 
	private Rectangle bounds = new Rectangle(20, 20, 200, 200);
	
	/**
	 * Creates a "dummy" model with one red piece, standard bounds and no labels
	 */
	public PieChartModel() {
		pieces = new ArrayList<PiePieceModel>(1);
		pieces.add(new PiePieceModel(bounds));
	}
	
	/**
	 * Creates a circle diagram with the proportions <code>proportions</code>, no labels, 
	 * standard bounds and generated colors.
	 * 
	 * @param proportions
	 */
	public PieChartModel(ArrayList<Double> proportions) {
		checkProportions(proportions);
		createPieces(proportions);
	}
	
	/**
	 * Creates a circle diagram with the proportions <code>proportions</code>, labels <code>labels</code> 
	 * standard bounds and generated colors.
	 * 
	 * @param proportions
	 * @param labels
	 */
	public PieChartModel(ArrayList<Double> proportions, ArrayList<String> labels) {
		checkProportions(proportions);
		checkSameSize(proportions, labels);
		this.labels = new ArrayList<String>(labels.size());
		
		for (String l : labels) {
			this.labels.add(l);
		}
				
		createPieces(proportions);
		createLabels();
	}
	
	/**
	 * Creates a circle diagram with the proportions <code>proportions</code>, labels <code>labels</code>, 
	 * colors <code>colors</code> and standard bounds.
	 * 
	 * @param proportions
	 * @param labels
	 * @param colors
	 */
	public PieChartModel(ArrayList<Double> proportions, ArrayList<String> labels, 
			ArrayList<Color> colors) {
		checkProportions(proportions);
		checkSameSize(proportions, labels);
		checkSameSize(proportions, colors);
		
		this.labels = new ArrayList<String>(labels.size());
		this.colors = new ArrayList<Color>(colors.size());
		
		for (String l : labels) {
			this.labels.add(l);
		}
		for (Color c : colors) {
			this.colors.add(c);
		}
				
		createPieces(proportions);
		createLabels();
	}
	
	/**
	 * Creates a circle diagram with the proportions <code>proportions</code>, labels <code>labels</code>, 
	 * colors <code>colors</code> and of size <code>diameter</code> in diameter at position 
	 * (<code>pos_x</code>, <code>pos_y</code>)
	 * 
	 * @param proportions
	 * @param labels
	 * @param colors
	 * @param diameter
	 * @param pos_x
	 * @param pos_y
	 */
	public PieChartModel(ArrayList<Double> proportions, ArrayList<String> labels, 
			ArrayList<Color> colors, int diameter, int pos_x, int pos_y) {
		checkProportions(proportions);
		checkSameSize(proportions, labels);
		checkSameSize(proportions, colors);
		
		this.labels = new ArrayList<String>(labels.size());
		this.colors = new ArrayList<Color>(colors.size());
		
		for (String l : labels) {
			this.labels.add(l);
		}
		for (Color c : colors) {
			this.colors.add(c);
		}
				
		bounds.setSize(diameter, diameter);
		bounds.setX(pos_x);
		bounds.setY(pos_y);
		
		createPieces(proportions);
		createLabels();
	}
	
	/**
	 * Checks that the proportions add up to 100 %
	 * 
	 * @param proportions
	 */
	private void checkProportions(ArrayList<Double> proportions) {
		double sum = 0;
		for (int i = 0; i < proportions.size(); i++) {
			sum += proportions.get(i);
		}
		
		if (sum != 100.0) {
			throw new IllegalArgumentException("Proportions of a circle "
					+ "diagram must sum up to 100 %");
		}
	}
	
	/**
	 * Checks that the lists a and b are of the same length
	 * 
	 * @param a
	 * @param b
	 */
	private void checkSameSize(ArrayList a, ArrayList b) {
		if (a.size() != b.size()) {
			throw new IllegalArgumentException("Input arrays not the same size");
		}
	}
	
	private void createPieces(ArrayList<Double> proportions) {
		pieces = new ArrayList<PiePieceModel>(proportions.size());
		int startAngle = 90; // Want to start at the top (12 o'clock)
		
		for (int i = 0; i < proportions.size(); i++) {
			int angle = getAngle(proportions.get(i).doubleValue());
			if (colors == null) {
				generateColors(proportions.size());
			}
			pieces.add(new PiePieceModel(colors.get(i), angle, startAngle, bounds));
			startAngle = (startAngle + angle)%360;
		}
	}
	
	private void createLabels() {
		labelModels = new ArrayList<ChartLabelModel>(labels.size());
		int x = bounds.getTopRight().x() + 40;
		int y = bounds.getTop().y() + 20;
		for (int i = 0; i < labels.size(); i++) {
			ChartLabelModel clm = new ChartLabelModel(labels.get(i), colors.get(i), x, y);
			labelModels.add(clm);
			y += clm.getBounds().height() + 15; 
		}
	}
	
	private int getAngle(double proportion) {
		return (int)Math.round(3.6 * proportion);
	}
	
	public ArrayList<PiePieceModel> getPieces() {
		return pieces;
	}
	
	public ArrayList<ChartLabelModel> getLabelModels() {
		return labelModels;
	}
	
	public int getNoOfSections() {
		return pieces.size();
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	/**
	 * Generate colors and fill the array <code>colors</code> of size <code>size</code>
	 * 
	 * @param size
	 */
	private void generateColors(int size) {
		
		// TODO Now colors are generated at a maximum of 8 colors and then the same colors will 
		// appear. Might want to generate random other colors.
			
		colors = new ArrayList<Color>(size);

		for (int i = 0; i < size; i++) {
			colors.add(ChartColor.getColor(i));
		}
		
	}
	
}
