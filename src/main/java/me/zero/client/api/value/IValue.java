package me.zero.client.api.value;

/**
 * Created by Brady on 1/23/2017.
 */
public interface IValue<T> {

    T getValue();

    void setValue(T value);
}
