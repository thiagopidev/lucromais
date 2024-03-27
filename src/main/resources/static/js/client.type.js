var ClientType = ClientType || {};

ClientType.DinamicMask = (function() {
	
	function DinamicMask() {
		this.radio = $('.client-type-radio');
		this.label = $('[for=cpfOrCnpj]');
		this.input = $('#cpfOrCnpj');
			
		this.fantasyNameLabel = $('[for=fantasyName]');
		this.birthLabel = $('[for=birth]');
		this.saveButton = $('#save');
	}
	
	DinamicMask.prototype.start = function() {
		this.radio.on('change', onSelectedClientType.bind(this));
		var selectedType = this.radio.filter(':checked')[0];
		if (selectedType) {
			applyMask.call(this, $(selectedType));
		}
	}
	
	function onSelectedClientType(event) {
		var selectedClientType = $(event.currentTarget);		
		applyMask.call(this, $(selectedClientType));
		this.input.val('');
	}
	
	function applyMask(selectedClientType) {
		updateOtherLabels.call(this, selectedClientType);
		this.label.text(selectedClientType.data('document'));
		this.input.mask(selectedClientType.data('mask'));
		this.input.removeAttr('disabled');
		this.saveButton.removeAttr('disabled');
	}
	
	function updateOtherLabels(selectedClientType) {
		var divdoc = document.getElementById("div-doc");
		var divdata = document.getElementById("div-data");
		var divcorp = document.getElementById("div-corporation");
		divdoc.classList.remove("hidden");
		divdata.classList.remove("hidden");
		this.fantasyNameLabel.text("Nome Completo");
		this.birthLabel.text("Data de Nascimento");
		if(selectedClientType.data('document') == "CNPJ") {
			this.fantasyNameLabel.text("Nome Fantasia");
			this.birthLabel.text("Data de Abertura");
			if(divcorp.classList.contains("hidden"))
				divcorp.classList.remove("hidden");	
		} else {
			if(!divcorp.classList.contains("hidden"))
				divcorp.classList.add("hidden");
		}
	}
	
	return DinamicMask;
}());

$(function() {
	var dynamicMask = new ClientType.DinamicMask();
	dynamicMask.start();
});