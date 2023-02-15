<%@ Page Title="About us" Language="C#" MasterPageFile="~/ui/root.Master" AutoEventWireup="true" CodeBehind="about-us.aspx.cs" Inherits="MMUMedical.ui.about_us" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="col-md-12 mt-4">
        <div class="row">
            <div class="col-md-8">
                <h1 class="fa-3x font-weight-bold">About Us</h1>
                <p class="font-size">
                    Here to make an impact on Manchester, our nation and beyond, with a driving ambition to discover and disseminate knowledge, and make higher education accessible and beneficial to all those with the passion and ability to succeed.
                </p>
            </div>
            <div class="col-md-4 d-none d-md-block">
                <img src="/URL/dist/img/1297272257.jpg" alt="about image" class="w-100  "/>
            </div>
            <div class="col-md-12 mt-4">
                <a href="/ui/contact.aspx" class="btn btn-primary">Contact Us</a>
            </div>
        </div>
    </div>
</asp:Content>
