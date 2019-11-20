<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LogParser</title>
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
      <link href="/css/sb-admin.css" rel="stylesheet">
        <link href="/css/main.css" rel="stylesheet">
        
    
  <style type="text/css">
 th, td { white-space: nowrap !important; }
    div.dataTables_wrapper {
        margin: 0 auto !important;
    }
 
    div.container {
        width: 100% !important;
    }
    

   
  </style>
</head>
  <style>
    .sticky{
    display: flex;
    position: absolute;

    margin-left: -15px;

    width: calc(100% - 90px);
    height: 80px;
    background-color: #e9ecef;
    }
  </style>
<body id="page-top" class="sidebar-toggled">


<%@include file="../navbar/nav.jsp" %>

  <div id="wrapper">
  
  <%@include file="../sidebar/side.jsp" %>
  
  
  
      <div id="content-wrapper">
    

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <p>Filtrer</p>
          </li>
        
        </ol>

        <!-- Icon Cards-->


        <!-- Area Chart Example-->
        <div class="s011">

          <form>

            <div class="inner-form">

              <div class="main-form" id="main-form">

                <div class="row second">
                  <div class="input-wrap">
                    <div class="icon-wrap">
                      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                        <path d="M19 3h-1V1h-2v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16H5V8h14v11zM7 10h5v5H7z"></path>
                      </svg>
                    </div>
                    <div class="input-field">
                      <label>Du ..</label>
                      <input class="datepicker" id="du" type="text" placeholder="mm/dd/yy" />
                    <div><span id="dateError" style="color: red;"></span></div>
                    
                    </div>
                  </div>
                  <div class="input-wrap">
                    <div class="icon-wrap">
                      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                        <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                      </svg>
                    </div>
                    <div class="input-field">
                      <label>Jusqu'à</label>
                      <input class="datepicker" id="juquau" type="text" placeholder="mm/dd/yy" />
                    </div>
                  </div>
                  <div class="input-wrap">
                    <div class="icon-wrap">

                    </div>
                    <div class="input-field">
                      <label>Status</label>
                      <div class="input-select">
                        <select data-trigger="" id="status" name="choices-single-defaul">
                          <option value="" placeholder="">Status</option>
                          <c:forEach var="Status" items="${status}">
                          	<option value="${Status}">${Status }</option>
                          </c:forEach>

                        </select>
                      </div>
                    </div>
                  </div>


                </div>

                  <div class="row second"  style=" margin-top: 10%;" >
                    <div class="input-wrap">
                      <div class="icon-wrap">

                      </div>
                      <div class="input-field">
                        <label>Utilisateur</label>
                        <input  type="text" id="user" placeholder="Code Utilisateur" />
                        <div><span id="userError" style="color: red;"></span></div>
                      </div>
                    </div>

                    <div class="input-wrap">
                      <div class="icon-wrap">

                      </div>
                      <div class="input-field">
                        <label>URI</label>
                        <input id="uri" type="text" placeholder="URI" />
                      <div><span id="uriError" style="color: red;"></span></div>
                        
                      </div>
                    </div>
                    <div class="input-wrap">
                      <div class="icon-wrap">

                      </div>
                      <div class="input-field">
                        <label>Methode</label>
                        <div class="input-select">
                          <select data-trigger="" id="methode" name="choices-single-defaul">
                            <option value="" placeholder="">Methode</option>
                            <c:forEach var="Methode" items="${methode}">
                          	<option value="${Methode}">${Methode}</option>
                          </c:forEach>


                          </select>
                        </div>
                      </div>
                    </div>
                  </div>

                <div class="last" style="text-align: center;margin-top: 7%;">
                  <button id="rechercher"class="btn-search" type="button">
                      <i class="fa fa-filter" aria-hidden="true"></i>
                      Filtrer</button>
                </div>

   <div id="loag" class="text-center">
        <div class="spinner-border" role="status">
          <span class="sr-only">Loading...</span>
        </div>
      </div>
              </div>
            </div>

          </form>
<div class="card mb-3" style="margin-top:3%;">
  <div class="card-header" style="cursor: pointer;">
    <i class="fas fa-tasks"></i>
    Liste des Demandes </div>
          <div class="box-body container">
        <div class="table-responsive dataTables_wrapper">
        <table id="ex" class="table table-bordered table-striped" style="width:100%">
        <thead>
        <tr>
                  <th>date</th>
                  <th> utilisateur</th>
                      <th> uri</th>
                        <th> methode</th>
                      <th> remote</th>
                  <th>thread</th>
                  <th>correlation</th>
                        <th> duree</th>
                                <th> status</th>
                                 <th>Action</th>
        </tr>
        </thead>
        <tbody>

        </tbody>

        </table>
        </div>
        </div>

        </div>

</div>
      <!-- /.container-fluid -->

      <!-- Sticky Footer -->
      <footer class="sticky">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
          <span>Copyright © SQLI-OUJDA</span>
          </div>
        </div>
      </footer>

    </div>
    <!-- /.content-wrapper -->

  </div>
    
      <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
  
  
  </div>



  
<script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Page level plugin JavaScript-->
  <script src="vendor/chart.js/Chart.min.js"></script>
  <script src="vendor/datatables/jquery.dataTables.js"></script>
  
  <script src="js/flatpickr.js"></script>

  <!-- Demo scripts for this page-->
<script>


  </script>

<script src="js/sb-admin.min.js"></script>

<script src="js/choices.js"></script>



 <script src="/js/RechercherJs/rechercher.js"></script>
  <!-- Demo scripts for this page-->

  
</body>
</html>