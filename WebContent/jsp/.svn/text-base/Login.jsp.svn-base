<HTML>
<HEAD>
<TITLE> Please Login!</TITLE>

<link href="css/stockapp.css" rel="stylesheet" type="text/css" />
<script language="Javascript" type="text/javascript">

function check()
{	
 	var filter=/^.+@.+\..{2,3}$/
	var test = "true";
	var UNAME = document.loginForm.EMAIL;
	var PWORD = document.loginForm.PWORD;

	if (UNAME.value == "" || !filter.test(UNAME.value)) 
	{
             alert("Please enter a valid Username"); 
	     test = "false";		     
        } 
	else if (PWORD.value== "" || PWORD.value.length != 8) 
        {
            alert("Password cannot be null and must be 8 characters long"); 
	    test="false";	               
        }	

if(test == "true")
{
	document.loginForm.submit();   
}

}


</script>

</HEAD>

<BODY>
<img src="images/logo.jpg" alt="logo"/>
<br />
<br />
<form name="loginForm" action="home.jsp" method="post">
<input type="hidden" name="redirect" value="LoginProcess" />
 <div class = "wrapper">
	<h2 id="para"> Welcome to the Stockapp Portal</h2>
   
    <div class = "left_box"> 

	<h3 id="para">What we Offer</h3>
	<p><img src="images/bullet_icon.jpg" />&nbsp&nbspComprehensive Portfolio Management</p>
	<p><img src="images/bullet_icon.jpg" />&nbsp&nbspA single platform for multiple exchange  </p>
	<p><img src="images/bullet_icon.jpg" />&nbsp&nbspOnline fund transfer  </p>
	<p><img src="images/bullet_icon.jpg" />&nbsp&nbspMultiple Market Watch  </p>
	<p><img src="images/bullet_icon.jpg" />&nbsp&nbspAny time Profit Withdrawal </p>
	<p><img src="images/bullet_icon.jpg" />&nbsp&nbspGet Instant order and trade confirmations </p>

    </div>
    <div class = "right_box">
  	<table id = "main">

		<tr>		
		<td id="lock"><img src="images/lock.gif" /></td>
		</tr>
		<tr>
		<td id="margin">Email:</td>
		</tr>
		<tr>		 
		<td><input type="text" name="EMAIL" size="20"></td> 	
		</tr>	
		<tr>
		<td>Password:</td> 
		</tr>
		<tr>
		
		<td><input type="password" name="PWORD" size="20"></td> 
		</tr>

		<tr>
		<td> </td>
		</tr>

		<tr>
		<td> </td>
		</tr>
		

  		<tr>		
		<td><a href="javascript:check();"><img src="images/login2.jpg" style="border-width: 0"/></a></td>	 
		</tr>

				
		<tr>		
		<td><a href="login.jsp">Forgot Password?</a>
		</tr>

		<tr>
		<td> </td>
		</tr>
  	</table>
	<table>
		<tr>
		<td></td>
		</tr>

		<tr>
		<td></td>
		</tr>

		<tr>
		<td></td>
		</tr>	
	</table>
  	<table id = "register">
		<tr>
		<td></td>
		</tr>
		<tr>
		<td>Don't have an account yet?</td>
		</tr>
		<tr>
		<td><a href="home.jsp?redirect=Register"><img src="images/register.jpg" style="border-width: 0"/></a></td>
		</tr>	
  	</table>
  </div>
</div>
</form>

</BODY>
</HTML>