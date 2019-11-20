$(".card-header").click(function(){
	

	$(".box-body").fadeToggle("slow");

	
	
});

$("#cardreq").click(function(){
	

	$("#req").fadeToggle("slow");

	
	
});


$("#cardrep").click(function(){

	
	
	$("#rep").fadeToggle("slow");
	
	
});



var datatable  = $("#ex").DataTable({	
     scrollX:        "100%",
     scrollCollapse: true,
   
     
	  "processing": true,
      "serverSide": true,
      "searching":false,
      "ordering": true,
		  'ajax': {
		    "type"   : "GET",
		    "url"    : 'r',
		    "dataType": 'json',
		    "data": function(d){
		    	
		    	
		    	return $.extend({},d,{
		    		
			    	
			    	 du:$("#du").val(),
		    	 juquau:$("#juquau").val(),
		    	 
		    	 status:$("#status").val(),
		    	 user:$("#user").val(),
		    	 uri:$("#uri").val(),
		    	 methode:$("#methode").val(),
			    	});
		    	 
		    	 
		    
		    	
		    	
		    },
		    "beforeSend": function() {
		      
		    
		       
		      },
		      
		     "complete": function (json, type) {
		    	  
	
		    	  $("#loag").css("display", "none");
		      }
		
		  },
		  "language": {
			    "sProcessing": "Traitement en cours ...",
			    "sLengthMenu": "Afficher _MENU_ lignes",
			    "sZeroRecords": "Aucun résultat trouvé",
			    "sEmptyTable": "Aucune donnée disponible",
			    "sInfo": "Lignes _START_ à _END_ sur _TOTAL_",
			    "sInfoEmpty": "Aucune ligne affichée",
			    "sInfoFiltered": "",
			    "sInfoPostFix": "",
			    "sSearch": "Chercher:",
			    "sUrl": "",
			    "sInfoThousands": ",",
			    "sLoadingRecords": "Chargement...",
			    "oPaginate": {
			      "sFirst": "Premier", "sLast": "Dernier", "sNext": "Suivant", "sPrevious": "Précédent"
			    },
			    "oAria": {
			      "sSortAscending": ": Trier par ordre croissant", "sSortDescending": ": Trier par ordre décroissant"
			    }
			  },
  		  
  	   
		    "columns": [
		    	 
                { "data": "date",

                "render":function(data,type,row){

                    if(type==="display"){

                      var html = getDate(data);
                      return html;
                    }
              return data;

                }
               },
                { "data": "utilisateur.code"},
                { "data": "uri" 
                	
                   

                    
                
                },
                  { "data": "methode" },
                    { "data": "remote" },
                      { "data": "thread"
                  
                      },
                        { "data": "correlation" },
                          { "data": "duree"},
                            { "data": "status",

                            "render":function(data,type,row){

                                if(type==="display"){
                             var html=data;
                               if(data===null){
                                  html = "Pas de reponse";

                                }

                                return html;
                                }

                          return data;

                            }


                          },
                          { "data": "id", 
                        	  
                              "render":function(data,type,row){

                                  if(type==="display"){
                             
                                 console.log(data);
                                   var html = "<a class='btn btn-info' href='details?id="+data+"' role='button'>Details</a>";

                                 

                                  return html;
                                  }

                            return data;

                              }
                          
                          },


            ],
           
		  
            "createdRow":function(row ,data ,index){
            	var parser = document.createElement('a');
            	parser.href = "http://"+data.uri;       	
            var  html = parser.pathname;
            	 $('td',row).eq(2).html("<div>"+html+"</div>");
                if(parseInt(data.utilisateur.code)==0){
                
               	 $('td',row).eq(1).html("<span class='badge badge-pill badge-dark'>anonyme</span>");
               }
                if(data.thread==""){
                    
                  	 $('td',row).eq(5).html("<span class='badge badge-pill badge-dark'>pas de thread</span>");
                  }
                
                $('td',row).eq(7).html(data.duree+"ms");
            
                switch(data.status){

              case "OK":
              $('td',row).eq(8).html("<span class='badge badge-pill badge-success'>OK</span>");

              break;

              case "BAD_REQUEST":
              $('td',row).eq(8).html("<span class='badge badge-pill badge-warning'>BAD_REQUEST</span>");

              break;

              case "CREATED":
              $('td',row).eq(8).html("<span class='badge badge-pill badge-primary'>CREATED</span>");

              break;
              
              case "FORBIDDEN":
                  $('td',row).eq(8).html("<span class='badge badge-pill badge-danger'>FORBIDDEN</span>");

                  break;
              case "UNAUTHORIZED":
                  $('td',row).eq(8).html("<span class='badge badge-pill badge-danger'>UNAUTHORIZED</span>");

                  break;
              case "INTERNAL_SERVER_ERROR":
                  $('td',row).eq(8).html("<span class='badge badge-pill badge-danger'>INTERNAL_SERVER_ERROR</span>");

                  break;
              case "NOT_FOUND":
                  $('td',row).eq(8).html("<span class='badge badge-pill badge-danger'>NOT_FOUND</span>");

                  break;
              case null:
              $('td',row).eq(7).html("<span class='badge badge-pill badge-dark'>Pas de reponse</span>")
                $('td',row).eq(8).html("<span class='badge badge-pill badge-dark'>Pas de reponse</span>");
              break;
              
              default : 
            	  
            	  $('td',row).eq(8).html("<span class='badge badge-pill badge-danger'>"+data.status+"</span>");
            	  
            	  break;



                }
                
            
              },
		
		  
		});    	    	
	

$("#user").keyup(function(){
	if($("#user").val().length<=0){
		$("#userError").html("");
	}
   
  });

$("#uri").keyup(function(){
	
	if($("#uri").val().length<=0)
$("#uriError").html("");	
	
});
	$("#du").keyup(function(){
		
		if($("#du").val().length<=0)
	$("#dateError").html("");	
	
});
	
	$("#juquau").keyup(function(){
		
		if($("#juquau").val().length<=0)
	$("#dateError").html("");	
	
});


$("#rechercher").click(function(){

	var regUser=/^[0-9]*$/;

	var dateDebut=$("#du").val();
	var dateFin=$("#juquau").val();

	var regUri=/^[a-zA-Z0-9\/\.(-\?){,1}]*$/;

	var uri =$("#uri").val();
	var user=$("#user").val();
	console.log(dateDebut +" "+ dateFin);
		if(regUri.test(uri) && regUser.test(user) && (new Date(dateDebut)<=new Date(dateFin) || dateDebut==="" || dateFin==="") ){
			$("#loag").css("display", "block");
			$("#userError").html("");
			$("#uriError").html("");	
			$("#dateError").html("");	


			datatable.ajax.url( 'rechercher' ).load();
			//datatable.ajax.reload()
		
}else{
	
	if(!regUser.test(user)){
	$("#userError").html("Veuillez saisir un code d'utilisateur valide !");
	}
	
	if(!regUri.test(uri)){
		
		$("#uriError").html("Veuillez saisir un uri valide")
	}
	
	if(new Date(dateDebut)>new Date(dateFin)){
		$("#dateError").html("veuillez vérifier la validité de vos dates ")
	}
}
	
});





/*--------------------------------*/
const choices = new Choices('[data-trigger]',
       {
         searchEnabled: true,
         itemSelectText: '',
       });
    

    flatpickr(".datepicker",
    	    {
    	        enableTime: true,
    	      dateFormat: "Y-m-d H:i",

    	      time_24hr:true,
    	     


    	    });
  
   
    function getDate(dateJson) {

    	var date = new Date(dateJson);

    	var dd = date.getDate();

    	var mm = date.getMonth()+1;
    	var yyyy = date.getFullYear();
    	var hh=date.getHours();
    	var min =date.getMinutes();
    	var ss=date.getSeconds();

    	if(dd<10)
    	{
    	    dd='0'+dd;
    	}

    	if(mm<10)
    	{
    	    mm='0'+mm;
    	}
    	if(min<10){
    	  min='0'+min;
    	}
    	if(ss<10){
    	  ss='0'+ss;
    	}
    	return yyyy+"-"+mm+"-"+dd+" "+hh+":"+min+":"+ss;
    	}
