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

package clientapi.load.argument;

import java.util.List;

/**
 * @author Brady
 * @since 10/9/2018
 */
public final class ClassifiedArgument implements Argument {

    private final String classifier;
    private final String value;

    ClassifiedArgument(String classifier, String value) {
        this.classifier = classifier;
        this.value = value;
    }

    @Override
    public final void addToList(List<String> arguments) {
        arguments.add(classifier);
        arguments.add(value);
    }

    @Override
    public final boolean conflicts(Argument argument) {
        if (!(argument instanceof ClassifiedArgument))
            return false;

        return ((ClassifiedArgument) argument).classifier.equals(this.classifier);
    }
}
