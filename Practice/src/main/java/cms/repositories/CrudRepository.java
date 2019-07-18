package cms.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    T findById(int id);
    boolean updateInfo(T t);
    boolean deleteById(int id);
    boolean insert(T t);
}
