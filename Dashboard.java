import java.io.Serializable;

public abstract class Dashboard implements Serializable {
    private static final long serialVersionUID = 1L;
    protected User user;
    protected Library library;

    public Dashboard(User user, Library library) {
        this.user = user;
        this.library = library;
    }

    public abstract void showOptions();
}
