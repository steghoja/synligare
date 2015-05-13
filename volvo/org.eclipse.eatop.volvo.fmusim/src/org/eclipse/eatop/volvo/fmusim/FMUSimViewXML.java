package org.eclipse.eatop.volvo.fmusim;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class FMUSimViewXML {

		
	private List<HighlightedVVLogsinEAXML> highlightedVVLogs = new ArrayList<HighlightedVVLogsinEAXML>();

	
	@XmlElementWrapper(name = "HighlightedVVLogsList")  
	@XmlElement
	public void setHighlightedVVLogs(List<HighlightedVVLogsinEAXML> highlightedVVLogs) {
		this.highlightedVVLogs = highlightedVVLogs;
	}

	public List<HighlightedVVLogsinEAXML> getHighlightedVVLogs() {
		return highlightedVVLogs;
	}



} 

