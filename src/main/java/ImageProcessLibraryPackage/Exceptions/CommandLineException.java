package ImageProcessLibraryPackage.Exceptions;

public class CommandLineException extends Exception {

    private static final String NAME = "<<CommandLineException>> ";
    @Override
    public String getMessage() {
        return NAME + super.getMessage();
    }
}
