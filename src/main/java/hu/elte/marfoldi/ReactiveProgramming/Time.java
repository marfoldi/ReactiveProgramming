/**
 * 
 */
package hu.elte.marfoldi.ReactiveProgramming;

/**
 * This class provides a method to create a "time signal"
 * @author marfoldi
 *
 */
public class Time {
	/**
	 * Creates a signal which value changes in every multiplier * MILLISECONDS
	 * @param value
	 * @param unit
	 * @return a new signal
	 */
	public static Signal<Integer> every(int value, Times unit) {
		final Signal<Integer> signal = new Signal<Integer>(0);
		
		Runnable timeSignalRunnable = () -> {
				for(;;) {
					signal.setValue((signal.getValue()+1)%3600);
					try {
						Thread.sleep(value * unit.getValue());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		};
		new Thread(timeSignalRunnable).start();
		
		return signal;
	}
}
