<!DOCTYPE html>
<html>
<head>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>

<%@include file="../element/header.jsp" %>

<section id="main">
    <div class="container">
        <div class="alert alert-danger">
            Error 404: Page not found. Too bad bitch!
            <br/>
            <!--
                Exception: ${pageContext.errorData.throwable.cause}
              -->
        </div>
    </div>
</section>

</body>
</html>