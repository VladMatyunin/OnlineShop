<#macro page_head >
<title>Main</title>
</#macro>
<#macro page_body>
<h1>Basic Page</h1>
<p>This is the body of the page!</p>
</#macro>
<#macro display_page>
    <#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>     <#--jsp core-->
<!doctype html>
<html>
<head>
    <#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
    <#assign spring=JspTaglibs["http://www.springframework.org/tags"] >
    <script src="<@c.url value='/js/jquery-1.11.1.min.js'/>"></script>
    <script src="<@c.url value='/js/bootstrap.js'/>"></script>
    <link rel="stylesheet" href="<@c.url value='/css/bootstrap.min.css'/>" >
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>
    <!-- styles -->
    <link href="/css/font-awesome.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/owl.carousel.css" rel="stylesheet">
    <link href="/css/owl.theme.css" rel="stylesheet">

    <!-- theme stylesheet -->
    <link href="/css/style.default.css" rel="stylesheet" id="theme-stylesheet">

    <!-- your stylesheet with modifications -->
    <link href="/css/custom.css" rel="stylesheet">

    <script src="/js/respond.min.js"></script>

    <link rel="shortcut icon" href="/img/favicon.png">
    <@page_head/>
</head>
<body>
    <#include "header.ftl"/>
    <@page_body/>
    <#include "footer.ftl"/>
<#--<#include "footer.ftl"/>-->
</body>
</html>
</#macro>
