/*
 *  This Class contains a list which will hold stock quote objects created in the UI
 */
package datacontainers;

import datamodels.StockQuote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// XML document list element name
@XmlRootElement(name = "stockQuoteList")
@XmlAccessorType(XmlAccessType.FIELD)
public class StockQuoteDataContainer {

   /**
    * Simple container that stores elements as a list, can contain duplicates
    * Stores in FIFO order
    */
   // XML document element name
   @XmlElement(name = "stockquote")   
   private List<StockQuote> stockQuoteList = new ArrayList<>();

   /**
    * Container that stores elements as a set of unique elements Random ordering
    */
   private Set stockQuoteSet = new HashSet();

   /**
    * Container that stores elements as a map, can contain duplicates Order not
    * enforced
    */
   private Map stockQuoteMap = new HashMap();

   // getters and setters
   public List<StockQuote> getStockQuoteList() {
      return stockQuoteList;
   }

   public void setStockQuoteList(List<StockQuote> stockQuoteList) {
      this.stockQuoteList = stockQuoteList;
   }

   public Set getStockQuoteSet() {
      return stockQuoteSet;
   }

   public void setStockQuoteSet(Set stockQuoteSet) {
      this.stockQuoteSet = stockQuoteSet;
   }

   public Map getStockQuoteMap() {
      return stockQuoteMap;
   }

   public void setStockQuoteMap(Map stockQuoteMap) {
      this.stockQuoteMap = stockQuoteMap;
   }

}
