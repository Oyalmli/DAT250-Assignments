import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPPostRequest {

    private static int port = 8080;
    private static String host = "localhost";
    private static String uri = "/todos";

    public static void main(String[] args) {

        try (Socket s = new Socket(host, port)) {

            // construct the HTTP request
            String jsonbody = "{\"summary\":\"summary\",\"description\":\"description\"}";

            System.out.println(jsonbody);
            String httpputrequest =
                    "POST " + uri + " HTTP/1.1\r\n" +
                            "Host: " + host + "\r\n" +
                            "Content-type: application/json\r\n" +
                            "Content-length: " + jsonbody.length() + "\r\n" +
                            "Connection: close\r\n" +
                            "\r\n" +
                            jsonbody +
                            "\r\n";

            // send the response over the TCP connection
            OutputStream output = s.getOutputStream();

            PrintWriter pw = new PrintWriter(output, false);
            pw.print(httpputrequest);
            pw.flush();

            // read the HTTP response
            InputStream in = s.getInputStream();

            Scanner scan = new Scanner(in);
            StringBuilder jsonresponse = new StringBuilder();
            boolean header = true;

            while (scan.hasNext()) {

                String nextline = scan.nextLine();

                if (header) {
                    System.out.println(nextline);
                } else {
                    jsonresponse.append(nextline);
                }

                if (nextline.isEmpty()) {
                    header = false;
                }

            }

            System.out.println("BODY:");
            System.out.println(jsonresponse.toString());

            scan.close();

        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
