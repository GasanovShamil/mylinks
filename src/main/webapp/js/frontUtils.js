$( document ).ready(function() {

//$('#checkbox').on('change', function(){
//    if (this.checked) {
//        $('#checkbox').val('New value');
//    } else {
//        $('#checkbox').val('Initial value');
//    }
//});

	$('#passwordCheckbox').on('change', function() { 						
		if($('#passwordCheckbox').prop('checked')){
			var res=Password.generate(5);
			$("#urlPassword").val(res);
			$("#urlPassword").attr("value",res );
			$('#crypt').collapse("show");
		}else{
			$("#keypass").val("");
			$("#keypass").attr("value", "");
			$('#crypt').collapse("hide");
		}
	});
	
	$('#refresh').click(function(){
		var mot=Password.generate(5);
		$("#urlPassword").val(mot);
		$("#urlPassword").attr("value", mot);
		
	});
	
});
var Password = {
		 
		  _pattern : /[a-zA-Z0-9]/,
		  
		  
		  _getRandomByte : function()
		  {
		    // http://caniuse.com/#feat=getrandomvalues
		    if(window.crypto && window.crypto.getRandomValues) 
		    {
		      var result = new Uint8Array(1);
		      window.crypto.getRandomValues(result);
		      return result[0];
		    }
		    else if(window.msCrypto && window.msCrypto.getRandomValues) 
		    {
		      var result = new Uint8Array(1);
		      window.msCrypto.getRandomValues(result);
		      return result[0];
		    }
		    else
		    {
		      return Math.floor(Math.random() * 256);
		    }
		  },
		  
		  generate : function(length)
		  {
		    return Array.apply(null, {'length': length})
		      .map(function()
		      {
		        var result;
		        while(true) 
		        {
		          result = String.fromCharCode(this._getRandomByte());
		          if(this._pattern.test(result))
		          {
		            return result;
		          }
		        }        
		      }, this)
		      .join('');  
		  }    
		    
		};