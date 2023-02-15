<%@ Page Title="" Language="C#" MasterPageFile="~/ui/root.Master" AutoEventWireup="true" CodeBehind="contact.aspx.cs" Inherits="MMUMedical.ui.contact" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="col-md-12">
        <div class="row mt-4">
            <div class="col-md-12 text-center">
                <h1 class="fa-3x font-weight-bold">Contact Us</h1>
            </div>
            <div class="col-md-12">
               <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4749.939768042415!2d-2.240877002477622!3d53.46899831704043!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487bb1eca118180b%3A0x739a45aa865ef5a1!2sUniversit%C3%A9%20m%C3%A9tropolitaine%20de%20Manchester!5e0!3m2!1sfr!2suk!4v1649894480273!5m2!1sfr!2suk" width="1500" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-md-6">
                <h6>Name</h6>
                <asp:TextBox ID="txtName" class="form-control" placeholder="Your name" runat="server"></asp:TextBox>
            </div>
             <div class="col-md-6">
                <h6>Email</h6>
                <asp:TextBox ID="txtEmail" TextMode="Email" class="form-control" placeholder="e.g. email@example.com" runat="server"></asp:TextBox>
            </div> 
            <div class="col-md-12">
                <h6>Subject</h6>
                <asp:TextBox ID="txtSubject" class="form-control" placeholder="Subject" runat="server"></asp:TextBox>
            </div>
            <div class="col-md-12">
                <h6>Message</h6>
                <asp:TextBox ID="txtMessage" TextMode="MultiLine" Height="100px" class="form-control" placeholder="Your message" runat="server"></asp:TextBox>
            </div>
            <div class="col-md-12">
                <asp:Button ID="btnSend" class="btn btn-primary btn-block" OnClick="btnSend_OnClick" runat="server" Text="Send" />
            </div>
        </div>
    </div>
</asp:Content>
