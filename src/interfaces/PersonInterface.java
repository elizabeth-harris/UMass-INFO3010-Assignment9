package interfaces;

import exceptionhandlers.MyFileException;
import java.util.Calendar;

/**
 * This interface lists all of the methods which need to be implemented in the
 * Person class
 */
public interface PersonInterface {

    public String getName();

    public void setName(String value) throws MyFileException;

    public String getAddress();

    public void setAddress(String value) throws MyFileException;

    public Calendar getDateOfBirth();

    public void setDateOfBirth(Calendar value);

    public long getId();

    public void setId(long value) throws MyFileException;

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

    @Override
    String toString();

}
