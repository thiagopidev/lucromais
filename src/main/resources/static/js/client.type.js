var ClientType = ClientType || {};

ClientType.DinamicMask = (function() {
	
	function DinamicMask() {
		this.radio = $('.client-type-radio');
		this.label = $('[for=cpfOrCnpj]');
		this.input = $('#cpfOrCnpj');
	}
	
	DinamicMask.prototype.start = function() {
		this.radio.on('change', onSelectedClientType.bind(this));
	}
	
	function onSelectedClientType(event) {
		var selectedClientType = $(event.currentTarget);		
		this.label.text(selectedClientType.data('document'));
		this.input.mask(selectedClientType.data('mask'));
		this.input.val('');
		this.input.removeAttr('disabled');
	}
	return DinamicMask;
}());

$(function() {
	var dynamicMask = new ClientType.DinamicMask();
	dynamicMask.start();
});