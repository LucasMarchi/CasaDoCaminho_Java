$(document).ready(function(){
});

var urlAdicionarVoluntario = "/projeto/registrar/voluntario"

function registrarVoluntario(idProjeto, idVolunatrio){
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

/*function selectProjetos() {
	$("#cadastrarVoluntario").click(function {
		var projetoSelecionado = $("#projetoSelecionado option:selected").val();
		$('input[name="projetoHidden"]').val(projetoSelecionado);
	});
}*/