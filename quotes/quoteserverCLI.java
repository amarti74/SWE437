/* SWE437-001
 * Assignment 2
 * Developed by: Amilcar Martinez and Artin Melakian
 * 
 * 
 */

package quotes;

import java.util.ArrayList;
import java.util.Scanner;


public class quoteserverCLI {
	
	//	quoteList will hold the list of quotes read in from quotes.xml
	private static QuoteList quoteList;
	//	searchList will hold the last five Strings that have been searched
	private static ArrayList<String> searchList = new ArrayList<String>();

	public static void main(String[] args) {	
		Scanner s = new Scanner(System.in);
		String searchText = null;
		QuoteList randQ = new QuoteList();
		QuoteSaxParser parser = new QuoteSaxParser("quotes.xml");
		//	Loads all quotes in quotes.xlm into randQ
		
		quoteList = parser.getQuoteList();
		
		//quoteList = parser.getQuoteList();
		
		//	Get random quote and print it
		Quote randomQuote = quoteList.getRandomQuote();
		printRandomQuote( randomQuote );

		
		// Main while loop, program runs until user triggers program Exit. See Switch statement.
		while(true) {
			//Print the main menu and prompt for user input
			menu();
			int input = s.nextInt();
			
			switch(input) {
				case 1:	//User input is 1, print another random quote
		
					randomQuote = quoteList.getRandomQuote();
					printRandomQuote( randomQuote );
		
					break;
				case 2 : //user input is 2, prompt user for search string, search by author
					searchText = helper(s);
					maintainSearchList( searchText );
					search(searchText, 2);
					break;
					
				case 3:	//user input is 3, prompt user for search string, search by quote
					searchText = helper(s);
					maintainSearchList( searchText );
					search(searchText, 3);
					break;
					
				case 4:	//user input is 4, prompt user for search string, search by both quote and author
					searchText = helper(s);
					maintainSearchList( searchText );
					search(searchText, 4);
					break;
					
				case 5:	// user input is 5, exit the program
					System.exit(0);
					break;
					
				case 6:	// user input 6 - 10 corresponds to the "RECENT SEARCH" menu. Take the recent search the user selected and display its results again.
				case 7:
				case 8:
				case 9:
				case 10:
					if( (input - 6) < searchList.size()  )		
						// Subcontract 6 from input, its the actual location of the recent search selected by the user in recent searches string list.
						//If the actual location selected exists, print it, else print error.
					{
						search( searchList.get( input - 6 ), 4);
						maintainSearchList( searchList.get( input - 6 ));
					}
					else
					{
						System.out.println("Invalid selection, empty search string.");
					}
					break;
			}// end switch
			
			System.out.println();

		}// end while loop
		
		
	}// end of main
	
	
	private static void printRandomQuote( Quote quote)
	{
		//PURPOSE: To print the program banner and the random quote passed as argument
		
		System.out.println(   "         The GMU Quote Generator"
				+ "\n______________________________________");
		System.out.println("\nRandom quote of the day\n");
		System.out.println(quote.getQuoteText());

		System.out.println("                  " + quote.getAuthor());
	}
	
	private static void maintainSearchList( String prevSearch )
	{
		// PURPOSE: Maintains the previous five searches in an array list searchList.
		searchList.add( prevSearch);
		if( searchList.size() > 5 )
			searchList.remove(0);
		
	}
	
	private static String helper(Scanner s) {
		//PURPOSE: prompts the user for search string
		
		System.out.println("Enter your quote to search\n");
		String searchText = "";
		searchText = s.nextLine();
		searchText = s.nextLine();
		return searchText;
	}
	
	private static void search(String searchText, int searchScopeInt) {
		//PURPOSE: to take the users search string and context and print results.

		   if (searchText != null && !searchText.equals(""))
		   {  // Received a search request
			   switch(searchScopeInt) {
				   case 2:
				       searchScopeInt = QuoteList.SearchAuthorVal;
					   break;
				   case 3:
				       searchScopeInt = QuoteList.SearchTextVal;
				       break;
				       default:
					       searchScopeInt = QuoteList.SearchBothVal; // Default
			   }// end of switch statement
			   
			   QuoteList searchRes = quoteList.search (searchText, searchScopeInt);
			   Quote quoteTmp;
			   if (searchRes.getSize() == 0)
			   {
		    	  System.out.println ("Your search - "+ searchText +" - did not match any quotes.");
			   }
			   else
			   {
		    	  System.out.println ("\n");
		    	  for (int i = 0; i < searchRes.getSize() ; i++)
		    	  {
		            quoteTmp = searchRes.getQuote(i);
		            System.out.println (quoteTmp.getQuoteText());
		            System.out.println (" 			" + quoteTmp.getAuthor());
		    	  }
			   }
		   }// end of primary if statement

	}
	
	private static void menu() {
		//PURPOSE: Prints the main menu and recent searches menu side by side.
		
		//Move the recent search string list, which can have a max of 5 elements, to a temp string list. 
		//if the search list doesn't yet have 5 elements, the temp string list will contain the string "(blank)" to make up for the lack of valid saved searches.
		 ArrayList<String> tempList = new ArrayList<String>();
		for( int i = 0; i < 5; i++)
		{
			if( i < searchList.size() )
			{
				tempList.add( searchList.get(i) );
			}
			else
			{
				tempList.add( "(blank)" );
			}
		}
		
		System.out.println("______________________________________\n");
		System.out.print( String.format("%-40s%s", "MAIN MENU", "RECENT SEARCHES") + "\n" 
				+ String.format("%-40s%s%s", "1. Another random quote", "6. ",tempList.get(0)) + "\n"
				+ String.format("%-40s%s%s", "2. Search a quote by author", "7. ", tempList.get(1)) + "\n"
				+ String.format("%-40s%s%s", "3. Search a quote by quote", "8. ", tempList.get(2)) + "\n"
				+ String.format("%-40s%s%s", "4. Search a quote by both", "9. ", tempList.get(3)) + "\n"
				+ String.format("%-40s%s%s", "5. Exit", "10. ", tempList.get(4)) + "\n"
				+ ">> ");
	}	

}
