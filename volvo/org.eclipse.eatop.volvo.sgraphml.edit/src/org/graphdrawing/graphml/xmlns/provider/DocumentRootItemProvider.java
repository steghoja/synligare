/**
 */
package org.graphdrawing.graphml.xmlns.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

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

import org.graphdrawing.graphml.xmlns.DocumentRoot;
import org.graphdrawing.graphml.xmlns.XmlnsFactory;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;

/**
 * This is the item provider adapter for a {@link org.graphdrawing.graphml.xmlns.DocumentRoot} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DocumentRootItemProvider 
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
	public DocumentRootItemProvider(AdapterFactory adapterFactory) {
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

			addDescPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Desc feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DocumentRoot_desc_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DocumentRoot_desc_feature", "_UI_DocumentRoot_type"),
				 XmlnsPackage.Literals.DOCUMENT_ROOT__DESC,
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
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__DATA);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__DEFAULT);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__EDGE);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__ENDPOINT);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPH);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPHML);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__HYPEREDGE);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__KEY);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__LOCATOR);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__NODE);
			childrenFeatures.add(XmlnsPackage.Literals.DOCUMENT_ROOT__PORT);
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
	 * This returns DocumentRoot.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DocumentRoot"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((DocumentRoot)object).getDesc();
		return label == null || label.length() == 0 ?
			getString("_UI_DocumentRoot_type") :
			getString("_UI_DocumentRoot_type") + " " + label;
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

		switch (notification.getFeatureID(DocumentRoot.class)) {
			case XmlnsPackage.DOCUMENT_ROOT__DESC:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case XmlnsPackage.DOCUMENT_ROOT__DATA:
			case XmlnsPackage.DOCUMENT_ROOT__DEFAULT:
			case XmlnsPackage.DOCUMENT_ROOT__EDGE:
			case XmlnsPackage.DOCUMENT_ROOT__ENDPOINT:
			case XmlnsPackage.DOCUMENT_ROOT__GRAPH:
			case XmlnsPackage.DOCUMENT_ROOT__GRAPHML:
			case XmlnsPackage.DOCUMENT_ROOT__HYPEREDGE:
			case XmlnsPackage.DOCUMENT_ROOT__KEY:
			case XmlnsPackage.DOCUMENT_ROOT__LOCATOR:
			case XmlnsPackage.DOCUMENT_ROOT__NODE:
			case XmlnsPackage.DOCUMENT_ROOT__PORT:
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
				(XmlnsPackage.Literals.DOCUMENT_ROOT__DATA,
				 XmlnsFactory.eINSTANCE.createDataType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__DEFAULT,
				 XmlnsFactory.eINSTANCE.createDefaultType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__EDGE,
				 XmlnsFactory.eINSTANCE.createEdgeType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__ENDPOINT,
				 XmlnsFactory.eINSTANCE.createEndpointType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPH,
				 XmlnsFactory.eINSTANCE.createGraphType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPHML,
				 XmlnsFactory.eINSTANCE.createGraphmlType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__HYPEREDGE,
				 XmlnsFactory.eINSTANCE.createHyperedgeType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__KEY,
				 XmlnsFactory.eINSTANCE.createKeyType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__LOCATOR,
				 XmlnsFactory.eINSTANCE.createLocatorType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__NODE,
				 XmlnsFactory.eINSTANCE.createNodeType()));

		newChildDescriptors.add
			(createChildParameter
				(XmlnsPackage.Literals.DOCUMENT_ROOT__PORT,
				 XmlnsFactory.eINSTANCE.createPortType()));
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
