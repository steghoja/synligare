/**
 */
package eu.synligare.sgraphml.provider;


import eu.synligare.sgraphml.ResourceType;
import eu.synligare.sgraphml.SgraphmlFactory;
import eu.synligare.sgraphml.SgraphmlPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.graphdrawing.graphml.xmlns.XmlnsFactory;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;

import org.graphdrawing.graphml.xmlns.provider.SgraphmlEditPlugin;

/**
 * This is the item provider adapter for a {@link eu.synligare.sgraphml.ResourceType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourceTypeItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceTypeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addFormatPropertyDescriptor(object);
			addIdPropertyDescriptor(object);
			addTypePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Format feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFormatPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ResourceType_format_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ResourceType_format_feature", "_UI_ResourceType_type"),
				 SgraphmlPackage.Literals.RESOURCE_TYPE__FORMAT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ResourceType_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ResourceType_id_feature", "_UI_ResourceType_type"),
				 SgraphmlPackage.Literals.RESOURCE_TYPE__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ResourceType_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ResourceType_type_feature", "_UI_ResourceType_type"),
				 SgraphmlPackage.Literals.RESOURCE_TYPE__TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ResourceType.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ResourceType"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		Object labelValue = ((ResourceType)object).getId();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ?
			getString("_UI_ResourceType_type") :
			getString("_UI_ResourceType_type") + " " + label;
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ResourceType.class)) {
			case SgraphmlPackage.RESOURCE_TYPE__FORMAT:
			case SgraphmlPackage.RESOURCE_TYPE__ID:
			case SgraphmlPackage.RESOURCE_TYPE__TYPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case SgraphmlPackage.RESOURCE_TYPE__MIXED:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT,
					 "")));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT,
					 "")));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION,
					 XMLTypeFactory.eINSTANCE.createProcessingInstruction())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA,
					 "")));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__DATA,
					 XmlnsFactory.eINSTANCE.createDataType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__DEFAULT,
					 XmlnsFactory.eINSTANCE.createDefaultType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__DESC,
					 "")));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__EDGE,
					 XmlnsFactory.eINSTANCE.createEdgeType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__ENDPOINT,
					 XmlnsFactory.eINSTANCE.createEndpointType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPH,
					 XmlnsFactory.eINSTANCE.createGraphType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPHML,
					 XmlnsFactory.eINSTANCE.createGraphmlType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__HYPEREDGE,
					 XmlnsFactory.eINSTANCE.createHyperedgeType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__KEY,
					 XmlnsFactory.eINSTANCE.createKeyType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__LOCATOR,
					 XmlnsFactory.eINSTANCE.createLocatorType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__NODE,
					 XmlnsFactory.eINSTANCE.createNodeType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(XmlnsPackage.Literals.DOCUMENT_ROOT__PORT,
					 XmlnsFactory.eINSTANCE.createPortType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(SgraphmlPackage.Literals.DOCUMENT_ROOT__GROUP_NODE,
					 SgraphmlFactory.eINSTANCE.createGroupNodeType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(SgraphmlPackage.Literals.DOCUMENT_ROOT__POLY_LINE_EDGE,
					 SgraphmlFactory.eINSTANCE.createPolyLineEdgeType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(SgraphmlPackage.Literals.DOCUMENT_ROOT__PORT_NODE,
					 SgraphmlFactory.eINSTANCE.createPortNodeType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(SgraphmlPackage.Literals.DOCUMENT_ROOT__RESOURCES,
					 SgraphmlFactory.eINSTANCE.createResourceBlockType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(SgraphmlPackage.Literals.DOCUMENT_ROOT__SCALED_ICON,
					 SgraphmlFactory.eINSTANCE.createScaledIconType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(SgraphmlPackage.Literals.DOCUMENT_ROOT__SHAPE_NODE,
					 SgraphmlFactory.eINSTANCE.createShapeNodeType())));

		newChildDescriptors.add
			(createChildParameter
				(SgraphmlPackage.Literals.RESOURCE_TYPE__MIXED,
				 FeatureMapUtil.createEntry
					(SgraphmlPackage.Literals.DOCUMENT_ROOT__SHAPE_NODE,
					 SgraphmlFactory.eINSTANCE.createGroupNodeType())));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		if (childFeature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)childFeature)) {
			FeatureMap.Entry entry = (FeatureMap.Entry)childObject;
			childFeature = entry.getEStructuralFeature();
			childObject = entry.getValue();
		}

		boolean qualify =
			childFeature == SgraphmlPackage.Literals.DOCUMENT_ROOT__GROUP_NODE ||
			childFeature == SgraphmlPackage.Literals.DOCUMENT_ROOT__SHAPE_NODE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return SgraphmlEditPlugin.INSTANCE;
	}

}
