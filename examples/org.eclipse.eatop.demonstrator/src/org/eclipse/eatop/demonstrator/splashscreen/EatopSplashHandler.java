package org.eclipse.eatop.demonstrator.splashscreen;

import java.util.List;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.branding.IProductConstants;
import org.eclipse.ui.splash.BasicSplashHandler;

public class EatopSplashHandler extends BasicSplashHandler {

	public EatopSplashHandler() {
		super();
	}

	@Override
	public void init(Shell splash) {
		super.init(splash);

		String progressRectString = "70,175,395,15"; //$NON-NLS-1$
		String messageRectString = "70,193,395,20"; //$NON-NLS-1$
		String foregroundColorString = null;
		String eatopVersion = null;
		String eatopBuildId = null;
		IProduct product = Platform.getProduct();

		if (product != null) {
			foregroundColorString = product.getProperty(IProductConstants.STARTUP_FOREGROUND_COLOR);
			String aboutText = product.getProperty(IProductConstants.ABOUT_TEXT);

			eatopVersion = aboutText.substring(aboutText.indexOf("Version: ")); //$NON-NLS-1$
			eatopVersion = "EATOP-" + eatopVersion.substring(0, eatopVersion.indexOf('\n')); //$NON-NLS-1$

			eatopBuildId = aboutText.substring(aboutText.indexOf("Build id: ")); //$NON-NLS-1$
			eatopBuildId = "EATOP-Build-Id:" + eatopBuildId.substring(9, eatopBuildId.indexOf('\n')); //$NON-NLS-1$
		}

		Rectangle progressRect = StringConverter.asRectangle(progressRectString, new Rectangle(10, 10, 300, 15));
		setProgressRect(progressRect);

		Rectangle messageRect = StringConverter.asRectangle(messageRectString, new Rectangle(10, 35, 300, 15));
		setMessageRect(messageRect);

		int foregroundColorInteger;
		try {
			foregroundColorInteger = Integer.parseInt(foregroundColorString, 16);
		} catch (Exception ex) {
			foregroundColorInteger = 0xD2D7FF; // off white
		}

		setForeground(new RGB((foregroundColorInteger & 0xFF0000) >> 16, (foregroundColorInteger & 0xFF00) >> 8, foregroundColorInteger & 0xFF));
		getContent(); // ensure creation of the progress

		// The list of supported EAST-ADL meta-model releases
		List<EastADLReleaseDescriptor> supportedReleases = MetaModelDescriptorRegistry.INSTANCE.getDescriptors(EastADLReleaseDescriptor.INSTANCE,
				true);

		//		if (eatopBuildId.contains("{")) { //$NON-NLS-1$
		eatopBuildId = ""; //$NON-NLS-1$
		// }

		final String eastADLVersion = "Latest EAST-ADL-Version: " + (supportedReleases.size() > 0 ? supportedReleases.get(0).toString() : "").substring(9); //$NON-NLS-1$ //$NON-NLS-2$

		final String versions = eatopVersion + "      " + eatopBuildId; //$NON-NLS-1$

		// Double offset = versionText.length() * 4.3;
		// final int xpos = 455 - offset.intValue();
		getContent().addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				// e.gc.drawText(versionText, xpos, ypos, true);
				// e.gc.drawText(eastADLRelease, 20, ypos, true);

				Font font = new Font(e.display, "Segoe UI", 8, SWT.NORMAL); //$NON-NLS-1$
				Color color = new Color(e.display, 85, 85, 85);

				e.gc.setForeground(color);
				e.gc.setFont(font);
				e.gc.drawText(versions, 9, 221, true);
				e.gc.drawText(eastADLVersion, 308, 221, true);
			}
		});
	}
}