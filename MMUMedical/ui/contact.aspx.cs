using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MMUMedical.classes;

namespace MMUMedical.ui
{
    //sending an email through contact us code
    public partial class contact : System.Web.UI.Page
    {
        private function func;

        public contact()
        {
            func = function.GetInstance();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {

            }
        }
        // Alerts if one of the fields is empty

        protected void btnSend_OnClick(object sender, EventArgs e)
        {
            if (txtName.Text == "")
            {
                func.PopAlert(this, "Name is required");
            }
            else if (txtEmail.Text == "")
            {
                func.PopAlert(this, "Email is required");
            }
            else if (txtSubject.Text == "")
            {
                func.PopAlert(this, "Subject is required");
            }
            else if (txtMessage.Text == "")
            {
                func.PopAlert(this, "Message is required");
            }
            else
            {
                //dummy email
                bool ans = func.SendEmail("MMUmedical2022@gmail.com", "MMUmedical2022@gmail.com", txtSubject.Text,
                    $@"Name:  {txtName.Text},<br/>Email: {txtEmail.Text} <br/>Message: {txtMessage.Text}", "MMUpass00");
                if (ans)
                {
                    txtName.Text = txtEmail.Text = txtSubject.Text = txtMessage.Text = "";
                    func.PopAlert(this,"Email sent successfully");
                }
                else
                {
                    func.PopAlert(this,"Email sent successfully");
                }
            }
        }
    }
}