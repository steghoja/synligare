package org.eclipse.eatop.volvo.fmusim;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "org.eatop.fmusim.FMUSimViewXML") 
public class HighlightedVVLogsinEAXML {

	String eaxmlURI;
	List<String> highlightedVVLogPaths;
	
	public String getEaxmlURI() {
		return eaxmlURI;
	}
	
	@XmlElement
	public void setEaxmlURI(String eaxmlURI) {
		this.eaxmlURI = eaxmlURI;
	}
	public List<String> getHighlightedVVLogPaths() {
		return highlightedVVLogPaths;
	}
	
    @XmlElementWrapper(name = "highlightedVVLogPathList")  
	@XmlElement(name = "highlightedVVLogPath")
	public void setHighlightedVVLogPaths(List<String> highlightedVVLogPaths) {
		this.highlightedVVLogPaths = highlightedVVLogPaths;
	}
	
	
	
}
