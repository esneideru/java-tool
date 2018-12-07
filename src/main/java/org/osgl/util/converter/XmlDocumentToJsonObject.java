package org.osgl.util.converter;

/*-
 * #%L
 * Java Tool
 * %%
 * Copyright (C) 2014 - 2018 OSGL (Open Source General Library)
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

import static org.osgl.util.converter.XmlDocumentToJsonUtil.nameOf;

import com.alibaba.fastjson.JSONObject;
import org.osgl.Lang;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlDocumentToJsonObject extends Lang.TypeConverter<Document, JSONObject> {
    @Override
    public JSONObject convert(Document document) {
        Element element = document.getDocumentElement();
        String name = nameOf(element);
        JSONObject json = new JSONObject();
        json.put(name, XmlDocumentToJsonUtil.convert(element));
        return json;
    }
}
