var eeda={}||eeda;

eeda.getQueryStringRegExp =function(name)
    {
        var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");  
        if (reg.test(location.href)) 
            return decodeURI(RegExp.$2.replace(/\+/g, " ")); 
        return "";
    };