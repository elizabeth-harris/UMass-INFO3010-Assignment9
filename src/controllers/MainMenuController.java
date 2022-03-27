/*
 * Listens for events on the menu form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 */
package controllers;

import controllers.inputformcontrollers.InputBrokerFormController;
import controllers.inputformcontrollers.InputInvestmentCompanyFormController;
import controllers.inputformcontrollers.InputInvestorFormController;
import controllers.inputformcontrollers.InputStockQuoteFormController;
import controllers.reportformcontrollers.ListAllBrokersController;
import controllers.reportformcontrollers.ListAllInvestmentCompaniesController;
import controllers.reportformcontrollers.ListAllInvestorsController;
import controllers.reportformcontrollers.ListAllStockQuotesController;
import java.awt.event.ActionListener;
import datacontainers.StockQuoteDataContainer;
import exceptionhandlers.DatabaseException;
import exceptionhandlers.FileIOErrorPopup;
import exceptionhandlers.MyFileException;
import utilities.db.DatabaseIO;
import utilities.io.StockQuoteIO;
import utilities.io.BrokerIO;
import utilities.io.InvestmentCompanyIO;
import utilities.io.InvestorIO;

// DONE:  Add additional import statements
import datacontainers.BrokerDataContainer;
import datacontainers.InvestmentCompanyDataContainer;
import datacontainers.InvestorDataContainer;
import view.MainMenu;

public class MainMenuController implements ActionListener {

	// File location
	private String fileLocation;
	// Log file location;
	private String logfilelocation;

	// The data models are instantiated here and passed to the
	// constructors for the controllers
	private StockQuoteDataContainer stockQuoteDataContainer = new StockQuoteDataContainer();
	// DONE: Add additional data containers
	private BrokerDataContainer brokerDataContainer = new BrokerDataContainer();
	private InvestmentCompanyDataContainer investmentCompanyDataContainer = new InvestmentCompanyDataContainer();
	private InvestorDataContainer investorDataContainer = new InvestorDataContainer();

	/**
	 * Constructor
	 *
	 * @param fileLocation
	 * @param log          file location
	 */
	public MainMenuController(String fileLocation, String logfilelocation) {
		this.fileLocation = fileLocation;
		this.logfilelocation = logfilelocation;
	}

	// The main menu form gets created here. Notice it takes this controller object
	// as an argument to the constructor
	private MainMenu mainMenu = new MainMenu(this);

	/**
	 * The ActionListener interface contains a single method, actionPerformed
	 */
	public void actionPerformed(java.awt.event.ActionEvent event) {

		// Figure out which button was clicked
		String menuItemClicked = event.getActionCommand();

		// create the controller which will open the correct form (refer to the
		// controller constructor
		// methods do determine which data container classes need to be passed to the
		// controller constructors)
		if (menuItemClicked.equals("Add Stock Quote")) {
			InputStockQuoteFormController inputController = new InputStockQuoteFormController(stockQuoteDataContainer);
		} else if (menuItemClicked.equals("List Available Stocks")) {
			ListAllStockQuotesController reportController = new ListAllStockQuotesController(stockQuoteDataContainer);
		}
		// DONE Add additional menu items to add and report (this should already be done
		// for assignment 5)
		if (menuItemClicked.equals("Add Investment Company")) {
			// DONE: create an input form controller object for the investment company and
			// pass the correct containers to the constructor
			InputInvestmentCompanyFormController inputController = new InputInvestmentCompanyFormController(
					investmentCompanyDataContainer, brokerDataContainer);
		} else if (menuItemClicked.equals("List Investment Companies")) {
			// DONE: create a report controller object for the investment company and pass
			// the correct containers to the constructor
			ListAllInvestmentCompaniesController reportController = new ListAllInvestmentCompaniesController(
					investmentCompanyDataContainer);
		}
		if (menuItemClicked.equals("Add Broker")) {
			// DONE: create an input form controller object for the broker and pass the
			// correct containers to the constructor
			InputBrokerFormController inputController = new InputBrokerFormController(brokerDataContainer,
					investorDataContainer);
		} else if (menuItemClicked.equals("List Brokers")) {
			// DONE: create a report controller object for the broker and pass the correct
			// containers to the constructor
			ListAllBrokersController reportController = new ListAllBrokersController(brokerDataContainer);
		}
		if (menuItemClicked.equals("Add Investor")) {
			// DONE: create an input form controller object for the investor and pass the
			// correct containers to the constructor
			InputInvestorFormController inputController = new InputInvestorFormController(investorDataContainer,
					stockQuoteDataContainer);
		} else if (menuItemClicked.equals("List Investors")) {
			// DONE: create a report controller object for the investor and pass the correct
			// containers to the constructor
			ListAllInvestorsController reportController = new ListAllInvestorsController(investorDataContainer);
		} else if (menuItemClicked.equals("Exit")) {
			System.exit(0);
		} else if (menuItemClicked.equals("Save Data")) {
			try {
				StockQuoteIO.writeSerializedFile(fileLocation, stockQuoteDataContainer);
				StockQuoteIO.writeTextFile(fileLocation, stockQuoteDataContainer);
				StockQuoteIO.writeXMLFile(fileLocation, stockQuoteDataContainer);
				StockQuoteIO.writeJSONFile(fileLocation, stockQuoteDataContainer);
				DatabaseIO.storeStockQuotes(stockQuoteDataContainer);
				// DONE: Add additional calls to write methods for the other objects
				// Broker
				BrokerIO.writeSerializedFile(fileLocation, brokerDataContainer);
				BrokerIO.writeTextFile(fileLocation, brokerDataContainer);
				BrokerIO.writeXMLFile(fileLocation, brokerDataContainer);
				BrokerIO.writeJSONFile(fileLocation, brokerDataContainer);
				DatabaseIO.storeBrokers(brokerDataContainer);
				// Investment Company
				InvestmentCompanyIO.writeSerializedFile(fileLocation, investmentCompanyDataContainer);
				InvestmentCompanyIO.writeTextFile(fileLocation, investmentCompanyDataContainer);
				InvestmentCompanyIO.writeXMLFile(fileLocation, investmentCompanyDataContainer);
				InvestmentCompanyIO.writeJSONFile(fileLocation, investmentCompanyDataContainer);
				DatabaseIO.storeInvestmentCompanies(investmentCompanyDataContainer);
				// Investor
				InvestorIO.writeSerializedFile(fileLocation, investorDataContainer);
				InvestorIO.writeTextFile(fileLocation, investorDataContainer);
				InvestorIO.writeXMLFile(fileLocation, investorDataContainer);
				InvestorIO.writeJSONFile(fileLocation, investorDataContainer);
				DatabaseIO.storeInvestors(investorDataContainer);
			} catch (MyFileException | DatabaseException exp) {
				new FileIOErrorPopup(mainMenu, exp);
			}
		} else if (menuItemClicked.equals("Load Data")) {
			try {
				// All 4 types for demonstration
				// Stock quotes
				stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readSerializedFile(fileLocation));
				stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readTextFile(fileLocation));
				stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readXMLFile(fileLocation).getStockQuoteList());
				stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readJSONFile(fileLocation));
				stockQuoteDataContainer.setStockQuoteList(DatabaseIO.retrieveStockQuotes());
				// Brokers
				brokerDataContainer.setBrokerList(BrokerIO.readSerializedFile(fileLocation));
				brokerDataContainer.setBrokerList(BrokerIO.readTextFile(fileLocation));
				brokerDataContainer.setBrokerList(BrokerIO.readXMLFile(fileLocation).getBrokerList());
				brokerDataContainer.setBrokerList(BrokerIO.readJSONFile(fileLocation));
				brokerDataContainer.setBrokerList(DatabaseIO.retrieveBrokers());
				// Investors
				investorDataContainer.setInvestorList(InvestorIO.readSerializedFile(fileLocation));
				investorDataContainer.setInvestorList(InvestorIO.readTextFile(fileLocation));
				investorDataContainer.setInvestorList(InvestorIO.readXMLFile(fileLocation).getInvestorList());
				investorDataContainer.setInvestorList(InvestorIO.readJSONFile(fileLocation));
				investorDataContainer.setInvestorList(DatabaseIO.retrieveInvestors());
				// Investment Companies
				investmentCompanyDataContainer
						.setInvestmentCompanyList(InvestmentCompanyIO.readSerializedFile(fileLocation));
				investmentCompanyDataContainer.setInvestmentCompanyList(InvestmentCompanyIO.readTextFile(fileLocation));
				investmentCompanyDataContainer.setInvestmentCompanyList(
						InvestmentCompanyIO.readXMLFile(fileLocation).getInvestmentCompanyList());
				investmentCompanyDataContainer.setInvestmentCompanyList(InvestmentCompanyIO.readJSONFile(fileLocation));
				investmentCompanyDataContainer.setInvestmentCompanyList(DatabaseIO.retrieveInvestmentCompanies());
			} catch (MyFileException | DatabaseException exp) {
				new FileIOErrorPopup(mainMenu, exp);
			}
		}

	}

	// Getter used in the Application.java class
	public MainMenu getMainMenu() {
		return mainMenu;
	}
}
