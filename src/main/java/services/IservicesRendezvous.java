package services;
import java.util.List;
public interface IservicesRendezvous  <T> {

    void addRendezvous(T t);
    void addRendezvous2(T t,int idUser);

    void updateRendezvous(T t);

    void deleteRendezvous(T t);

    List<T> getAllDataRendezvous ();
}
