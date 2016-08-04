<HTML>
<HEAD>

<TITLE>Create an Account</TITLE>

<link href="css/stockapp.css" rel="stylesheet" type="text/css" />

<script language="Javascript" type="text/javascript">

function validate()
{

	var filter=/^.+@.+\..{2,3}$/
	var test = "true";

	var FNAME = document.registerForm.FNAME;
	var LNAME = document.registerForm.LNAME;
	var email = document.registerForm.EMAIL;
	var PWORD = document.registerForm.PWORD;
	var CONFIRM = document.registerForm.CONFIRM;

	if (FNAME.value == "")
	{
		alert("Please enter a valid First Name");
		test = "false";
	}

	else if (LNAME.value == "" )
	{
		alert("Please enter a valid Last Name");
		test = "false";
	}

	else if (email.value == "" || !filter.test(email.value))
	{
		alert("Please enter a valid E-Mail address");
		test = "false";
	}

	else if (PWORD.value== "")
	{
		alert("Please enter a valid Passowrd");
		test = "false";
	}

	else if (CONFIRM.value== "")
	{
		alert("Please enter a valid Confirm Passowrd");
		test = "false";
	}

	else if (CONFIRM.value != PWORD.value)
	{
		alert("Password and Confirm Password should match");
		test = "false";
	}

	else if (PWORD.value.length != 8 )
	{
		alert("The password must be 8 characters long");
		test = "false";
	}



if(test == "true")
{
	document.registerForm.submit();
}



}


</script>

</HEAD>

<BODY>

<img src="images/logo.jpg" alt="logo"/>


<br />
<br />



<br />
<br />

<form name="registerForm" action="home.jsp" method="post">
 <input type="hidden" name="redirect" value="RegisterProcess" />
 <div class = "wrapper">
   <h4 id="para"> Creating an account is easy!</h4>


  <div class = "personal">
   <p id="para"> Please enter your Personal details</p>

    <table id = "data">
	<tr>
	<td>First Name <span>*</span></td>
	<td><input type="text" name="FNAME" size="30"></td>
	</tr>



	<tr>
	<td>Last Name <span>*</span></td>
	<td><input type="text" name="LNAME" size="30"></td>
	</tr>


	<tr>
	<td>E-mail Address <span>*</span></td>
	<td><input type="text" name="EMAIL" size="30"></td>
	</tr>

	<tr>
	<td>Password <span>*</span></td>
	<td><input type="password" name="PWORD" size="30"></td>
	</tr>

	<tr>
	<td>Confirm Password <span>*</span></td>
	<td><input type="password" name="CONFIRM" size="30"></td>
	</tr>



</table>
</div>


<br />




<a href= "javascript:validate()"><img src="images/register2.jpg" style="border-width: 0"/></a>





</BODY>
</HTML>