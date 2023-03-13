<%@taglib  uri="http://www.springframework.org/tags/form"   prefix="form"%>

<%@taglib   uri="http://java.sun.com/jsp/jstl/core"     prefix="c" %>

<br>  <br>
<c:if   test="${message ne  null }">
     <c:out  value="${message}"/>
</c:if>
<hr>

<br> <br>
<form:form  action="add_customer"  method="post"  modelAttribute="customer">
  <table>
     <tr>
        <td>PhoneNo </td> 
        <td> <form:input path="phoneNumber"/> </td>
        <td> <form:errors path="phoneNumber"/> </td>
     </tr>
     
     <tr>
        <td>UserName </td> 
        <td> <form:input path="userName"/> </td>
        <td> <form:errors path="userName"/> </td>
     </tr>
     
     <tr>
        <td>Password </td> 
        <td> <form:input path="password"/> </td>
        <td> <form:errors path="password"/> </td>
     </tr>
     
     <tr>
        <td>Email </td> 
        <td> <form:input path="email"/> </td>
        <td> <form:errors path="email"/> </td>
     </tr>
     
     <tr>
        <td> PlanId </td>
        <td>
                 <form:select   path="planId">
                          <form:option  value="">----select-----</form:option>
                          <form:options items="${customer.plansList}"  itemValue="planId"   itemLabel="planId"/>
                 </form:select>
        </td>
        <td>
              <form:errors   path="planId"/>
        </td>
     </tr>
   </table>     
        
   <input  type=submit    value="REGISTER">
        
</form:form>