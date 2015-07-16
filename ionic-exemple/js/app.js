// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('starter', ['ionic'])

.run(function($rootScope, $window, $ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if(window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
	
	$rootScope.log = "";
	
	$rootScope.showAlert = function(){
		$window.natif.alert('ALERT', 'Hello You !', 
			function(success){
				$rootScope.log = "Vous avez cliquez sur le bouton ok de l'alerte\n";
				$rootScope.$apply();
			},
			function(error){
				$rootScope.log = "Erreur de la boite d'alerte\n";
				$rootScope.$apply();
			});
	};
	$rootScope.showConfirm = function(){
		$window.natif.confirm('CONFIRMATION','Etes-vous certain de vouloir effectuer cette action?', 
			function(confirmed){
				if(confirmed) {
					$rootScope.log = "Vous avez confirmé la demande\n";
					$rootScope.$apply();
				}
				else {
					$rootScope.log = "Vous n'avez pas confirmé la demande\n";
					$rootScope.$apply();
				}
			},
			function(error){
				$rootScope.log = "Erreur de la boite de confirmation\n";
				$rootScope.$apply();
			});
	};
	$rootScope.showPromt = function(){
		$window.natif.promt('QUESTION', 'Que voulez-vous faire aujourd\'hui ?', 
			function(result){
				if(result && result.value) {
					$rootScope.log = "Mhhh... J'aime faire " + result.value + "\n";
					$rootScope.$apply();
				}
				else {
					$rootScope.log = "Vous avez refusé de répondre à la question\n";
					$rootScope.$apply();
				}
			},
			function(error){
				$rootScope.log = JSON.stringify(error);
				$rootScope.$apply();
			});
	};
	$rootScope.showBeep = function(){
		$window.natif.beep('2');
	};
  });
})
.controller('MainCtrl', function($scope, $window){
	
});
