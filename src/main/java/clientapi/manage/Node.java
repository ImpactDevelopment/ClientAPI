/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.manage;

import clientapi.value.Property;
import clientapi.value.holder.ValueHolder;
import clientapi.util.interfaces.Nameable;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Nodes can have children, values, properties
 *
 * @author Brady
 * @since 2/21/2017 12:00 PM
 */
public class Node extends ValueHolder implements Nameable {

    /**
     * Child nodes
     */
    private final Set<Node> children = Sets.newLinkedHashSet();

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
    public final Collection<Node> getChildren() {
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

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }
}
