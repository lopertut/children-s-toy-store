package org.lopertut.interfaces;

import java.util.List;


public interface Service<T> {
    boolean add();
    boolean edit();
    boolean remove();
    boolean print();
    List<T> list();
}