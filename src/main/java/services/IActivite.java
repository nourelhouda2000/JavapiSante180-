package services;

public interface IActivite<T>{
    void addEntity(T t);
    void updateEntity(T t);
    void deleteEntity(T t);
}
