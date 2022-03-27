/*
 *  This Class contains methods to write out the stockquote objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import datacontainers.StockQuoteDataContainer;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import datamodels.StockQuote;
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

public class StockQuoteIO implements Serializable {

   /**
    * Constructor is declared private because the IO classes are utilities which
    * contain static methods and should never be instantiated
    */
   private StockQuoteIO() {
   }

   /**
    * Writes out a text file containing all stock quotes in the stock quote data
    * container
    *
    * The format of the text file is:
    *
    * Example: FA301,STOCKQUOTE
    */
   public static void writeTextFile(String fileLocation, StockQuoteDataContainer datacontainer) throws MyFileException {

      PrintWriter textFile = null;

      try {
         // Create output file
         // We are putting it in a location specified when the program is run
         // This is done via a command line argument
         textFile = new PrintWriter(fileLocation + "/stockquotes.csv");

         // Loop through the array list of stockquotes and print delimited text to a file
         for (StockQuote quote : datacontainer.getStockQuoteList()) {
            textFile.println(quote.getTickerSymbol() + "," + quote.getValue()
                    + "," + DateFunctions.dateToString(quote.getQuoteDate()));
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
    * Creates a serialized object output file containing all StockQuotes in the
    * StockQuote data container
    */
   public static void writeSerializedFile(String fileLocation, StockQuoteDataContainer datacontainer) throws MyFileException {
      try {
         // Create output file
         ObjectOutputStream serializedFile = new ObjectOutputStream(
                 new FileOutputStream(fileLocation + "/stockquotes.ser"));
         // Write out the data
         serializedFile.writeObject(datacontainer.getStockQuoteList());
      } catch (IOException exp) {
         throw new MyFileException("Can't serialize file");
      }
   }

   /**
    * Creates an xml output file containing all StockQuotes in the StockQuote
    * data container using the JAXB libraries
    */
   public static void writeXMLFile(String fileLocation, StockQuoteDataContainer stockquoteDataContainer) throws MyFileException {
      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(StockQuoteDataContainer.class);
         // Create the marshaller
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         // Create nicely formatted xml
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         //Marshal the stockquotes list into an xml file
         jaxbMarshaller.marshal(stockquoteDataContainer, new File(fileLocation + "/stockquotes.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

   /**
    * Writes out the StockQuote data in JSON format containing all StockQuotes
    * in the stockquote data container
    *
    */
   public static void writeJSONFile(String fileLocation, StockQuoteDataContainer datacontainer) throws MyFileException {

      PrintWriter jsonFile = null;

      try {
         // Create output file
         jsonFile = new PrintWriter(fileLocation + "/stockquotes.json");

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // Convert stockquote list to JSON format
         gson.toJson(datacontainer.getStockQuoteList(), jsonFile);

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
    * Reads a set of stockquote objects from a serialized file and returns an
    * array list of stockquotes
    */
   public static ArrayList<StockQuote> readSerializedFile(String fileLocation) throws MyFileException {

      ArrayList<StockQuote> listOfStockQuotes = new ArrayList<>();

      try {
         ObjectInputStream serializedFile = new ObjectInputStream(
                 new FileInputStream(fileLocation + "/stockquotes.ser"));
         // Read the serialized object and cast to its original type
         listOfStockQuotes = (ArrayList<StockQuote>) serializedFile.readObject();
         return listOfStockQuotes;
      } catch (IOException | ClassNotFoundException exp) {
         throw new MyFileException("Can't deserialize file");
      }
   }

   /**
    * Reads a delimited text file of stockquotes and returns an array list of
    * stockquotes.
    *
    * An end of file flag is used to keep track of whether we hit the end of the
    * file, It starts out false and if we hit the end of file (null input), it
    * changes to true and execution stops.
    *
    * The format of the text file is:
    *
    * Example: FA301,StockQuote
    */
   public static ArrayList<StockQuote> readTextFile(String fileLocation) throws MyFileException {

      ArrayList<StockQuote> listOfStockQuotes = new ArrayList<>();

      try {
         boolean eof = false;
         BufferedReader textFile = new BufferedReader(new FileReader(fileLocation + "/stockquotes.csv"));
         while (!eof) {
            String lineFromFile = textFile.readLine();
            if (lineFromFile == null) {
               eof = true;
            } else {
               // Create a stockquote
               StockQuote quote = new StockQuote();

               // Split the input line into stockquote elements using the delimiter
               String[] lineElements = lineFromFile.split(",");

               // The first element is the ticker symbol
               quote.setTickerSymbol(lineElements[0]);

               // The second element is the value
               quote.setValue(Double.parseDouble(lineElements[1]));
               
               // The third element is the date
               quote.setQuoteDate(DateFunctions.stringToDate(lineElements[2]));
               
               // add the stockquote to the arraylist
               listOfStockQuotes.add(quote);
            }
         }
         return listOfStockQuotes;
      } catch (MyFileException | IOException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

   /**
    * Read in an XML file of StockQuote objects
    *
    * @param fileLocation
    * @return
    */
   public static StockQuoteDataContainer readXMLFile(String fileLocation) throws MyFileException {

      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(StockQuoteDataContainer.class);
         // Create the unmarshaller
         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
         //Unmarshal the file
         return (StockQuoteDataContainer) jaxbUnmarshaller.unmarshal(new File(fileLocation + "/stockquotes.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }

   }

   /**
    * Reads a JSON formatted file of stock quotes and returns an array list of
    * StockQuotes.
    *
    */
   public static ArrayList<StockQuote> readJSONFile(String fileLocation) throws MyFileException {

      ArrayList<StockQuote> listOfStockQuotes = new ArrayList<>();

      try {
         // Create input file
         BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "/stockquotes.json"));

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // fromJson returns an array
         StockQuote[] stockquoteArray = gson.fromJson(jsonFile, StockQuote[].class);

         // Convert to arraylist for the data model
         listOfStockQuotes.addAll(Arrays.asList(stockquoteArray));
         return listOfStockQuotes;
      } catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }
}
