$(document).ready(function () {
    bindEvents();
});

function bindEvents() {
    const analyseWebPage = $("#analyse-webPage");
    analyseWebPage.on('click', '.submit', function () {
        getAnalysisInfo($(this));
    });

}

function getAnalysisInfo(element) {
    const form = element.closest('form[name=analyseForm]')[0];
    const data = new FormData(form);
    fireAjax(ANALYSE_WEB_PAGE_URL, data, {
        "type": "POST",
        "formData": true,
        "processData": false,
        "contentType": false
    },function (response) {
        const source = $("#web-page-detail-template").html();
        const template = Handlebars.compile(source);
        const html = template(response.data);
        $('#web-page-detail').html(html);
    });
}