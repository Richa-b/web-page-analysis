function fireAjax(url, data, options, successCallback, failureCallback) {
    options = options || {};
    options.url = srsEnv.baseURL + url;
    options.data = data;
    options.method = options.method || 'POST';
    options.dataType = options.dataType || 'json';
    if (!options.formData && options.dataType === 'json') {
        options.contentType = "application/json";
    }
    if (!options.formData && options.method === 'POST') {
        options.data = JSON.stringify(data)
    }
    options.success = function (response) {
        if (successCallback) {
            successCallback(response);
        }
        if (response.status) {
            /*showMessage("success", response.message)*/
            console.log("Success",response.message)
        } else if (!response.status) {
            console.log("Error",response.message)
            /*showMessage("error", response.message)*/
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
