$(document).ready(function () {
    bindEvents();
});

function bindEvents() {
    var analyseWebPage = $("#analyse-webPage");
    console.log(analyseWebPage)
    analyseWebPage.on('click', '.submit', function () {
        console.log("sjjsjj")
        getAnalysisInfo($(this));
    });

}

function getAnalysisInfo(element) {
    var form = element.closest('form[name=analyseForm]')[0];
    var data = new FormData(form);
    fireAjax(ANALYSE_WEB_PAGE_URL, data, {
        "type": "POST",
        "formData": true,
        "processData": false,
        "contentType": false
    });
}