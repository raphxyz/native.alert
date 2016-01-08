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
	},
	promtPassword: function(title, content, onSuccess, onError){
		exec(onSuccess, onError, 'RaphxyzNatif', 'promptPassword', [title, content]);
	},
	beep: function(count) {
        	var defaultedCount = count || 1;
        	exec(null, null, "RaphxyzNatif", "beep", [ defaultedCount ]);
    	}
};

module.exports = RaphxyzNatif;
