package org.lopertut.interfaces;

import java.util.List;


public interface AppHelper<T> {
    T create();
    boolean printList(List<T> elements);
    boolean removeToy(List<T> elements);
    boolean editToy(List<T> elements);
}
