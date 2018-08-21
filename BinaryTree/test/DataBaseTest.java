import DataBase.Database;
/**
 *
 * @author leo
 */
public class DataBaseTest {
    public static void main(String args[]) {
        Database database =new Database(100,3,"TEST DATABASE");
        System.out.println(database);
    }
}
