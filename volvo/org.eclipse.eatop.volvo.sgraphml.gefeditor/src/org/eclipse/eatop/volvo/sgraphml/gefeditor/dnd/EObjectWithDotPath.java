package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

public class EObjectWithDotPath {
		public String dotPath;
		public EObject eObject;

		public EObjectWithDotPath(String dotPath, EObject eObject){
			this.dotPath = dotPath;
			this.eObject = eObject;
		}
		
		@Override
		//Use dotPath as sign of equal to be used in list functions like contain
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;

			EObjectWithDotPath other = (EObjectWithDotPath) obj;
			if (dotPath == null) {
				if (other.dotPath != null)
					return false;
			} else if (!dotPath.equals(other.dotPath))
				return false;
			return true;
		}


}
