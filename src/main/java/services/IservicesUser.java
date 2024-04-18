package services;
import java.util.List;
public interface IservicesUser  <T>  {



    boolean entityExists(T t);


    void addUser(T t);

    boolean updateUser(T t);

    boolean deleteUser(T t);

    List<T> getAllUsersWithRoleNames();

}
