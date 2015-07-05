function Plugin(){};
Plugin.alert = function(content){
  var onSuccess = function(){};
  var onFail = function(){};
  cordova.exec(onSuccess, onFail, 'RaphxyzNatif', 'alert', [content]);
};
module.exports = Plugin;