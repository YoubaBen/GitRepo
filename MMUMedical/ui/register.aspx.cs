using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MMUMedical.classes;

namespace MMUMedical.ui
{
    public partial class register : System.Web.UI.Page
    {
        //initialize class
        private function func;

        public register()
        {
            func = function.GetInstance();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {

            }

        }
        //Register code
        protected void btnRegister_OnClick(object sender, EventArgs e)
        {
            if (txtName.Text == "")
            {
                func.PopAlert(this, "Name is required");
            }
            else if (txtDOB.Text == "")
            {
                func.PopAlert(this, "Date of Birth is required");
            }
            else if (ddlGender.SelectedIndex <= 0)
            {
                func.PopAlert(this, "Gender is required");
            }
            else if (txtAddress.Text == "")
            {
                func.PopAlert(this, "Address is required");
            }
            else if (txtEmail.Text == "")
            {
                func.PopAlert(this, "Email is required");
            }
            else if (txtMobile.Text == "")
            {
                func.PopAlert(this, "Mobile no is required");
            }
            else if (txtPassowrd.Text == "")
            {
                func.PopAlert(this, "Password is required");
            }
            else
            {
                //insert query for register
                bool ans = func.Execute($@"INSERT INTO Users(Name,Email,MobileNo,DateofBirth,Gender,Address,Password,RegTime) VALUES('{txtName.Text}','{txtEmail.Text}','{txtMobile.Text}','{txtDOB.Text}','{ddlGender.SelectedValue}','{txtAddress.Text}','{txtPassowrd.Text}','{func.Date()}')");
                if (ans)
                {
                    func.AlertWithRedirect(this, "Registered successfully", "/ui/register.aspx");
                }
                else
                {
                    func.PopAlert(this,"Failed to register");
                }
            }
        }
    }
}