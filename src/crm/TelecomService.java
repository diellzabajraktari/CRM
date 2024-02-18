package crm;

import java.util.List;

public interface TelecomService<T> {
    void create(T entity);
    void update(T entity);
    void delete(int id);
    T findById(int id);
    List<T> findAll();
}
