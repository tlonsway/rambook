import java.security.*;
import java.io.*;
public class login {
    private static final String FILENAME = "loginlist.txt";
    public static String hash(String input) throws Exception {
        String original = input;
	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(original.getBytes());
	byte[] digest = md.digest();
	StringBuffer sb = new StringBuffer();
	for (byte b : digest) {
		sb.append(String.format("%02x", b & 0xff));
	}
	return sb.toString();
    }
    public static void addUser(String username, String password) throws Exception {
        String hash = hash(password);
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true));
	bw.write(username + ":" + hash);
	bw.newLine();
	bw.flush();        
    }
    public static void clearList()  throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, false));
        bw.write("");
    }
    public static boolean verify(String username, String password) throws Exception{
        String hash = hash(password);
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));
        String currentLine;
        String user;
        String pass;
        while ((currentLine = br.readLine()) != null) {
            if (currentLine.substring(0, currentLine.indexOf(":")).equals(username)) {
                if (currentLine.substring(currentLine.indexOf(":")+1).equals(hash)) {
                    return true; //returns true if the given user:pass is valid
                }
            }
        }
        return false; //returns false if the given user:pass is invalid
    }
    public static void dumpList() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            System.out.println(currentLine);
        }
    }
}