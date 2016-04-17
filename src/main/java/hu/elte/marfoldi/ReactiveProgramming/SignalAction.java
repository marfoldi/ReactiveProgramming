/**
 * 
 */
package hu.elte.marfoldi.ReactiveProgramming;

/**
 * @author marfoldi
 *
 */
public interface SignalAction<T> {
	public void signalChanged(T oldValue, T newValue);
}
