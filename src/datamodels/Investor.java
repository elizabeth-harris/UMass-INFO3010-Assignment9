package datamodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import utilities.date.DateFunctions;

public class Investor extends Person implements Serializable {

	private Calendar memberSince;
	private List<InvestorStockQuote> listOfStocks = new ArrayList<>();;

	public Investor() {
	}

	public Investor(String name, String address, Calendar dateOfBirth, long id, Calendar memberSince) {
		super(name, address, dateOfBirth, id);
		this.memberSince = memberSince;
	}

	public Calendar getMemberSince() {
		return this.memberSince;
	}

	public void setMemberSince(Calendar memberSince) {
		if (memberSince != null) {
			this.memberSince = memberSince;
		}
		else {
			this.memberSince = Calendar.getInstance();
		}
	}

	public double getAccountValue() {
		double accountValue = 0.0;
		
		for (InvestorStockQuote s : this.listOfStocks)
			accountValue += s.getShares() * s.getStock().getValue();
			
		return accountValue;
	}

	public List<InvestorStockQuote> getListOfStocks() {
		return this.listOfStocks;
	}

	public void addStock(InvestorStockQuote value) {
		this.listOfStocks.add(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(listOfStocks, memberSince);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investor other = (Investor) obj;
		return Objects.equals(listOfStocks, other.listOfStocks) && Objects.equals(memberSince, other.memberSince);
	}

	@Override
	public String toString() {
		return "Investor:\n---------" + super.toString() + "\nId : " + this.getId() + "\nPortfolio Value : " + this.getAccountValue() + "\n";
	}

}
