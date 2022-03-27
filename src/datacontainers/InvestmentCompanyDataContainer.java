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

import datamodels.InvestmentCompany;

//Required to use JAXB XML library
@XmlRootElement(name = "companyList")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvestmentCompanyDataContainer {

	private List<InvestmentCompany> companyList = new ArrayList<>();
	private Set companySet = new HashSet();
	private Map companyMap = new HashMap();

	public List<InvestmentCompany> getInvestmentCompanyList() {
		return companyList;
	}

	public void setInvestmentCompanyList(List<InvestmentCompany> companyList) {
		this.companyList = companyList;
	}

	public Set getInvestmentCompanySet() {
		return companySet;
	}

	public void setInvestmentCompanySet(Set companySet) {
		this.companySet = companySet;
	}

	public Map getInvestmentCompanyMap() {
		return companyMap;
	}

	public void setInvestmentCompanyMap(Map companyMap) {
		this.companyMap = companyMap;
	}
}
