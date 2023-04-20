package y;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 2 arguments
        // 1 argum,ent for file
        // 1 arguement for port the server will start on
        String port = args[1];
        String fileName = args[0];

        File cookieFile = new File(fileName);
        if (!cookieFile.exists()) {
            System.out.println("Cookie file not found");
            System.exit(0);
        }
        //testing cookie class
        cookie cookie = new cookie();
        cookie.readCookiesFile(fileName);
        String myCookie = cookie.getRandomCookie();
        System.out.println(myCookie);
        String myCookie2= cookie.getRandomCookie();
        System.out.println(myCookie2);

        // slide 8 - establish server connection
        ServerSocket ss = new ServerSocket(Integer.parseInt(port));
        Socket socket = ss.accept();

        // store the data sent over from the client, e.g. get- cookie

        String msgReceived = "";

        // slide 9 - allow server to read and write over the communication channel

        try (InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            try (OutputStream os = socket.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                // write our logic to receive and send
                while (!msgReceived.equals("close")) {
                    // slide 9 - receive message
                    msgReceived = dis.readUTF();
                    if (msgReceived.equals("get-cookie")){
                        
                        //get a random cookie
                        String randomCookie=cookie.getRandomCookie();

                        //send the random cookie out using dataOutputStream (dos.write(UTF(XXXXX)))
                        dos.writeUTF(randomCookie);
                        dos.flush();

                    } else{
                        dos.writeUTF("");
                        dos.flush();
                    }

                }
                dos.close();
                bos.close();
                os.close();

                // closes all output streams in reverse order
            } catch (EOFException ex) {
                ex.printStackTrace();
            }
            socket.close();
            ss.close();
            is.close();

        } catch (EOFException ex) {

        }

    }
}
