<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>from message:${message}</h1> <!-- XSS -->
<h1>from message 2:${message2}</h1> <!-- XSS -->

</body>
</html>