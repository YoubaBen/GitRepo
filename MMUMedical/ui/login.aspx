<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="login.aspx.cs" Inherits="MMUMedical.ui.login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Responsive Design</title>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="Welcome to Responsive Web Design" />
    <meta name="author" content="Responsive Web Design" />
    <link rel="icon" type="image/png" sizes="16x16" href="/URL/heartbeat.png" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" />
    <link href="../URL/font-awesome.min.css" rel="stylesheet" />
    <link href="../URL/dist/css/style.css" rel="stylesheet" />

</head>
<body>
    <form id="form1" runat="server">

        <main>
            <div id="app" class="has-alert">

                <%-- main body --%>
                <div class="wrapper white" style="padding-top: 0 !important;">
                    <div class="container">
                        <div class="row" style="min-height: 90vh !important;">
                            <div class="col-md-3"></div>
                            <div class="col-md-6 mt-5 pt-5">
                                <div class="card card-body">
                                    <div class="col-12 text-center">
                                        <h1 class="font-weight-bold">Login</h1>
                                    </div>
                                    <div class="col-12 mt-4 p-0 pl-md-4 pr-md-4">
                                        <h6>Email</h6>
                                        <asp:TextBox ID="txtEmail" runat="server" placeholder="name@example.com" class="fomr-control"></asp:TextBox>
                                    </div>
                                    <div class="col-12 p-0 pl-md-4 pr-md-4">
                                        <h6>Password</h6>
                                        <asp:TextBox ID="txtPassword" TextMode="Password" runat="server" placeholder="Password" class="fomr-control"></asp:TextBox>
                                    </div>
                                    <div class="col-12 p-0 pl-md-4 pr-md-4">
                                        <asp:Button ID="btnLogin" class="btn btn-primary btn-block" runat="server" Text="Login" OnClick="btnLogin_OnClick" />
                                    </div>
                                    <div class="col-12 p-0 pl-md-4 pr-md-4 mt-4">
                                        Back to home <a href="/ui/home.aspx">Click here</a><br/>
                                        Don't have an account? <a href="/ui/register.aspx">Click here</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                    </div>
                </div>
                <%-- footer --%>
                <footer id="footer">
                    <div class="row tac">
                        <div class="column">
                        </div>
                    </div>
                </footer>
                <span class="scrollToTop"><span class="fa fa-arrow-up"></span></span>
            </div>
        </main>

    </form>
    <% HttpCookie cookie = HttpContext.Current.Request.Cookies["font"];
        int f = 0;
        if (cookie == null)
        {
            f = 0;
        }
        else if (cookie["size"].ToString() == "s")
        {
            f = 0;
        }
        else if (cookie["size"].ToString() == "m")
        {
            f = 1;
        }
        else if (cookie["size"].ToString() == "l")
        {
            f = 2;
        }
        if (f == 0)
        { %>
    <style>
        .font-size {
            font-size: 16px;
        }

        .sm-active {
            font-weight: bold;
        }
    </style>
    <% }
        else if (f == 1)
        {
    %>
    <style>
        .font-size {
            font-size: 20px;
        }

        .md-active {
            font-weight: bold;
        }
    </style>
    <% }
        else if (f == 2)
        {
    %>
    <style>
        .font-size {
            font-size: 24px;
        }

        .lg-active {
            font-weight: bold;
        }
    </style>
    <% } %>
    <style>
        .dropbtn {
            border: none;
            cursor: pointer;
        }


        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            overflow: auto;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 11111111111;
        }

            .dropdown-content a {
                color: black;
                padding: 12px 16px !important;
                text-decoration: none;
                display: block;
            }

        .dropdown a:hover {
            background-color: #ddd;
        }

        .show {
            display: block;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="../URL/dist/js/style.js"></script>

    <script>
        $('.menu').click(function () {
            $('.triggers').toggleClass('expanded');
            $('.items').toggleClass('show-for-large');
        })
    </script>
    <script>
        /* When the user clicks on the button, 
        toggle between hiding and showing the dropdown content */
        function myFunction() {
            document.getElementById("myDropdown").classList.toggle("show");
        }

        // Close the dropdown if the user clicks outside of it
        window.onclick = function (event) {
            if (!event.target.matches('.dropbtn')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                var i;
                for (i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }
    </script>
</body>
</html>
