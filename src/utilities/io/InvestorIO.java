/*
 *  This Class contains methods to write out the investor objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import datacontainers.InvestorDataContainer;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import datamodels.Investor;
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

public class InvestorIO implements Serializable {

	/**
	 * Constructor is declared private because the IO classes are utilities which
	 * contain static methods and should never be instantiated
	 */
	private InvestorIO() {
	}

	/**
	 * Writes out a text file containing all investors in the investor data
	 * container
	 *
	 * The format of the text file is:
	 *
	 * Example: Name, InvestorSince
	 */
	public static void writeTextFile(String fileLocation, InvestorDataContainer datacontainer) throws MyFileException {

		PrintWriter textFile = null;

		try {
			// Create output file
			// We are putting it in a location specified when the program is run
			// This is done via a command line argument
			textFile = new PrintWriter(fileLocation + "/investors.csv");

			// Loop through the array list of investors and print delimited text to a file
			for (Investor investor : datacontainer.getInvestorList()) {
				textFile.println(investor.getName() + "," + DateFunctions.dateToString(investor.getMemberSince()));
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
	 * Creates a serialized object output file containing all Investors in the
	 * Investor data container
	 */
	public static void writeSerializedFile(String fileLocation, InvestorDataContainer datacontainer)
			throws MyFileException {
		try {
			// Create output file
			ObjectOutputStream serializedFile = new ObjectOutputStream(
					new FileOutputStream(fileLocation + "/investors.ser"));
			// Write out the data
			serializedFile.writeObject(datacontainer.getInvestorList());
		} catch (IOException exp) {
			throw new MyFileException("Can't serialize file");
		}
	}

	/**
	 * Creates an xml output file containing all Investors in the Investor data
	 * container using the JAXB libraries
	 */

	public static void writeXMLFile(String fileLocation, InvestorDataContainer investorDataContainer)
			throws MyFileException {
		try {
			// Create the format of the xml
			JAXBContext jaxbContext = JAXBContext.newInstance(InvestorDataContainer.class);
			// Create the marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// Create nicely formatted xml
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Marshal the investors list into an xml file
			jaxbMarshaller.marshal(investorDataContainer, new File(fileLocation + "/investors.xml"));
		} catch (JAXBException exp) {
			throw new MyFileException(exp.getMessage());
		}
	}

	/**
	 * Writes out the Investor data in JSON format containing all Investors in the
	 * investor data container
	 *
	 */
	public static void writeJSONFile(String fileLocation, InvestorDataContainer datacontainer) throws MyFileException {

		PrintWriter jsonFile = null;

		try {
			// Create output file
			jsonFile = new PrintWriter(fileLocation + "/investors.json");

			// Create JSON object
			Gson gson = new GsonBuilder().create();

			// Convert investor list to JSON format
			gson.toJson(datacontainer.getInvestorList(), jsonFile);

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
	 * Reads a set of investor objects from a serialized file and returns an array
	 * list of investors
	 */
	public static ArrayList<Investor> readSerializedFile(String fileLocation) throws MyFileException {

		ArrayList<Investor> listOfInvestors = new ArrayList<>();

		try {
			ObjectInputStream serializedFile = new ObjectInputStream(
					new FileInputStream(fileLocation + "/investors.ser"));
			// Read the serialized object and cast to its original type
			listOfInvestors = (ArrayList<Investor>) serializedFile.readObject();
			return listOfInvestors;
		} catch (IOException | ClassNotFoundException exp) {
			throw new MyFileException("Can't deserialize file");
		}
	}

	/**
	 * Reads a delimited text file of investors and returns an array list of
	 * investors.
	 *
	 * An end of file flag is used to keep track of whether we hit the end of the
	 * file, It starts out false and if we hit the end of file (null input), it
	 * changes to true and execution stops.
	 *
	 * The format of the text file is:
	 *
	 * Example: Name, InvestorSince
	 */
	public static ArrayList<Investor> readTextFile(String fileLocation) throws MyFileException {

		ArrayList<Investor> listOfInvestors = new ArrayList<>();

		try {
			boolean eof = false;
			BufferedReader textFile = new BufferedReader(new FileReader(fileLocation + "/investors.csv"));
			while (!eof) {
				String lineFromFile = textFile.readLine();
				if (lineFromFile == null) {
					eof = true;
				} else {
					// Create a investor
					Investor investor = new Investor();

					// Split the input line into investor elements using the delimiter
					String[] lineElements = lineFromFile.split(",");

					// The first element is the investor name
					try {
						investor.setName(lineElements[0]);
					} catch (InvalidDataException exp) {
						throw new MyFileException(exp.getMessage());
					}

					// The second element is the date
					investor.setMemberSince(DateFunctions.stringToDate(lineElements[1]));

					// add the investor to the arraylist
					listOfInvestors.add(investor);
				}
			}
			return listOfInvestors;
		} catch (MyFileException | IOException exp) {
			throw new MyFileException(exp.getMessage());
		}
	}

	/**
	 * Read in an XML file of Investor objects
	 *
	 * @param fileLocation
	 * @return
	 */
	
	public static InvestorDataContainer readXMLFile(String fileLocation) throws MyFileException {

		try {
			// Create the format of the xml
			JAXBContext jaxbContext = JAXBContext.newInstance(InvestorDataContainer.class);
			// Create the unmarshaller
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			// Unmarshal the file
			return (InvestorDataContainer) jaxbUnmarshaller.unmarshal(new File(fileLocation + "\\investors.xml"));
		} catch (JAXBException exp) {
			throw new MyFileException(exp.getMessage());
		}

	}
	 

	/**
	 * Reads a JSON formatted file of stock investors and returns an array list of
	 * Investors.
	 *
	 */
	public static ArrayList<Investor> readJSONFile(String fileLocation) throws MyFileException {

		ArrayList<Investor> listOfInvestors = new ArrayList<>();

		try {
			// Create input file
			BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "/investors.json"));

			// Create JSON object
			Gson gson = new GsonBuilder().create();

			// fromJson returns an array
			Investor[] investorArray = gson.fromJson(jsonFile, Investor[].class);

			// Convert to arraylist for the data model
			listOfInvestors.addAll(Arrays.asList(investorArray));
			return listOfInvestors;
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
			throw new MyFileException(exp.getMessage());
		}
	}
}
