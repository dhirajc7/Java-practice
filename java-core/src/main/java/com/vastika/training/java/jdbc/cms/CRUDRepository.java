package com.vastika.training.java.jdbc.cms;

import java.util.List;

public interface CRUDRepository<T> {

List<T> findAll();
T findById(int id);
boolean update(T t);
boolean deleteById(int id);



}
