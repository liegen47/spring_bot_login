<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Customer List</h1>
<h2>welcome ${LoginID} to customer page</h2>
    <table>
        <tr>
            <th>UUID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Street</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Phone</th>
        </tr>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td>${customer.uuid}</td>
                <td>${customer.first_name}</td>
                <td>${customer.last_name}</td>
                <td>${customer.email}</td>
                <td>${customer.street}</td>
                <td>${customer.address}</td>
                <td>${customer.city}</td>
                <td>${customer.state}</td>
                <td>${customer.phone}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
