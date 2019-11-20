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
    
     
    *{
    transition: all 0.6s;
}


#main{
    display: table;
    width: 100%;
    height: 100vh;
    text-align: center;
}

.fof{
	  display: table-cell;
	  vertical-align: middle;
}

.fof h1{
	  font-size: 50px;
	  display: inline-block;
	  padding-right: 12px;
	  animation: type .5s alternate infinite;
}

@keyframes type{
	  from{box-shadow: inset -3px 0px 0px #888;}
	  to{box-shadow: inset -3px 0px 0px transparent;}
}
    
  </style>
<body id="page-top">


<%@include file="../navbar/nav.jsp" %>

  <div id="wrapper">
  
  <%@include file="../sidebar/side.jsp" %>
  
  
  
      <div id="content-wrapper">
    

      <div class="container-fluid">

     
       
<div id="main">
    	<div class="fof">
        		<h1> Oups ..Erreur 500</h1>
        		<div>
        		<a class="btn btn-dark" href="/" role="button">Accueil</a>
        		</div>
    	</div>
</div>
      <!-- /.container-fluid -->
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
 

  <!-- Page level plugin JavaScript-->
 


  <!-- Demo scripts for this page-->
<script>


  </script>


<script src="js/sb-admin.min.js"></script>




 <script src="/js/RechercherJs/rechercher.js"></script>
  <!-- Demo scripts for this page-->

  
</body>
</html>