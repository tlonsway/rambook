public class StressTest {
    public static void main(String[] args) {
        while(true) {
            try {
                System.out.println((new client()).getData("tristan", "bio", 0));
                System.out.println((new client()).getData("wez", "status", 0));
                System.out.println((new client()).checkPassword("tristan", "5f4dcc3b5aa765d61d8327deb882cf99"));
                String temp;
                for (int i=0; i<100; i++) {
                    temp = "";
                    temp += i;
                    //(new client()).addUser(temp, temp, i, temp, temp);
                    System.out.println((new client()).checkUserExist("tristan"));
                    System.out.println((new client()).checkUserExist("wez"));
                    //(new client()).addPost(temp, temp, temp);
                    System.out.println((new client()).getNumPosts(temp));
                    //System.out.println((new client()).getPost(temp, 1));
                }
                System.out.println((new client()).getPost("wez", 1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}