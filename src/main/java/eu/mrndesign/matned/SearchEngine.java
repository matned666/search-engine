package eu.mrndesign.matned;

public interface SearchEngine<T, E> {

    T find(T source, E target);

}
