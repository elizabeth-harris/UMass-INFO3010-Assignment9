package datamodels;

import java.io.Serializable;
import java.util.Objects;

public class InvestorStockQuote implements Serializable {

	private StockQuote stock = new StockQuote();
	private int shares;

	public InvestorStockQuote() {
	}

	public InvestorStockQuote(StockQuote stock, int shares) {
		this.stock = stock;
		this.shares = shares;
	}

	public int getShares() {
		return this.shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public StockQuote getStock() {
		return this.stock;
	}

	public void setStock(StockQuote stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(shares, stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestorStockQuote other = (InvestorStockQuote) obj;
		return shares == other.shares && Objects.equals(stock, other.stock);
	}

	@Override
	public String toString() {
		return "InvestorStockQuote [stock=" + stock + ", shares=" + shares + "]";
	}



}
