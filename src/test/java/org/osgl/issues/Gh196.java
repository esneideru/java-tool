package org.osgl.issues;

/*-
 * #%L
 * Java Tool
 * %%
 * Copyright (C) 2014 - 2019 OSGL (Open Source General Library)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.Test;
import org.osgl.TestBase;
import org.osgl.util.Generics;

import java.util.Map;
import java.util.TreeMap;

public class Gh196 extends TestBase {

    public static class GrandParent<T> {
        T t;
    }

    public static class Parent<K, V, M extends Map<K, V>> extends GrandParent<M> {
    }

    public static class Me extends Parent<String, Integer, TreeMap<String, Integer>> {}

    @Test
    public void test() {
        Map<String, Class> lookup = Generics.buildTypeParamImplLookup(Me.class);
        eq(String.class, lookup.get("K"));
        eq(Integer.class, lookup.get("V"));
        eq(TreeMap.class, lookup.get("M"));
    }

}
