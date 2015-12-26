package api;

/**
 * Created by jorge on 26-12-2015.
 */
public interface SeleniumAction<T> {
    T execute(Object param) throws Throwable;
}
