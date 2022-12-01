package Labs.Lab_3;
import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    HashMap<Location, Waypoint> openWaypoints = new HashMap<>();

    HashMap<Location, Waypoint> closeWaypoints = new HashMap<>();


    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint() {
        /*
        // TODO:  Implement.
        Map.Entry<Location, Waypoint> minEntry = null;
        if (openWaypoints.size() == 0)
            return null;
        for (Map.Entry<Location, Waypoint> entry : openWaypoints.entrySet()) {
            if (minEntry == null || openWaypoints.get(entry).getTotalCost() < minEntry.getValue().getTotalCost())
                minEntry = entry;
        }
        return (Waypoint) minEntry;


        return openWaypoints.values().stream().min(Comparator.comparing(Waypoint::getTotalCost)).get();
         */
        if (openWaypoints.size() == 0)
            return null;
        float minCost = Float.MAX_VALUE;
        Location location = null;
        for (Location loc : openWaypoints.keySet()) {
            float currentValueOfcCost = openWaypoints.get(loc).getTotalCost();
            if (currentValueOfcCost < minCost) {
                minCost = currentValueOfcCost;
                location = loc;
            }
        }
        return openWaypoints.get(location);
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // TODO:  Implement.
        if (openWaypoints.containsKey(newWP.getLocation())) {
            if (openWaypoints.get(newWP.getLocation()).getPreviousCost() > newWP.getPreviousCost()) {
                openWaypoints.put(newWP.getLocation(), newWP);
                return true;
            }
        }
        else {
            openWaypoints.put(newWP.getLocation(), newWP);
        }
        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        // TODO:  Implement.
        return openWaypoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        // TODO:  Implement.
        closeWaypoints.put(loc, openWaypoints.get(loc));
        openWaypoints.remove(loc);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        // TODO:  Implement.
        if (closeWaypoints.containsKey(loc))
            return true;
        return false;
    }

}
