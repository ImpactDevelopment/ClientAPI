package me.zero.client.api.manage;

import com.google.common.collect.Sets;
import me.zero.client.api.value.Property;
import me.zero.client.api.util.interfaces.Nameable;
import me.zero.client.api.value.Value;
import me.zero.client.api.value.Values;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Nodes can have children, values, properties, and can be saved/loaded.
 *
 * @author Brady
 * @since 2/21/2017 12:00 PM
 */
public class Node implements Nameable {

    /**
     * Child nodes
     */
    private final Set<Node> children = Sets.newLinkedHashSet();

    /**
     * Values
     */
    private final Set<Value> values = Sets.newLinkedHashSet();

    /**
     * Properties
     */
    private final Set<Property> properties = Sets.newLinkedHashSet();

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
     * @param children The children
     */
    protected final void addChildren(Node... children) {
        this.addChildren(Arrays.asList(children));
    }

    /**
     * Adds children to the list of child nodes
     *
     * @param children The children
     */
    public final void addChildren(Collection<Node> children) {
        this.children.addAll(children);
    }

    /**
     * @return The set of all child nodes
     */
    public final Set<Node> getChildren() {
        return Sets.newLinkedHashSet(this.children);
    }

    /**
     * Sets the property with the specified label's value
     *
     * @param label Label of the property
     * @param value Value being set
     */
    public final void setProperty(String label, Object value) {
        Property property = getProperty(label);
        if (property != null) {
            property.setValue(value);
            return;
        }

        properties.add(property = new Property(label));
        property.setValue(value);
    }

    /**
     * Gets a property with the specified label from the properties set
     *
     * @param label Target property label
     * @return Property found, null if not found
     */
    public final Property getProperty(String label) {
        return properties.stream().filter(property -> property.getLabel().equalsIgnoreCase(label)).findFirst().orElse(null);
    }

    /**
     * Adds a value to this node
     *
     * @param value The value
     */
    public final void addValue(Value value) {
        if (!this.values.contains(value))
            this.values.add(value);
    }

    /**
     * Gets a value from its ID
     *
     * @param id The value's ID
     */
    public final Value getValue(String id) {
        return values.stream().filter(value -> value.getId().equals(id)).findFirst().orElse(null);
    }

    /**
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
