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

import clientapi.util.Tag;
import clientapi.util.interfaces.Taggable;
import clientapi.value.holder.ValueHolder;
import clientapi.util.interfaces.Nameable;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * Nodes can have child nodes, contain values, and be taggable.
 *
 * @author Brady
 * @since 2/21/2017 12:00 PM
 */
public class Node extends ValueHolder implements Nameable, Taggable {

    /**
     * Child nodes
     */
    private final Set<Node> children = new LinkedHashSet<>();

    /**
     * Tags
     */
    private final List<Tag> tags = new ArrayList<>();

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

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final void addTag(Tag tag) {
        if (!getTag(tag).isPresent())
            tags.add(tag);
    }

    @Override
    public final void removeTag(String id) {
        Tag tag;
        if ((tag = getTag(id).orElse(null)) != null)
            tags.remove(tag);
    }

    @Override
    public final boolean hasTag(String id) {
        return getTag(id) != null;
    }

    @Override
    public Optional<Tag> getTag(String id) {
        return this.tags.stream().filter(tag -> tag.getId().equals(id)).findFirst();
    }

    @Override
    public final List<Tag> getTags() {
        return this.tags;
    }
}
