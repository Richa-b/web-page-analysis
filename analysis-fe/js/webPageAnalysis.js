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
    const url = $("#url").val();
    fireAjax(ANALYSE_WEB_PAGE_URL + "?url=" + url, null, {
        "method": "GET"
    }, function (response) {
        $("#msg-div").html(createMessageDiv(response));
        if (response.status) {
            populateWebPageDetails(response);
            populatelinkDetails(response);
            $('#linkDetailTable').DataTable();
        }
    });
}

function populateWebPageDetails(response) {
    const webPageSource = $("#web-page-detail-template").html();
    const webPageTemplate = Handlebars.compile(webPageSource);
    const webPageHtml = webPageTemplate(response.data);
    $('#web-page-detail').html(webPageHtml);
}

function populatelinkDetails(response) {
    const linkDetailSource = $("#link-details-template").html();
    const linkDetailTemplate = Handlebars.compile(linkDetailSource);
    const html = linkDetailTemplate(response.data);
    $('#link-detail').html(html);
}

function createMessageDiv(response) {
    const className = response.status ? "alert-success" : "alert-danger";
    return "<div class='" + className + " alert alert-dismissible fade in' >" + response.message + " " +
        "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
        "</div>"
}