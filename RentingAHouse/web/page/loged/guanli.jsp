<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
    <TITLE>青鸟租房 - 用户管理</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">

    <META name=GENERATOR>

    <script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        var pageNum = 1;//默认为第一页

        $("a.getPage").live("click", function () {
            pageNum = $(this).text();
            load(pageNum);
        });

        $(".update").live("click", function () {
            var id = $(this).next().val();
            window.location.href = "../houseController/findById?id=" + id;
        })

        $(".del").live("click", function () {
            var id = $(this).next().val();
            var reg = confirm("是否删除此条记录？");
            if (reg) {
                $.get("../houseController/del", "id=" + id, function (data) {
                    if (data <= 0) {
                        alert("删除异常！");
                    }
                    load(pageNum);
                });
            }
        })


        function load(pageno) {
            $("#list tr").remove();
            $("#page li").remove();
            $("span.total").remove();
            $.getJSON("../houseController/loadAdmin", "pageNum=" + pageNum, function (data) {
                var span = "<span class='total'>" + pageNum + "/" + data.totalPage + "页</span>";
                $("#page").after(span);
                for (var i = 0; i < data.list.length; i++) {
                    var tr = "<TR><TD class=house-thumb><SPAN>" +
                        "<A href='../houseController/details?id=" + data.list[i].id + "' target=_\"blank\">";
                    var filenames = data.list[i].fileName.split(",");
                    tr += "<img src=\"../images/"+filenames[0]+"\"width=\"100\" height=\"75\""
                    tr += "alt=\"\"></A></SPAN></TD>";
                    tr += "<TD><DL><DT><A href=\"../houseController/details?id=" + data.list[i].id + "\" target=\"_blank\">" + data.list[i].title + "</A></DT>";
                    tr += "<DD>" + data.list[i].description + "," + data.list[i].floorage + "平米<BR>联系方式：" + data.list[i].contact + "</DD>";
                    tr += "</DL></TD>";
                    tr += "<TD class=house-type><LABEL class=ui-green><INPUT  value=\"上传图片\" type=button name=search class='upload'><input value='" + data.list[i].id + "' type='hidden'></LABEL></TD>";
                    tr += "<TD class=house-type><LABEL class=ui-green><INPUT  value=\"修    改\" type=button name=search class='update'><input value='" + data.list[i].id + "' type='hidden'></LABEL></TD>";
                    tr += "<TD class=house-price><LABEL class=ui-green><INPUT value=\"删    除\" type=button name=search class='del'><input value='" + data.list[i].id + "' type='hidden'></LABEL></TD>";
                    tr += " </TR>";
                    $("#list").append(tr);
                }
                for (var i = 1; i <= data.totalPage; i++) {
                    var cla = data.pageIndex == i ? "class=\"current\"" : "";
                    var li = "<LI " + cla + "><A class='getPage' ";
                    li += " href=\"javascript:void(0);\"";
                    li += "parseContent=\"true\" showError=\"true\" targets=\"houseArea\"";
                    li += "ajaxAfterValidation=\"false\" validate=\"false\"";
                    li += "dojoType=\"struts:BindAnchor\">" + i + "</A>";
                    li += "</LI>";
                    $("#page").append(li)
                }
            })
        }

        $(function () {
            load(pageNum);
        })

        $(".upload").live("click",function () {
            var id = $(this).next().val();
            window.location.href = "../houseController/upload?id="+id;
        })


    </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif">
        <c:if test="${user ne null}">
            <span style="font:normal bold 15px 宋体">欢迎:${user.userName}</span>
            <a href="../userController/exit" style="padding-left:5px">注销</a>
        </c:if>
    </DIV>
    <DIV class=search>
        <LABEL class="ui-green searchs"><a href="../houseController/sxlx" title="">导出表格文件</a></LABEL>
        <LABEL class="ui-green searchs"><a href="../userController/fabu" title="">发布房屋信息</a></LABEL>
        <LABEL class=ui-green><INPUT onclick='document.location="../userController/exit"' value="退       出" type=button
                                     name=search></LABEL>
    </DIV>
</DIV>
<DIV class="main wrap">
    <DIV id=houseArea>
        <TABLE class=house-list>
            <TBODY id="list">

            <TR>
                <TD class=house-thumb><SPAN><A href="details.jsp" target="_blank">
                    <img src="../images/thumb_house.gif"
                         width="100" height="75"
                         alt=""></A></SPAN></TD>
                <TD>
                    <DL>
                        <DT><A href="details.jsp" target="_blank">123</A></DT>
                        <DD>海淀区中关村大街,123平米<BR>联系方式：123</DD>
                    </DL>
                </TD>
                <TD class=house-type><LABEL class=ui-green><INPUT onclick=update(46) value="修    改" type=button
                                                                  name=search></LABEL></TD>
                <TD class=house-price><LABEL class=ui-green><INPUT value="删    除" type=button name=search></LABEL></TD>
            </TR>


            <TR class=odd>
                <TD class=house-thumb><SPAN><A href="details.jsp" target="_blank"><img src="../images/thumb_house.gif"
                                                                                       width="100" height="75"
                                                                                       alt=""></A></SPAN></TD>
                <TD>
                    <DL>
                        <DT><A href="details.jsp" target="_blank">jjjj</A></DT>
                        <DD>海淀区中关村大街,123平米<BR>联系方式：ff</DD>
                    </DL>
                </TD>
                <TD class=house-type><LABEL class=ui-green><INPUT onclick=update(47) value="修    改" type=button
                                                                  name=search></LABEL></TD>
                <TD class=house-price><LABEL class=ui-green><INPUT value="删    除" type=button name=search></LABEL></TD>
            </TR>
            <TR>
                <TD class=house-thumb><SPAN><A href="details.jsp" target="_blank"><img src="../images/thumb_house.gif"
                                                                                       width="100" height="75"
                                                                                       alt=""></A></SPAN></TD>
                <TD>
                    <DL>
                        <DT><A href="details.jsp" target="_blank">大房子</A></DT>
                        <DD>海淀区中关村大街,100平米<BR>联系方式：123456789</DD>
                    </DL>
                </TD>

                <TD class=house-type><LABEL class=ui-green><INPUT onclick=update(6) value="修    改" type=button
                                                                  name=search></LABEL></TD>
                <TD class=house-price><LABEL class=ui-green><INPUT value="删    除" type=button name=search></LABEL></TD>
            </TR>
            </TBODY>
        </TABLE>
    </DIV>
    <DIV class=pager>
        <UL id="page">

        </UL>
        <%--        <SPAN class=total>1/2页</SPAN>--%></DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2010 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
</HTML>
