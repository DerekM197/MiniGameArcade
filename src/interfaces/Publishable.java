package interfaces;

public interface Publishable<T> {
	void add(Subscribable<T> sub);
	void notifySubscribers();
}
