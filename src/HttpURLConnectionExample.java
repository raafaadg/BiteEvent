import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozila/5.0";
	public static boolean loop = true;
	public static boolean loopBite = false;
	
	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();
		JTextField textField = new JTextField();

	    textField.addKeyListener(new Keychecker());		
	    JFrame jframe = new JFrame();

	    jframe.add(textField);

	    jframe.setSize(400, 350);

	    jframe.setVisible(true);
		while(loop){
			System.out.println("EXECUTANDO");
			while(loopBite){
				http.sendGet();
				break;
			}			
		}
		System.exit(0);
	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "http://192.168.4.1/mestrado/mordida";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		//int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		if(response.toString().equals("Morder")){
			click(960,540);
			
			System.out.println("Mordida Detectada!!");
		}

	}
	
	public static void click(int x, int y) throws AWTException{
	    Robot bot = new Robot();
	    //bot.mouseMove(x, y);    
	    //bot.keyPress(KeyEvent.KEY_LOCATION_RIGHT);
	    //bot.keyRelease(KeyEvent.KEY_LOCATION_RIGHT);
	    bot.mousePress(InputEvent.BUTTON3_MASK);
	    bot.mouseRelease(InputEvent.BUTTON3_MASK);
	}
	
	public static void start() throws AWTException{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		if(scan.nextLine().equals("s"))
			loopBite = true; 
		else
			loopBite = false;

	}
}
class Keychecker extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {
	char ch = event.getKeyChar();
    if (ch == 's')
    	HttpURLConnectionExample.loopBite = true;
    else if (ch == 'f')
    	HttpURLConnectionExample.loop = false;
    else
    	System.out.println("Nenhum comando recebido");
    }
}


