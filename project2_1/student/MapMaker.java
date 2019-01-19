/**
 * Language: Java
 * Author(s): Jason Hicks & Ryan Keihm
 * Project 2 (Part 1), MapMaker.java
 */
package student;

        import model.RailroadBaronsException;
        import model.RailroadMap;

        import java.io.InputStream;
        import java.io.OutputStream;
        import java.io.PrintStream;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.Scanner;

public class MapMaker implements model.MapMaker {

    public Scanner input;
    public HashSet<Space> spaces;
    public ArrayList<Route> routes;
    public HashMap<Integer, Station> stations;
    public PrintStream output;

    /**
     * Loads a map using the data in the given input stream.
     * @param in The {@link InputStream} used to read the {@link RailroadMap
     * map} data.
     * @return RailroadMap (Object)
     * @throws RailroadBaronsException
     */
    @Override
    public RailroadMap readMap(InputStream in) throws RailroadBaronsException {
        input = new Scanner(in);
        String line;
        String[] elements;

        while(input.hasNextLine()){
            line = input.nextLine();

            if(!line.equals("##ROUTES##")){
                elements = line.split(" ");

                spaces.add(new Space(Integer.parseInt(elements[1]), Integer.parseInt(elements[2])));
                 stations.put(Integer.parseInt(elements[0]), new Station(Integer.parseInt(elements[1]),
                         Integer.parseInt(elements[2]), String.valueOf(elements[3])));
            }else{
                elements = line.split(" ");

                routes.add(new Route(stations.get(Integer.parseInt(elements[0])), stations.get
                        (Integer.parseInt(elements[1])), String.valueOf(elements[3])));
            }
        }
        return new student.RailroadMap(spaces, routes);
    }

    /**
     * Writes the specified map in the Railroad Barons map file format to the given output stream. The written map
     * should include an accurate record of any routes that have been claimed, and by which Baron.
     * @param map The {@link RailroadMap map} to write out to the
     * {@link OutputStream}.
     * @param out The {@link OutputStream} to which the
     * {@link RailroadMap map} data should be written.
     *
     * @throws RailroadBaronsException
     */
    @Override
    public void writeMap(RailroadMap map, OutputStream out) throws RailroadBaronsException {

        output = new PrintStream(out);

        for(int i = 0; i < stations.size(); i++){
            Station station = stations.get(i);
            output.println(i + " " + station.getRow() + " " + station.getCol() + " " + station.getName());
        }

        output.println("##ROUTES##");
        for(Route route : routes){
            output.println(route.getOrigin() + " " + route.getDestination() + " " + route.getBaron());
        }
    }
}