<%@ taglib uri="/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/taglib/struts-logic.tld" prefix="logic" %>
<%@page import="com.stockapp.util.FormatHelper"%>

<HTML>
<HEAD>
<TITLE>Stockapp - Buy and Sell</TITLE>

<link href="css/stockapp.css" rel="stylesheet" type="text/css" />
<%
Double total = 0.00d;
%>
</HEAD>

<BODY>
<img src="images/logo.jpg" alt="logo"/>
<div id="contentBuySell">
<span class="lefttext">Welcome akshayamahesh@hotmail.com</span>
<span class="righttext">Initial balance: $100,000</span>
<div class="headertext">Here is a list of your transactions.</div>

<table class="BuySell" border="1">
	<thead>
		<th>Transaction Date</th>
		<th>Stock Code</th>
		<th class="right">Quantity</th>
		<th class="right">Buy Price</th>
		<th class="right">Total</th>
	</thead>
<logic:iterate id="transaction" name="transactionList" indexId="c" type="com.stockapp.hibernate.Transaction">
		<tbody>
		<tr class="row<%=c.intValue()%2%>">
			<td><%=FormatHelper.formatDate(transaction.getTransactionDate()) %></td>
			<td><%=transaction.getStockCode() %></td>
			<td class="right"><%=transaction.getQuantity() %></td>
			<td class="right"><%=FormatHelper.roundToDec(transaction.getBuyPrice(),1) %></td>
			<td class="right"><%=FormatHelper.roundToDec(transaction.getQuantity()*transaction.getBuyPrice(),1) %></td>
			<% total += transaction.getQuantity()*transaction.getBuyPrice();
			%>
		</tr>
		</tbody>
</logic:iterate>
	<tr class="footer">
		<td colspan="4">Total</td>
		<td class="right"><%=FormatHelper.roundToDec(total,1) %></td>
	</tr>
</table>
Amount available to Buy : $20,000

<form>
	<select name="stock">
		<option>MSFT</option>
		<option>GOOG</option>
		<option>IBM</option>
	</select>	
	<input type="text" name="quantity" value="100" />
	<input type="submit" />
</form>
</div>
</BODY>
</HTML>

