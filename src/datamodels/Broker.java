package datamodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import exceptionhandlers.InvalidDataException;
import utilities.date.DateFunctions;

public class Broker extends Person implements Serializable {

	private Calendar dateOfHire;
	private Calendar dateOfTermination;
	private double salary;
	private String status;
	private List<Investor> listOfClients = new ArrayList<>();;

	public Broker() {

	}

	public Broker(String name, String address, Calendar dateOfBirth, long id, Calendar dateOfHire,
			Calendar dateOfTermination, double salary, String status) {
		super(name, address, dateOfBirth, id);
		this.dateOfHire = dateOfHire;
		this.dateOfTermination = dateOfTermination;
		this.salary = salary;
		this.status = status;
	}

	public Calendar getDateOfHire() {
		return this.dateOfHire;
	}

	public void setDateOfHire(Calendar dateOfHire) {
		if (dateOfHire != null) {
			this.dateOfHire = dateOfHire;
		}
		else {
			this.dateOfHire = Calendar.getInstance();
		}

	}

	public Calendar getDateOfTermination() {
		return this.dateOfTermination;
	}

	public void setDateOfTermination(Calendar dateOfTermination) {
		if (dateOfTermination != null) {
			this.dateOfTermination = dateOfTermination;
		}
		else {
			this.dateOfTermination = Calendar.getInstance();
		}
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) throws InvalidDataException {
		
		if (salary <= 0.0) {
        	this.salary = 0;
			throw new InvalidDataException("Setting broker salary failed, broker salary must be greater than zero.\r\n  Value will default to 0.");
        }
        else {
        	this.salary = salary;
        }
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) throws InvalidDataException {
		
		if (status.equals("Full Time") || status.equals("Part Time")) {
			this.status = status;        }
        else {
        	throw new InvalidDataException("Setting broker status failed, broker status is invalid\r\n");
        }
	}

	public List<Investor> getListOfClients() {
		return this.listOfClients;
	}

	public void addClient(Investor newClient) {
		this.listOfClients.add(newClient);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateOfHire, dateOfTermination, listOfClients, salary, status);
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
		Broker other = (Broker) obj;
		return Objects.equals(dateOfHire, other.dateOfHire)
				&& Objects.equals(dateOfTermination, other.dateOfTermination)
				&& Objects.equals(listOfClients, other.listOfClients)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		
		String value = "Broker:\n-------" + super.toString() + "\nDate of Hire : "
				+ DateFunctions.dateToString(dateOfHire) + "\nDate of Termination : "
				+ DateFunctions.dateToString(dateOfTermination) + "\nSalary : " + salary + "\nStatus : " + status
				+ "\nId : " + this.getId() + "\n\nList of Clients:\n----------------\n";
		for (Investor i : this.listOfClients)
			value = value.concat(i.getName() + "\n");
		
		return value;
	}

}
