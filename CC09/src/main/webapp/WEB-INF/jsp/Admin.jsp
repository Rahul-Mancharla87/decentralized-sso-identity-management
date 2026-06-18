
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="utf-8"/>

  
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <!-- Site Metas -->
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
  
    <title>BigWing</title>
  
    <!-- slider stylesheet -->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.3/assets/owl.carousel.min.css"/>
  
    <!-- bootstrap core css -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
  
    <!-- fonts style -->
    <link href="https://fonts.googleapis.com/css?family=Dosis:400,500|Poppins:400,700&amp;display=swap" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet"/>
    <!-- responsive style -->
    <link href="css/responsive.css" rel="stylesheet"/>
  </head>
  
  <body class="sub_page">
    <div class="hero_area">
      <!-- header section strats -->
      <header class="header_section">
        <div class="container-fluid">
          <div class="row">
            <div class="col-lg-8">
              <nav class="navbar navbar-expand-lg custom_nav-container ">
                <a class="navbar-brand" href="index.html">
                  <span>
                   Cloud Computing
                  </span>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
  
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <div class="d-flex  flex-column flex-lg-row align-items-center">
                    <ul class="navbar-nav  ">
                      <li class="nav-item active">
                        <a class="nav-link" href="/adminHome">Home <span class="sr-only">(current)</span></a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="/users">View Users </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="/files">View Files</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="/harmFiles"></a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="/logout"> Logout</a>
                      </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0 ml-0 ml-lg-4 mb-3 mb-lg-0">
                      <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit"></button>
                    </form>
                  </div>
                </div>
              </nav>
            </div>
          </div>
        </div>
      </header>
      <!-- end header section -->
    </div>
  
    <!-- welcome section -->
    <section class="welcome_section layout_padding">
      <div class="container">
        <div class="custom_heading-container">
          <h2>
            Welcome Admin
          </h2>
        </div>
        <c:choose>
            <c:when test="${home != null}">
                <div class="row">
                    <div class="col-lg-6">
                       <div class="layout_padding">
                          
                           <div class="detail-box">
                             <p>
                               dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
                               aliqua.
                               Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                               Duis
                               aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                             </p>
                             <div>
                               <a href="">
                                 Read More
                               </a>
                             </div>
                           </div>
                         </div>
           
                    </div>
                    <div class="col-lg-6">
                       <div class="layout_padding">
           
                           <div class="img-box">
                               <img src="images/welcome.png" alt=""/>
                              
                             </div>
                       </div>
           
                    </div>
                   </div>
            </c:when>

            <c:when test="${userspage != null}">
                <div class="row">
                    <div class="col-lg-6">
                       <div class="layout_padding">
                          
                           <div class="detail-box">
                          <table class="table">
                          <tr>
                            <td>User Id</td>
                            <td>User Name</td>
                            <td>User Email</td>
                            <td>Mobile</td>
                          </tr>
                          <c:forEach items="${users}" var="element"> 
                            <tr>
                              <td>${element.userId}</td>
                              <td>${element.name}</td>
                              <td>${element.emailId}</td>
                              <td>${element.mobile}</td>
                             
        
                            </tr>
                          </c:forEach>

                          </table>
                           </div>
                         </div>
           
                    </div>
                    <div class="col-lg-6">
                       <div class="layout_padding">
           
                           <div class="img-box">
                               <img src="images/welcome.png" alt=""/>
                              
                             </div>
                       </div>
           
                    </div>
                   </div>
            </c:when>


            <c:when test="${filespage != null}">
                <div class="row">
                    <div class="col-lg-6">
                       <div class="layout_padding">
                          
                           <div class="detail-box">
                          <table class="table">
                          <tr>
                            <td>File Id</td>
                            <td>User Name</td>
                            <td>File Name</td>
                            <td>Hash Key</td>
                            
                          </tr>
                          <c:forEach items="${files}" var="element"> 
                            <tr>
                              <td>${element.fileId}</td>
                              <td>${element.userId}</td>
                              <td>${element.fileName}</td>
                              <td>${element.hashKey}</td>
                             
        
                            </tr>
                          </c:forEach>

                          </table>
                           </div>
                         </div>
           
                    </div>
                    <div class="col-lg-6">
                       <div class="layout_padding">
           
                           <div class="img-box">
                               
                              
                             </div>
                       </div>
           
                    </div>
                   </div>
            </c:when>


            <c:when test="${harmFiles != null}">
                <div class="row">
                  <h2>Files Reported with errors</h2>
                    <div class="col-lg-6">
                       <div class="layout_padding">
                          
                           <div class="detail-box">
                          <table class="table">
                          <tr>
                            <td>File Id</td>
                            <td>User Name</td>
                            <td>File Name</td>
                            <td>Hash Key</td>
                           
                          </tr>
                          <c:forEach items="${files}" var="element"> 
                            <tr>
                              <td>${element.fileId}</td>
                              <td>${element.userId}</td>
                              <td>${element.fileName}</td>
                              <td>${element.hashKey}</td>
                             
        
                            </tr>
                          </c:forEach>

                          </table>
                           </div>
                         </div>
           
                    </div>
                    <div class="col-lg-6">
                       <div class="layout_padding">
           
                           <div class="img-box">
                               
                              
                             </div>
                       </div>
           
                    </div>
                   </div>
            </c:when>


            
      
       </c:choose>
  
      </div>
    </section>
    <!-- end welcome section -->
  
  
  
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
  </body></html>