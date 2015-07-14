# native.alert
Cordova Native Alert

Is just to test



Installation
-------------
	cordova plugins add https://github.com/raphxyz/native.alert.git
	

Requirements
-------------
- Android 2 or higher / iOS 6 or higher / WP8
- Cordova 3.0 or higher


Usage
------

Open a native alert :

		natif.alert(
			'Title Box !', 
			'Text on the box...'
		);
    
Open a native confirmation request box :
		
		function onSuccess(confirmed) {
			if(confirmed) {
				//---
			}
			else {
				//---
			}
		}
		
		function onError(e) {
			console.error(e);
		}
		
		natif.confirm(
			'Tile Box ?',
			'Text on the box...',
			onSuccess,
			onError
		);
		
Open a native promt box :
		
		function onSuccess(result) {
			if(result) {
				alert(result.value)
			}
			else {
				alert('Response Cancelled')
			}
		}
		
		function onError(e) {
			console.error(e);
		}
		
		natif.promt(
			'Tile Box ?',
			'Text on the box...',
			onSuccess,
			onError
		);
	
	
Enjoy.