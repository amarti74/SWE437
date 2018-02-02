package quotes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class quoteserver extends JFrame{
	
	private static QuoteList quoteList;
	private static ArrayList<String> searchList = new ArrayList<String>();
	
	
	private JTextField search;
	
	public quoteserver() {
		
//		setSize(WIDTH, HEIGHT); // size of the window
//		addWindowListener(new WindowDestroyer());
//		setTitle("The GMU Quote Generator");
//		Container contentPane = getContentPane();
//		
//		contentPane.setLayout(new BorderLayout());
//		
//		contentPane.setBackground(Color.BLUE);
//
//		JPanel numberPanel = new JPanel();
//		numberPanel.setLayout(new BorderLayout());
//		
//		JTextField numberLineField = new JTextField("Quote of the day", 90); // size of the button
//		numberPanel.add(numberLineField);
//		numberPanel.setFont(null);
//		//numberPanel.
//		contentPane.add(numberPanel, BorderLayout.NORTH);
		
		

		
	}

	public static void main(String[] args) {	
		Scanner s = new Scanner(System.in);
		String searchText = null;
		//quoteserver qs = new quoteserver();
		
		QuoteList randQ = new QuoteList();
		QuoteSaxParser parser = new QuoteSaxParser("quotes.xml");
		randQ = parser.getQuoteList();
		
		quoteList = parser.getQuoteList();
		
		Quote q = randQ.getRandomQuote();
		
		System.out.println(   "         The GMU Quote Generator"
							+ "\n______________________________________");
		System.out.println("\nRandom quote of the day");
		System.out.println("\n" + q.getQuoteText());
		System.out.println("                 ---" + q.getAuthor());
		
		
		
		while(true) {
		menu();
		int input = s.nextInt();
		
		switch(input) {
		case 1:

			Quote q1 = randQ.getRandomQuote();
			System.out.println(   "         The GMU Quote Generator"
					+ "\n______________________________________");
			System.out.println("\nRandom quote of the day");
			System.out.println(q1.getQuoteText());

			System.out.println("                  " + q1.getAuthor());
			break;
		case 2 :
			searchText = helper(s);
			maintainSearchList( searchText );
			search(searchText);
			break;
		case 3:
			searchText = helper(s);
			maintainSearchList( searchText );
			search(searchText);
			break;
		case 4:
			searchText = helper(s);
			maintainSearchList( searchText );
			search(searchText);
			break;
		case 5:
			System.exit(0);
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			if( (input - 6) < searchList.size()  )
			{
				search( searchList.get( input - 6 ));
				maintainSearchList( searchList.get( input - 6 ));
			}
			else
			{
				System.out.println("Invalid selection, empty search string.");
			}
			break;
		}
		
		System.out.println();
		
		//qs.setVisible(true);

		}
		
		
	}
	
//	private static void printSearchList()
//	{
//		// PURPOSE: Prints the last five searches, prints blanks if less than five searches have been made.
//		for( int i = 0; i < 5; i++)
//		{
//			if( i < searchList.size() )
//			{
//				System.out.println( (i+1) + ". " + searchList.get(i) );
//			}
//			else
//			{
//				System.out.println( (i+1) + ". " );
//			}
//		}
//	}
	
	private static void maintainSearchList( String prevSearch )
	{
		// Purpose: Maintains the previous five searches in an array list searchList.
		searchList.add( prevSearch);
		if( searchList.size() > 5 )
			searchList.remove(0);
		
	}
	
	private static String helper(Scanner s) {
		System.out.println("Enter your quote to search\n");
		String searchText = "";
		searchText = s.nextLine();
		searchText = s.nextLine();
		return searchText;
	}
	
	private static void search(String searchText) {
		

		   if (searchText != null && !searchText.equals(""))
		   {  // Received a search request
		      int searchScopeInt = QuoteList.SearchBothVal; // Default
		      

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
		            System.out.println (" 			" + quoteTmp.getAuthor() + " ");
		         }
		         System.out.println ("\n");
		      }
		   }
		   System.out.println (" 	");
		
		
		
	}
	
//	private static void menu() {
//		System.out.println("______________________________________\n");
//		System.out.println("1. Another random quote \n"
//				+ "2. Search a quote by author \n"
//				+ "3. Search a quote by quote \n"
//				+ "4. Search a quote by both \n"
//				+ "5. Print recent search \n"
//				+ "6. Exit");
//	}
	
	private static void menu() {
		//PURPOSE: Prints the main menu and recent searches menu side by side.
		
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
		System.out.println( String.format("%-40s%s", "MAIN MENU", "RECENT SEARCHES") + "\n" 
				+ String.format("%-40s%s%s", "1. Another random quote", "6. ",tempList.get(0)) + "\n"
				+ String.format("%-40s%s%s", "2. Search a quote by author", "7. ", tempList.get(1)) + "\n"
				+ String.format("%-40s%s%s", "3. Search a quote by quote", "8. ", tempList.get(2)) + "\n"
				+ String.format("%-40s%s%s", "4. Search a quote by both", "9. ", tempList.get(3)) + "\n"
				+ String.format("%-40s%s%s", "5. Exit", "10. ", tempList.get(4)) + "\n"
				);
	}	

}
