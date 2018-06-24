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
    }, function (response) {
        if (response.status) {
            const webPageSource = $("#web-page-detail-template").html();
            const webPageTemplate = Handlebars.compile(webPageSource);
            const webPageHtml = webPageTemplate(response.data);
            $('#web-page-detail').html(webPageHtml);
            const linkDetailSource = $("#link-details-template").html();
            const linkDetailTemplate = Handlebars.compile(linkDetailSource);
            const html = linkDetailTemplate(response.data);
            $('#link-detail').html(html);
        } else {
            $("#web-page-detail").html(response.message);
        }
    });
}