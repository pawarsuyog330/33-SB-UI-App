<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>

<h2 style="background: yellow;"> Customer Details</h2> 
<hr>
Phone Number : <c:out value="${customer_profile.phoneNumber}"/> <br>
Username : <c:out  value="${customer_profile.userName}"/> <br>
Email : <c:out  value="${customer_profile.email}"/> <br>
<br>
<h2 style="background: cyan;">Current Plan Details </h2> 
<hr>
Planid : <c:out value="${customer_profile.currentPlan.planId}"/> <br>
Plan Validity : <c:out value="${customer_profile.currentPlan.planValidity}"/> <br>
Plan Description : <c:out value="${customer_profile.currentPlan.planDescription}"/> <br>
<br>
<h2  style="background: grey;">Friends Contact Numbers</h2> 
<hr>
 <c:forEach  items="${customer_profile.friendsContactNumbers}"  var="friendNumber">
    <c:out  value="${friendNumber}"/> <br>
 </c:forEach> 
 <br>
 <a href="dash_board">Goto Dashboard</a>   
