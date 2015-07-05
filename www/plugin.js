function Plugin(){};

Plugin.alert = function(content, onSuccess, onError){
  cordova.exec(onSuccess, onError, 'RaphxyzNatif', 'alert', [content]);
};

Plugin.confirm = function(content, onSuccess, onError){
  cordova.exec(onSuccess, onError, 'RaphxyzNatif', 'confirm', [content]);
};

module.exports = Plugin;