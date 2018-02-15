import java.security.*;
import java.io.*;
public class login {
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
        BufferedWriter bw = new BufferedWriter(new FileWriter("loginlist.txt", true));
	bw.write(username + ":" + hash);
	bw.newLine();
	bw.flush();        
    }
    public static void clearList()  throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter("loginlist.txt", false));
        bw.write("");
    }
}