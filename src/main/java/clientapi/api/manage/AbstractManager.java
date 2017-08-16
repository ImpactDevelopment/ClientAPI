/*
 * Copyright 2017 ZeroMemes
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

package clientapi.api.manage;

/**
 * Marks a manager as being intended for storing entries
 * with an abstract superclass. If Manager#get(Class) is
 * accessed without this interface being implemented, then
 * an {@code ActionNotSupportedException} will be thrown.
 *
 * @author Brady
 * @since 5/24/2017 10:15 AM
 */
public interface AbstractManager {}
