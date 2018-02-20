public class netTest {
    public static void main(String[] args) {
       while(true) {
            (new client()).getData("tristan", "bio", 0);
            (new client()).getData("brian", "friends", 0);
            (new client()).getData("bob", "status", 0);
            /*try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
       }
    }
}