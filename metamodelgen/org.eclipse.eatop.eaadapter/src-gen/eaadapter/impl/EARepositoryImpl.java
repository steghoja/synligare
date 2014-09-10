/**
 * <copyright>
 *  
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors:
 * 		Continental AG, Matthias Nick - Initial API and implementation
 * </copyright>
 * 
 */
package eaadapter.impl;

import java.io.File;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sparx.Element;
import org.sparx.Repository;

import eaadapter.EAAttribute;
import eaadapter.EAAttributeTag;
import eaadapter.EAConnector;
import eaadapter.EAConnectorConstraint;
import eaadapter.EAConnectorEnd;
import eaadapter.EAConnectorTag;
import eaadapter.EAConstraint;
import eaadapter.EAElement;
import eaadapter.EAMethod;
import eaadapter.EAMethodTag;
import eaadapter.EAPackage;
import eaadapter.EAParameter;
import eaadapter.EARepository;
import eaadapter.EARoleTag;
import eaadapter.EATaggedValue;
import eaadapter.EaadapterFactory;
import eaadapter.EaadapterPackage;
import eaadapter.abstracthierachy.EANamedElement;
import eaadapter.datatypes.T_ConnectorAggregation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EA Repository</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link eaadapter.impl.EARepositoryImpl#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.impl.EARepositoryImpl#getFile <em>File</em>}</li>
 * <li>{@link eaadapter.impl.EARepositoryImpl#getModels <em>Models</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EARepositoryImpl extends EObjectImpl implements EARepository {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final Repository EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected Repository eaLink = EA_LINK_EDEFAULT;

	/**
	 * The default value of the '{@link #getFile() <em>File</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected static final File FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFile() <em>File</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected File file = FILE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModels() <em>Models</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getModels()
	 * @generated
	 * @ordered
	 */
	protected EList<EAPackage> models;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EARepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAPackage> getModels() {
		if (models == null) {
			models = new EObjectContainmentEList<EAPackage>(EAPackage.class, this, EaadapterPackage.EA_REPOSITORY__MODELS);
		}
		return models;
	}

	public void addModel(EAPackage p) {
		getModels().add(p);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Repository getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEaLink(Repository newEaLink) {
		Repository oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_REPOSITORY__EA_LINK, oldEaLink, eaLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public File getFile() {
		return file;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setFile(File newFile) {
		File oldFile = file;
		file = newFile;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_REPOSITORY__FILE, oldFile, file));
		}
	}

	private void openFile() throws Exception {
		if (getFile() == null) {
			throw new Exception("EAP-file is not set");
		}

		if (!getFile().exists()) {
			throw new Exception(file.getAbsolutePath() + " does not exist!");
		}

		eaLink = new org.sparx.Repository();
		eaLink.OpenFile(getFile().getAbsolutePath());
	}

	private void openFile(IProgressMonitor monitor) throws Exception {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		progress.subTask("open EAP file " + file.getName());

		if (getFile() == null) {
			throw new Exception("EAP-file is not set");
		}

		if (!getFile().exists()) {
			throw new Exception(file.getAbsolutePath() + " does not exist!");
		}

		eaLink = new org.sparx.Repository();
		eaLink.OpenFile(getFile().getAbsolutePath());

		progress.done();
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT mnick
	 */
	@Override
	public void load() throws Exception {
		openFile();
		clearModel();

		// initialize models
		for (org.sparx.Package modelPackage : eaLink.GetModels()) {
			getModels().add(createEAPackageFromSparxPackage(modelPackage));
		}

		for (EAPackage model : getModels()) {
			// initialize packages
			createSubpackagesForPackage(model);

			// initialize elements
			createElementsForPackage(model);
		}

		/*
		 * Now that all packages and elements are initialized, the connectors can be initialized. Note: Before the
		 * connectors can be set, all of the Elements must be present, e.g. Element A in Package A references Element Z
		 * in Package Z --> All elements and packages must be present before.
		 */
		for (EAPackage model : getModels()) {
			createConnectorsForPackage(model);
		}

		eaLink.CloseFile();
	}

	@Override
	public void load(IProgressMonitor monitor) throws Exception {

		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		openFile(progress.newChild(10));
		clearModel();

		// initialize models
		SubMonitor createPackateProgress = progress.newChild(10).setWorkRemaining(eaLink.GetModels().GetCount());
		for (org.sparx.Package modelPackage : eaLink.GetModels()) {
			createPackateProgress.subTask("create EAPackage From Sparx Package " + modelPackage.GetName());

			getModels().add(createEAPackageFromSparxPackage(modelPackage));

			createPackateProgress.worked(1);
			if (createPackateProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		SubMonitor elementProgress = progress.newChild(40).setWorkRemaining(getModels().size());
		for (EAPackage model : getModels()) {
			// initialize packages
			createSubpackagesForPackage(model, elementProgress.newChild(30));

			// initialize elements
			createElementsForPackage(model, elementProgress.newChild(70));
		}

		/*
		 * Now that all packages and elements are initialized, the connectors can be initialized. Note: Before the
		 * connectors can be set, all of the Elements must be present, e.g. Element A in Package A references Element Z
		 * in Package Z --> All elements and packages must be present before.
		 */
		SubMonitor createConnectorProgress = progress.newChild(40).setWorkRemaining(getModels().size());
		for (EAPackage model : getModels()) {
			createConnectorsForPackage(model, createConnectorProgress);
		}

		eaLink.CloseFile();
	}

	private void clearModel() {
		if (getModels().size() > 0) {
			getModels().clear();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT mnick
	 */
	@Override
	public EANamedElement getEANamedElementByGUID(String guid) {
		for (TreeIterator<EANamedElement> iter = EcoreUtil.getAllContents(getModels()); iter.hasNext();) {
			EANamedElement element = iter.next();
			if (element.getGuid().equals(guid)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT mnick
	 */
	@Override
	public EAElement getEAElementByGUID(String guid) {
		EANamedElement result = getEANamedElementByGUID(guid);
		if (result instanceof EAElement) {
			return (EAElement) result;
		} else {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT mnick
	 */
	@Override
	public EList<EANamedElement> getEANamedElementByID(long id) {
		EList<EANamedElement> result = new BasicEList<EANamedElement>();
		for (TreeIterator<EANamedElement> iter = EcoreUtil.getAllProperContents(getModels(), true); iter.hasNext();) {
			EANamedElement element = iter.next();
			if (element.getId() == id) {
				result.add(element);
			}
		}
		return result;

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT mnick
	 */
	@Override
	public EList<EAElement> getEAElementByID(long id) {
		EList<EAElement> result = new BasicEList<EAElement>();
		EList<EANamedElement> allElements = getEANamedElementByID(id);
		for (EANamedElement element : allElements) {
			if (element instanceof EAElement) {
				result.add((EAElement) element);
			}
		}
		return result;
	}

	private void createConnectorsForPackage(EAPackage pkg) {
		for (TreeIterator<EANamedElement> iter = EcoreUtil.getAllContents(pkg, true); iter.hasNext();) {
			EANamedElement element = iter.next();
			if (element instanceof EAElement) {
				EAElement eaElement = (EAElement) element;

				for (org.sparx.Connector c : eaElement.getEaLink().GetConnectors()) {
					// only add the connector if its 'source' is the current element
					EAConnector eaConnector = createEAConnectorFromSparxConnector(c);
					if (eaConnector.getClientID() == eaElement.getId()) {
						eaElement.getConnectors().add(eaConnector);
					}
				}
			}
		}
	}

	private void createConnectorsForPackage(EAPackage pkg, IProgressMonitor monitor) {

		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		for (TreeIterator<EANamedElement> iter = EcoreUtil.getAllContents(pkg, true); iter.hasNext();) {

			EANamedElement element = iter.next();

			if (element instanceof EAElement) {
				EAElement eaElement = (EAElement) element;

				SubMonitor connectorProgress = progress.newChild(1).setWorkRemaining(eaElement.getEaLink().GetConnectors().GetCount());
				for (org.sparx.Connector c : eaElement.getEaLink().GetConnectors()) {
					// only add the connector if its 'source' is the current element
					EAConnector eaConnector = createEAConnectorFromSparxConnector(c, connectorProgress);
					if (eaConnector.getClientID() == eaElement.getId()) {
						eaElement.getConnectors().add(eaConnector);
					}

					connectorProgress.worked(1);
					if (connectorProgress.isCanceled()) {
						throw new OperationCanceledException();
					}
				}
			}

			progress.worked(1);
			if (progress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}
	}

	private EAConnectorEnd createEAConnectorEndFromSparxConnectorEnd(org.sparx.ConnectorEnd ce) {
		EAConnectorEnd connEnd = EaadapterFactory.eINSTANCE.createEAConnectorEnd();

		switch (ce.GetAggregation()) {
		case 0:
			connEnd.setAggregation(T_ConnectorAggregation.NONE);
			break;
		case 1:
			connEnd.setAggregation(T_ConnectorAggregation.SHARED);
			break;
		case 2:
			connEnd.setAggregation(T_ConnectorAggregation.COMPOSITE);
			break;
		default:
			connEnd.setAggregation(T_ConnectorAggregation.NONE);
			break;
		}

		connEnd.setCardinality(ce.GetCardinality());
		connEnd.setConstraint(ce.GetConstraint());
		connEnd.setContainment(ce.GetContainment());
		connEnd.setDerived(ce.GetDerived());
		connEnd.setEaLink(ce);
		connEnd.setEnd(ce.GetEnd());
		connEnd.setIsChangable(ce.GetIsChangeable());
		connEnd.setIsNavigable(ce.GetIsNavigable());
		connEnd.setOrdering(ce.GetOrdering());
		connEnd.setQualifier(ce.GetQualifier());
		connEnd.setRole(ce.GetRole());
		connEnd.setRoleNote(ce.GetRoleNote());
		connEnd.setRoleType(ce.GetRoleType());
		connEnd.setVisibility(ce.GetVisibility());

		for (org.sparx.RoleTag rt : ce.GetTaggedValues()) {
			EARoleTag roleTag = EaadapterFactory.eINSTANCE.createEARoleTag();
			roleTag.setBaseClass(rt.GetBaseClass());
			roleTag.setEaLink(rt);
			roleTag.setElementGUID(rt.GetElementGUID());
			roleTag.setPropertyGUID(rt.GetPropertyGUID());
			roleTag.setTag(rt.GetTag());
			roleTag.setValue(rt.GetValue());
			connEnd.getTaggedValues().add(roleTag);
		}

		return connEnd;
	}

	private EAConnectorEnd createEAConnectorEndFromSparxConnectorEnd(org.sparx.ConnectorEnd ce, IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		EAConnectorEnd connEnd = EaadapterFactory.eINSTANCE.createEAConnectorEnd();

		switch (ce.GetAggregation()) {
		case 0:
			connEnd.setAggregation(T_ConnectorAggregation.NONE);
			break;
		case 1:
			connEnd.setAggregation(T_ConnectorAggregation.SHARED);
			break;
		case 2:
			connEnd.setAggregation(T_ConnectorAggregation.COMPOSITE);
			break;
		default:
			connEnd.setAggregation(T_ConnectorAggregation.NONE);
			break;
		}

		connEnd.setCardinality(ce.GetCardinality());
		connEnd.setConstraint(ce.GetConstraint());
		connEnd.setContainment(ce.GetContainment());
		connEnd.setDerived(ce.GetDerived());
		connEnd.setEaLink(ce);
		connEnd.setEnd(ce.GetEnd());
		connEnd.setIsChangable(ce.GetIsChangeable());
		connEnd.setIsNavigable(ce.GetIsNavigable());
		connEnd.setOrdering(ce.GetOrdering());
		connEnd.setQualifier(ce.GetQualifier());
		connEnd.setRole(ce.GetRole());
		connEnd.setRoleNote(ce.GetRoleNote());
		connEnd.setRoleType(ce.GetRoleType());
		connEnd.setVisibility(ce.GetVisibility());

		progress.worked(10);

		SubMonitor roleTagProgress = progress.newChild(90).setWorkRemaining(ce.GetTaggedValues().GetCount());
		for (org.sparx.RoleTag rt : ce.GetTaggedValues()) {

			EARoleTag roleTag = EaadapterFactory.eINSTANCE.createEARoleTag();
			roleTag.setBaseClass(rt.GetBaseClass());
			roleTag.setEaLink(rt);
			roleTag.setElementGUID(rt.GetElementGUID());
			roleTag.setPropertyGUID(rt.GetPropertyGUID());
			roleTag.setTag(rt.GetTag());
			roleTag.setValue(rt.GetValue());
			connEnd.getTaggedValues().add(roleTag);

			roleTagProgress.worked(1);
			if (roleTagProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		return connEnd;
	}

	private EAConnectorConstraint createEAConnectorConstraintFromSparxConnectorConstraint(org.sparx.ConnectorConstraint cc) {
		EAConnectorConstraint eaCC = EaadapterFactory.eINSTANCE.createEAConnectorConstraint();
		eaCC.setConnectorID(cc.GetConnectorID());
		eaCC.setEaLink(cc);
		eaCC.setName(cc.GetName());
		eaCC.setNotes(cc.GetNotes());
		eaCC.setType(cc.GetType());
		return eaCC;
	}

	private EAConnector createEAConnectorFromSparxConnector(org.sparx.Connector c) {
		EAConnector eaConnector = EaadapterFactory.eINSTANCE.createEAConnector();
		eaConnector.setClientEnd(createEAConnectorEndFromSparxConnectorEnd(c.GetClientEnd()));
		eaConnector.setConnectorID(c.GetConnectorID());

		// create constraints
		eaConnector.setClientID(c.GetClientID());
		for (org.sparx.ConnectorConstraint cc : c.GetConstraints()) {
			eaConnector.getConstraints().add(createEAConnectorConstraintFromSparxConnectorConstraint(cc));
		}

		eaConnector.setDirection(c.GetDirection());
		eaConnector.setEaLink(c);
		eaConnector.setEventFlags(c.GetEventFlags());
		eaConnector.setGuid(c.GetConnectorGUID());
		eaConnector.setId(c.GetConnectorID());
		eaConnector.setIsLeaf(c.GetIsLeaf());
		eaConnector.setIsRoot(c.GetIsRoot());
		eaConnector.setIsSpec(c.GetIsSpec());
		eaConnector.setName(c.GetName());
		eaConnector.setNotes(c.GetNotes());
		eaConnector.setRouteStyle(c.GetRouteStyle());
		eaConnector.setSequenceNo(c.GetSequenceNo());
		eaConnector.setStereotype(c.GetStereotype());
		eaConnector.setStereotypeEx(c.GetStereotypeEx());
		eaConnector.setStyleEx(c.GetStyleEx());
		eaConnector.setSubtype(c.GetSubtype());

		/*
		 * The connector's supplier is identified via its supplierID. However, we don't know if this ID is unique
		 * (Sparx-Support says: "it is unique within the model, but not within the project") Therefore the
		 * getEAElementByID() method must return exactly 1 Element! Otherwise we have a Problem.
		 */

		EList<EAElement> supplierList = getEAElementByID(c.GetSupplierID());
		if (supplierList.size() == 1) {
			eaConnector.setSupplier(supplierList.get(0));
		} else if (supplierList.size() == 0) {
			System.err.println("ERROR, could not find supplier with id '" + c.GetSupplierID() + "' for connector '" + c.toString() + "'");
		} else {
			System.err.println("ERROR, too much supplier for connector. Size: " + supplierList.size());
		}

		eaConnector.setSupplierEnd(createEAConnectorEndFromSparxConnectorEnd(c.GetSupplierEnd()));
		eaConnector.setSupplierID(c.GetSupplierID());
		eaConnector.setTransitionAction(c.GetTransitionAction());
		eaConnector.setTransitionEvent(c.GetTransitionEvent());
		eaConnector.setTransitionGuard(c.GetTransitionGuard());
		eaConnector.setType(c.GetType());
		eaConnector.setVirtualInheritance(c.GetVirtualInheritance());

		// Add tagged values
		for (org.sparx.ConnectorTag cTag : c.GetTaggedValues()) {
			EAConnectorTag eaCTag = EaadapterFactory.eINSTANCE.createEAConnectorTag();
			eaCTag.setEaLink(cTag);
			eaCTag.setGuid(cTag.GetTagGUID());
			eaCTag.setName(cTag.GetName());
			eaCTag.setNotes(cTag.GetNotes());
			eaCTag.setId(cTag.GetTagID());
			eaCTag.setValue(cTag.GetValue());
			eaConnector.getTaggedValues().add(eaCTag);
		}
		return eaConnector;
	}

	private EAConnector createEAConnectorFromSparxConnector(org.sparx.Connector c, IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}
		progress.subTask("create EAConnector From Sparx Connector " + c.GetConnectorID());

		EAConnector eaConnector = EaadapterFactory.eINSTANCE.createEAConnector();
		eaConnector.setClientEnd(createEAConnectorEndFromSparxConnectorEnd(c.GetClientEnd(), progress.newChild(15)));
		eaConnector.setConnectorID(c.GetConnectorID());

		// create constraints
		SubMonitor constraintProgress = progress.newChild(15).setWorkRemaining(c.GetConstraints().GetCount());
		eaConnector.setClientID(c.GetClientID());
		for (org.sparx.ConnectorConstraint cc : c.GetConstraints()) {
			eaConnector.getConstraints().add(createEAConnectorConstraintFromSparxConnectorConstraint(cc));

			constraintProgress.worked(1);
			if (constraintProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		eaConnector.setDirection(c.GetDirection());
		eaConnector.setEaLink(c);
		eaConnector.setEventFlags(c.GetEventFlags());
		eaConnector.setGuid(c.GetConnectorGUID());
		eaConnector.setId(c.GetConnectorID());
		eaConnector.setIsLeaf(c.GetIsLeaf());
		eaConnector.setIsRoot(c.GetIsRoot());
		eaConnector.setIsSpec(c.GetIsSpec());
		eaConnector.setName(c.GetName());
		eaConnector.setNotes(c.GetNotes());
		eaConnector.setRouteStyle(c.GetRouteStyle());
		eaConnector.setSequenceNo(c.GetSequenceNo());
		eaConnector.setStereotype(c.GetStereotype());
		eaConnector.setStereotypeEx(c.GetStereotypeEx());
		eaConnector.setStyleEx(c.GetStyleEx());
		eaConnector.setSubtype(c.GetSubtype());

		/*
		 * The connector's supplier is identified via its supplierID. However, we don't know if this ID is unique
		 * (Sparx-Support says: "it is unique within the model, but not within the project") Therefore the
		 * getEAElementByID() method must return exactly 1 Element! Otherwise we have a Problem.
		 */

		EList<EAElement> supplierList = getEAElementByID(c.GetSupplierID());
		if (supplierList.size() == 1) {
			eaConnector.setSupplier(supplierList.get(0));
		} else if (supplierList.size() == 0) {
			System.err.println("ERROR, could not find supplier with id '" + c.GetSupplierID() + "' for connector '" + c.toString() + "'");
		} else {
			System.err.println("ERROR, too much supplier for connector. Size: " + supplierList.size());
		}

		eaConnector.setSupplierEnd(createEAConnectorEndFromSparxConnectorEnd(c.GetSupplierEnd()));
		eaConnector.setSupplierID(c.GetSupplierID());
		eaConnector.setTransitionAction(c.GetTransitionAction());
		eaConnector.setTransitionEvent(c.GetTransitionEvent());
		eaConnector.setTransitionGuard(c.GetTransitionGuard());
		eaConnector.setType(c.GetType());
		eaConnector.setVirtualInheritance(c.GetVirtualInheritance());

		// Add tagged values
		SubMonitor connectorTagProgress = progress.newChild(70).setWorkRemaining(c.GetTaggedValues().GetCount());
		for (org.sparx.ConnectorTag cTag : c.GetTaggedValues()) {
			EAConnectorTag eaCTag = EaadapterFactory.eINSTANCE.createEAConnectorTag();
			eaCTag.setEaLink(cTag);
			eaCTag.setGuid(cTag.GetTagGUID());
			eaCTag.setName(cTag.GetName());
			eaCTag.setNotes(cTag.GetNotes());
			eaCTag.setId(cTag.GetTagID());
			eaCTag.setValue(cTag.GetValue());
			eaConnector.getTaggedValues().add(eaCTag);

			connectorTagProgress.worked(1);
			if (connectorTagProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		return eaConnector;
	}

	private void createElementsForPackage(EAPackage root) {
		for (EAPackage currentPackage : root.getPackages()) {
			for (org.sparx.Element e : currentPackage.getEaLink().GetElements()) {
				EAElement eaElement = createEAElementFromSparxElement(e);
				currentPackage.getElements().add(eaElement);
				// System.out.println("added element " + eaElement.getName() + " in " + root.getName());
			}
			createElementsForPackage(currentPackage);
		}

	}

	private void createElementsForPackage(EAPackage root, IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}
		progress.subTask("create Elements For Package " + root.getName());

		SubMonitor packageProgress = progress.newChild(100).setWorkRemaining(root.getPackages().size());
		for (EAPackage currentPackage : root.getPackages()) {

			SubMonitor elementProgress = packageProgress.newChild(40).setWorkRemaining(currentPackage.getEaLink().GetElements().GetCount());
			for (org.sparx.Element e : currentPackage.getEaLink().GetElements()) {
				EAElement eaElement = createEAElementFromSparxElement(e, elementProgress);
				currentPackage.getElements().add(eaElement);
				// System.out.println("added element " + eaElement.getName() + " in " + root.getName());

				elementProgress.worked(1);
				if (elementProgress.isCanceled()) {
					throw new OperationCanceledException();
				}
			}
			createElementsForPackage(currentPackage, packageProgress.newChild(60));

			packageProgress.worked(1);
			if (packageProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

	}

	private EAElement createEAElementFromSparxElement(org.sparx.Element e) {
		EAElement eaElement = EaadapterFactory.eINSTANCE.createEAElement();

		if (e.GetAbstract().equals("1")) {
			eaElement.setIsAbstract(true);
		} else {
			eaElement.setIsAbstract(false);
		}

		eaElement.setAuthor(e.GetAuthor());
		eaElement.setClassifierName(e.GetClassifierName());
		eaElement.setClassifierID(e.GetClassfierID());
		eaElement.setComplexity(e.GetComplexity());
		eaElement.setDifficulty(e.GetDifficulty());
		eaElement.setEaLink(e);
		eaElement.setExtensionPoints(e.GetExtensionPoints());
		eaElement.setGenfile(e.GetGenfile());
		eaElement.setGenlinks(e.GetGenlinks());
		eaElement.setGentype(e.GetGentype());
		eaElement.setGuid(e.GetElementGUID());
		eaElement.setId(e.GetElementID());
		eaElement.setIsActive(e.GetIsActive());
		eaElement.setIsLocked(e.GetIsLeaf());
		eaElement.setMultiplicity(e.GetMultiplicity());
		eaElement.setMetaType(e.GetMetaType());
		eaElement.setName(e.GetName());
		eaElement.setNotes(e.GetNotes());
		eaElement.setPackageID(e.GetPackageID());
		eaElement.setParentID(e.GetParentID());
		eaElement.setPhase(e.GetPhase());
		eaElement.setPriority(e.GetPriority());
		eaElement.setPropertyType(e.GetPropertyType());
		eaElement.setStatus(e.GetStatus());
		eaElement.setStereotype(e.GetStereotype());
		eaElement.setStereotypeEx(e.GetStereotypeEx());
		eaElement.setSubtype(e.GetSubtype());
		eaElement.setTablespace(e.GetTablespace());
		eaElement.setTablespace(e.GetTag());
		eaElement.setVersion(e.GetVersion());
		eaElement.setVisibility(e.GetVisibility());

		for (org.sparx.TaggedValue tv : e.GetTaggedValues()) {
			EATaggedValue eaTv = EaadapterFactory.eINSTANCE.createEATaggedValue();
			eaTv.setEaLink(tv);
			eaTv.setId(tv.GetElementID());
			eaTv.setGuid(tv.GetPropertyGUID());
			eaTv.setName(tv.GetName());
			eaTv.setNotes(tv.GetNotes());
			eaTv.setValue(tv.GetValue());
			eaElement.getTaggedValues().add(eaTv);
		}

		// create constraints here
		for (org.sparx.Constraint constr : e.GetConstraints()) {
			EAConstraint eaConstraint = EaadapterFactory.eINSTANCE.createEAConstraint();
			eaConstraint.setName(constr.GetName());
			eaConstraint.setNotes(constr.GetNotes());
			eaConstraint.setStatus(constr.GetStatus());
			eaConstraint.setType(constr.GetType());
			eaElement.getConstraints().add(eaConstraint);
		}

		// create attributes here
		for (org.sparx.Attribute attr : e.GetAttributes()) {
			EAAttribute newAttrib = createEAAttributeFromSparxAttribute(attr);
			eaElement.getAttributes().add(newAttrib);
			// System.out.println("added attribute " + newAttrib.getName() + " in Element " +
			// newAttrib.getElement().getName() );
		}

		// create methods here
		for (org.sparx.Method mthd : e.GetMethods()) {
			EAMethod newMethod = createEAMethodFromSparxMethod(mthd);
			eaElement.getMethods().add(newMethod);
			// System.out.println("added method " + newMethod.getName() + " in Element " +
			// newMethod.getElement().getName() );
		}

		/**
		 * if an element has sub-elements we are not sure how to manage this. An Element should not have sub-elements
		 */
		if (e.GetElements().GetCount() > 0) {
			for (Element subElement : e.GetElements()) {
				EAElement element = createEAElementFromSparxElement(subElement);
				eaElement.getElements().add(element);
			}
		}

		return eaElement;
	}

	private EAElement createEAElementFromSparxElement(org.sparx.Element e, IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}
		progress.subTask("create EAElement From Sparx Element " + e.GetElementID());

		EAElement eaElement = EaadapterFactory.eINSTANCE.createEAElement();

		if (e.GetAbstract().equals("1")) {
			eaElement.setIsAbstract(true);
		} else {
			eaElement.setIsAbstract(false);
		}

		eaElement.setAuthor(e.GetAuthor());
		eaElement.setClassifierName(e.GetClassifierName());
		eaElement.setClassifierID(e.GetClassfierID());
		eaElement.setComplexity(e.GetComplexity());
		eaElement.setDifficulty(e.GetDifficulty());
		eaElement.setEaLink(e);
		eaElement.setExtensionPoints(e.GetExtensionPoints());
		eaElement.setGenfile(e.GetGenfile());
		eaElement.setGenlinks(e.GetGenlinks());
		eaElement.setGentype(e.GetGentype());
		eaElement.setGuid(e.GetElementGUID());
		eaElement.setId(e.GetElementID());
		eaElement.setIsActive(e.GetIsActive());
		eaElement.setIsLocked(e.GetIsLeaf());
		eaElement.setMultiplicity(e.GetMultiplicity());
		eaElement.setMetaType(e.GetMetaType());
		eaElement.setName(e.GetName());
		eaElement.setNotes(e.GetNotes());
		eaElement.setPackageID(e.GetPackageID());
		eaElement.setParentID(e.GetParentID());
		eaElement.setPhase(e.GetPhase());
		eaElement.setPriority(e.GetPriority());
		eaElement.setPropertyType(e.GetPropertyType());
		eaElement.setStatus(e.GetStatus());
		eaElement.setStereotype(e.GetStereotype());
		eaElement.setStereotypeEx(e.GetStereotypeEx());
		eaElement.setSubtype(e.GetSubtype());
		eaElement.setTablespace(e.GetTablespace());
		eaElement.setTablespace(e.GetTag());
		eaElement.setVersion(e.GetVersion());
		eaElement.setVisibility(e.GetVisibility());

		progress.worked(10);
		SubMonitor taggedValueProgress = progress.newChild(20).setWorkRemaining(e.GetTaggedValues().GetCount());

		for (org.sparx.TaggedValue tv : e.GetTaggedValues()) {
			EATaggedValue eaTv = EaadapterFactory.eINSTANCE.createEATaggedValue();
			eaTv.setEaLink(tv);
			eaTv.setId(tv.GetElementID());
			eaTv.setGuid(tv.GetPropertyGUID());
			eaTv.setName(tv.GetName());
			eaTv.setNotes(tv.GetNotes());
			eaTv.setValue(tv.GetValue());
			eaElement.getTaggedValues().add(eaTv);

			taggedValueProgress.worked(1);
			if (taggedValueProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		// create constraints here
		SubMonitor constraintProgress = progress.newChild(10).setWorkRemaining(e.GetConstraints().GetCount());
		for (org.sparx.Constraint constr : e.GetConstraints()) {
			EAConstraint eaConstraint = EaadapterFactory.eINSTANCE.createEAConstraint();
			eaConstraint.setName(constr.GetName());
			eaConstraint.setNotes(constr.GetNotes());
			eaConstraint.setStatus(constr.GetStatus());
			eaConstraint.setType(constr.GetType());
			eaElement.getConstraints().add(eaConstraint);

			constraintProgress.worked(1);
			if (constraintProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		// create attributes here
		SubMonitor attrtProgress = progress.newChild(20).setWorkRemaining(e.GetAttributes().GetCount());
		for (org.sparx.Attribute attr : e.GetAttributes()) {
			EAAttribute newAttrib = createEAAttributeFromSparxAttribute(attr, attrtProgress);
			eaElement.getAttributes().add(newAttrib);
			// System.out.println("added attribute " + newAttrib.getName() + " in Element " +
			// newAttrib.getElement().getName() );
		}

		// create methods here
		SubMonitor mthdProgress = progress.newChild(20).setWorkRemaining(e.GetMethods().GetCount());
		for (org.sparx.Method mthd : e.GetMethods()) {
			EAMethod newMethod = createEAMethodFromSparxMethod(mthd, mthdProgress);
			eaElement.getMethods().add(newMethod);
			// System.out.println("added method " + newMethod.getName() + " in Element " +
			// newMethod.getElement().getName() );
		}

		/**
		 * if an element has sub-elements we are not sure how to manage this. An Element should not have sub-elements
		 */
		if (e.GetElements().GetCount() > 0) {
			SubMonitor subElementProgress = progress.newChild(20).setWorkRemaining(e.GetElements().GetCount());
			for (Element subElement : e.GetElements()) {
				EAElement element = createEAElementFromSparxElement(subElement, subElementProgress);
				eaElement.getElements().add(element);
			}
		}

		monitor.done();
		return eaElement;
	}

	private EAAttribute createEAAttributeFromSparxAttribute(org.sparx.Attribute a) {
		EAAttribute eaAttribute = EaadapterFactory.eINSTANCE.createEAAttribute();
		eaAttribute.setAllowDuplicates(a.GetAllowDuplicates());
		eaAttribute.setContainer(a.GetContainer());
		eaAttribute.setContainment(a.GetContainment());
		eaAttribute.setClassifierID(a.GetClassifierID());
		eaAttribute.setDefault(a.GetDefault());
		eaAttribute.setEaLink(a);
		eaAttribute.setGuid(a.GetAttributeGUID());
		eaAttribute.setId(a.GetAttributeID());
		eaAttribute.setCollection(a.GetIsCollection());
		eaAttribute.setIsConst(a.GetIsConst());
		eaAttribute.setDerived(a.GetIsDerived());
		eaAttribute.setOrdered(a.GetIsOrdered());
		eaAttribute.setIsStatic(a.GetIsStatic());
		eaAttribute.setLength(a.GetLength());
		eaAttribute.setLowerBound(a.GetLowerBound());
		eaAttribute.setName(a.GetName());
		eaAttribute.setNotes(a.GetNotes());
		eaAttribute.setPrecision(a.GetPrecision());
		eaAttribute.setScale(a.GetScale());
		eaAttribute.setStereotype(a.GetStereotype());
		eaAttribute.setStereotypeEx(a.GetStereotypeEx());
		eaAttribute.setType(a.GetType());
		eaAttribute.setUpperBound(a.GetUpperBound());
		eaAttribute.setVisibility(a.GetVisibility());

		for (org.sparx.AttributeTag attrTag : a.GetTaggedValues()) {
			EAAttributeTag eaAttribTag = EaadapterFactory.eINSTANCE.createEAAttributeTag();
			eaAttribTag.setEaLink(attrTag);
			eaAttribTag.setGuid(attrTag.GetTagGUID());
			eaAttribTag.setId(attrTag.GetTagID());
			eaAttribTag.setName(attrTag.GetName());
			eaAttribTag.setNotes(attrTag.GetNotes());
			eaAttribTag.setValue(attrTag.GetValue());
			eaAttribute.getTaggedValues().add(eaAttribTag);
		}
		return eaAttribute;
	}

	private EAAttribute createEAAttributeFromSparxAttribute(org.sparx.Attribute a, IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		EAAttribute eaAttribute = EaadapterFactory.eINSTANCE.createEAAttribute();
		eaAttribute.setAllowDuplicates(a.GetAllowDuplicates());
		eaAttribute.setContainer(a.GetContainer());
		eaAttribute.setContainment(a.GetContainment());
		eaAttribute.setClassifierID(a.GetClassifierID());
		eaAttribute.setDefault(a.GetDefault());
		eaAttribute.setEaLink(a);
		eaAttribute.setGuid(a.GetAttributeGUID());
		eaAttribute.setId(a.GetAttributeID());
		eaAttribute.setCollection(a.GetIsCollection());
		eaAttribute.setIsConst(a.GetIsConst());
		eaAttribute.setDerived(a.GetIsDerived());
		eaAttribute.setOrdered(a.GetIsOrdered());
		eaAttribute.setIsStatic(a.GetIsStatic());
		eaAttribute.setLength(a.GetLength());
		eaAttribute.setLowerBound(a.GetLowerBound());
		eaAttribute.setName(a.GetName());
		eaAttribute.setNotes(a.GetNotes());
		eaAttribute.setPrecision(a.GetPrecision());
		eaAttribute.setScale(a.GetScale());
		eaAttribute.setStereotype(a.GetStereotype());
		eaAttribute.setStereotypeEx(a.GetStereotypeEx());
		eaAttribute.setType(a.GetType());
		eaAttribute.setUpperBound(a.GetUpperBound());
		eaAttribute.setVisibility(a.GetVisibility());

		progress.worked(10);
		SubMonitor attrTagProgress = progress.newChild(90).setWorkRemaining(a.GetTaggedValues().GetCount());
		for (org.sparx.AttributeTag attrTag : a.GetTaggedValues()) {
			EAAttributeTag eaAttribTag = EaadapterFactory.eINSTANCE.createEAAttributeTag();
			eaAttribTag.setEaLink(attrTag);
			eaAttribTag.setGuid(attrTag.GetTagGUID());
			eaAttribTag.setId(attrTag.GetTagID());
			eaAttribTag.setName(attrTag.GetName());
			eaAttribTag.setNotes(attrTag.GetNotes());
			eaAttribTag.setValue(attrTag.GetValue());
			eaAttribute.getTaggedValues().add(eaAttribTag);

			attrTagProgress.worked(1);
			if (attrTagProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		monitor.done();
		return eaAttribute;
	}

	private EAMethod createEAMethodFromSparxMethod(org.sparx.Method m) {
		EAMethod eaMethod = EaadapterFactory.eINSTANCE.createEAMethod();
		eaMethod.setCode(m.GetCode());
		eaMethod.setConcurrency(m.GetConcurrency());
		eaMethod.setEaLink(m);
		eaMethod.setGuid(m.GetMethodGUID());
		eaMethod.setId(m.GetMethodID());
		eaMethod.setIsAbstract(m.GetAbstract());
		eaMethod.setIsConst(m.GetIsConst());
		eaMethod.setIsLeaf(m.GetIsLeaf());
		eaMethod.setIsPure(m.GetIsPure());
		eaMethod.setIsQuery(m.GetIsQuery());
		eaMethod.setIsRoot(m.GetIsRoot());
		eaMethod.setIsStatic(m.GetIsStatic());
		eaMethod.setIsSynchronized(m.GetIsSynchronized());
		eaMethod.setName(m.GetName());
		eaMethod.setNotes(m.GetNotes());
		eaMethod.setReturnIsArray(m.GetReturnIsArray());
		eaMethod.setReturnType(m.GetReturnType());
		eaMethod.setStereotype(m.GetStereotype());
		eaMethod.setStereotypeEx(m.GetStereotypeEx());
		try {
			eaMethod.setClassifierID(Integer.valueOf(m.GetClassifierID()));
		} catch (NumberFormatException e) {
			System.err.println(m.GetName() + ": " + m.GetClassifierID() + " can't be casted to a number!");
		}
		eaMethod.setThrows(m.GetThrows());
		eaMethod.setType("");

		// create Parameters
		for (org.sparx.Parameter par : m.GetParameters()) {
			EAParameter eaPar = createEAParameterFromSparxParameter(par);
			eaMethod.getParameters().add(eaPar);
		}

		// create Tagged Values
		for (org.sparx.MethodTag mTag : m.GetTaggedValues()) {
			EAMethodTag eaMtag = EaadapterFactory.eINSTANCE.createEAMethodTag();
			eaMtag.setEaLink(mTag);
			eaMtag.setGuid(mTag.GetTagGUID());
			eaMethod.setId(mTag.GetTagID());
			eaMtag.setName(mTag.GetName());
			eaMtag.setNotes(mTag.GetNotes());
			eaMtag.setValue(mTag.GetValue());
			eaMethod.getTaggedValues().add(eaMtag);
		}
		return eaMethod;
	}

	private EAMethod createEAMethodFromSparxMethod(org.sparx.Method m, IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		EAMethod eaMethod = EaadapterFactory.eINSTANCE.createEAMethod();
		eaMethod.setCode(m.GetCode());
		eaMethod.setConcurrency(m.GetConcurrency());
		eaMethod.setEaLink(m);
		eaMethod.setGuid(m.GetMethodGUID());
		eaMethod.setId(m.GetMethodID());
		eaMethod.setIsAbstract(m.GetAbstract());
		eaMethod.setIsConst(m.GetIsConst());
		eaMethod.setIsLeaf(m.GetIsLeaf());
		eaMethod.setIsPure(m.GetIsPure());
		eaMethod.setIsQuery(m.GetIsQuery());
		eaMethod.setIsRoot(m.GetIsRoot());
		eaMethod.setIsStatic(m.GetIsStatic());
		eaMethod.setIsSynchronized(m.GetIsSynchronized());
		eaMethod.setName(m.GetName());
		eaMethod.setNotes(m.GetNotes());
		eaMethod.setReturnIsArray(m.GetReturnIsArray());
		eaMethod.setReturnType(m.GetReturnType());
		eaMethod.setStereotype(m.GetStereotype());
		eaMethod.setStereotypeEx(m.GetStereotypeEx());
		try {
			eaMethod.setClassifierID(Integer.valueOf(m.GetClassifierID()));
		} catch (NumberFormatException e) {
			System.err.println(m.GetName() + ": " + m.GetClassifierID() + " can't be casted to a number!");
		}
		eaMethod.setThrows(m.GetThrows());
		eaMethod.setType("");

		progress.worked(10);

		// create Parameters
		SubMonitor parameterProgress = progress.newChild(20).setWorkRemaining(m.GetParameters().GetCount());
		for (org.sparx.Parameter par : m.GetParameters()) {
			EAParameter eaPar = createEAParameterFromSparxParameter(par);
			eaMethod.getParameters().add(eaPar);

			parameterProgress.worked(1);
			if (parameterProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		// create Tagged Values
		SubMonitor mtagProgress = progress.newChild(70).setWorkRemaining(m.GetTaggedValues().GetCount());
		for (org.sparx.MethodTag mTag : m.GetTaggedValues()) {
			EAMethodTag eaMtag = EaadapterFactory.eINSTANCE.createEAMethodTag();
			eaMtag.setEaLink(mTag);
			eaMtag.setGuid(mTag.GetTagGUID());
			eaMethod.setId(mTag.GetTagID());
			eaMtag.setName(mTag.GetName());
			eaMtag.setNotes(mTag.GetNotes());
			eaMtag.setValue(mTag.GetValue());
			eaMethod.getTaggedValues().add(eaMtag);

			mtagProgress.worked(1);
			if (mtagProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		monitor.done();
		return eaMethod;
	}

	private EAParameter createEAParameterFromSparxParameter(org.sparx.Parameter p) {
		EAParameter eaPar = EaadapterFactory.eINSTANCE.createEAParameter();
		try {
			eaPar.setClassifierID(Integer.valueOf(p.GetClassifierID()));
		} catch (NumberFormatException e) {
			System.err.println(p.GetName() + ": " + p.GetClassifierID() + " can't be casted to a number!");
		}
		eaPar.setDefault(p.GetDefault());
		eaPar.setEaLink(p);
		eaPar.setGuid(p.GetParameterGUID());
		eaPar.setId(p.GetOperationID());
		eaPar.setIsConst(p.GetIsConst());
		eaPar.setKind(p.GetKind());
		eaPar.setName(p.GetName());
		eaPar.setNotes(p.GetNotes());
		eaPar.setPosition(p.GetPosition());
		eaPar.setStereotype(p.GetStereotype());
		eaPar.setType(p.GetType());
		return eaPar;
	}

	private void createSubpackagesForPackage(EAPackage root) {
		for (org.sparx.Package pkg : root.getEaLink().GetPackages()) {
			EAPackage newPackage = createEAPackageFromSparxPackage(pkg);
			root.getPackages().add(newPackage);
			createSubpackagesForPackage(newPackage);
		}
	}

	private void createSubpackagesForPackage(EAPackage root, IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}
		progress.subTask("create Subpackages For Package " + root.getName());

		SubMonitor packageProgress = progress.newChild(100).setWorkRemaining(root.getEaLink().GetPackages().GetCount());
		for (org.sparx.Package pkg : root.getEaLink().GetPackages()) {
			EAPackage newPackage = createEAPackageFromSparxPackage(pkg);
			root.getPackages().add(newPackage);
			createSubpackagesForPackage(newPackage, packageProgress);

			packageProgress.worked(1);
			if (packageProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}

		monitor.done();
	}

	private EAPackage createEAPackageFromSparxPackage(org.sparx.Package pkg) {
		EAPackage eaPackage = EaadapterFactory.eINSTANCE.createEAPackage();
		eaPackage.setEaLink(pkg);
		eaPackage.setCodePath(pkg.GetCodePath());
		eaPackage.setFlags(pkg.GetFlags());
		eaPackage.setName(pkg.GetName());
		eaPackage.setNotes(pkg.GetNotes());
		eaPackage.setStereotype(pkg.GetStereotypeEx());
		eaPackage.setVersion(pkg.GetVersion());
		eaPackage.setGuid(pkg.GetPackageGUID());
		eaPackage.setId(pkg.GetPackageID());
		return eaPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case EaadapterPackage.EA_REPOSITORY__MODELS:
			return ((InternalEList<?>) getModels()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case EaadapterPackage.EA_REPOSITORY__EA_LINK:
			return getEaLink();
		case EaadapterPackage.EA_REPOSITORY__FILE:
			return getFile();
		case EaadapterPackage.EA_REPOSITORY__MODELS:
			return getModels();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case EaadapterPackage.EA_REPOSITORY__EA_LINK:
			setEaLink((Repository) newValue);
			return;
		case EaadapterPackage.EA_REPOSITORY__FILE:
			setFile((File) newValue);
			return;
		case EaadapterPackage.EA_REPOSITORY__MODELS:
			getModels().clear();
			getModels().addAll((Collection<? extends EAPackage>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case EaadapterPackage.EA_REPOSITORY__EA_LINK:
			setEaLink(EA_LINK_EDEFAULT);
			return;
		case EaadapterPackage.EA_REPOSITORY__FILE:
			setFile(FILE_EDEFAULT);
			return;
		case EaadapterPackage.EA_REPOSITORY__MODELS:
			getModels().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case EaadapterPackage.EA_REPOSITORY__EA_LINK:
			return EA_LINK_EDEFAULT == null ? eaLink != null : !EA_LINK_EDEFAULT.equals(eaLink);
		case EaadapterPackage.EA_REPOSITORY__FILE:
			return FILE_EDEFAULT == null ? file != null : !FILE_EDEFAULT.equals(file);
		case EaadapterPackage.EA_REPOSITORY__MODELS:
			return models != null && !models.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (eaLink: ");
		result.append(eaLink);
		result.append(", file: ");
		result.append(file);
		result.append(')');
		return result.toString();
	}

} // EARepositoryImpl
