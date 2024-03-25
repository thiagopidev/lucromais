var ClientType = ClientType || {};

ClientType.DinamicMask = (function() {
	
	function DinamicMask() {
		this.radio = $('.client-type-radio');
		this.label = $('[for=cpfOrCnpj]');
		this.input = $('#cpfOrCnpj');
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
		this.label.text(selectedClientType.data('document'));
		this.input.mask(selectedClientType.data('mask'));
		this.input.removeAttr('disabled');
	}
	return DinamicMask;
}());

$(function() {
	var dynamicMask = new ClientType.DinamicMask();
	dynamicMask.start();
});