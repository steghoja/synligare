package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.HardwarePort;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.emf.common.util.EList;

public class HardwareComponentTypeContentProvider implements
		ITypePrototypeContentProvider {

	HardwareComponentType hct;

	public HardwareComponentTypeContentProvider(HardwareComponentType adaptableObject){
			this.hct = adaptableObject;
	}
	
	@Override
	public List<EAPort> getInports() {
		List<EAPort> ports = getPorts(EADirectionKind.IN);
		List<EAPort> pins = getPins(EADirectionKind.IN);
		ports.addAll(pins);
		return ports;
	}

	@Override
	public List<EAPort> getOutports() {
		List<EAPort> ports = getPorts(EADirectionKind.OUT);
		List<EAPort> pins = getPins(EADirectionKind.OUT);
		ports.addAll(pins);
		return ports;
	}

	@Override
	public List<EAPort> getInOutports() {
		List<EAPort> ports = getPorts(EADirectionKind.INOUT);
		List<EAPort> pins = getPins(EADirectionKind.INOUT);
		ports.addAll(pins);
		return ports;
	}

		
	//HardwareComponentType has getPin = single pins, getPort = contacts that holds pins and contacts
	
	//inports = number of pins + number of ports
	protected List<EAPort> getPins(EADirectionKind direction){
		List<EAPort> foundpins = new ArrayList<EAPort>();

		EList<HardwarePin> pins =  hct.getPin();
		for (HardwarePin hardwarePin : pins) {
			if (hardwarePin.getDirection() == direction){
				foundpins.add(hardwarePin);
			}
		}
		return foundpins;
	}
	
	protected List<EAPort> getPorts(EADirectionKind direction){
		List<EAPort> foundports = new ArrayList<EAPort>();

		EList<HardwarePort> ports =  hct.getPort();
		
		//check what kind of port this is, we look at the pins 
		//and if all in then we say that the port is an inport etc.

		for (HardwarePort hardwarePort : ports) {
			if (hardwarePort.getContainedPin().size() > 0)
				if (hwPortDirectionKind(hardwarePort) == direction){
					foundports.add(hardwarePort);
				}
		}

		return foundports;
	}

	
	//assumes at least one getContainedPin
	static public EADirectionKind hwPortDirectionKind(HardwarePort hwPort){

		if (hwPort.getContainedPort().size() > 0){
			Utils.showInformationMessage("Contained Ports in HardwarePort not supported. ");
		}
	
		if (hwPort.getContainedPin().isEmpty()){
			Utils.showInformationMessage("No Pins contained in port. "); 
		}
		
		EADirectionKind direction = hwPort.getContainedPin().get(0).getDirection();
		
		for (HardwarePin pin : hwPort.getContainedPin()) {
			if (pin.getDirection() != direction){
				return EADirectionKind.INOUT;
			}
		}
		
		return direction;
	}
	
	@Override
	public List<EAPrototype> getParts() {
		List<EAPrototype> prototypes = new ArrayList<EAPrototype>();

		for (HardwareComponentPrototype hcp :  (hct.getPart())){
				prototypes.add(hcp);
		}
		return prototypes;
	}

	@Override
	public List<EAConnector> getConnectors() {
		List<EAConnector> connectors = new ArrayList<EAConnector>();

		//add HardwareConnectors (ie for pins)
		for (EAConnector c :  (hct.getConnector())){
			connectors.add(c);
		}

		//add HardwarePortConnectors (ie for ports)
		for (EAConnector c :  (hct.getPortConnector())){
			connectors.add(c);
		}
		
		return connectors;
	}
	
}
