package org.eclipse.eatop.examples.contextview.providers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.eastadl21.SafetyConstraint;
import org.eclipse.eatop.examples.common.ui.providers.TypeNameLabelDecorator;
import org.eclipse.eatop.examples.explorer.AppearanceExampleExplorerLabelProvider;
import org.eclipse.eatop.examples.explorer.internal.Activator.Implementation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

public class ContextViewLabelProvider extends AppearanceExampleExplorerLabelProvider {

	private boolean showPaths;
	private boolean showTypes;

	private TypeNameLabelDecorator typeNameLabelDecorator;

	private Image arrowLeft = Implementation.getImageDescriptor("icons/full/obj16/arrow_left.gif").createImage();
	private Image arrowRight = Implementation.getImageDescriptor("icons/full/obj16/arrow_right.gif").createImage();
	private Image instRef = Implementation.getImageDescriptor("icons/full/obj16/instref.png").createImage();
	private Image relation = Implementation.getImageDescriptor("icons/full/obj16/type.gif").createImage();

	private final static Map<String, String> relationshipLabels;
	static {
		Map<String, String> aMap = new HashMap<>();
		aMap.put("Satisfy_satisfiedBy", "Satisfied by");
		aMap.put("satisfiedRequirement", "Satisfies");
		aMap.put("Realization_realizedBy", "Realized by");
		aMap.put("Realization_realized", "Realizes");
		aMap.put("derived", "Derived");
		aMap.put("derivedFrom", "Derived from");
		aMap.put("refinedRequirement", "Refines");
		aMap.put("Refine_refinedBy", "Refined by");
		aMap.put("verifiedByCase", "Verified by case");
		aMap.put("verifiedByProcedure", "Verified by procedure");
		aMap.put("verifiedRequirement", "Verifies");
		aMap.put("target", "Links to");
		aMap.put("source", "Links to");
		relationshipLabels = Collections.unmodifiableMap(aMap);
	}

	public ContextViewLabelProvider() {
		typeNameLabelDecorator = new TypeNameLabelDecorator();
	}

	@Override
	public String getText(Object element) {
		String str = super.getUndecoratedText(element);
		if (element instanceof TextOnlyNode) {
			if (relationshipLabels.containsKey(element.toString())) {
				str = relationshipLabels.get(element.toString());
			} else {
				str = element.toString();
			}
		}
		if (showTypes) {
			str = typeNameLabelDecorator.decorateText(str, element);
		}
		if (element instanceof EObject) {
			EReference containmentFeature = ((EObject) element).eContainmentFeature();
			if (containmentFeature != null) {
				EStructuralFeature nameFeature = containmentFeature.eClass().getEStructuralFeature("name");
				if (nameFeature != null) {
					str += " [" + containmentFeature.eGet(nameFeature) + "]";
				}
			}
		}
		if (element instanceof EObject && showPaths) {
			str += " [" + ModelSearcher.getPathTo((EObject) element) + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return str;
	}

	@Override
	public StyledString getStyledText(Object element) {
		StyledString ss = new StyledString(getText(element));
		if (element instanceof SafetyConstraint) {
			SafetyConstraint safetyConstraint = (SafetyConstraint) element;
			ss.append(" " + safetyConstraint.getAsilValue().getLiteral(), new Styler() {
				@Override
				public void applyStyles(final TextStyle textStyle) {
					Font boldFont = new Font(Display.getDefault(), new FontData("Segoe UI", 9, SWT.BOLD));
					textStyle.font = boldFont;
				}
			});

		}
		return ss;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof TextOnlyNode) {
			String imageName = ((TextOnlyNode) element).getImageName();
			Image img = null;
			if (imageName.equals("right")) {
				img = arrowRight;
			} else if (imageName.equals("left")) {
				img = arrowLeft;
			} else if (imageName.equals("instref")) {
				img = instRef;
			} else if (imageName.equals("relation")) {
				img = relation;
			}
			if (img != null) {
				return img;
			}
		}
		return super.getImage(element);
	}

	public void setShowPaths(boolean show) {
		showPaths = show;
	}

	public void setShowTypes(boolean show) {
		showTypes = show;
	}

	@Override
	public void dispose() {
		if (arrowLeft != null) {
			arrowLeft.dispose();
		}
		if (arrowRight != null) {
			arrowRight.dispose();
		}
		if (instRef != null) {
			instRef.dispose();
		}
		if (relation != null) {
			relation.dispose();
		}
	}
}
