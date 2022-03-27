package interfaces;

import java.util.Calendar;

import exceptionhandlers.InvalidDataException;

public interface IPerson {
	
	String getName();
	void setName(String value) throws InvalidDataException;
	String getAddress();
	void setAddress(String value) throws InvalidDataException;
	Calendar getDateOfBirth();
	void setDateOfBirth(Calendar value);
	long getId();
	void setId(long value) throws InvalidDataException;
	boolean equals(Object obj);
	int hashCode();
	String toString();
	
}
