/**
 * 
 */
package hu.elte.marfoldi.ReactiveProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class provides a method to create a "console signal"
 * @author marfoldi
 *
 */
public class ConsoleSignal {
	/**
	 * Creates a signal which contains the latest console input
	 * @return a new signal
	 */
	public static Signal<String> getSignal() {
		final Signal<String> consoleSignal = new Signal<String>(null);
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
	    Runnable consoleSignalRunnable = () -> {
			String input;
			for(;;) {
				try {
					if ((input = bufferedReader.readLine()) != null && input.length() > 0) {
						consoleSignal.setValue(input);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	    };
	    new Thread(consoleSignalRunnable).start();
	    
	    return consoleSignal;
	}
}
