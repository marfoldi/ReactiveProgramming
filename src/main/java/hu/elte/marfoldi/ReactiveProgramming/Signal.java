/**
 * 
 */
package hu.elte.marfoldi.ReactiveProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 * This class represents the signals
 * @author marfoldi
 *
 */
public class Signal<T> {
	private T value;
	private List<SignalAction<T>> actions;
	private boolean isConstant;
	
	/**
	 * Constant signal constructor
	 * @param value
	 * @return A constant signal
	 */
	public static <T> Signal<T> createConstantSignal(final T value) {
		Signal<T> constantSignal = new Signal<T>(value);
		constantSignal.isConstant = true;
		return constantSignal;
	}
	
	/**
	 * Signal constructor
	 * @param value
	 */
	public Signal(T value) {
		this.value = value;
		this.actions = new ArrayList<SignalAction<T>>();
	}
	
	/**
	 * Signal constructor /w action
	 * @param value
	 * @param action
	 */
	public Signal(T value, List<SignalAction<T>> action) {
		this.value = value;
		this.actions = action;
	}
	
	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		if(this.isConstant) { // It was a constant signal, don't modify the value
			return;
		}
		else { // Modify the value and call the action
			T oldValue = this.value;
			this.value = value;
			actions.stream().forEach(action -> action.signalChanged(oldValue, value));
		}
	}
	
	public void addAction(SignalAction<T> action) {
		this.actions.add(action);
	}

	/**
	 * Applies a function on the signal value
	 * @param operator
	 * @return this
	 */
	public Signal<T> map(UnaryOperator<T> operator) {
		setValue(operator.apply(value));
		return this;
	}
	
	/**
	 * Joins this signal with another by the given function
	 * @param otherSignal
	 * @param function
	 * @return the joined signal
	 */
	public <U,R> Signal<R> join(Signal<U> otherSignal, BiFunction<T,U,R> function) {
		Signal<R> joinedSignal = new Signal<R>(function.apply(this.value, otherSignal.getValue()));
		
		// Add actions which modifies the joined signal if the value of this or the otherSignal modified
		this.addAction(new SignalAction<T>() {
			@Override
			public void signalChanged(T oldValue, T newValue) {
				if(oldValue != newValue) {
					joinedSignal.setValue(function.apply(newValue, otherSignal.getValue()));
				}
			}
		});
		
		otherSignal.addAction(new SignalAction<U>() {
			@Override
			public void signalChanged(U oldValue, U newValue) {
				if (!oldValue.equals(newValue)) {
					joinedSignal.setValue(function.apply(value, newValue));
				}
			}
		});
		
		return joinedSignal;
	}
	
	/**
	 * Modifies the signal by the given start value
	 * @param accumulater
	 * @param initValue
	 * @return the accumulated signal
	 */
	<R> Signal<R> accumulate(BiFunction<R,T,R> function, final R startValue) {
		Signal<R> accumulatedSignal = new Signal<R>(startValue);
		
		// Add action which modifies the accumulated signal if the value of this modified
		this.addAction(new SignalAction<T>() {
			@Override
			public void signalChanged(T oldValue, T newValue) {
				if(oldValue != newValue) {
					accumulatedSignal.setValue(function.apply(accumulatedSignal.getValue(), newValue));
				}
			}
		});
		
		return accumulatedSignal;
	}
}
