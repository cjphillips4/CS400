// --== CS400 File Header Information ==--
// Name: Connor Phillips
// Email: cjphillips@wisc.edu
// Team: Red
// Role: Data Wrangler
// TA: Hang
// Lecturer: Florian
import java.util.ArrayList;
public class BusStop implements BusInterface
{
    private String name;
    private String schedule;
    private ArrayList<String> connectedStops;
    private ArrayList<Integer> stopWeights;
    public BusStop(){}
    
    /**
	 * BusStop object that will be stores in the vertices
	 * @param name 	the name of the bus stop
	 * @param Schedule   The schedule of bus arrival times
	 * @param connectedStops	names of all bus stops this bus stop connects to
	 * @param stopWeights the weights of bus routes
	 */
    public BusStop(String name, String schedule,ArrayList<String> connectedStops, ArrayList<Integer> stopWeights)
    {
     this.name=name;
     
     this.connectedStops=new <String>ArrayList();
     this.stopWeights=new<Integer>ArrayList();
     this.schedule=schedule;
     this.connectedStops=connectedStops;
     this.stopWeights=stopWeights;
    }
    
    /**
   	 * retrieves name of bus stop
   	 * @return String value for name of bus stop
   	 */
    public String getName(){return name;}
    
    /**
   	 * retrieves schedule of bus stop
   	 * @return String value for schedule of bus stop
   	 */
    public String getSchedule(){return schedule;}
    
    /**
   	 * retrieves all connected bus stops to this bus stop
   	 * @return ArrayList of Strings containing all connected bus stops
   	 */
    public ArrayList<String> getConnectedStops(){return connectedStops;}
    
    /**
   	 * retrieves weights of bus routes of bus stop
   	 * @return ArrayList of integers representing the weights of bus routes
   	 */
    public ArrayList<Integer> getStopWeights(){return stopWeights;}
    
    /**
   	 * Sets the name of the bus stop
   	 * @param name	the name of the bus stop
   	 */
    public void setName(String name)
    {
     this.name=name;   
    }
    
    /**
   	 * Sets the schedule of the bus stop
   	 * @param name	the name of the bus stop
   	 */
    public void setSchedule(String schedule)
    {
     this.schedule=schedule;   
    }
    
    /**
   	 * Sets the connected stops of the bus stop
   	 * @param connectedStops an ArrayList of all the connected bus stops
   	 */
    public void setConnectedStops(ArrayList<String> connectedStops)
    {
     this.connectedStops=connectedStops;   
    }
    
    /**
   	 * Adds a stop to list of connectedStops
   	 * @param stop name of the new connected stop
   	 */
    public void addStop(String stop)
    {
    	if(connectedStops==null)
    		connectedStops=new ArrayList();
    	this.connectedStops.add(stop);
    }
    
    /**
   	 * Adds a new weight to the stopWeights list
   	 * @param weight the value of new route weight
   	 */
    public void addWeight(int weight)
    {
    	if(stopWeights==null)
    		stopWeights=new ArrayList();
    	stopWeights.add(weight);
    	}
    
    /**
   	 * Sets the stopWeights of the bus stop
   	 * @param weights the ArrayList to be set to stopWeights
   	 */
    public void setWeights(ArrayList<Integer> weights)
    {
        stopWeights=weights;
    }
    
    /**
   	 * Creates a string value representing the bus Stop
   	 * @return the String value representing all attributes of the bus stop
   	 */
    public String toString()
    {
        return "\n\n   "+name+"\n Connected stops: "+connectedStops+"\n Time between stops: "+stopWeights+"\n Schedule: "+schedule; 
    }
}