package edu.neu.coe.info6205.union_find_assignment4;

public interface TypedUF<T> extends UF {
    boolean connected(T t1, T t2) throws UFException;

    void union(T t1, T t2) throws UFException;
}
