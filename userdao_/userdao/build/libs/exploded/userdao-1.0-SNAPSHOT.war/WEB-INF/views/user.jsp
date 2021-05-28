<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<body>
<h2>
    File Upload
</h2>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" />
</form>
<img src="${url}" />
<%--    <h1>--%>
<%--&lt;%&ndash;        Hello ${user.name}!!!&ndash;%&gt;--%>
<%--        Error ${e.message}--%>
<%--    </h1>--%>
</body>
</html>