package interfaces;

public interface Publishable<T> {
	void addNeighbor(Subscribable<T> sub);
	void notifySubscribers();
}
