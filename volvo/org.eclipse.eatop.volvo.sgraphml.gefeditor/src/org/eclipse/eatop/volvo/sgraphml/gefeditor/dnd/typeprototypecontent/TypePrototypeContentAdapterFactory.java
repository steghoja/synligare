package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.volvo.sgraphml.testcode.ErrorModelTypeGroupNodePartsProvider;
import org.eclipse.eatop.volvo.sgraphml.testcode.FunctionTypeGroupNodePartsProvider;
import org.eclipse.eatop.volvo.sgraphml.testcode.HardwareComponentTypeGroupNodePartsProvider;
import org.eclipse.eatop.volvo.sgraphml.testcode.IGroupNodePartsProvider;

public class TypePrototypeContentAdapterFactory implements IAdapterFactory {


	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
	
		if (adapterType == ITypePrototypeContentProvider.class){

			if (adaptableObject instanceof FunctionType)
				return new FunctionTypeContentProvider((FunctionType)adaptableObject);

			if (adaptableObject instanceof FunctionPrototype)
				return new FunctionPrototypeContentProvider((FunctionPrototype)adaptableObject);

			if (adaptableObject instanceof HardwareComponentType)
				return new HardwareComponentTypeContentProvider((HardwareComponentType)adaptableObject);

			if (adaptableObject instanceof HardwareComponentPrototype)
				return new HardwareComponentPrototypeContentProvider((HardwareComponentPrototype)adaptableObject);

			if (adaptableObject instanceof ErrorModelType)
				return new ErrorModelTypeContentProvider((ErrorModelType)adaptableObject);

			if (adaptableObject instanceof ErrorModelPrototype)
				return new ErrorModelPrototypeContentProvider((ErrorModelPrototype)adaptableObject);

			
		}
			return null;
	}


	@Override
	public Class[] getAdapterList() {

		return new Class[]{ DesignFunctionType.class, 
				DesignFunctionPrototype.class,  
				HardwareComponentType.class,
				HardwareComponentPrototype.class,
				ErrorModelType.class,
				ErrorModelPrototype.class
		};
	}

}
