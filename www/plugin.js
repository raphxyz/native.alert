module.exports = {
	alert: function(title, content, onSuccess, onError){
		cordova.exec(onSuccess, onError, 'RaphxyzNatif', 'alert', [title, content]);
	},
	confirm: function(title, content, onSuccess, onError){
		cordova.exec(onSuccess, onError, 'RaphxyzNatif', 'confirm', [title, content]);
	},
	promt: function(title, content, onSuccess, onError){
		cordova.exec(onSuccess, onError, 'RaphxyzNatif', 'prompt', [title, content]);
	}
};