<%@page session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <title>Logged Out</title>
  </head>
<body>
<div id="content">
<h2>Abgemeldet</h2>
<p>
Sie wurden abgemeldet. <a href="<c:url value='/'/>">Erneut anmelden</a>.
</p>
</div>
</body>
</html>
