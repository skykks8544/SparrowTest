package com.fasoo.syn.security;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

public class XSS_TestCase {
    @RequestMapping("/aps/common/makeCommonCode.do")
    public void makeCommonCode(HttpSession session, @ModelAttribute("apApsCommonCodeVO") ApApsCommonCodeVO apApsCommonCodeVO,
                               HttpServletRequest request, HttpServletResponse response,
                               ModelMap model) throws Exception {
        String target = apApsCommonCodeVO.getTarget();
        List resultList = apApsExcelService.selectCommonCode(apApsCommonCodeVO);
        JSONArray jsonArray = new JSONArray();
        jsonArray = JSONArray.fromObject(resultList);
        response.setContentType("text/xml;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(target); /* BUG */
        printWriter.print(jsonArray.toString());
        printWriter.flush();
        printWriter.close();
    }
}
