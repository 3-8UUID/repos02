<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
    <script type="text/javascript" src="../scripts/jquery-3.0.0.min.js"></script>
</HEAD>
<body>

Language(语言):
<a href="?lang=zh_CN"><spring:message code="language.cn"/></a> &nbsp;&nbsp
<a href="?lang=en_US"><spring:message code="language.en"/></a>
<h1>
    <spring:message code="welcome"/>
</h1>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT><spring:message code="title"/> </DT>
            <DD class=past><spring:message code="table"/> </DD>
        </DL>
        <DIV class=box>
            <FORM id=add_action method=post name=add.action enctype="multipart/form-data"
                  action="../houseController/fileUpload">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field><spring:message code="file"/>：</TD>
                            <TD><input name=files type="file"></TD>
                        </TR>
                        <TR>
                            <TD class=field><spring:message code="file"/>：</TD>
                            <TD><input name=files type="file"></TD>
                        </TR>
                        <TR>
                            <TD class=field><spring:message code="file"/>：</TD>
                            <TD><input name=files type="file"></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons><INPUT value=<spring:message code="upload"/> type=submit>
                    </DIV>
                </DIV>
            </FORM>
        </DIV>
    </DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT><spring:message code="ban"/></DT>
        <DD><spring:message code="about"/></DD>
    </DL>
</DIV>
</BODY>
</HTML>