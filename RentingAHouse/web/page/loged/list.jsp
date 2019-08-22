<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 首页</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
    <script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="../scripts/nextBox.js"></script>
    <script type="text/javascript">
        var pageNum = 1;//默认为第一页
        $("a.getPage").live("click", function () {
            var title = $("#title").val();
            var allData = $("input,select").serialize();
            pageNum = $(this).text();
            load(pageNum, allData, title);
        });

        function load(pageno, allData, title) {
            $("#list tr").remove();
            $("#page li").remove();
            $("span.total").remove();
            $.getJSON("../houseController/findByWhere?allData=" + allData + "&title=" + title, "pageNum=" + pageno, function (data) {
                var span = "<span class='total'>" + pageno + "/" + data.totalPage + "页</span>";
                $("#page").after(span);
                for (var i = 0; i < data.list.length; i++) {
                    var cate = data.list[i];
                    var type = "一室一厅"
                    if (data.list[i].type_id == 8) {
                        type = "四室两厅"
                    } else if (data.list[i].type_id == 3) {
                        type = "两室一厅"
                    } else if (data.list[i].type_id == 2) {
                        type = "一室两厅"
                    } else if (data.list[i].type_id == 4) {
                        type = "两室两厅"
                    } else if (data.list[i].type_id == 5) {
                        type = "三室一厅"
                    } else if (data.list[i].type_id == 6) {
                        type = "三室两厅"
                    } else if (data.list[i].type_id == 7) {
                        type = "四室一厅"
                    } else if (data.list[i].type_id == 9) {
                        type = "四室三厅"
                    }
                    var tr = "<TR> <TD class=house-thumb><span><A href='../houseController/details?id=" + cate.id + "' target=_\"blank\">" +
                        "<img src=\"../images/"+cate.fileName+"\"";
                    tr += "width=\"100\" height=\"75\""
                    tr += "alt=\"\"></a></span></TD>";
                    tr += "<TD> <DL> <DT><A href='../houseController/details?id=" + cate.id + "' target=_\"blank\">" + cate.title + "</A></DT>";
                    tr += "<DD>" + cate.floorage + "平米<BR>联系方式：" + cate.contact + "</DD>";
                    tr += "</DL> </TD> <TD class=house-type>" + type + "</TD>";
                    tr += "<TD class=house-price><SPAN>" + cate.price + "</SPAN>元/月</TD> </TR>";
                    $("#list").append(tr);
                }
                //"<A href=\"#\" onclick='fristPgae()'>首页</A></LI><LI><A href=\"#\" onclick='upPage()'>上一页</A></LI>"
                for (var i = 1; i <= data.totalPage; i++) {
                    var cla = data.pageIndex == i ? "class=\"current\"" : "";
                    var li = ""
                    li += "<LI " + cla + "><A class='getPage' ";
                    li += " href=\"javascript:void(0);\"";
                    li += "parseContent=\"true\" showError=\"true\" targets=\"houseArea\"";
                    li += "ajaxAfterValidation=\"false\" validate=\"false\"";
                    li += "dojoType=\"struts:BindAnchor\">" + i + "</A>";
                    $("#page").append(li);
                }
                // var li1 = "</LI><LI><A href=\"#\" onclick='downPage()'>下一页</A></LI><LI><A href=\"#\" onclick='lastPage()'>末页</A></LI>";
                // $("#page").append(li1);
            })
        }

        // //上一页
        // function upPage(){
        //     if(pageNum!=1){
        //         pageNum--;
        //     }
        //     load(pageNum);
        // };
        // //下一页
        // function downPage(){
        //     if(pageNum!=pageMax){
        //         pageNum++;
        //     }
        //     load(pageNum);
        // };
        // //首页
        // function fristPgae(){
        //     pageNum = 1
        //     load(pageNum);
        // }
        // //末页
        // function lastPage(){
        //     pageNum = pageMax;
        //     load(pageNum);
        // }
        //加载
        $(function () {
            var title = $("#title").val();
            var allData = $("input,select").serialize();
            load(pageNum, allData, title);
        })

        function doSearch() {
            pageNum = 1;//每次都首先查第一页
            var title = $("#title").val();
            var allData = $("input,select").serialize();
            load(pageNum, allData, title);
        }
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
</DIV>
<DIV id=navbar class=wrap>
    <DL class="search clearfix">
        <FORM id=sform method=post action=../houseController/findByWhere>
            <DT>
                <UL>
                    <LI class=bold>房屋信息</LI>
                    <LI>标题：<INPUT class=text type=text name=title id="title"> <LABEL class=ui-blue><INPUT
                            onclick=doSearch()
                            value=搜索房屋 type=button
                            name=search></LABEL>
                    </LI>
                </UL>
            </DT>

            <DD>
                <UL>
                    <LI class=first>区县</LI>
                    <LI><SELECT id=dist name=district_id onchange="findStreet()">
                        <option value="0">请选择</option>
                    </SELECT>
                    </LI>
                </UL>
            </DD>
            <DD>
                <UL>
                    <LI class=first>街道</LI>
                    <LI><SELECT id=street name=street_id>
                        <option value="0">请选择</option>
                    </SELECT></LI>
                </UL>
            </DD>
            <DD>
                <UL>
                    <LI class=first>房型</LI>
                    <LI><SELECT name=type_id id="type">
                        <option value="0">请选择</option>
                    </SELECT>
                    </LI>
                </UL>
            </DD>
            <DD>
                <UL>
                    <LI class=first>价格</LI>
                    <LI><SELECT name=price id="price">
                        <OPTION selected value="">不限</OPTION>
                        <OPTION
                                value=0-100>100元以下
                        </OPTION>
                        <OPTION value=100-200>100元—200元</OPTION>
                        <OPTION value=200-1000000>200元以上</OPTION>
                    </SELECT></LI>
                </UL>
            </DD>
            <DD>
                <UL>
                    <LI class=first>面积</LI>
                    <LI><SELECT name=floorage id="floorage">
                        <OPTION selected value="">不限</OPTION>
                        <OPTION
                                value=0-40>40以下
                        </OPTION>
                        <OPTION value=40-500>40-500</OPTION>
                        <OPTION
                                value=500-1000000>500以上
                        </OPTION>
                    </SELECT></LI>
                </UL>
            </DD>
        </FORM>
    </DL>
</DIV>
<DIV class="main wrap">
    <TABLE class=house-list>
        <TBODY id="list">
        <TR>
            <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="../images/thumb_house.gif"
                                                                                   width="100" height="75"
                                                                                   alt=""></a></span></TD>
            <TD>
                <DL>
                    <DT><A href="details.jsp" target="_blank">${page.list.title}</A></DT>
                    <DD>海淀区中关村大街,346平米<BR>联系方式：3456</DD>
                </DL>
            </TD>
            <TD class=house-type>一室一厅</TD>
            <TD class=house-price><SPAN>346.0</SPAN>元/月</TD>
        </TR>

        <TR class=odd>
            <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="../images/thumb_house.gif"
                                                                                   width="100" height="75"
                                                                                   alt=""></a></span></TD>
            <TD>
                <DL>
                    <DT><A href="details.jsp" target="_blank">大房子</A></DT>
                    <DD>海淀区中关村大街,100平米<BR>联系方式：123456789</DD>
                </DL>
            </TD>
            <TD class=house-type>一室一厅</TD>
            <TD class=house-price><SPAN>230.0</SPAN>元/月</TD>
        </TR>
        <TR>
            <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="../images/thumb_house.gif"
                                                                                   width="100" height="75"
                                                                                   alt=""></a></span></TD>
            <TD>
                <DL>
                    <DT><A href="details.jsp" target="_blank">123</A></DT>
                    <DD>海淀区中关村大街,123平米<BR>联系方式：123</DD>
                </DL>
            </TD>
            <TD class=house-type>一室一厅</TD>
            <TD class=house-price><SPAN>123.0</SPAN>元/月</TD>
        </TR>
        <TR class=odd>
            <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="../images/thumb_house.gif"
                                                                                   width="100" height="75"
                                                                                   alt=""></a></span></TD>
            <TD>
                <DL>
                    <DT><A href="details.jsp" target="_blank">jjjj</A></DT>
                    <DD>海淀区中关村大街,123平米<BR>联系方式：ff</DD>
                </DL>
            </TD>
            <TD class=house-type>一室一厅</TD>
            <TD class=house-price><SPAN>123.0</SPAN>元/月</TD>
        </TR>

        </TBODY>
    </TABLE>
    <DIV class=pager>
        <UL id="page">
            <LI class=current><A href="#">首页</A></LI>
            <LI><A href="#">上一页</A></LI>
            <LI><A href="#">下一页</A></LI>
            <LI><A href="#">末页</A></LI>
        </UL>
        <SPAN
                class=total>1/2页</SPAN></DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2010 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
</HTML>
