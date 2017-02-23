package me.zero.client.api.manage;

import com.google.common.collect.Sets;
import me.zero.client.api.util.interfaces.Nameable;
import me.zero.client.api.value.Value;
import me.zero.client.api.value.Values;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Nodes can have children, values, and can be saved/loaded.
 *
 * @since 1.0
 *
 * Created by Brady on 2/21/2017.
 */
public class Node<T extends Node> implements Nameable {

    private Set<T> children = Sets.newLinkedHashSet();
    private Set<Value> values = Sets.newLinkedHashSet();

    protected String name;
    protected String description;

    protected Node() {
        Values.discover(this);
    }

    public final void addChildren(T... data) {
        this.addChildren(Arrays.asList(data));
    }

    public final void addChildren(Collection<T> children) {
        this.children.addAll(children);
    }

    public final Set<T> getChildren() {
        return Sets.newLinkedHashSet(this.children);
    }

    public final void addValue(Value value) {
        if (!this.values.contains(value))
            this.values.add(value);
    }

    public final Set<Value> getValues() {
        return this.values;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }
}
