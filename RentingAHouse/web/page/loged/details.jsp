<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>

<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>

<DIV id=view class="main wrap">
    <DIV class=intro>
        <DIV class=lefter>
            <H1>${House.title}</H1>
            <DIV class=subinfo>2013-06-28 14:06:33.0</DIV>
            <DIV class=houseinfo>
                <P>户　　型：<SPAN>${Type.name}</SPAN></P>
                <P>面　　积：<SPAN>${House.floorage}m<SUP>2</SUP></SPAN></P>
                <P>位　　置：<SPAN>${Dist.name},${Street.name}</SPAN></P>
                <P>联系方式：<SPAN>${House.contact}</SPAN></P>
                <P>房    价：<SPAN>${House.price}&nbsp;&nbsp;&nbsp;&nbsp;
<%--                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="">扫码支付</a>--%>
                    </SPAN></P> </DIV>

        </DIV>
        <DIV class=side>
            <P><A class=bold href="../userController/login">北京青鸟房地产经纪公司</A></P>
            <P>资质证书：有</P>
            <P>内部编号:1000</P>
            <P>联 系 人：</P>
            <P>联系电话：<SPAN></SPAN></P>
            <P>手机号码：<SPAN>暂无</SPAN></P></DIV>
        <DIV class=clear></DIV>
        <DIV class=introduction>
            <H2><SPAN><STRONG>房源详细信息</STRONG></SPAN></H2>
            <DIV class=content>
                <P>${House.description}</P></DIV>
        </DIV>
        <DIV class=introduction>
            <H2><SPAN><STRONG>实际图片</STRONG></SPAN></H2>
            <DIV class=content>
                <ul style="float: left;display: inline-block">
                    <c:forEach var="imge" items="${list}" varStatus="0">
                        <li style="display: inline-block"><IMG src="../images/${imge}"></li>
                    </c:forEach>
                </ul>
            </DIV>
        </DIV>
    </DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2010 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
</HTML>
