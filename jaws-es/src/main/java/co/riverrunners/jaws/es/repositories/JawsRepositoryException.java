package co.riverrunners.jaws.es.repositories;

/**
 * Generic exception for jaws respository issues
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public class JawsRepositoryException extends Exception {
    public JawsRepositoryException() {
        super();
    }

    public JawsRepositoryException(String message) {
        super(message);
    }

    public JawsRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public JawsRepositoryException(Throwable cause) {
        super(cause);
    }

    protected JawsRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
