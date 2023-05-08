async function uploadFile() {
    let file = document.getElementById("upload-file").files[0];
    let formData = new FormData();

    formData.append("file", file);
    await fetch('http://localhost:8080/upload', {
        method: "POST",
        body: formData
    });
}

$(document).ready(function() {
    $("#btnFetch").click(function() {
        // load data via AJAX
        //fetch_data($("#inputTopic").val());
        // disable button
        //$(this).prop("disabled", true);
        // add spinner to button
        $(this).html(
            `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Загрузка...`
        );
    });
});
