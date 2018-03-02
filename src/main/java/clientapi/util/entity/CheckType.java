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

package clientapi.util.entity;

/**
 * @author Brady
 * @since 2/5/2018 2:16 PM
 */
public enum CheckType {

    /**
     * Run before the ALLOW type. If any check of this type fails,
     * then the filter will fail.
     */
    RESTRICT,

    /**
     * Run after the RESTRICT type. If any check of this type passes,
     * then the filter will pass.
     */
    ALLOW
}
