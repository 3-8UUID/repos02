<%@ page language="java" contentType="text/html; charset=utf-8"  isErrorPage="true" pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE HTML>
<html>
<head>
    <title>500</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="http://7xqxjz.com1.z0.glb.clouddn.com/404style.css" rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
<div class="wrap">
    <h1>&nbsp;</h1>
    <div class="banner">
        <!--<img src="images/banner.png" alt=""/>-->
    </div>
    <div class="page" >
        <h2>程序有点小情绪，请尽快联系管理员，并按返回'上一页'进行操作。</h2>
        <div style="color:#000;font-size: 0.8em;font-family: '微软雅黑';text-align: left;margin: 0 auto;">
            <div class="remark">
                <p><span style="color:red">An exception was thrown:</span><br><b><%=exception.getClass()%>:<%=exception.getMessage()%></b></p>
                <br>
                <%
                    Enumeration<String> e = request.getHeaderNames();
                    String key;
                    while(e.hasMoreElements()){
                        key = e.nextElement();
                        System.out.println(key+"="+request.getHeader(key));
                    }
                    e = request.getAttributeNames();
                    while(e.hasMoreElements()){
                        key = e.nextElement();
                        System.out.println(key+"="+request.getAttribute(key));
                    }

                    e = request.getParameterNames();
                    while(e.hasMoreElements()){
                        key = e.nextElement();
                        System.out.println(key+"="+request.getParameter(key));
                    }
                %>
                请求地址： <%=request.getAttribute("javax.servlet.forward.request_uri") %><br>
                <pre>
<%exception.printStackTrace();
    ByteArrayOutputStream ostr = new ByteArrayOutputStream();
    exception.printStackTrace(new PrintStream(ostr));
    out.print(ostr);
%>
</pre>
                <hr width=80%>
            </div>
            <div style="text-align: center;">
                <a class="copyNews" style="background:#000;color:#fff;width:80px;height:40px;border-radius:2px;line-height:40px;text-align: center;cursor: pointer;padding:5px 5px;">复制</a>
                <a href="javascript:history.go(-1)" style="background:#000;color:#fff;width:120px;height:40px;border-radius:2px;line-height:40px;text-align: center;cursor: pointer;padding:5px 5px;">返回上一页</a>
            </div>
        </div>
    </div>
    <div class="footer" style="text-align:center;">
    </div>
</div>

<script type="text/javascript" src="http://7xqxjz.com1.z0.glb.clouddn.com/jquery.js"></script>
<script type="text/javascript" src="http://7xqxjz.com1.z0.glb.clouddn.com/clipboard.min.js"></script>
<script type="text/javascript" src="http://res.layui.com/lay/lib/layer/build/layer.js"></script>
<script type="text/javascript">
    $(".copyNews").click(function(){

        var clipboard = new Clipboard('.copyNews', {
            text: function() {

                return $('.remark').text();
            }
        });
        clipboard.on('success', function(e) {});
        clipboard.on('error', function(e) {});
        layer.msg("复制成功!");
    })
</script>
</body>
</html>
