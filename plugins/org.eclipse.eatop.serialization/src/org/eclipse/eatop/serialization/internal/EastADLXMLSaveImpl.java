/**
 * <copyright>
 *  
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     itemis - Initial API and implementation
 *  
 * </copyright>
 * 
 */
package org.eclipse.eatop.serialization.internal;

import static org.eclipse.sphinx.emf.resource.ExtendedResource.OPTION_SCHEMA_LOCATION_CATALOG;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eatop.common.metamodel.EastADLMetaModelVersionData;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLString;
import org.eclipse.sphinx.emf.metamodel.IMetaModelDescriptor;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;
import org.eclipse.sphinx.emf.resource.IModelConverter;
import org.xml.sax.InputSource;

/**
 * An XMLSaveImpl that is specially designed to load EAST-ADL resources.
 */
public class EastADLXMLSaveImpl extends ExtendedXMLPersistenceMappingSaveImpl {

	private static final String SCHEMA_BASE = "eastadl"; //$NON-NLS-1$
	private static final String SCHEMA_EXTENSION = "xsd"; //$NON-NLS-1$

	/**
	 * Creates a new XMLSaveImpl associated with given helper.
	 * 
	 * @param helper
	 *            The helper that provides info about the way serialization should be performed.
	 */
	public EastADLXMLSaveImpl(XMLHelper helper) {
		super(helper);
	}

	// ===================
	@SuppressWarnings("restriction")
	protected void saveReferencedHREFWithTypeSaved(EStructuralFeature f, EObject remote, String qname, boolean doSaveType) {
		{
			String href = helper.getHREF(remote);
			if (href != null) {
				href = convertURI(href);
				EClass eClass = remote.eClass();
				doc.startElement(qname);
				saveTypeAttribute(f, eClass);
				doc.endContentElement(href);
			}
		}
	}

	// TODO TO BE CHECKED
	/**
	 * Save EReference Referenced 1100 many. The type attribute is always saved instead of saved depends on the
	 * conditions which is the default case.
	 * 
	 * @param values
	 * @param f
	 */
	@SuppressWarnings("restriction")
	@Override
	protected void saveEReferenceReferenced1100Many(InternalEList<? extends EObject> values, EStructuralFeature f) {
		int size = values.size();
		String qname;
		EObject value;
		doc.startElement(getFeatureWrapperQName(f));
		for (int i = 0; i < size; i++) {
			value = values.basicGet(i);
			qname = getFeatureQName(f);
			saveReferencedHREFWithTypeSaved(f, value, qname, true);
		}
		doc.endElement();
	}

	// TODO TO BE CHECKED
	@SuppressWarnings("restriction")
	@Override
	/**
	 * Save EReference Referenced 0100 single. The type attribute is always saved instead of saving depending on the
	 * conditions (remote.eClass() != (EClass) f.getEType()) which is the default case.
	 *
	 * @param values
	 * @param f
	 */
	protected void saveEReferenceReferenced0100Single(EObject remote, EStructuralFeature f) {
		String qname = getFeatureQName(f);
		saveReferencedHREFWithTypeSaved(f, remote, qname, true);
	}

	// ===================

	@Override
	protected void addNamespaceDeclarations() {
		// This is a Hack! Sphinx ExtendedXMLSaveImpl assumes that different revisions have different namespaces. This
		// is not true for EASTADL 2.1. Therefore the Converter, SchemaLocationCatalog and SchemaLocationMap are
		// manipulated in such a way that the ExtendedXMLSaveImpl will do what we expect even for 4.0. This can be
		// removed as soon as Sphinx mechanism is refactored to be MetaModelDescriptor based instead of namespace based.
		IMetaModelDescriptor release = MetaModelDescriptorRegistry.INSTANCE.getDescriptor(helper.getResource());
		boolean isSingleNsReleaseResource = isSingleNsRelease((EastADLReleaseDescriptor) release);
		@SuppressWarnings("unchecked")
		Map<String, String> originalSchemaCatalog = (Map<String, String>) getDefaultSaveOptions().get(OPTION_SCHEMA_LOCATION_CATALOG);
		IModelConverter originalConverter = converter;
		if (isSingleNsReleaseResource) {
			if (converter != null) {
				if (converter.getResourceVersionDescriptor() != release) {
					setSchemaLocationFromConverter();
				}
			} else {
				Map<String, String> schemaLocationMap = getSchemaLocationMap();
				if (!schemaLocationMap.isEmpty()) {
					setSchemaCatalog(schemaLocationMap);
					converter = new SingleNsReleaseConverter(release);
				}
			}
		}

		super.addNamespaceDeclarations();

		// Hack part 2. Restoring manipulated values. SchemaLocationMap is not restored as it should be adjusted as soon
		// as a Converter is applied.
		if (isSingleNsReleaseResource) {
			setSchemaCatalog(originalSchemaCatalog);
			converter = originalConverter;
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> getSchemaLocationMap() {
		EObject schemaLocationRoot = getSchemaLocationRoot(helper.getResource().getContents().get(0));
		EReference xsiSchemaLocationMapFeature = extendedMetaData.getXSISchemaLocationMapFeature(schemaLocationRoot.eClass());
		if (xsiSchemaLocationMapFeature != null) {
			return ((EMap<String, String>) schemaLocationRoot.eGet(xsiSchemaLocationMapFeature)).map();
		}
		return null;
	}

	private class SingleNsReleaseConverter implements IModelConverter {

		private IMetaModelDescriptor fRelease;

		public SingleNsReleaseConverter(IMetaModelDescriptor release) {
			fRelease = release;
		}

		@Override
		public IMetaModelDescriptor getMetaModelVersionDescriptor() {
			return fRelease;
		}

		@Override
		public IMetaModelDescriptor getResourceVersionDescriptor() {
			return fRelease;
		}

		@Override
		public boolean isLoadConverterFor(XMLResource resource, Map<?, ?> options) {
			return false;
		}

		@Override
		public boolean isSaveConverterFor(XMLResource resource, Map<?, ?> options) {
			return false;
		}

		@Override
		public InputSource convertLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) throws IOException {
			return null;
		}

		@Override
		public void convertSave(XMLString xml, int flushThreshold, org.eclipse.emf.common.util.URI uri, OutputStream outputStream, String encoding,
				XMLHelper helper, Map<?, ?> options) throws IOException {

		}

		@Override
		public void addExtraAttributesToSavedRootElement(XMLString rootElement, Map<?, ?> options) {

		}

		@Override
		public void dispose() {

		}
	}

	private void setSchemaLocationFromConverter() {
		EastADLReleaseDescriptor revision = (EastADLReleaseDescriptor) converter.getResourceVersionDescriptor();
		Map<String, String> converterSchemaCatalog = createSchemaCatalog(revision);
		setSchemaCatalog(converterSchemaCatalog);
		// Update SchemaMap
		Map<String, String> schemaLocationMap = getSchemaLocationMap();
		schemaLocationMap.put(revision.getNamespace(), converterSchemaCatalog.get(revision.getNamespace()));
	}

	private void setSchemaCatalog(Map<String, String> schemaCatalog) {
		getDefaultSaveOptions().put(OPTION_SCHEMA_LOCATION_CATALOG, schemaCatalog);
	}

	private boolean isSingleNsRelease(EastADLReleaseDescriptor release) {
		if (release == null) {
			return false;
		}
		List<URI> revisionUris = release.getCompatibleNamespaceURIs();
		if (revisionUris.isEmpty()) {
			return false;
		}
		for (URI revisionUri : revisionUris) {
			if (!revisionUri.equals(release.getNamespaceURI())) {
				return false;
			}
		}
		return true;
	}

	private Map<Object, Object> getDefaultSaveOptions() {
		return helper.getResource().getDefaultSaveOptions();
	}

	// TODO: These constants and methods are a HACK!!!! EASTADL 2.1 revisions all share the same namespace! The
	// SchemaLocationCatalog mechanism does not work for 2.1.
	// This has to be removed as soon as the SchemaLocationCatalog mechanism has been refactored.
	private Map<String, String> createSchemaCatalog(EastADLReleaseDescriptor revision) {
		Map<String, String> schemaCatalog = new HashMap<String, String>();
		schemaCatalog.put(revision.getNamespace(), getSchemaLocation(revision));
		return schemaCatalog;
	}

	public String getSchemaLocation(EastADLReleaseDescriptor revision) {
		return SCHEMA_BASE + "_" + getSchemaReleaseNumber(revision) + "." + SCHEMA_EXTENSION; //$NON-NLS-1$//$NON-NLS-2$
	}

	private String getSchemaReleaseNumber(EastADLReleaseDescriptor revision) {
		final String separator = "-"; //$NON-NLS-1$
		EastADLMetaModelVersionData avd = revision.getEastADLVersionData();
		return avd.getMajor() + separator + avd.getMinor() + separator + avd.getRevision();
	}
}