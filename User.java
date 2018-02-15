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
    

    public void bulkAddFriends(ArrayList<User> u)
    {
        friendsList = u;
    }//END bulkAddFriends
    
    public boolean equals(User other) {
        if (name.equals(other.getName()) && age == other.getAge() && hometown.equals(other.getHometown())) {
            return true;
        } else {
            return false;
        } 
    }
    
    public void addFriend(User other) {
        friendsList.add(other);
    }
    
    public void unfriend(String friend) {
        for (int i=0; i<friendsList.size(); i++) {
            if (friendsList.get(i).equals(friend)) {
                friendsList.remove(i);
            }   
        }
    } 
    
    public int countFriends() {
        return friendsList.size(); //this should work I think
    }
    
    public ArrayList<User> getMutualFriends(User other) {
        ArrayList<User> mutlist = new ArrayList<User>();
        for (User u1 : friendsList) {
            for (User u2 : other.getFriendsList()) {
                if (u1.equals(u2)) {
                    mutlist.add(u1);
                } 
            }
        }
        return mutlist;
    }




    public String toString()
    {
        String retStr = "";
        retStr += "Name: \t\t" + name + "\n";
        retStr += "Age: \t\t" + age + "\n";
        retStr += "Hometown: \t" + hometown + "\n";
        for(int i = 1; i <= schools.length; i ++)
        {
            retStr += "School " + i + ": " + schools[i-1] + "\n";
        }
        for (int i=0; i<friendsList.size(); i++) {
            retStr += "Friends with " + friendsList.get(i).getName() + "\n";
        }        
        return retStr;
    }//END toString
    
    

    public String getName()
    {
        return name;
    }//END getName
    public int getAge() {
        return age;
    }
    public String getHometown() {
        return hometown;
    }
    public String[] getSchools() {
        return schools;
    }
    public ArrayList<User> getFriendsList() {
        return friendsList;
    }
    
    
    
    
}//END CLASS