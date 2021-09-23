// --== CS400 File Header Information ==--
// Name: Connor Phillips
// Email: cjphillips@wisc.edu
// Team: Red
// Role: Data Wrangler
// TA: Hang
// Lecturer: Florian
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
public class BusReader implements BusReaderInterface
{
	/**
   	 * Creates an ArrayList of bus stops that only contain name and schedule values
   	 * @param file the Reader for the csv file containing bus names and schedules
   	 * @return ArrayList of BusStops generated from csv file which only have names and a schedule
   	 */
   private ArrayList<BusStop> readBusStop(Reader file)
    {
        ArrayList<BusStop> busList=new ArrayList<BusStop>();
        String[] list=null;
        BusStop newStop;
        //Attempts to insert file path into the Reader
        try{
            String line;
            BufferedReader reader=new BufferedReader(file);
            //Reads through lines of csv file and assigns BusStops with their values
            while((line=reader.readLine()) !=null) {
                list=line.split(",");
                newStop=new BusStop();
                //Assigns name
                newStop.setName(list[0]);
                //Assigns schedule
                newStop.setSchedule(list[1]);
                busList.add(newStop);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
        return busList;
    }

   /**
  	 * Creates 3 ArrayLists which are put into one larger Array List.  The first contains start bus stops, the second 
  	 * 		contains end bus stops and the third contains the weight for that route.
  	 * @param file the Reader to read the csv file containing the start,end and weights
  	 * @param stop a list of Bus Stops that must already contain names and schedules
  	 * @return ArrayList of ArrayList of Objects that contains the start, end, and weights of bus routes
  	 */
   private ArrayList<ArrayList<Object>> readRoutes(Reader file,ArrayList<BusStop> stop)
    {
	    //List that will hold the 3 other lists
        ArrayList<ArrayList<Object>> bigList=new ArrayList();
        //List of all start points for bus route
        ArrayList<Object> startList=new ArrayList();
        //List of all possible end points for bus route
        ArrayList<Object> endList=new ArrayList();
        //List of all weights for bus routes
        ArrayList<Object> weights=new ArrayList();
        //List of all end points for routes
        ArrayList<BusStop> stops=new ArrayList();
        stops=stop;
        String[] list=null;
        BusStop newStop;
        try
        {
         String line;
         BufferedReader reader=new BufferedReader(file);
         //Reads all lines of file
         while((line=reader.readLine()) !=null)
         {
             list=line.split(",");
             newStop=new BusStop();
             //Goes through all possible stops and searches for start matches
             for(int i=0;i<stops.size();i++)
             {
                 if(stops.get(i).getName().equals(list[0]))
                   startList.add(stops.get(i)); 
                }
             //Goes through all possible stops and checks for end matches
             for(int i=0;i<stops.size();i++)
             {
                 if(stops.get(i).getName().equals(list[1]))
                  endList.add(stops.get(i));
                }
             Integer add=Integer.parseInt(list[2]);
             weights.add(add);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
        bigList.add(startList);
        bigList.add(endList);
        bigList.add(weights);
        return bigList;
    }
    
   /**
  	 * Creates an ArrayList of bus stops contain completed bus stop objects
  	 * @param in an ArrayList of ArrayList of Objects that contain start points, end points, and weights
  	 * @param bus and ArrayList of Bus Stops that contain only a name and schedule
  	 * @return ArrayList of completed bus stop objects
  	 */
   private ArrayList<BusStop> connectStops(ArrayList<ArrayList<Object>> in,ArrayList<BusStop> bus)
    {
    	ArrayList<BusStop> end=new ArrayList();
    	ArrayList<ArrayList<Object>> info=new ArrayList();
    	info=in;
    	ArrayList<BusStop> busses=new ArrayList();
    	busses=bus;
    	//Goes through all creates bus stops and matches them with their respective routes
    	for(int i=0;i<busses.size();i++)
    	{
    		for(int j=0;j<info.get(0).size();j++)
    		{
    			if(busses.get(i).getName().equals(((BusStop) info.get(0).get(j)).getName()))
    				{
    				busses.get(i).addStop(((BusStop) info.get(1).get(j)).getName());
    				busses.get(i).addWeight((Integer) info.get(2).get(j));
    				}
    		}
    	}
    	//Adds all the completed busses to new list to return
    	for(int i=0;i<busses.size();i++){
    		end.add(busses.get(i));
    	}
    	return end;
    	}
   
   /**
  	 * Creates an ArrayList of completed bus stops from two csv files
  	 * @param busNames the file Reader that reads a csv file containing the names and schedules of bus stops
  	 * @param busRoutes the file Reader that reads a csv file containing the start points, end points, and weights for those routes
  	 * @return ArrayList of completed BusStops
  	 */
   public ArrayList<BusStop> readFiles(Reader busNames, Reader busRoutes)
    {
    	ArrayList<BusStop> busses=new ArrayList();
    	busses=readBusStop(busNames);
    	ArrayList<ArrayList<Object>> routes=new ArrayList();
    	routes=readRoutes(busRoutes,busses);
    	ArrayList<BusStop> completedStops=new ArrayList();
    	completedStops=connectStops(routes,busses);
    	return completedStops;
    	
    }
    

}
    
    

