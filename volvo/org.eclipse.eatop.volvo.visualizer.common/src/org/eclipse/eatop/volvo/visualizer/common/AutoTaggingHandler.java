package org.eclipse.eatop.volvo.visualizer.common;

import java.util.ArrayList;

import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Environment;
import org.eclipse.eatop.eastadl21.FeatureModel;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.ImplementationLevel;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.Timing;
import org.eclipse.eatop.eastadl21.UserAttributedElement;
import org.eclipse.eatop.eastadl21.Variability;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Handler for creating visual tags for elements in a model
 * automatically.
 * It will tag elements to be levels "VehicleLevel", "AnalysisLevel", "DesignLevel" or
 * "ImplementationLevel" and categories "EnvironmentalModel", "SystemModel", "Requirements",
 * "Variability", "Timing" or "Dependability" depending on what type the element is and
 * naming of packages.
 * 
 * @author Joanna Svantesson
 *
 */
public class AutoTaggingHandler extends VisualTagsHandler { 

	private ArrayList<String> vehicleStrings = new ArrayList<String>();
	private ArrayList<String> analysisStrings = new ArrayList<String>();
	private ArrayList<String> designStrings = new ArrayList<String>();
	private ArrayList<String> implementationStrings = new ArrayList<String>();


	public enum AbstractionLevel {
		VehicleLevel {
			public String toString() {
				return "VehicleLevel";
			}
		},
		AnalysisLevel {
			public String toString() {
				return "AnalysisLevel";
			}
		},
		DesignLevel {
			public String toString() {
				return "DesignLevel";
			}
		},
		ImplementationLevel {
			public String toString() {
				return "ImplementationLevel";
			}
		};

		public abstract String toString();
	}

	public enum Category {
		EnvironmentModel {
			public String toString() {
				return "EnvironmentModel";
			}
		},
		SystemModel {
			public String toString() {
				return "SystemModel";
			}
		},
		Requirements {
			public String toString() {
				return "Requirements";
			}
		},
		Variability {
			public String toString() {
				return "Variability";
			}
		}, 
		Timing {
			public String toString() {
				return "Timing";
			}
		},
		Dependability {
			public String toString() {
				return "Dependability";
			}
		};

		public abstract String toString();
	}

	public AutoTaggingHandler() {
		super();
		vehicleStrings.add("vehicleLevel");
		analysisStrings.add("analysisLevel");
		designStrings.add("designLevel");
		implementationStrings.add("implementationLevel");
	}

	public void createAutoTags(EditingDomain ed, EAXML root, String tagsPackageName) {	
		this.ed = ed;
		this.root = root;
		// Create packages
		createPackages(ed, root, tagsPackageName);
		// Go through all elements in the model and choose the ones to tag with appropriate tags
		for (EAPackage tp : root.getTopLevelPackage()) {
			recursiveCreateAutoTags(tp);
		}
	}

	private void recursiveCreateAutoTags(EAPackage eaPackage) {
		if (eaPackage.equals(tagsPackage)) {
			return;
		}

		AbstractionLevel level = null;
		if (packageNameContains(eaPackage, vehicleStrings)) {
			level = AbstractionLevel.VehicleLevel;
		} else if (packageNameContains(eaPackage, analysisStrings)) {
			level = AbstractionLevel.AnalysisLevel;
		} else if (packageNameContains(eaPackage, designStrings)) {
			level = AbstractionLevel.DesignLevel;
		} else if (packageNameContains(eaPackage, implementationStrings)) {
			level = AbstractionLevel.ImplementationLevel;
		}

		for (EAElement e : eaPackage.getElement()) {

			if (e instanceof Environment) {
				if (level == null) level = decideLevel(e); 
				// If element already has a tag we will not create a new one
				if (!elementHasTag(e))  {
					createTagForElement(e, Category.EnvironmentModel.toString(), level.toString());
				}
			} else if (e instanceof SystemModel) {
				// This is a special case. Find the elements of the SystemModel
				// and set their category and level 
				SystemModel sm = (SystemModel) e;

				VehicleLevel vl = sm.getVehicleLevel();
				if (vl != null) {
					// If element already has a tag we will not create a new one
					if (!elementHasTag(vl))  {
						createTagForElement(vl, Category.SystemModel.toString(), 
								AbstractionLevel.VehicleLevel.toString());
					}

					// Get the TechnicalFeatureModel(s)
					EList<FeatureModel> featureModels = vl.getTechnicalFeatureModel();	
					for (FeatureModel fm : featureModels) {
						// If element already has a tag we will not create a new one
						if (!elementHasTag(fm))  {
							createTagForElement(fm, Category.SystemModel.toString(), 
									AbstractionLevel.VehicleLevel.toString());
						}
					}

				}

				AnalysisLevel al = sm.getAnalysisLevel();
				if (al != null) {
					// If element already has a tag we will not create a new one
					if (!elementHasTag(al))  {
						createTagForElement(al, Category.SystemModel.toString(), 
								AbstractionLevel.AnalysisLevel.toString());
					}
					// Get the FAA and create a tag for it
					AnalysisFunctionPrototype FAA = al.getFunctionalAnalysisArchitecture();
					if (FAA != null) {
						// If element already has a tag we will not create a new one
						if (!elementHasTag(FAA))  {
							createTagForElement(FAA, Category.SystemModel.toString(), 
									AbstractionLevel.AnalysisLevel.toString());
						}
					}

				}

				DesignLevel dl = sm.getDesignLevel();
				if (dl != null) {
					// If element already has a tag we will not create a new one
					if (!elementHasTag(dl))  {
						createTagForElement(dl, Category.SystemModel.toString(), 
								AbstractionLevel.DesignLevel.toString());
					}

					DesignFunctionPrototype FDA = dl.getFunctionalDesignArchitecture();
					if (FDA != null) {
						// If element already has a tag we will not create a new one
						if (!elementHasTag(FDA))  {
							createTagForElement(FDA, Category.SystemModel.toString(), 
									AbstractionLevel.DesignLevel.toString());
						}
					}
					HardwareComponentPrototype HDA = dl.getHardwareDesignArchitecture();
					if (HDA != null) {
						// If element already has a tag we will not create a new one
						if (!elementHasTag(HDA))  {
							createTagForElement(HDA, Category.SystemModel.toString(), 
									AbstractionLevel.DesignLevel.toString());
						}
					}
					EList<Allocation> allocations = dl.getAllocation();	
					for (Allocation a : allocations) {
						// If element already has a tag we will not create a new one
						if (!elementHasTag(a))  {
							createTagForElement(a, Category.SystemModel.toString(), 
									AbstractionLevel.DesignLevel.toString());
						}
					}
				}

				ImplementationLevel il = sm.getImplementationLevel();
				if (il != null) {
					// If element already has a tag we will not create a new one
					if (!elementHasTag(il))  {
						createTagForElement(il, Category.SystemModel.toString(), 
								AbstractionLevel.ImplementationLevel.toString());
					}
				}
			} else if (e instanceof RequirementsModel) {
				if (level == null) level = decideLevel(e); 
				// If element already has a tag we will not create a new one
				if (!elementHasTag(e))  {
					createTagForElement(e, Category.Requirements.toString(), level.toString());
				}
			} else if (e instanceof Variability) {
				if (level == null) level = decideLevel(e); 
				// If element already has a tag we will not create a new one
				if (!elementHasTag(e))  {
					createTagForElement(e, Category.Variability.toString(), level.toString());
				}
			} else if (e instanceof Timing) {
				if (level == null) level = decideLevel(e); 
				// If element already has a tag we will not create a new one
				if (!elementHasTag(e))  {
					createTagForElement(e, Category.Timing.toString(), level.toString());
				}
			} else if (e instanceof Dependability) {
				if (level == null) level = decideLevel(e); 
				// If element already has a tag we will not create a new one
				if (!elementHasTag(e))  {
					createTagForElement(e, Category.Dependability.toString(), level.toString());
				}
			}

		}

		for (EAPackage sp : eaPackage.getSubPackage()) {
			recursiveCreateAutoTags(sp);
		}
	}

	private AbstractionLevel decideLevel(EObject eObject) {
		// TODO Implement some smart way to decide which level
		// an element belongs to.
		return AbstractionLevel.DesignLevel;
	}

	private boolean elementHasTag(EAElement element) {
		for (EAElement ea : taggedElementsPackage.getElement()) {
			if (ea instanceof UserAttributedElement) {
				if (((Identifiable)element).equals(((UserAttributedElement)ea).getAttributedElement())) {
					return true;
				}
			}
		}
		return false;
	}
}
