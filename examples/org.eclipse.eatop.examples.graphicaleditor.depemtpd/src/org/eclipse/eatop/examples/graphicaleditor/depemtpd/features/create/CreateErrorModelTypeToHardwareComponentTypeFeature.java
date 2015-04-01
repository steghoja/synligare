package org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.create;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateErrorModelTypeToHardwareComponentTypeFeature extends
		AbstractCreateConnectionFeature {

	public CreateErrorModelTypeToHardwareComponentTypeFeature(
			IFeatureProvider fp) {
		super(fp, "ErrorModelType To HardwareComponentType",
				"Create ErrorModelType To HardwareComponentType");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// return true if both anchors belong to an EClass
		// and those EClasses are not identical
		ErrorModelType source = getErrorModelType(context.getSourceAnchor());
		HardwareComponentType target = getHardwareComponentType(context
				.getTargetAnchor());
		if (source != null && target != null && source != target) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a EClass
		if (getErrorModelType(context.getSourceAnchor()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;

		// get EClasses which should be connected
		ErrorModelType errorModelType = getErrorModelType(context
				.getSourceAnchor());
		HardwareComponentType HardwareComponentType = getHardwareComponentType(context
				.getTargetAnchor());
		Assert.isNotNull(errorModelType);
		Assert.isNotNull(HardwareComponentType);

		if (errorModelType != null && HardwareComponentType != null) {

			EReference referenceId = Eastadl21Factory.eINSTANCE
					.getEastadl21Package().getErrorModelType_HwTarget();
			DiagramUtil.addReferenceToBOResource(errorModelType, referenceId,
					HardwareComponentType);

			AddConnectionContext addContext = new AddConnectionContext(
					context.getSourceAnchor(), context.getTargetAnchor());
			newConnection = (Connection) getFeatureProvider().addIfPossible(
					addContext);
		}

		return newConnection;
	}

	private HardwareComponentType getHardwareComponentType(Anchor targetAnchor) {
		if (targetAnchor != null) {
			Object object = getBusinessObjectForPictogramElement(targetAnchor
					.getParent());
			if (object instanceof HardwareComponentType) {
				return (HardwareComponentType) object;
			}
		}
		return null;
	}

	private ErrorModelType getErrorModelType(Anchor sourceAnchor) {
		if (sourceAnchor != null) {
			Object object = getBusinessObjectForPictogramElement(sourceAnchor
					.getParent());
			if (object instanceof ErrorModelType) {
				return (ErrorModelType) object;
			}
		}
		return null;
	}

}
