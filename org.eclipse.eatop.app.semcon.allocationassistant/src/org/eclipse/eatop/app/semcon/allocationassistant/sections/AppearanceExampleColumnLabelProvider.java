package org.eclipse.eatop.app.semcon.allocationassistant.sections;

import org.eclipse.eatop.app.semcon.allocationassistant.editor.AllocationFormEditor;
import org.eclipse.eatop.examples.common.ui.providers.TypeNameLabelDecorator;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * Taken from AppearanceExampleColumnLabelProvider.java in org.eclipse.eatop.app.semcon.allocationassistant
 *  {@link org.eclipse.eatop.examples <em>EastADL plugin</em>}
 * 
 */
public class AppearanceExampleColumnLabelProvider extends ColumnLabelProvider {
	private final AllocationFormEditor allocationEditor;
	private final TransactionalAdapterFactoryLabelProvider delegate;
	private final ILabelDecorator typeNameLabelDecorator;

	public AppearanceExampleColumnLabelProvider(AllocationFormEditor allocationEditor) {
		this.allocationEditor = allocationEditor;

		delegate = new TransactionalAdapterFactoryLabelProvider((TransactionalEditingDomain) allocationEditor.getEditingDomain(),
				allocationEditor.getAdapterFactory());
		typeNameLabelDecorator = new TypeNameLabelDecorator();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) element;
			element = selection.getFirstElement();
		}

		String text = delegate.getText(element);
		if (allocationEditor.isShowTypeName()) {
			return typeNameLabelDecorator.decorateText(text, element);
		} else {
			return text;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
	 */
	@Override
	public String getToolTipText(Object element) {
		String typeName = TypeNameLabelDecorator.getTypeName(element);
		return typeName.length() > 0 ? typeName : null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellLabelProvider#useNativeToolTip(java.lang.Object)
	 */
	@Override
	public boolean useNativeToolTip(Object object) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {
		delegate.addListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		delegate.dispose();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.lang.Object)
	 */
	@Override
	public Color getBackground(Object object) {
		return delegate.getBackground(object);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getFont(java.lang.Object)
	 */
	@Override
	public Font getFont(Object object) {
		return delegate.getFont(object);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getForeground(java.lang.Object)
	 */
	@Override
	public Color getForeground(Object object) {
		return delegate.getForeground(object);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object object) {
		return delegate.getImage(object);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object object, String id) {
		return delegate.isLabelProperty(object, id);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {
		delegate.removeListener(listener);
	}
}
