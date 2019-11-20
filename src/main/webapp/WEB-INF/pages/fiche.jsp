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
    #cardreq , #cardrep{
    
    cursor: pointer;
    
    }
   
  </style>
<body id="page-top">


<%@include file="../navbar/nav.jsp" %>

  <div id="wrapper">
  
  <%@include file="../sidebar/side.jsp" %>
  
  
  
      <div id="content-wrapper">
    

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <p>Details</p>
          </li>
       
        </ol>

        <!-- Icon Cards-->
  <div class="s011">

               <!-- Area Chart Example-->
        <div class="card mb-3">
          <div class="card-header" id="cardreq">
            <i class="fas fa-level-up-alt"></i>
            Requete</div>
          <div class="card-body" id="req">
            <div class="list-group">
             <div  class="list-group-item">
                <div class="key">Date </div>
                <div class="separator">:</div>
                <div class="value"><c:out value="${ demande.date}"></c:out></div>
              </div>
             <div  class="list-group-item">
                  <div class="key">Utilisateur</div>
                  <div class="separator">:</div>
                         <c:choose>
                  <c:when test="${demande.utilisateur.code==0}">
                  
                    <div class="value"> 
                    <span class="badge badge-pill badge-dark">anonyme</span></div>
                  </c:when>
                   <c:when test="${ demande.utilisateur.code!=0}">
                  
                  
                   <div class="value"><c:out value="${ demande.utilisateur.code}"></c:out></div>
                  </c:when>
                      
                      </c:choose>
              
              </div>
             <div  class="list-group-item">
                  <div class="key">Thread</div>
                  <div class="separator">:</div>
                  <c:choose>
                  <c:when test="${empty demande.thread}">
                  
                    <div class="value"> 
                    <span class="badge badge-pill badge-dark">Pas de Thread</span></div>
                  </c:when>
                   <c:when test="${ not empty demande.thread}">
                  
                  
                   <div class="value"><c:out value="${ demande.thread}"></c:out></div>
                  </c:when>
                      
                      </c:choose>
              </div>
             <div  class="list-group-item">
                  <div class="key">Uri</div>
                  <div class="separator">:</div>
                  <div class="value"><c:out value="${ demande.uri}"></c:out></div>
              </div>
             <div  class="list-group-item">
                  <div class="key">Origine</div>
                  <div class="separator">:</div>
                    <div class="value"><c:out value="${ demande.requete.origine}"></c:out></div>
             </div>
            <div  class="list-group-item">
                  <div class="key">Correlation</div>
                  <div class="separator">:</div>
                    <div class="value"><c:out value="${ demande.correlation}"></c:out></div>
             </div> 
            <div  class="list-group-item">
                <div class="key">Protocole</div>
                <div class="separator">:</div>
                  <div class="value"><c:out value="${ demande.protocole}"></c:out></div>
             </div>         
            <div  class="list-group-item">
                <div class="key">Remote</div>
                <div class="separator">:</div>
                   <div class="value"><c:out value="${ demande.remote}"></c:out></div>
            </div> 
           <div  class="list-group-item">
                <div class="key">Methode</div>
                <div class="separator">:</div>
                  <div class="value"><c:out value="${ demande.methode}"></c:out></div>
            </div> 
           <div  class="list-group-item">
                <div class="key">Headers</div>
                <div class="separator">:</div>
                  <div class="value" style="max-width:90%;"><c:out value="${ demande.requete.headers}"></c:out></div>
            </div>
           <div  class="list-group-item">
                <div class="key">Body</div>
                <div class="separator">:</div>
                  <c:choose>
                  <c:when test="${empty demande.requete.body}">
                  
                    <div class="value"> 
                    <span class="badge badge-pill badge-dark">Pas de Body</span></div>
                  </c:when>
                   <c:when test="${ not empty demande.requete.body}">
                 
               <div class="value" style="max-width: 90%;"
               ><c:out value="${ demande.requete.body}"></c:out></div>
                  </c:when>
                      
                      </c:choose>
                
            </div>
           <div  class="list-group-item">
                <div class="key">Fichier</div>
                <div class="separator">:</div>
                    <div class="value"><c:out value="${ demande.fichier.nom}"></c:out></div>
             </div>
            </div>
          </div>
        </div>

        <!-- DataTables Example -->
        <div class="card mb-3">
          <div class="card-header" id="cardrep">
            <i class="fas fa-level-down-alt"></i>
            Reponse</div>
          <div class="card-body" id="rep">
              <div class="list-group">
                    <div  class="list-group-item">
                <div class="key">Date </div>
                <div class="separator">:</div>
                <div class="value"><c:out value="${ demande.reponse.date}"></c:out></div>
              </div>
             <div  class="list-group-item">
                  <div class="key">Utilisateur</div>
                  <div class="separator">:</div>
                   <c:choose>
                  <c:when test="${demande.utilisateur.code==0}">
                  
                    <div class="value"> 
                    <span class="badge badge-pill badge-dark">anonyme</span></div>
                  </c:when>
                   <c:when test="${ demande.utilisateur.code!=0}">
                  
                  
                   <div class="value"><c:out value="${ demande.utilisateur.code}"></c:out></div>
                  </c:when>
                      
                      </c:choose>
              </div>
             <div  class="list-group-item">
                  <div class="key">Thread</div>
                  <div class="separator">:</div>
                         <c:choose>
                  <c:when test="${empty demande.thread}">
                  
                    <div class="value"> 
                    <span class="badge badge-pill badge-dark">Pas de Thread</span></div>
                  </c:when>
                   <c:when test="${ not empty demande.thread}">
                  
                  
                   <div class="value"><c:out value="${ demande.thread}"></c:out></div>
                  </c:when>
                      
                      </c:choose>
              </div>
             <div  class="list-group-item">
                  <div class="key">Uri</div>
                  <div class="separator">:</div>
                  <div class="value"><c:out value="${ demande.uri}"></c:out></div>
              </div> 
             <div  class="list-group-item">
                  <div class="key">Origine</div>
                  <div class="separator">:</div>
                  
                   <c:choose>
                    
                    
                    <c:when test="${ empty demande.reponse.origine}">
                    
                    <span class="badge badge-pill badge-dark">pas de reponse</span>
                    </c:when>
                    
                     <c:when test="${ not empty demande.reponse.origine}">
                        <div class="value"><c:out value="${ demande.reponse.origine}"></c:out></div>
                    </c:when>
                    
                    </c:choose>
                  
             </div>
            <div  class="list-group-item">
                  <div class="key">Correlation</div>
                  <div class="separator">:</div>
                    <div class="value"><c:out value="${ demande.correlation}"></c:out></div>
             </div> 
            <div  class="list-group-item">
                <div class="key">Protocole</div>
                <div class="separator">:</div>
                  <div class="value"><c:out value="${ demande.protocole}"></c:out></div>
             </div>   
                <div  class="list-group-item">
                    <div class="key">Durée</div>
                    <div class="separator">:</div>
                    
                     <c:choose>
                    
                    
                    <c:when test="${ empty demande.duree}">
                    
                    <span class="badge badge-pill badge-dark">pas de reponse</span>
                    </c:when>
                    
                     <c:when test="${ not empty demande.duree}">
                        <div class="value"><c:out value="${ demande.duree} ms"></c:out></div>
                    </c:when>
                    
                    </c:choose>
                    
                   
                </div> 
               <div  class="list-group-item">
                    <div class="key">Methode</div>
                    <div class="separator">:</div>
          <div class="value"><c:out value="${ demande.methode}"></c:out></div>
                 </div>  
               <div  class="list-group-item">
                    <div class="key">Headers</div>
                    <div class="separator">:</div>
                  
                   <c:choose>
                    
                    
                    <c:when test="${ empty demande.reponse.headers}">
                    
                    <span class="badge badge-pill badge-dark">pas de reponse</span>
                    </c:when>
                    
                     <c:when test="${ not empty demande.reponse.headers}">
                        <div class="value" style="max-width:90%;"><c:out value="${ demande.reponse.headers}"></c:out></div>
                    </c:when>
                    
                    </c:choose>
                             
                    
                    
                </div>
               <div  class="list-group-item">
                    <div class="key">Body</div>
                    <div class="separator">:</div>
                    <c:choose>
                    
                    
                    <c:when test="${ empty demande.reponse.body}">
                    
                    <span class="badge badge-pill badge-dark">pas de body</span>
                    </c:when>
                    
                     <c:when test="${ not empty demande.reponse.body}">
                        <div class="value" style="max-width:90%;"><c:out value="${ demande.reponse.body}"></c:out></div>
                    </c:when>
                    
                    </c:choose>
                
                </div>
               <div  class="list-group-item">
                    <div class="key">Status</div>
                    <div class="separator">:</div>
<div class="value">


<c:choose >

    <c:when test="${demande.status=='OK'}">  
      <span class="badge badge-pill badge-success"><c:out value="${demande.status}"></c:out></span>
    </c:when>  
    
      <c:when test="${demande.status=='CREATED'}">  
      <span class="badge badge-pill badge-primary"><c:out value="${demande.status}"></c:out></span>
    </c:when>  
    
      <c:when test="${demande.status=='FORBIDDEN'}">  
    <span class="badge badge-pill badge-danger"><c:out value="${demande.status}"></c:out></span>
    </c:when>  
    
       <c:when test="${demande.status=='BAD_REQUEST'}">  
      <span class="badge badge-pill badge-warning"><c:out value="${demande.status}"></c:out></span>
    </c:when>  
      <c:when test="${demande.status== 'INTERNAL_SERVER_ERROR'}">  
      <span class="badge badge-pill badge-danger"><c:out value="${demande.status}"></c:out></span>
    </c:when>  
    
     <c:when test="${demande.status== 'NOT_FOUND'}">  
      <span class="badge badge-pill badge-danger"><c:out value="${demande.status}"></c:out></span>
    </c:when>  
    
      <c:when test="${demande.status== 'UNAUTHORIZED'}">  
      <span class="badge badge-pill badge-danger"><c:out value="${demande.status}"></c:out></span>
    </c:when> 
    
      <c:when test="${empty  demande.status}">  
      <span class="badge badge-pill badge-dark">pas de reponse</span>
    </c:when> 
    
<c:otherwise>


 <span class="badge badge-pill badge-danger"><c:out value="${demande.status}"></c:out></span>

</c:otherwise>
    


</c:choose>



</div>
                </div>
               <div  class="list-group-item">
                    <div class="key">Fichier</div>
                    <div class="separator">:</div>
                  
                    <div class="value"><c:out value="${ demande.fichier.nom}"></c:out></div>
                 </div>
          </div>
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