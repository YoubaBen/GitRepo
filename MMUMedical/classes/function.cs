using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Drawing;
using System.Drawing.Imaging;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using Image = System.Drawing.Image;

namespace MMUMedical.classes
{
    public class function
    {
        IFormatProvider dateformat = new System.Globalization.CultureInfo("fr-FR", true);
        private static function _instance;
        private SqlConnection con;
        public static function GetInstance()
        {
            if (_instance == null)
            {
                _instance = new function();
            }
            return _instance;
        }

        public function()
        {
            if (con == null)
            {
                con = new SqlConnection(Connection);
            }
        }
        //connection string
        public string Connection = new SqlConnectionStringBuilder
        {
            DataSource = ".\\local",
            InitialCatalog = "GeneralDb",
            IntegratedSecurity = true,
            Pooling = true,
            MinPoolSize = 0,
            MaxPoolSize = 4000,
            ConnectTimeout = 0
        }.ToString();
        //Bind Dropdown or select option
        public void BindDropDown(DropDownList ddl, string root, string query)
        {
            con = new SqlConnection(Connection);
            DataSet dataSet = new DataSet();
            try
            {
                if (con.State != ConnectionState.Open)
                    con.Open();
                SqlCommand cmd = new SqlCommand(query, con);
                SqlDataAdapter adapter = new SqlDataAdapter(cmd);
                adapter.Fill(dataSet);
                ddl.DataSource = dataSet;
                ddl.DataTextField = "Name";
                ddl.DataValueField = "ID";
                ddl.DataBind();
                ddl.Items.Insert(0, new ListItem("--" + root.ToUpper() + "--"));
                if (con.State != ConnectionState.Closed)
                    con.Close();
            }
            catch (Exception ex)
            {
                if (con.State != ConnectionState.Closed)
                    con.Close();
            }
        }
        //for executing  any type of query
        public bool Execute(string str)
        {
            bool result = false;
            SqlConnection Conn = new SqlConnection(Connection);
            try
            {

                if (Conn.State != ConnectionState.Open) Conn.Open();
                SqlCommand cmd = new SqlCommand(str, Conn);
                int count = cmd.ExecuteNonQuery();
                if (count > 0)
                    result = true;
                else
                    result = false;
                if (Conn.State != ConnectionState.Closed) Conn.Close();
            }
            catch { if (Conn.State != ConnectionState.Closed) Conn.Close(); }
            return result;
        }
        //for getting a single value from the query
        public string IsExist(string str)
        {
            string result = "";
            try
            {
                con = new SqlConnection(Connection);
                if (con.State != ConnectionState.Open) con.Open();
                SqlCommand cmd = new SqlCommand(str, con);
                SqlDataReader DR = cmd.ExecuteReader();
                while (DR.Read())
                {
                    result = DR[0].ToString();
                }
                if (con.State != ConnectionState.Closed) con.Close();
                DR.Close();
                return result;
            }
            catch (Exception ex)
            {
                if (con.State != ConnectionState.Closed) con.Close();
                return result;
            }
        }
        //getting date time
        public string Date()
        {
            string date = DateTime.Now.ToString("dd/MM/yyyy_hh:mm:ss");
            return date;
        }
        //printing date with eu timezone
        public DateTime Timezone(DateTime datetime)
        {
            var timezoneInfo = TimeZoneInfo.FindSystemTimeZoneById("Central European Standard Time");
            DateTime printDate = TimeZoneInfo.ConvertTime(datetime, timezoneInfo);
            return printDate;
        }
        //jquery dynamic alert
        public void PopAlert(Page page, string msg)
        {
            ScriptManager.RegisterStartupScript(page, page.GetType(), "script", "alert('" + msg + "')", true);

        }
        //showing the alert and then redirecting to another page

        public void AlertWithRedirect(Page page, string msg, string link)
        {
            ScriptManager.RegisterStartupScript(page, page.GetType(), "script", "alert('" + msg + "');setTimeout(function(){location.replace('" + link + "')},100);", true);
        }
        //sending an  email  throught contact us
        public bool SendEmail(string fromMail, string toMail, string subject, string body, string fromPass)
        {
            try
            {
                MailMessage message = new MailMessage();
                SmtpClient smtp = new SmtpClient();
                message.From = new MailAddress(fromMail);
                message.To.Add(new MailAddress(toMail));
                message.Subject = subject;
                message.IsBodyHtml = true; //Maling the message body as html  
                message.Body = body;
                smtp.Port = 587;
                smtp.Host = "smtp.gmail.com"; //setting up a gmail host since the dummy email used for contact us is gmail
                smtp.EnableSsl = true;
                smtp.UseDefaultCredentials = false;
                smtp.Credentials = new NetworkCredential(fromMail, fromPass);
                smtp.DeliveryMethod = SmtpDeliveryMethod.Network;
                smtp.Send(message);
                return true;
            }
            catch (Exception ex)
            {
                return true;
            }
        }
        //checking the  cookie
        public void CheckCookies()
        {
            HttpCookie cookies = HttpContext.Current.Request.Cookies["dating"];
            if (cookies == null)
            {
                HttpContext.Current.Response.Redirect("/ui/home.aspx", true);
            }
            else if (cookies != null)
            {
                string x = IsExist($"SELECT UserId FROM Users where UserId='{UserIdCookie()}'");
                if (x == "")
                {
                    Logout();
                }
            }
        }
        //clearing the cookie and logging  out
        public void Logout()
        {
            HttpCookie cookie = function.CreateCookie();
            cookie.Expires = DateTime.Now.AddDays(-1);
            HttpContext.Current.Response.Cookies.Add(cookie);
            HttpContext.Current.Response.Redirect("/ui/home.aspx");
        }
        //getting the  user id from  a cookie
        public string UserIdCookie()
        {
            HttpCookie cookies = GetCookie();
            return cookies["UserId"];
        }
        //getting the  picture from a cookie
        public string PictureCookie()
        {
            HttpCookie cookies = GetCookie();
            return cookies["Picture"];
        }
        //getting the  name from the cookie
        public string NameCookie()
        {
            HttpCookie cookies = GetCookie();
            return cookies["Name"];
        }
        //getting  Mobile number  from  the cookie
        public string MobileCookie()
        {
            HttpCookie cookies = GetCookie();
            return cookies["Mobile"];
        }
        //getting the  email from  the cookie
        public string EmailCookie()
        {
            HttpCookie cookies = GetCookie();
            return cookies["Email"];
        }
        //getting user type from cookie
        public string TypeCookie()
        {
            HttpCookie cookies = GetCookie();
            return cookies["Type"];
        }
        //creating cookie
        public static HttpCookie CreateCookie()
        {
            HttpCookie cookie = new HttpCookie("general");
            if (cookie == null || cookie?.Value == "")
            {
                cookie = null;
            }
            return cookie;
        }
        //getting cookie
        public static HttpCookie GetCookie()
        {
            HttpCookie cookie = HttpContext.Current.Request.Cookies["general"];
            if (cookie == null || cookie?.Value == "")
            {
                cookie = null;
            }
            return cookie;
        }
        //generating  dynamic id

        public string GenerateId(string query)
        {
            string id = "";
            try
            {
                if (con.State != ConnectionState.Open) con.Open();
                SqlCommand cmd = new SqlCommand(query, con);
                SqlDataReader reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    id = reader[0].ToString();
                    if (id == "")
                        id = "1001";
                    else
                    {
                        id = (int.Parse(id) + 1).ToString();
                    }

                }
                reader.Close();
                if (con.State != ConnectionState.Closed) con.Close();
            }
            catch
            {
            }
            return id;
        }
    }
}