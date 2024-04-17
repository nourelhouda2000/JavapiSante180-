package services;
import java.util.List;
public interface IservicesRapport <T>{


    boolean entityExists(T t);
    int getIdRapportFromDatabase(T t);
    void updateRendezvousWithRapportId(int idR , int idRapport);

    void addEntity2Rapport(T t, int idR);

    void updateRapport(T t);

    void deleteRapport(T t);

    List<T> getAllDataRapport ();
}
