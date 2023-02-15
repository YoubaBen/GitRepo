using MMUMedical.classes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace MMUMedical.ui
{
    public partial class login : System.Web.UI.Page
    {
        //initialize class
        private function func;
        //get cookie data
        private HttpCookie cookie = function.GetCookie();

        public login()
        {
            func = function.GetInstance();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (cookie != null)
                {
                    Response.Redirect("/ui/home.aspx");
                }
                else
                {
                    txtEmail.Focus();
                }
            }
        }
        //login code

        protected void btnLogin_OnClick(object sender, EventArgs e)
        {
            if (txtEmail.Text == "")
            {
                func.PopAlert(this, "Email is required");
            }
            else if (txtPassword.Text == "")
            {
                func.PopAlert(this, "Password is required");
            }
            else
            {
                string password =
                    func.IsExist(
                        $"SELECT Password FROM Users WHERE Email='{txtEmail.Text}' AND Password='{txtPassword.Text}' COLLATE Latin1_General_CS_AI");

                if (password == txtPassword.Text.Trim())
                {
                    HttpCookie cookie = function.CreateCookie();
                    cookie.Expires = DateTime.Now.AddDays(-1);
                    HttpContext.Current.Response.Cookies.Add(cookie);
                    cookie["Name"] = func.IsExist($"SELECT Name FROM Users WHERE Email='{txtEmail.Text}'");
                    cookie["UserId"] = func.IsExist($"SELECT UserId FROM Users WHERE Email='{txtEmail.Text}'"); 
                    cookie["Type"] = "patient";
                    cookie.Expires = DateTime.Now.AddDays(30);
                    Response.Cookies.Add(cookie); 
                    Response.Redirect("/ui/home.aspx");
                }
                else
                {
                    func.PopAlert(this, "Please enter valid email or password");
                }
            }
        }
    }
}