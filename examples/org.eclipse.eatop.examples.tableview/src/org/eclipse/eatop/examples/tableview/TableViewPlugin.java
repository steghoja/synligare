package org.eclipse.eatop.examples.tableview;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class TableViewPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.eatop.examples.tableview"; //$NON-NLS-1$

	// The shared instance
	private static TableViewPlugin plugin;
	
	/**
	 * The constructor
	 */
	public TableViewPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static TableViewPlugin getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		return getDefault().getImageRegistry().getDescriptor(key);
	}
	
	public static Image getImage(String key) {
		return getDefault().getImageRegistry().get(key);
	}
	
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		super.initializeImageRegistry(reg);
		
		reg.put("lock",    imageDescriptorFromPlugin(PLUGIN_ID, "icons/lock.png"));
		reg.put("columns", imageDescriptorFromPlugin(PLUGIN_ID, "icons/text_columns.png"));
		reg.put("resize", imageDescriptorFromPlugin(PLUGIN_ID, "icons/resize.png"));
		
	}
	

}
