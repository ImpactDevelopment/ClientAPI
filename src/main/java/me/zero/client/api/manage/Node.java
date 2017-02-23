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

    /**
     * Child nodes
     */
    private Set<T> children = Sets.newLinkedHashSet();

    /**
     * Values
     */
    private Set<Value> values = Sets.newLinkedHashSet();

    /**
     * Name of the node
     */
    protected String name;

    /**
     * Description of the node
     */
    protected String description;


    protected Node() {
        Values.discover(this);
    }

    /**
     * Adds children to the list of child nodes
     *
     * @since 1.0
     *
     * @param children The children
     */
    public final void addChildren(T... children) {
        this.addChildren(Arrays.asList(children));
    }

    /**
     * Adds children to the list of child nodes
     *
     * @since 1.0
     *
     * @param children The children
     */
    public final void addChildren(Collection<T> children) {
        this.children.addAll(children);
    }

    /**
     * @since 1.0
     *
     * @return The set of all child nodes
     */
    public final Set<T> getChildren() {
        return Sets.newLinkedHashSet(this.children);
    }

    /**
     * Adds a value to this node
     *
     * @since 1.0
     *
     * @param value The value
     */
    public final void addValue(Value value) {
        if (!this.values.contains(value))
            this.values.add(value);
    }

    /**
     * @since 1.0
     *
     * @return The set of all values
     */
    public final Set<Value> getValues() {
        return Sets.newLinkedHashSet(this.values);
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
