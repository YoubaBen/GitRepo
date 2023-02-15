USE [master]
GO
/****** Object:  Database [GeneralDb]    Script Date: 4/8/2022 4:00:40 AM ******/
CREATE DATABASE [GeneralDb]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'GeneralDb', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.LOCAL\MSSQL\DATA\GeneralDb.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'GeneralDb_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.LOCAL\MSSQL\DATA\GeneralDb_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [GeneralDb].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [GeneralDb] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [GeneralDb] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [GeneralDb] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [GeneralDb] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [GeneralDb] SET ARITHABORT OFF 
GO
ALTER DATABASE [GeneralDb] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [GeneralDb] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [GeneralDb] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [GeneralDb] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [GeneralDb] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [GeneralDb] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [GeneralDb] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [GeneralDb] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [GeneralDb] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [GeneralDb] SET  DISABLE_BROKER 
GO
ALTER DATABASE [GeneralDb] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [GeneralDb] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [GeneralDb] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [GeneralDb] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [GeneralDb] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [GeneralDb] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [GeneralDb] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [GeneralDb] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [GeneralDb] SET  MULTI_USER 
GO
ALTER DATABASE [GeneralDb] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [GeneralDb] SET DB_CHAINING OFF 
GO
ALTER DATABASE [GeneralDb] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [GeneralDb] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [GeneralDb] SET DELAYED_DURABILITY = DISABLED 
GO
USE [GeneralDb]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 4/8/2022 4:00:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[Email] [nvarchar](max) NULL,
	[MobileNo] [nvarchar](max) NULL,
	[DateofBirth] [nvarchar](50) NULL,
	[Gender] [nvarchar](50) NULL,
	[Address] [nvarchar](max) NULL,
	[Password] [nvarchar](max) NULL,
	[RegTime] [nvarchar](50) NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
USE [master]
GO
ALTER DATABASE [GeneralDb] SET  READ_WRITE 
GO
