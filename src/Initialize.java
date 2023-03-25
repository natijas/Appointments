import dao.DbInitializator;

public class Initialize {
    public static void main(String[] args) {
        var db = new DbInitializator();
        db.initialize();
    }
}