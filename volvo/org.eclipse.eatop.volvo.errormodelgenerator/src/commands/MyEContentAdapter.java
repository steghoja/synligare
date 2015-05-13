package commands;

import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;

public class MyEContentAdapter extends EContentAdapter {
	EAXML root;
	
	public MyEContentAdapter(EAXML root) {
		this.root = root;
		addAdapter(this.root);
	}
	
	public void dispose() {
		removeAdapter(root);
	}
	
	@Override
	public void notifyChanged(Notification n) {
		super.notifyChanged(n);

		if(n.getEventType() != Notification.REMOVING_ADAPTER) {
			Object notifier = n.getNotifier();
			if (notifier instanceof EObject) {
				EObject Enotifier = (EObject) notifier;
				EStructuralFeature econtainFeature = Enotifier.eContainingFeature();
				System.out.println("The EObject has been modified!");
//				handleElementNotification();			
			}
		}
	}
    
//    private void handleElementNotification() {
//    	eContentAccessSequence++;
//    }
}	

