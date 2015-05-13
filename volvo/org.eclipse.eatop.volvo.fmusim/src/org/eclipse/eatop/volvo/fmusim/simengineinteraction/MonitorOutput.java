package org.eclipse.eatop.volvo.fmusim.simengineinteraction;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.actions.AddBookmarkAction;

public class MonitorOutput {

	String name;
	List<Float> timeVector = new ArrayList<Float>();
	List<Float> valueVector = new ArrayList<Float>();
		
	public MonitorOutput(String name){
		this.name = name;
	}

	public List<Float> getTimeVector() {
		return timeVector;
	}

	public void setTimeVector(List<Float> timeVector) {
		this.timeVector = timeVector;
	}

	public List<Float> getValueVector() {
		return valueVector;
	}

	public void setValueVector(List<Float> valueVector) {
		this.valueVector = valueVector;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void Add(float t, float v){
		timeVector.add(t);
		valueVector.add(v);
		
	}
	
	
	public boolean IsAllTrue(){
		for (Float v : valueVector){
			if (!(v==1.0f)){ 
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonitorOutput other = (MonitorOutput) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
