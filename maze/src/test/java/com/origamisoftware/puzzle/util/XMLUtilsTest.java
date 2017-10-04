/*
 *  Copyright  (c)  2017.  Spencer Marks
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
 *
 */

package com.origamisoftware.puzzle.util;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for methods in the XMLUtils class.
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class XMLUtilsTest {


    @Test
    public void testParseDocumentPositive() throws Exception {
        Document document = XMLUtils.parseXML("src/test/test_data/good-map.xml");
        assertNotNull("Verify document was returned", document);
    }

    @Test(expected = SAXParseException.class)
    public void testParseDocumentDataDoesMatchDTD() throws Exception {
        XMLUtils.parseXML("src/test/test_data/invalid-map.xml");
    }
}
