$(document).ready(function(){

	
	$("#login").submit(function(){
		var allRequiredFilledOut=true;
		$(".required2").each(function(index){
			if($(this).val().length == 0)
			{
				allRequiredFilledOut=false;
				$('[for="'+$(this).attr('id')+'"]').filter('label').addClass('text-required')
			}else{
				$('[for="'+$(this).attr('id')+'"]').filter('label').removeClass('text-required')
			}
		})
		return allRequiredFilledOut;
	})
	$("#flightSearch").submit(function(){
		var allRequiredFilledOut=true;
		$(".required").each(function(index){
			if($(this).val().length == 0)
			{
				allRequiredFilledOut=false;
				$('[for="'+$(this).attr('id')+'"]').filter('label').addClass('text-required')
			}else{
				$('[for="'+$(this).attr('id')+'"]').filter('label').removeClass('text-required')
			}
		})
		return allRequiredFilledOut;
	})
	$('[name="oneWayOrReturn"]').filter('[value=oneWay]').click(function(){
		$("#returnDate").hide();
		$("#in_return").removeClass('required');
	})
	
	$('[name="oneWayOrReturn"]').filter('[value=return]').click(function(){
		$("#returnDate").show();
		$("#in_return").addClass('required');
	})
});

