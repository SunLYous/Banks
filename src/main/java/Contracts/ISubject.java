package Contracts;

/**
 * Interface for subjects.
 */
public interface ISubject {
    /**
     * Attaches an observer to the subject.
     *
     * @param observer the observer to attach
     */
    void Attach(IObserver observer);

    /**
     * Notifies all attached observers with the given message.
     *
     * @param message the message to notify observers with
     */
    void Notify(String message);
}
