<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<c:if test = "${!empty callsList}">
   <table style="margin-left: auto; margin-right: auto;" background-color="yellow" border="1">
      <tr>
        <th> CALL ID </th>
        <th> FROM NUMBER </th>
        <th> TO NUMBER </th>
        <th> CALL DURATION </th>
        <th> CALLED ON </th>
      </tr>
      <c:forEach items = "${callsList}"  var = "calldetails">
         <tr>
            <td> <c:out value="${calldetails.callId}"/> </td>
            <td> <c:out value="${calldetails.fromNumber}"/> </td>
            <td> <c:out value="${calldetails.toNumber}"/> </td>
            <td> <c:out value="${calldetails.callDuration}"/> </td>
            <td> <c:out value="${calldetails.calledOn}"/> </td>
         </tr>  
      </c:forEach>
    </table>
</c:if>

<c:if test = "${callsList eq null}">
   <h3> No calldetails found for the customer </h3>
</c:if>
<br>
<a href="dash_board"> Goto Dashboard </a>         