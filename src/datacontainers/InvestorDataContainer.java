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

import datamodels.Investor;

//Required to use JAXB XML library
@XmlRootElement(name = "investorList")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvestorDataContainer {

	private List<Investor> investorList = new ArrayList<>();
	private Set investorSet = new HashSet();
	private Map investorMap = new HashMap();
 
	public List<Investor> getInvestorList() {
		return investorList;
	}

	public void setInvestorList(List<Investor> investorList) {
		this.investorList = investorList;
	}

	public Set getInvestorSet() {
		return investorSet;
	}

	public void setInvestorSet(Set investorSet) {
		this.investorSet = investorSet;
	}

	public Map getInvestorMap() {
		return investorMap;
	}

	public void setInvestorMap(Map investorMap) {
		this.investorMap = investorMap;
	}

}
