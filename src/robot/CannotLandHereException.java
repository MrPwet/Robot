package robot;

public class CannotLandHereException extends Exception {

    public CannotLandHereException() {
        super("Le robot ne peut être posé sur un terrain infranchissable");
    }
}
