
public class CardType
	{
		
		private String color;
		private int number;
		private String symbol;
		
		public CardType(String c, int n, String s)
		{
			color = c;
			number = n;
			symbol = s;
		}


		public String getColor()
			{
				return color;
			}


		public void setColor(String color)
			{
				this.color = color;
			}


		public int getNumber()
			{
				return number;
			}


		public void setNumber(int number)
			{
				this.number = number;
			}


		public String getSymbol()
			{
				return symbol;
			}


		public void setSymbol(String symbol)
			{
				this.symbol = symbol;
			}
		
		
		
		
	}
