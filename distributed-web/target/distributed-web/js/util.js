function checkNotNull(str){
	if(typeof(str) != "undefined" && null!=str  && ""!=str){
		return true;
	}
	return false;
}