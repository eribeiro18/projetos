<%-- 
    Document   : main
    Created on : 16 de set. de 2023, 16:02:53
    Author     : evandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"/>

  <body>
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"/>
  
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
      <jsp:include page="navbar.jsp"/>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                  <jsp:include page="navbarmainmenu.jsp"/>
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      <jsp:include page="page-header.jsp"/>
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                            <h1>Conteudo base das paginas do sistema</h1>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>    
    <!-- Required Jquery -->
  <jsp:include page="javascriptfile.jsp"></jsp:include>
</body>

</html>

