/**
 * 
 */
package hu.elte.marfoldi.ReactiveProgramming;

/**
 * This class is for testing purposes
 * @author marfoldi
 *
 */
public class ConsoleAndTimeSignal {
	private static Signal<String> consoleSignal;
	private static Signal<Integer> timeSignal;
	private static Signal<String> joinedSignal;
	
	public static void main(String[] args) {
	    createConsoleSignal();
	    createTimeSignal();
	    joinConsoleAndTimeSignal();
	}
	
	private static void createConsoleSignal() {
		consoleSignal = ConsoleSignal.getSignal();
	}
	
	private static void createTimeSignal() {
		timeSignal = Time.every(1, Times.SECONDS).accumulate((elpashed, value) -> elpashed + 1, 0);
	}
	
	private static void joinConsoleAndTimeSignal() {
		joinedSignal = consoleSignal.join(timeSignal, (x,y) -> x + " (" + y);
		joinedSignal.addAction(new SignalAction<String>() {
			@Override
			public void signalChanged(String oldValue, String newValue) {
				System.out.println(newValue + " seconds)");
				}
			});
	}
}
