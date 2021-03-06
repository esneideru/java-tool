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

import com.alibaba.fastjson.JSONObject;
import org.osgl.*;
import org.w3c.dom.Document;

public class XmlDocumentToJsonObject extends Lang.TypeConverter<Document, JSONObject> {
    @Override
    public JSONObject convert(Document document) {
        return convert(document, null);
    }

    @Override
    public JSONObject convert(Document document, Object hint) {
        String rootTag = OsglConfig.xmlRootTag();
        String listItemTag = OsglConfig.xmlListItemTag();
        if (hint instanceof JsonXmlConvertHint) {
            JsonXmlConvertHint hint1 = $.cast(hint);
            rootTag = hint1.rootTag;
            listItemTag = hint1.listItemTag;
        } else if (hint instanceof String) {
            rootTag = $.cast(hint);
        }
        return (JSONObject) XmlDocumentToJsonUtil.convert(document, rootTag, listItemTag, false);
    }
}
