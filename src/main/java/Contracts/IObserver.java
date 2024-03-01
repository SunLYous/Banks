package Contracts;

/**
 * Interface for observers.
 */
public interface IObserver {
    /**
     * Updates the observer with new information.
     *
     * @param information the information to update the observer with
     */
    void update(String information);
}
