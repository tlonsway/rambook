public class netTest {
    public static void main(String[] args) {
        client c = new client();
        client cc = new client();
        client ccc = new client();
        c.getData("tristan", "bio", 0);
        cc.getData("brian", "friends", 0);
        ccc.getData("bob", "status", 0);
    }
}