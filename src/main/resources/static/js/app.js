$(document).ready(function(){
});

var urlAdicionarVoluntario = "/projeto/adicionar/voluntario"

function adicionarVoluntario(idProjeto, idVolunatrio){
	$.ajax({
	    type : "GET",
	    url : urlAdicionarVoluntario,
	    data : {
	    	"idProjeto" : idProjeto,
	    	"idVoluntario" : idVolunatrio,
	    },
	    success: function(data){
	    	console.log(data);
	    }
	});
}