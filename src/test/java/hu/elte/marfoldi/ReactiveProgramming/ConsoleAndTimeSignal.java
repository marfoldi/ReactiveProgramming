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
	public static void main(String[] args) {
	    createConsoleSignal();
	    createTimeFromStartSignal();
	}
	
	private static void createConsoleSignal() {
		Signal<String> consoleSignal = ConsoleSignal.getSignal();
	    consoleSignal.addAction(new SignalAction<String>() {
			@Override
			public void signalChanged(String oldValue, String newValue) {
				System.out.println(newValue);
			}
	    });
	}
	
	private static void createTimeFromStartSignal() {
		Signal<Integer> timeSignal = Time.every(1, Times.SECONDS);
		Signal<Integer> timeFromStart = timeSignal.accumulate((elpashed, value) -> elpashed + 1, 0);
		timeFromStart.addAction(new SignalAction<Integer>() {
			@Override
			public void signalChanged(Integer oldValue, Integer newValue) {
				System.out.println(newValue + " seconds");
				}
			});
	}
}
