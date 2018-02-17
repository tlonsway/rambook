import java.util.*;
public class UserTester
{
    public static void main(String[] args)
    {
        String[] schooolz = new String[5];
        User test1 = new User("brian", 21, "Atlanta gorge",schooolz );
        User test2 = new User("tristan", 5, "Dewitt", schooolz);
        User test3 = new User("sam" , 20, "jamesville", schooolz);
        test1.addFriend(test2);
        System.out.println(test1.toString());;
        test2.addFriend(test3);
        test1.unfriend(test2.getName());
        System.out.println(test1.toString());
        test1.addFriend(test2);
        System.out.println("Num Friends: " + test1.countFriends());
        System.out.println("Does test1 equal test2: " + test1.equals(test2));
        ArrayList<User> mutualFriends = test1.getMutualFriends(test1);
        System.out.println("test1 mutual friends: " + mutualFriends.get(0).getName());
        System.out.println(test2.toString());
        User test4 = new User("bob",30, "Dewitt", schooolz);
        test4.addFriend(test2);
        test4.addFriend(test3);
        mutualFriends.add(test4.getHometownFriends(test4).get(0));
        System.out.println("Hometown friends of test4: " + mutualFriends.get(1).getName());
        System.out.println("Suggested friend of test1: " + test1.suggestAFriend().getName());
    }
}   