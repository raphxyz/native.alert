var exec = require('cordova/exec');

var RaphxyzNatif = {
	alert: function(title, content, onSuccess, onError){
		exec(onSuccess, onError, 'RaphxyzNatif', 'alert', [title, content]);
	},
	confirm: function(title, content, onSuccess, onError){
		exec(onSuccess, onError, 'RaphxyzNatif', 'confirm', [title, content]);
	},
	promt: function(title, content, onSuccess, onError){
		exec(onSuccess, onError, 'RaphxyzNatif', 'prompt', [title, content]);
	}
};

module.exports = RaphxyzNatif;