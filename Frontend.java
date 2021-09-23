// --== CS400 File Header Information ==--
// Name: <Connor Phillips>
// Email: <cjphillips4@wisc.edu>
// Team: <Red>
// Group: <HF>
// TA: <Hang>
// Lecturer: <Florian>
// Notes to Grader: <optional extra notes>
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
public class Frontend {

private Backend backend;
	public Frontend()
	{
	}
	
	private void genre() 
	{
		//used to loop
		boolean go=true;
		while (go) {
			System.out.println(
					"Enter the number of a corresponding genre that you want to select, enter the number again to deselect.  Press 'x' to go home.");
			//Used to print genres and if they are selected
			for (int i = 0; i < backend.getAllGenres().size(); i++) {
				System.out.print(i + ". " + backend.getAllGenres().get(i));
				for (int j = 0; j < backend.getGenres().size(); j++)
					if (backend.getAllGenres().get(i).equals(backend.getGenres().get(j))) {
						System.out.println(" selected");
						break;
					} else if (j == backend.getGenres().size() - 1) {
						System.out.println(" not selected");
						break;
					}
			}
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			//goes home if x is pressed
			if (input.equals("x"))
				break;
			else {
				int genre = -1;
				//Checks if input is unacceptable string
				try {
					genre = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Please enter only a number corresponding to a genre or 'x' to go home");
					continue;
				}
				//checks that input is in range
				if (genre < 0 || genre >= backend.getAllGenres().size()) {
					System.out.println("Please enter a number within the range of the genres");
					continue;
				}
				//checks if the input is a selected or non selected genre
				boolean remove = false;
				for (int i = 0; i < backend.getAllGenres().size(); i++) {
					for (int j = 0; j < backend.getGenres().size(); j++)
						if (backend.getGenres().get(j).equals(backend.getAllGenres().get(i))) {
							backend.removeGenre(backend.getGenres().get(j));
							remove = true;
						}
				}
				//adds genre if not selected
				if (!remove)
					backend.addGenre(backend.getAllGenres().get(genre));

			}
		}

	}
	
	public void rating()
	{
		//used for loop
		boolean go=true;
		while (go) {
			System.out.println(
					"Enter a number corresponding to a rating to select/deselect, press 'x' to return home");
			//prints ratings and checks if they are selected
			for (int i = 0; i < 11; i++) {
				System.out.print(i + ": ");
				for (int j = 0; j < backend.getAvgRatings().size(); j++) {
					float cast = Float.parseFloat(backend.getAvgRatings().get(j));
					int check = (int) cast;
					if (i == check) {
						System.out.println("selected");
						break;
					} else if (j == backend.getAvgRatings().size() - 1)
						System.out.println("not selected");

				}

			}
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			//goes home if x is input
			if (input.equals("x"))
				break;
			int rate = -1;
			//checks if input is unacceptable string
			try {
				rate = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Please only enter a number in the range of 0-10 or 'x' to go home.");
			}
			//checks that input is in range
			if (rate < 0 || rate > 10)
				System.out.println("Please only enter a number in the range of 0-10 or 'x' to go home.");
			boolean remove = false;
			//checks if input is selected or not
			for (int j = 0; j < backend.getAvgRatings().size(); j++) {
				float cast = Float.parseFloat(backend.getAvgRatings().get(j));
				int check = (int) cast;
				if (rate == check) {
					backend.removeAvgRating("" + cast);
					remove = true;
				}
			}
			//adds rating if not selected
			if (!remove)
				backend.addAvgRating("" + rate);

		}

	}
	
	public void run(Backend b) throws IOException, DataFormatException
	{
		backend=b;
		System.out.println("Welcome to Movie Mapper!");
		//used for loop
		boolean go = true;
		//inserting all ratings into backend
		String x = "";
		for (int i = 0; i < 11; i++) {
			x = "" + i;
			backend.addAvgRating(x);
		}
		//used to keep track of users place in list
		int location=0;
		//loops method until x is pressed
		while (go) {
			
			System.out.println("Your list is starting at position "+location);
			System.out.println(backend.getThreeMovies(location));
			System.out.println(
					"Enter 'g' to go to genre selection mode 'r' to go to rating mode, and 'x' to exit the program.  You can also enter a number to scroll through movie list.");

			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			if (input.equals("g"))
				genre();
			else if (input.equals("r"))
				rating();
			else if (input.equals("x"))
				return;
			//Used to check if the user is trying to go to a different location in list
			int check = -1;
			//User entered a unacceptable string
			try {
				check = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println(
						"Please enter 'g' for genre mode, 'r' for ratings mode, 'x' to end the program or a number between 0-"
								+ (backend.getNumberOfMovies() - 1));
				continue;
			}
			//User entered a number outside of list range
			if (check < 0 || check >= backend.getNumberOfMovies())
				System.out.println(
						"Please enter 'g' for genre mode, 'r' for ratings mode, 'x' to end the program or a number between 0-"
								+ (backend.getNumberOfMovies() - 1));
			else
				location=check;

	}
  }
	
}
