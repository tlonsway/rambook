import java.util.*;

public class User
{
    //INSTANCE FIELDS
    private String name;
    private int age;
    private String hometown;
    private String[] schools;
    private ArrayList<User> friendsList;
    
    //CONSTRUCTOR - DONE FOR YOU
    //NOTE - it leaves the friendsList empty
    public User(String n, int a, String h, String[] s)
    {
        name = n;
        age = a;
        hometown = h;
        schools = s;
        friendsList = new ArrayList<User>(); 
        
    }//END Constructor
    
    // DONE FOR YOU
    // Accepts a List of Users that will replace the current friendsList
    public void bulkAddFriends(ArrayList<User> u)
    {
        friendsList = u;
    }//END bulkAddFriends
    

    
    // STARTED FOR YOU
    // Should print out all information for the user, nicely formatted
    public String toString()
    {
        String retStr = "";
        retStr += "Name: \t\t" + name + "\n";
        retStr += "Age: \t\t" + age + "\n";
        retStr += "Hometown: \t" + hometown + "\n";
        
        //Need to include schools and friendslist
        
        
        
        return retStr;
    }//END toString
    
    
    // DONE FOR YOU
    // Returns the User's name
    public String getName()
    {
        return name;
    }//END getName
    
    
    
    
    
}//END CLASS