package com.fasoo.sem;

import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import javax.servlet.http.HttpServletRequest;

public class XPathInjectionTest {
    public void test(HttpServletRequest request, Object doc) throws XPathExpressionException {
        
        // 외부로 부터 입력을 받음
        String name = request.getParameter("name");
        String passwd = request.getParameter("password");

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        // 외부 입력이 xpath의 인자로 사용
        XPathExpression expr = xpath.compile("//users/user[login/text()='" + "tiger" + "' and password/text() = '" + passwd + "']/home_dir/text()"); /* BUG */

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            String value = nodes.item(i).getNodeValue();
            if (value.indexOf(">") < 0) {
                System.out.println(value);
            }
        }
    }
}
