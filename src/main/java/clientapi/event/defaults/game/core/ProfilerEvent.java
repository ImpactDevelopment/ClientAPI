/*
 * Copyright 2018 ImpactDevelopment
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

package clientapi.event.defaults.game.core;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Called when a section is started in the profiler.
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public class ProfilerEvent {

    /**
     * Array containing the current profiler sections
     */
    private static final List<String> SECTIONS = new ArrayList<>();

    /**
     * Complete current profiler section.
     *
     * ex) 'root.tick.render'
     */
    private String sectionPath;

    /**
     * Current profiler section.
     *
     * ex) 'render'
     */
    private final String section;

    private ProfilerEvent(String section) {
        this.section = section;
    }

    /**
     * @return The full profiler section
     */
    public final String getSectionPath() {
        // If the section's path hasn't been created yet, create it.
        if (sectionPath == null) {
            StringJoiner joiner = new StringJoiner(".");
            SECTIONS.forEach(joiner::add);
            sectionPath = joiner.toString();
        }

        return this.sectionPath;
    }

    /**
     * @return The current profiler section
     */
    public final String getSection() {
        return this.section;
    }

    public static final class Start extends ProfilerEvent {

        public Start(String section) {
            super(section);
            SECTIONS.add(section);
        }
    }

    public static final class End extends ProfilerEvent {

        public End() {
            super(SECTIONS.get(SECTIONS.size() - 1));
            if (SECTIONS.size() > 0)
                SECTIONS.remove(SECTIONS.size() - 1);
        }
    }
}
