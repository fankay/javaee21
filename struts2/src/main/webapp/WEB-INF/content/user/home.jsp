<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h3>User List</h3>
<s:if test="name == 'Jack'">
    Hello,Jack
</s:if>
<s:else>
    Hello,Other
</s:else>

<ul>
    <s:iterator value="nameList" var="n" status="st">
    <li>${st.first} - ${n} - <s:property value="n"/> </li>
    </s:iterator>
</ul>

</body>
</html>