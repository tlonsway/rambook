public class UserFunctionalityTester
{
    public static void main(String[] args)
    {
        User beyonce = new User("Beyonce",30,"DeWitt",new String[]{"JDHS", "SU", null});
        User adele = new User("Adele",28,"DeWitt",new String[]{"JDHS", "Lemoyne", null});
        User katy = new User("Katy",25,"Hollywood",new String[]{"Hollywood High", "UCLA", "UCLA"});
        User legend = new User("Legend",25,"DeWitt",new String[]{"NO HS", "SU", null});
        
        beyonce.addFriend(adele);
        beyonce.addFriend(katy);
        beyonce.addFriend(legend);
    
        adele.addFriend(katy);
        katy.addFriend(adele);
        
        //PRINT EVERYONE
        System.out.println(beyonce);
        System.out.println(adele);
        System.out.println(katy);
        System.out.println(legend);
        
        System.out.println("\n*** HOMETOWN ***");
        for (User u : beyonce.getHometownFriends())
            System.out.println(u.getName());
        
              
        System.out.println("\n*** MUTUAL ***");
        for (User u : beyonce.getMutualFriends(adele))
            System.out.println(u.getName());
        
        System.out.println("\n*** SCHOOLMATES ***");
        for (User u : beyonce.getSchoolmates())
            System.out.println(u.getName());
        
    
            
        User drake = new User("Drake",27,"DeWitt",new String[]{"CBA", null, null});
        User eminem = new User("Eminem",40,"Detroit",new String[]{"Detroit HS", "MSU", "EMU"});    
            
        katy.addFriend(drake);
        katy.addFriend(eminem);
        
        System.out.println("\n*** SUGGEST A FRIEND FOR ADELE***");
        System.out.println(adele.suggestAFriend());
        
        System.out.println("\n*** SUGGEST A FRIEND FOR KATY***");
        System.out.println(katy.suggestAFriend());
    
    }//END MAIN
}//END CLASS
