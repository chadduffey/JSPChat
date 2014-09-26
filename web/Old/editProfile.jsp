<html>
    <head>
        <title>New Message</title>
        <link href="css/normalize.css" rel="stylesheet" />
        <link href='http://fonts.googleapis.com/css?family=Amatic+SC|Shadows+Into+Light|PT+Sans+Narrow' rel='stylesheet' type='text/css'>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
        <link href="css/messages.css" rel="stylesheet" />
        <link href="css/profileform.css" rel="stylesheet" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#"> ... jsp chat</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                <li><a href="#">Your Stuff</a></li>
                <li ><a href="#">New Message</a></li>
                <li class="active"><a href="#">Your Profile</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li><a href="logout.html">Log Out</a></li>
              </ul>
            </div><!-- /.navbar-collapse -->
          </div><!-- /.container-fluid -->
        </nav>
        
        <h1>Editing ${sessionFullName}</h1>
        <form action="EditUserController" method="post">
                <p>Login Name:       <input type="text" name="username"/></p>
                <p>Password:         <input type="password" name="password"/></p>
        	<p>Your Full Name:   <input type="text" name="name"/></p>
                
                <p><label>Your Gender</label></p>
                <input type="radio" id="Male" value="Male" name="gender" checked>
                <label for="Male" class="light">Male</label><br/>
                <input type="radio" id="Female" value="Female" name="gender">
                <label for="Female" class="light">Female</label>
                
                <p><label>Date of Birth:</label></p>
                <input class="bday" type="text" name="bDay" value="Date"/>
                <select class="bday" id="bMonth" name="bMonth">
                    <option value="01">January</option>
                    <option value="02">February</option>
                    <option value="03">March</option>
                    <option value="04">April</option>
                    <option value="05">May</option>
                    <option value="06">June</option>
                    <option value="07">July</option>
                    <option value="08">August</option>
                    <option value="09">September</option>
                    <option value="10">October</option>
                    <option value="11">November</option>
                    <option value="12">December</option>
                </select>
                <input class="bday" type="text" name="bYear" value="Year"/>
        	
                <button type="submit">Update</button>
        </form>
    </body>
</html>
