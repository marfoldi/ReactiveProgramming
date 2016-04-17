/**
 * 
 */
package hu.elte.marfoldi.ReactiveProgramming;

/**
 * Enumeration for time units
 * @author marfoldi
 *
 */
public enum Times {
	MILLISECONDS(1),
	SECONDS(MILLISECONDS.getValue()*1000),
	MINUTES(SECONDS.getValue()*60),
	HOURS(MINUTES.getValue()*60),
	DAYS(HOURS.getValue()*24);
	
	private int value;
	
	private Times(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
