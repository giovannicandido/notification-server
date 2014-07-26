<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.keycloak.util.KeycloakUriBuilder" %>
<%@ page import="org.keycloak.ServiceUrlConstants" %>
<%
    String logoutUri = KeycloakUriBuilder.fromUri("http://localhost:8080/auth").path(ServiceUrlConstants.TOKEN_SERVICE_LOGOUT_PATH)
            .queryParam("redirect_uri", request.getRequestURL()).build("apps").toString();
    String acctUri = KeycloakUriBuilder.fromUri("http://localhost:8080/auth").path(ServiceUrlConstants.ACCOUNT_SERVICE_PATH)
            .queryParam("referrer", "notification").build("apps").toString();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Atende Notification Server</title>

    <!-- If you are using the CSS version, only link these 2 files, you may add app.css to use for your overrides if you like -->
    <!--<link rel="stylesheet" href="/resources/foundation/css/normalize.css">-->
    <!--<link rel="stylesheet" href="/resources/foundation/css/foundation.min.css">-->
    <link rel="stylesheet" href="/packages/vader/web/bower_components/animate.css/animate.min.css" />
    <link rel="stylesheet" href="/packages/vader/web/stylesheets/application.css" />

    <!-- If you are using the gem version, you need this only -->
    <link rel="stylesheet" href="/resources/css/app.css">

    <script src="/packages/vader/web/bower_components/modernizr/modernizr.js"></script>
</head>
<body ng-app>

<nav class="top-bar" data-topbar>
    <ul class="title-area">
        <li class="name">
            <h1><a href="/">Notification</a></h1>
        </li>
        <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
        <li class="toggle-topbar menu-icon"><a target="_self" href="#"><span>Menu</span></a></li>
    </ul>

    <section class="top-bar-section">
        <!-- Right Nav Section -->
        <ul class="right">
            <li class="has-dropdown">
                <a>Configuração</a>
                <ul class="dropdown">
                    <li><a href="#/config/email">Servidor de Email</a></li>
                    <li><a href="#/config/auth">Autenticação</a></li>
                </ul>

            </li>
            <li><a target="_self" href="<%=logoutUri%>">Logout</a></li>
            <li><a target="_self" href="<%=acctUri%>">Gerenciar Conta</a></li>
        </ul>

        <!-- Left Nav Section -->
        <ul class="left">
            <li><a href="#/apps">Applicações</a></li>
            <li><a href="#/graphics">Gráficos</a></li>
            <li><a href="#/status">Status de Serviços</a></li>
        </ul>
    </section>
</nav>
<div id="loading" class="right" style="padding: 25px; margin: 3px" >
    <img class="ng-hide" src="/resources/images/loading_global.gif" />
</div>
<ng-view></ng-view>
<script type="application/dart" src="/application.dart"></script>
<script type="application/javascript" src="/packages/browser/dart.js"></script>

<script type="application/javascript" src="/packages/vader/web/bower_components/jquery/dist/jquery.min.js"></script>
<script type="application/javascript" src="/packages/vader/web/bower_components/foundation/js/foundation.min.js"></script>
<script type="application/javascript">
    $(document).foundation();
</script>
</body>
</html>