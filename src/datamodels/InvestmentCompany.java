package datamodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptionhandlers.InvalidDataException;

public class InvestmentCompany implements Serializable {

	private String companyName;
	private List<Broker> listOfBrokers = new ArrayList<>();;

	public InvestmentCompany(String companyName, List<Broker> listOfBrokers) {
		this.companyName = companyName;
		this.listOfBrokers = listOfBrokers;
	}

	public InvestmentCompany() {
	}

	public InvestmentCompany(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) throws InvalidDataException {
		
		if (companyName.isEmpty()) {
        	throw new InvalidDataException("Setting investment company name failed, company name not specified\r\n");
        }
        else {
        	this.companyName = companyName;
        }
	}

	public List<Broker> getListOfBrokers() {
		return this.listOfBrokers;
	}

	public void addBroker(Broker newBroker) {
		this.listOfBrokers.add(newBroker);
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyName, listOfBrokers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestmentCompany other = (InvestmentCompany) obj;
		return Objects.equals(companyName, other.companyName) && Objects.equals(listOfBrokers, other.listOfBrokers);
	}

	@Override
	public String toString() {
		
		String value = "InvestmentCompany : " + companyName + "\n\nList Of Brokers\n---------------\n";
		for (Broker b : this.listOfBrokers)
			value = value.concat(b.getName() + "\n");
		return  value;
	}


}
