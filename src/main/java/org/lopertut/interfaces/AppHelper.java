package org.lopertut.interfaces;

import org.lopertut.models.Purchase;
import org.lopertut.models.Toy;

import java.util.List;


public interface AppHelper<T> {
    T create();
    boolean printList(List<T> elements);
    int delete(List<T> elements);
    T edit(List<T> elements);
}
