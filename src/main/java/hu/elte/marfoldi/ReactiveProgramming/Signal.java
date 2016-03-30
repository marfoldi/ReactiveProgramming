/**
 * 
 */
package hu.elte.marfoldi.ReactiveProgramming;

import java.util.function.UnaryOperator;

/**
 * @author marfoldi
 *
 */
public class Signal<T> {
	private T value;
	private T lastValue;
	
	public T map(UnaryOperator<T> operator) {
		return operator.apply(value);
	}
}
