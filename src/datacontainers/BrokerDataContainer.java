package datacontainers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import datamodels.Broker;

//Required to use JAXB XML library
@XmlRootElement(name = "brokerList")
@XmlAccessorType(XmlAccessType.FIELD)
public class BrokerDataContainer {

	private List<Broker> brokerList = new ArrayList<>();
	private Set brokerSet = new HashSet();
	private Map brokerMap = new HashMap();

	public List<Broker> getBrokerList() {
		return brokerList;
	}

	public void setBrokerList(List<Broker> brokerList) {
		this.brokerList = brokerList;
	}

	public Set getBrokerSet() {
		return brokerSet;
	}

	public void setBrokerSet(Set brokerSet) {
		this.brokerSet = brokerSet;
	}

	public Map getBrokerMap() {
		return brokerMap;
	}

	public void setBrokerMap(Map brokerMap) {
		this.brokerMap = brokerMap;
	}

}
