package services;

public interface IExercice<T>{
    void addEntity(T t);
    void updateEntity(T t);
    void deleteEntity(T t);
}
