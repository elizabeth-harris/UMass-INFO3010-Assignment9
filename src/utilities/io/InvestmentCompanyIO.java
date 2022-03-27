/*
 *  This Class contains methods to write out the investment company objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import datacontainers.InvestmentCompanyDataContainer;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import datamodels.InvestmentCompany;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MyFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import utilities.date.DateFunctions;

public class InvestmentCompanyIO implements Serializable {

	/**
	 * Constructor is declared private because the IO classes are utilities which
	 * contain static methods and should never be instantiated
	 */
	private InvestmentCompanyIO() {
	}

	/**
	 * Writes out a text file containing all investment companies in the investment
	 * company data container
	 *
	 * The format of the text file is:
	 *
	 * Example: Name
	 */
	public static void writeTextFile(String fileLocation, InvestmentCompanyDataContainer datacontainer)
			throws MyFileException {

		PrintWriter textFile = null;

		try {
			// Create output file
			// We are putting it in a location specified when the program is run
			// This is done via a command line argument
			textFile = new PrintWriter(fileLocation + "/investmentcompanies.csv");

			// Loop through the array list of investment companies and print delimited text
			// to a file
			for (InvestmentCompany investmentCompany : datacontainer.getInvestmentCompanyList()) {
				textFile.println(investmentCompany.getCompanyName());
			}
		} catch (FileNotFoundException exp) {
			throw new MyFileException(exp.getMessage());
		} finally {
			// Flush the output stream and close the file
			if (textFile != null) {
				textFile.flush();
				textFile.close();
			}
		}
	}

	/**
	 * Creates a serialized object output file containing all InvestmentCompanys in
	 * the InvestmentCompany data container
	 */
	public static void writeSerializedFile(String fileLocation, InvestmentCompanyDataContainer datacontainer)
			throws MyFileException {
		try {
			// Create output file
			ObjectOutputStream serializedFile = new ObjectOutputStream(
					new FileOutputStream(fileLocation + "/investmentcompanies.ser"));
			// Write out the data
			serializedFile.writeObject(datacontainer.getInvestmentCompanyList());
		} catch (IOException exp) {
			throw new MyFileException("Can't serialize file");
		}
	}

	/**
	 * Creates an xml output file containing all InvestmentCompanies in the
	 * InvestmentCompany data container using the JAXB libraries
	 */

	public static void writeXMLFile(String fileLocation, InvestmentCompanyDataContainer investmentCompanyDataContainer)
			throws MyFileException {
		try {
			// Create the format of the xml
			JAXBContext jaxbContext = JAXBContext.newInstance(InvestmentCompanyDataContainer.class);
			// Create the marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// Create nicely formatted xml
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Marshal the investment companies list into an xml file
			jaxbMarshaller.marshal(investmentCompanyDataContainer, new File(fileLocation + "/investmentcompanies.xml"));
		} catch (JAXBException exp) {
			throw new MyFileException(exp.getMessage());
		}
	}

	/**
	 * Writes out the InvestmentCompany data in JSON format containing all
	 * InvestmentCompanys in the investment company data container
	 *
	 */
	public static void writeJSONFile(String fileLocation, InvestmentCompanyDataContainer datacontainer)
			throws MyFileException {

		PrintWriter jsonFile = null;

		try {
			// Create output file
			jsonFile = new PrintWriter(fileLocation + "/investmentcompanies.json");

			// Create JSON object
			Gson gson = new GsonBuilder().create();

			// Convert investment company list to JSON format
			gson.toJson(datacontainer.getInvestmentCompanyList(), jsonFile);

		} catch (JsonIOException | FileNotFoundException exp) {
			throw new MyFileException(exp.getMessage());
		} finally {
			// Flush the output stream and close the file
			if (jsonFile != null) {
				jsonFile.flush();
				jsonFile.close();
			}
		}
	}

	/**
	 * Reads a set of investment company objects from a serialized file and returns
	 * an array list of investment companies
	 */
	public static ArrayList<InvestmentCompany> readSerializedFile(String fileLocation) throws MyFileException {

		ArrayList<InvestmentCompany> listOfInvestmentCompanies = new ArrayList<>();

		try {
			ObjectInputStream serializedFile = new ObjectInputStream(
					new FileInputStream(fileLocation + "/investmentcompanies.ser"));
			// Read the serialized object and cast to its original type
			listOfInvestmentCompanies = (ArrayList<InvestmentCompany>) serializedFile.readObject();
			return listOfInvestmentCompanies;
		} catch (IOException | ClassNotFoundException exp) {
			throw new MyFileException("Can't deserialize file");
		}
	}

	/**
	 * Reads a delimited text file of investment companies and returns an array list
	 * of investment companies.
	 *
	 * An end of file flag is used to keep track of whether we hit the end of the
	 * file, It starts out false and if we hit the end of file (null input), it
	 * changes to true and execution stops.
	 *
	 * The format of the text file is:
	 *
	 * Example: Name
	 */
	public static ArrayList<InvestmentCompany> readTextFile(String fileLocation) throws MyFileException {

		ArrayList<InvestmentCompany> listOfInvestmentCompanies = new ArrayList<>();

		try {
			boolean eof = false;
			BufferedReader textFile = new BufferedReader(new FileReader(fileLocation + "/investmentcompanies.csv"));
			while (!eof) {
				String lineFromFile = textFile.readLine();
				if (lineFromFile == null) {
					eof = true;
				} else {
					// Create a investment company
					InvestmentCompany investmentCompany = new InvestmentCompany();

					// Split the input line into investment company elements using the delimiter
					String[] lineElements = lineFromFile.split(",");

					// The first element is the company name
					try {
						investmentCompany.setCompanyName(lineElements[0]);
					} catch (InvalidDataException exp) {
						throw new MyFileException(exp.getMessage());
					}

					// add the investment company to the arraylist
					listOfInvestmentCompanies.add(investmentCompany);
				}
			}
			return listOfInvestmentCompanies;
		} catch (MyFileException | IOException exp) {
			throw new MyFileException(exp.getMessage());
		}
	}

	/**
	 * Read in an XML file of InvestmentCompany objects
	 *
	 * @param fileLocation
	 * @return
	 */

	public static InvestmentCompanyDataContainer readXMLFile(String fileLocation) throws MyFileException {

		try {
			// Create the format of the xml
			JAXBContext jaxbContext = JAXBContext.newInstance(InvestmentCompanyDataContainer.class);
			// Create the unmarshaller
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			// Unmarshall the file
			return (InvestmentCompanyDataContainer) jaxbUnmarshaller
					.unmarshal(new File(fileLocation + "\\investmentcompanies.xml"));
		} catch (JAXBException exp) {
			throw new MyFileException(exp.getMessage());
		}

	}

	/**
	 * Reads a JSON formatted file of investment companies and returns an array list
	 * of InvestmentCompanys.
	 *
	 */
	public static ArrayList<InvestmentCompany> readJSONFile(String fileLocation) throws MyFileException {

		ArrayList<InvestmentCompany> listOfInvestmentCompanies = new ArrayList<>();

		try {
			// Create input file
			BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "/investmentcompanies.json"));

			// Create JSON object
			Gson gson = new GsonBuilder().create();

			// fromJson returns an array
			InvestmentCompany[] investmentCompanyArray = gson.fromJson(jsonFile, InvestmentCompany[].class);

			// Convert to arraylist for the data model
			listOfInvestmentCompanies.addAll(Arrays.asList(investmentCompanyArray));
			return listOfInvestmentCompanies;
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
			throw new MyFileException(exp.getMessage());
		}
	}
}
