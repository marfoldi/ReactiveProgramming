/**
 * 
 */
package hu.elte.marfoldi.ReactiveProgramming;

/**
 * Provides a method which describes what to do when a signal value changed
 * @author marfoldi
 *
 */
public interface SignalAction<T> {
	public void signalChanged(T oldValue, T newValue);
}
