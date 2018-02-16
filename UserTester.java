public class UserTester
{
    public static void main(String[] args)
    {
        String[] schooolz = new String[5];
        User test1 = new User("brian", 21, "Atlanta gorge",schooolz );
        User test2 = new User("tristan", 5, "Dewitt", schooolz);
        test1.addFriend(test2);
        System.out.println(test1.toString());;
        test1.unfriend(test2.getName());
        System.out.println(test1.toString());
    }
}