package datamodels;

import java.util.Calendar;
import java.util.Objects;

import exceptionhandlers.InvalidDataException;
import interfaces.IPerson;
import utilities.date.DateFunctions;

public class Person implements IPerson {
	
	private String name;
	private String address;
	private Calendar dateOfBirth;
	private long id;
	
	Person() {
	}
	
	Person(String name, String address, Calendar dateOfBirth, long id) {
		super();
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.id = id;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public void setName(String value) throws InvalidDataException {
		
		if (value.isEmpty()) {
        	throw new InvalidDataException("Setting name failed, no name specified.\r\n");
        }
        else {
        	this.name = value;
        }
		
	}
	@Override
	public String getAddress() {
		return this.address;
	}
	@Override
	public void setAddress(String value) throws InvalidDataException {
		
		if (value.isEmpty()) {
        	throw new InvalidDataException("Setting address failed, no address specified.\r\n");
        }
        else {
        	this.address = value;
        }
		
	}
	@Override
	public Calendar getDateOfBirth() {
		return this.dateOfBirth;
	}
	@Override
	public void setDateOfBirth(Calendar value) {
		if (value != null) {
			this.dateOfBirth = value;
		}
		else {
			this.dateOfBirth = Calendar.getInstance();
		}
		
	}
	@Override
	public long getId() {
		return this.id;
	}
	@Override
	public void setId(long value) throws InvalidDataException {
		
		if (value <= 0) {
        	throw new InvalidDataException("Setting id failed; it must be 7 numeric digits: " + value + ".\r\n");
        }
        else {
        	this.id = value;
        }
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, dateOfBirth, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(address, other.address) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "\n" + this.getName() + "\n" + this.getAddress() + "\nDate of Birth : "
				+ DateFunctions.dateToString(this.getDateOfBirth());
	}
	
	

}
