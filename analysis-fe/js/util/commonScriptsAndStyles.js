(function () {

    <!-- ADD COMMON CSS FILES HERE -->
    addCss("../css/bootstrap.min.css");
    addCss("../css/style.css");


    <!-- ADD COMMON JAVASCRIPT FILES HERE -->
    addScriptTag("../js/thirdParty/bootstrap.js");
    addScriptTag("../js/util/constants.js");
    addScriptTag("../config/env.js");
    addScriptTag("../js/util/endpoints.js");
    addScriptTag("../js/util/application.js");
    addScriptTag("../js/thirdParty/handlebars.min.js");
    addScriptTag("../js/thirdParty/blockUI.js");
    addScriptTag("../js/util/handleBarHelper.js");
})();


function addCss(cssPath) {
    const link = document.createElement("link");
    link.href = cssPath;
    link.rel = "stylesheet";
    $("head").append(link);
}

function addScriptTag(scriptPath) {
    const script = document.createElement("script");
    script.src = scriptPath;
    $("head").append(script);
}

