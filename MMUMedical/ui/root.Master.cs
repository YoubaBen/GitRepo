using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MMUMedical.classes;




/// Code to change the font size , used cookies that gets stored for 30 days so even if the user refreshes the page , the font size will stay the same

namespace MMUMedical.ui
{
    public partial class root : System.Web.UI.MasterPage
    {
        private function func;

        public root()
        {
            func = function.GetInstance();
        }
        protected void Page_Load(object sender, EventArgs e)
        {

        }


        protected void lnkSmall_OnClick(object sender, EventArgs e)
        {
            HttpCookie cookie = new HttpCookie("font");
            cookie.Expires = DateTime.Now.AddDays(-1);
            HttpContext.Current.Response.Cookies.Add(cookie);
            cookie["size"] = "s";
            cookie.Expires = DateTime.Now.AddDays(30);
            Response.Cookies.Add(cookie);
            Response.Redirect(Request.Url.AbsolutePath);
        }

        protected void lnkMedium_OnClick(object sender, EventArgs e)
        {
            HttpCookie cookie = new HttpCookie("font");
            cookie.Expires = DateTime.Now.AddDays(-1);
            HttpContext.Current.Response.Cookies.Add(cookie);
            cookie["size"] = "m";
            cookie.Expires = DateTime.Now.AddDays(30);
            Response.Cookies.Add(cookie);
            Response.Redirect(Request.Url.AbsolutePath);
        }

        protected void lnkLarge_OnClick(object sender, EventArgs e)
        {
            HttpCookie cookie = new HttpCookie("font");
            cookie.Expires = DateTime.Now.AddDays(-1);
            HttpContext.Current.Response.Cookies.Add(cookie);
            cookie["size"] = "l";
            cookie.Expires = DateTime.Now.AddDays(30);
            Response.Cookies.Add(cookie);
            Response.Redirect(Request.Url.AbsolutePath);
        }

        protected void logout_OnServerClick(object sender, EventArgs e)
        {
            func.Logout();
        }
    }
}