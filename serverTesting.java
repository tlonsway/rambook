import java.net.*;
import java.io.*;


public class serverTesting {
    ServerSocket ss;
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    
    public static void main(String[] args) {
        new serverTesting();
    }
    public serverTesting() {
        try {
            ss = new ServerSocket(8888);
            s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            try {
                listenForData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listenForData() throws Exception {
        while(true) {
            while(din.available() == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String dataIn = din.readUTF();
            dout.writeUTF(dataIn);
            break;
        }
        try {
            din.close();
            dout.close();
            s.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}