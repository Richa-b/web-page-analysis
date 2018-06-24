function fireAjax(url, data, options, successCallback, failureCallback) {
    options = options || {};
    options.url = srsEnv.baseURL + url;
    options.data = data;
    options.dataType = options.dataType || 'json';
    if (options.dataType === 'json') {
        options.contentType = "application/json";
    }
    options.success = function (response) {
        if (successCallback) {
            successCallback(response);
        }
        if (!response.status) {
            console.log("Error", response.message)
        }
    };
    options.error = function (response) {
        console.log("Error Occurred while fetching data from Ajax :: ", response);
        if (failureCallback) {
            failureCallback(response)
        }
    };
    return $.ajax(options);
}


(function ($) {
    $(document).ajaxStart(function () {
        $.blockUI({message : $('#spinner')});
    }).ajaxStop(function () {
        $.unblockUI();
    });

})(jQuery);