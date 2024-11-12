package org.lopertut.interfaces;

import org.lopertut.models.Purchase;

import java.util.List;


public interface AppHelper<T> {
    T create();
    boolean printList(List<T> elements);
}
